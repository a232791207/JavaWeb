package com.wust.service;

import com.wust.domain.Admin;

public interface AdminService {
	/**
	 * 根据用用户名密码查找管理员
	 * @param username 用户名
	 * @param password 密码
	 * @return 如果找到则返回一个管理员bean，找不到则返回null
	 */
	public Admin isAdmin(String username, String password);
	
}
