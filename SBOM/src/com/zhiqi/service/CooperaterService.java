package com.zhiqi.service;

import org.springframework.stereotype.Service;

import com.zhiqi.bean.Cooperater;

@Service
public interface CooperaterService {

	/**
	 * 根据用户名和登录密码查找User
	 * @param userName 用户名
	 * @param loginPassword 登录密码
	 * @return Cooperater
	 */
	 Cooperater findByUserName(String userName, String loginPassword);
	 
	 
}
