package com.zhiqi.dao;

import java.util.List;

import com.zhiqi.bean.Notice;

public interface NoticeDao {

	void add(Notice notice);

	List<Notice> getNoticePageByState(int from, int count, short state);

	int getNoticeCountByState(short state);

	Notice getNotice(int id);

	void update(Notice notice);

}
