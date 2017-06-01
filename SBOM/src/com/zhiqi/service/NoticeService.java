package com.zhiqi.service;

import com.zhiqi.bean.Notice;
import com.zhiqi.bean.Page;

public interface NoticeService {

	void add(Notice notice);

	Page getNoticePageByState(int thispage, int rowperpage, short state);

	Notice getNotice(int id);

	void update(Notice notice);

}
