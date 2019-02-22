package cn.net.aipic.sysadminuser.service;


import cn.net.aipic.sysadminuser.entity.SysUserToken;

/**
 * 用户Token管理
 * @author Louis
 * @date Aug 21, 2018
 */
public interface SysUserTokenService extends CurdService<SysUserToken> {

	/**
	 * 根据用户id查找
	 * @param userId
	 * @return
	 */
	SysUserToken findByUserId(Long userId);

	/**
	 * 根据token查找
	 * @param token
	 * @return
	 */
	SysUserToken findByToken(String token);
	
	/**
	 * 生成token
	 * @param userId
	 * @return
	 */
	SysUserToken createToken(long userId);

}
