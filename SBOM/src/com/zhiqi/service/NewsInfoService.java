package com.zhiqi.service;

import com.zhiqi.bean.NewsInfo;

public interface NewsInfoService {

	void update(NewsInfo newsInfo);

	NewsInfo getNewsInfo(int id);
	
	NewsInfo getByState(int id,short state);

}
