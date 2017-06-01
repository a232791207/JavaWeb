package com.zhiqi.dao;

import org.springframework.stereotype.Repository;

import com.zhiqi.bean.Cooperater;
@Repository
public interface CooperaterDao {
	/**
	 * 根据用户名和登录密码查找User
	 * @param userName 用户名
	 * @param loginPassword 登录密码
	 * @return Cooperater
	 */
 Cooperater findByUserName(String userName, String loginPassword);
}
