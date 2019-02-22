package cn.net.aipic.sysadminuser.service;

import cn.net.aipic.sysadminuser.entity.SysDept;

import java.util.List;


/**
 * 机构管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysDeptService extends CurdService<SysDept> {

	/**
	 * 查询机构树
	 * @param userId 
	 * @return
	 */
	List<SysDept> findTree();
}
