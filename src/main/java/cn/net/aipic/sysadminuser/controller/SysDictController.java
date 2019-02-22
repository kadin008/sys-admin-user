package cn.net.aipic.sysadminuser.controller;

import cn.net.aipic.sysadminuser.entity.SysDict;
import cn.net.aipic.sysadminuser.utils.HttpResult;
import cn.net.aipic.sysadminuser.page.PageRequest;
import cn.net.aipic.sysadminuser.service.SysDictService;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 字典控制器
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
@RequestMapping("dict")
public class SysDictController {

	@Autowired
	private SysDictService sysDictService;
	
//	@RequiresPermissions({"sys:dict:add", "sys:dict:edit"})
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody SysDict record) {
		return HttpResult.ok(sysDictService.save(record));
	}

//	@RequiresPermissions("sys:dict:delete")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<SysDict> records) {
		return HttpResult.ok(sysDictService.delete(records));
	}

//	@RequiresPermissions("sys:dict:view")
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysDictService.findPage(pageRequest));
	}
	
//	@RequiresPermissions("sys:dict:view")
	@GetMapping(value="/findByLable")
	public HttpResult findByLable(@RequestParam String lable) {
		return HttpResult.ok(sysDictService.findByLable(lable));
	}
}
