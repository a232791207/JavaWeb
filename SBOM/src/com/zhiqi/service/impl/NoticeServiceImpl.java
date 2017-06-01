package com.zhiqi.service.impl;

import java.util.List;

import com.zhiqi.bean.Notice;
import com.zhiqi.bean.Page;
import com.zhiqi.dao.NoticeDao;
import com.zhiqi.service.NoticeService;

public class NoticeServiceImpl implements NoticeService {
	private NoticeDao noticeDao;

	public NoticeDao getNoticeDao() {
		return noticeDao;
	}

	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	@Override
	public void add(Notice notice) {
		noticeDao.add(notice);
	}

	@Override
	public Page getNoticePageByState(int thispage, int rowperpage, short state) {
		Page page = new Page();
		List<Notice> list = null;
		int countrow = 0;
		list  = noticeDao.getNoticePageByState((thispage-1)*rowperpage,rowperpage,state);
		countrow = noticeDao.getNoticeCountByState(state);
		//当前页数据
		page.setList(list);
		//当前页码
		page.setThispage(thispage);
		//每页记录数
		page.setRowperpage(rowperpage);
		//总记录数
		page.setCountrow(countrow);
		//总页数
		int countpage = countrow/rowperpage + (countrow%rowperpage==0?0:1);
		page.setCountpage(countpage);
		//首页
		page.setFirstpage(1);
		//尾页
		page.setLastpage(countpage);
		//上一页
		page.setPrepage(thispage==1?1:thispage-1);
		//下一页
		page.setNextpage(thispage==countpage?thispage:thispage+1);
		return page;
	}

	@Override
	public Notice getNotice(int id) {
		return noticeDao.getNotice(id);
	}

	@Override
	public void update(Notice notice) {
		noticeDao.update(notice);
	}
}
