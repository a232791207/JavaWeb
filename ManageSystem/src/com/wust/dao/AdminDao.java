package com.wust.dao;

import com.wust.domain.Admin;

public interface AdminDao {
	/**
	 * �����û���������ҹ���Ա
	 * @param username	����Ա�˺�	
	 * @param password	����Ա����
	 * @return	�ҵ��򷵻ع���Աbean���Ҳ����򷵻�null
	 */
	public Admin findAdminByUSAndPAS(String username, String password);

}
