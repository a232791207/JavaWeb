package com.wust.service;

import com.wust.dao.AdminDao;
import com.wust.domain.Admin;
import com.wust.factory.BasicFactory;

public class AdminServiceImpl implements AdminService{
	private AdminDao dao = BasicFactory.getFactory().getInstance(AdminDao.class);

	public Admin isAdmin(String username, String password) {
		Admin admin = dao.findAdminByUSAndPAS(username,password);
		return admin;
	}

}
