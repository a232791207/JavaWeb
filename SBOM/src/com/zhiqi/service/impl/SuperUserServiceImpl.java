package com.zhiqi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiqi.bean.SuperUser;
import com.zhiqi.dao.SuperUserDao;
import com.zhiqi.service.SuperUserService;
@Service
public class SuperUserServiceImpl implements SuperUserService{

	@Autowired
	private SuperUserDao superUserDao;
	@Override
	public SuperUser findByUserName(String userName, String loginPassword) {
		return superUserDao.findByUserName(userName, loginPassword);
	}
	
	public SuperUserDao getSuperUserDao() {
		return superUserDao;
	}
	public void setSuperUserDao(SuperUserDao superUserDao) {
		this.superUserDao = superUserDao;
	}

	
}
