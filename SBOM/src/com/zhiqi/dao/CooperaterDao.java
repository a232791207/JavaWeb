package com.zhiqi.dao;

import org.springframework.stereotype.Repository;

import com.zhiqi.bean.Cooperater;
@Repository
public interface CooperaterDao {
	/**
	 * �����û����͵�¼�������User
	 * @param userName �û���
	 * @param loginPassword ��¼����
	 * @return Cooperater
	 */
 Cooperater findByUserName(String userName, String loginPassword);
}
