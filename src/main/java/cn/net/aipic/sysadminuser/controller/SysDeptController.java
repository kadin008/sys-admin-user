package cn.net.aipic.sysadminuser.controller;

import cn.net.aipic.sysadminuser.entity.SysDept;
import cn.net.aipic.sysadminuser.utils.HttpResult;
import cn.net.aipic.sysadminuser.service.SysDeptService;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 机构控制器
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
@RequestMapping("dept")
public class SysDeptController {

	@Autowired
	private SysDeptService sysDeptService;
	
//	@RequiresPermissions({"sys:dept:add", "sys:dept:edit"})
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody SysDept record) {
		return HttpResult.ok(sysDeptService.save(record));
	}

//	@RequiresPermissions("sys:dept:delete")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<SysDept> records) {
		return HttpResult.ok(sysDeptService.delete(records));
	}

//	@RequiresPermissions("sys:dept:view")
	@GetMapping(value="/findTree")
	public HttpResult findTree() {
		return HttpResult.ok(sysDeptService.findTree());
	}

}
