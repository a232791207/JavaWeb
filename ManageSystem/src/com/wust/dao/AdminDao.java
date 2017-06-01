package com.wust.dao;

import com.wust.domain.Admin;

public interface AdminDao {
	/**
	 * 根据用户名密码查找管理员
	 * @param username	管理员账号	
	 * @param password	管理员密码
	 * @return	找到则返回管理员bean，找不到则返回null
	 */
	public Admin findAdminByUSAndPAS(String username, String password);

}
