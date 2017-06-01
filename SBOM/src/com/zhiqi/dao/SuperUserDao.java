package com.zhiqi.dao;

import org.springframework.stereotype.Repository;

import com.zhiqi.bean.SuperUser;
@Repository
public interface SuperUserDao {
	/**
	 * 根据用户名和登录密码查找User
	 * @param userName 用户名
	 * @param loginPassword 登录密码
	 * @return SuperUser
	 */
	SuperUser findByUserName(String userName, String loginPassword);
	
}
