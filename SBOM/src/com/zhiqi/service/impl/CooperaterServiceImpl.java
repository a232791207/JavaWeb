package com.zhiqi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiqi.bean.Cooperater;
import com.zhiqi.dao.CooperaterDao;
import com.zhiqi.service.CooperaterService;

@Service
public class CooperaterServiceImpl implements CooperaterService{
	@Autowired
   private CooperaterDao cooperaterDao;

	@Override
	public Cooperater findByUserName(String userName, String loginPassword) {
		return cooperaterDao.findByUserName(userName, loginPassword);
	}

	public CooperaterDao getCooperaterDao() {
		return cooperaterDao;
	}

	public void setCooperaterDao(CooperaterDao cooperaterDao) {
		this.cooperaterDao = cooperaterDao;
	}
	
}
