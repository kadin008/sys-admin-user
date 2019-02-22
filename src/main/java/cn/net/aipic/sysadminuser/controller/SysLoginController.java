package cn.net.aipic.sysadminuser.controller;


import cn.net.aipic.sysadminuser.entity.SysUser;
import cn.net.aipic.sysadminuser.entity.SysUserToken;
import cn.net.aipic.sysadminuser.service.SysUserService;
import cn.net.aipic.sysadminuser.service.SysUserTokenService;
import cn.net.aipic.sysadminuser.utils.HttpResult;
import cn.net.aipic.sysadminuser.utils.PasswordUtils;
import cn.net.aipic.sysadminuser.utils.ShiroUtils;
import cn.net.aipic.sysadminuser.vo.LoginBean;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;



@RestController
public class SysLoginController {

    @Autowired
    private Producer producer;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserTokenService sysUserTokenService;

    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存到验证码到 session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    @PostMapping(value = "/sys/login")
    public HttpResult login(@RequestBody LoginBean loginBean) throws IOException {
        String userName = loginBean.getAccount();
        String password = loginBean.getPassword();
//        String captcha = loginBean.getCaptcha();


        // 用户信息
        SysUser user = sysUserService.findByName(userName);

        // 账号不存在、密码错误
        if (user == null) {
            return HttpResult.error("账号不存在");
        }

        if (!match(user, password)) {
            return HttpResult.error("密码不正确");
        }

        // 账号锁定
        if (user.getStatus() == 0) {
            return HttpResult.error("账号已被锁定,请联系管理员");
        }

        // 生成token，并保存到数据库
        SysUserToken data = sysUserTokenService.createToken(user.getId());
        return HttpResult.ok(data);
    }

    /**
     * 验证用户密码
     * @param user
     * @param password
     * @return
     */
    public boolean match(SysUser user, String password) {
        return user.getPassword().equals(PasswordUtils.encrypte(password, user.getSalt()));
    }

}
