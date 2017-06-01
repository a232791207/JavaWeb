package com.wust.dao;



import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.wust.domain.Admin;
import com.wust.util.DaoUtils;

public class AdminDaoImpl implements AdminDao{

	@Override
	public Admin findAdminByUSAndPAS(String username, String password) {
		Admin admin= null;
		String sql = "select * from admin where username = ? and password = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			admin = runner.query(sql, new BeanHandler<Admin>(Admin.class), username, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return admin;
	}

}
