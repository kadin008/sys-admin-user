package cn.net.aipic.sysadminuser.service;

import cn.net.aipic.sysadminuser.entity.SysUser;
import cn.net.aipic.sysadminuser.entity.SysUserRole;

import java.util.List;
import java.util.Set;


/**
 * 用户管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysUserService extends CurdService<SysUser> {

	SysUser findByName(String username);

	/**
	 * 查找用户的菜单权限标识集合
	 * @param userName
	 * @return
	 */
	Set<String> findPermissions(String userName);

	/**
	 * 查找用户的角色集合
	 * @param userName
	 * @return
	 */
	List<SysUserRole> findUserRoles(Long userId);

}
