package com.zhiqi.dao;

import org.springframework.stereotype.Repository;

import com.zhiqi.bean.SuperUser;
@Repository
public interface SuperUserDao {
	/**
	 * �����û����͵�¼�������User
	 * @param userName �û���
	 * @param loginPassword ��¼����
	 * @return SuperUser
	 */
	SuperUser findByUserName(String userName, String loginPassword);
	
}
