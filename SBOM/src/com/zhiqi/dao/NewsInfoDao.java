package com.zhiqi.dao;

import com.zhiqi.bean.NewsInfo;

public interface NewsInfoDao {

	void update(NewsInfo newsInfo);

	NewsInfo getNewsInfo(int id);
	
	NewsInfo getByState(int id,short state);
	
	

}
