package com.wust.service;

import com.wust.domain.Admin;

public interface AdminService {
	/**
	 * �������û���������ҹ���Ա
	 * @param username �û���
	 * @param password ����
	 * @return ����ҵ��򷵻�һ������Աbean���Ҳ����򷵻�null
	 */
	public Admin isAdmin(String username, String password);
	
}
