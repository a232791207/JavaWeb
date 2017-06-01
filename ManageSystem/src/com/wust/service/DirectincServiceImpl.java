package com.wust.service;

import java.util.List;

import com.wust.dao.DirectincDao;
import com.wust.domain.Directinc;
import com.wust.domain.DirectincPage;
import com.wust.factory.BasicFactory;

public class DirectincServiceImpl implements DirectincService {
	DirectincDao dao = BasicFactory.getFactory().getInstance(DirectincDao.class);
	@Override
	public Directinc findDirectinc(String time, String distributor, String area, String format, String level) {
		Directinc directinc = dao.findDirectinc(time,distributor,area,format,level);
		return directinc;
	}
	@Override
	public DirectincPage pageDirectinc(int thispage, int rowperpage) {
		DirectincPage page = new DirectincPage();
		List<Directinc> list = dao.findDirectincByPage((thispage-1)*rowperpage,rowperpage);
		//当前页数据
		page.setList(list);
		//当前页码
		page.setThispage(thispage);
		//每页记录数
		page.setRowperpage(rowperpage);
		//总记录数
		int countrow = dao.getCountRow();
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
	public Directinc getDirectincInfo(String id) {
		Directinc directinc = dao.findDirectincById(id);
		return directinc;
	}
	@Override
	public void updateDirectinc(Directinc directinc) {
		dao.updateDirectinc(directinc);
	}
	@Override
	public void delDirectincById(String id) {
		dao.delDirectincById(id);
	}
	@Override
	public void addDirectinc(Directinc directinc) {
		dao.addDirectinc(directinc);
	}
}
