package com.zhiqi.service;

import org.springframework.stereotype.Service;

import com.zhiqi.bean.Cooperater;

@Service
public interface CooperaterService {

	/**
	 * �����û����͵�¼�������User
	 * @param userName �û���
	 * @param loginPassword ��¼����
	 * @return Cooperater
	 */
	 Cooperater findByUserName(String userName, String loginPassword);
	 
	 
}
