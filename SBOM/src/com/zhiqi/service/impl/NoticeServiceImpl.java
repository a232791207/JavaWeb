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
		//��ǰҳ����
		page.setList(list);
		//��ǰҳ��
		page.setThispage(thispage);
		//ÿҳ��¼��
		page.setRowperpage(rowperpage);
		//�ܼ�¼��
		page.setCountrow(countrow);
		//��ҳ��
		int countpage = countrow/rowperpage + (countrow%rowperpage==0?0:1);
		page.setCountpage(countpage);
		//��ҳ
		page.setFirstpage(1);
		//βҳ
		page.setLastpage(countpage);
		//��һҳ
		page.setPrepage(thispage==1?1:thispage-1);
		//��һҳ
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
