package cn.net.aipic.sysadminuser.controller;

import cn.net.aipic.sysadminuser.utils.HttpResult;
import cn.net.aipic.sysadminuser.page.PageRequest;
import cn.net.aipic.sysadminuser.service.SysLogService;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 日志控制器
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
@RequestMapping("log")
public class SysLogController {

	@Autowired
	private SysLogService sysLogService;

//	@RequiresPermissions("sys:log:view")
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysLogService.findPage(pageRequest));
	}
}
