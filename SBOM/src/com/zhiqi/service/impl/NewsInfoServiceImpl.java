package com.zhiqi.service.impl;

import com.zhiqi.bean.NewsInfo;
import com.zhiqi.dao.NewsInfoDao;
import com.zhiqi.service.NewsInfoService;

public class NewsInfoServiceImpl implements NewsInfoService{
	private NewsInfoDao newsInfoDao;

	public NewsInfoDao getNewsInfoDao() {
		return newsInfoDao;
	}

	public void setNewsInfoDao(NewsInfoDao newsInfoDao) {
		this.newsInfoDao = newsInfoDao;
	}

	@Override
	public void update(NewsInfo newsInfo) {
		newsInfoDao.update(newsInfo);
	}

	@Override
	public NewsInfo getNewsInfo(int id) {
		return newsInfoDao.getNewsInfo(id);
	}

	@Override
	public NewsInfo getByState(int id,short state) {
		return newsInfoDao.getByState(id,state);
	}
	

}
