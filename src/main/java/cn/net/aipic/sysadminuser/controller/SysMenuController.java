package cn.net.aipic.sysadminuser.controller;

import cn.net.aipic.sysadminuser.entity.SysMenu;
import cn.net.aipic.sysadminuser.utils.HttpResult;
import cn.net.aipic.sysadminuser.service.SysMenuService;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 菜单控制器
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
@RequestMapping("menu")
public class SysMenuController {

	@Autowired
	private SysMenuService sysMenuService;
	
//	@RequiresPermissions({"sys:menu:add", "sys:menu:edit"})
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody SysMenu record) {
		return HttpResult.ok(sysMenuService.save(record));
	}

//	@RequiresPermissions("sys:menu:delete")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<SysMenu> records) {
		return HttpResult.ok(sysMenuService.delete(records));
	}

//	@RequiresPermissions("sys:menu:view")
	@GetMapping(value="/findNavTree")
	public HttpResult findNavTree(@RequestParam String userName) {
		return HttpResult.ok(sysMenuService.findTree(userName, 1));
	}
	
//	@RequiresPermissions("sys:menu:view")
	@GetMapping(value="/findMenuTree")
	public HttpResult findMenuTree() {
		return HttpResult.ok(sysMenuService.findTree(null, 0));
	}
}
