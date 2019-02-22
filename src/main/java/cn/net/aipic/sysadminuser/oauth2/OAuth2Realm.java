package cn.net.aipic.sysadminuser.oauth2;

import cn.net.aipic.sysadminuser.entity.SysUser;
import cn.net.aipic.sysadminuser.entity.SysUserToken;
import cn.net.aipic.sysadminuser.service.SysUserService;
import cn.net.aipic.sysadminuser.service.SysUserTokenService;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class OAuth2Realm extends AuthorizingRealm {

    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysUserTokenService sysUserTokenService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser user = (SysUser)principals.getPrimaryPrincipal();
        // 用户权限列表，根据用户拥有的权限标识与如 @permission标注的接口对比，决定是否可以调用接口
        Set<String> permsSet = sysUserService.findPermissions(user.getName());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getPrincipal();
        // 根据accessToken，查询用户token信息
        SysUserToken sysUserToken = sysUserTokenService.findByToken(token);
        if(sysUserToken == null || sysUserToken.getExpireTime().getTime() < System.currentTimeMillis()){
            // token已经失效
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        // 查询用户信息
        SysUser user = sysUserService.findById(sysUserToken.getUserId());
        // 账号被锁定
        if(user.getStatus() == 0){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, token, getName());
        return info;
    }
}
