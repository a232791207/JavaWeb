package com.wust.service;

import java.util.List;


import com.wust.dao.CheckDrawDao;
import com.wust.domain.CheckDraw;
import com.wust.domain.CheckDrawPage;
import com.wust.domain.Draworder;
import com.wust.factory.BasicFactory;

public class CheckDrawServiceImpl implements CheckDrawService {
	
	CheckDrawDao dao = BasicFactory.getFactory().getInstance(CheckDrawDao.class);
	@Override
	public List<Object> findLevelOption(String distributor,String format) {
		return dao.findLevelOption(distributor,format);
	}
	@Override
	public CheckDrawPage getCheckDrawByPage(int thispage, int rowperpage) {
		CheckDrawPage page = new CheckDrawPage();
		List<CheckDraw> list = dao.findCheckDrawByPage((thispage-1)*rowperpage,rowperpage);
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
	public CheckDrawPage getCheckDrawByDistributorPage(String distributor,int thispage,
			int rowperpage) {
		CheckDrawPage page = new CheckDrawPage();
		List<CheckDraw> list = dao.getCheckDrawByDistributorPage(distributor, (thispage-1)*rowperpage, rowperpage);
		//当前页数据
		page.setList(list);
		//当前页码
		page.setThispage(thispage);
		//每页记录数
		page.setRowperpage(rowperpage);
		//总记录数
		int countrow = dao.getCountRowWithDistributor(distributor);
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
	public void updateCheckDrawByDrawnum(String distributor, String format,
			String level, int number) {
		dao.updateCheckDrawByDrawnum(distributor,format,level,number);
		
	}
	@Override
	public void deleteCheckDraw(String distributor, String format, String level) {
		dao.deleteCheckDraw(distributor,format,level);
		
	}
	@Override
	public CheckDraw getCheckDraw(String distributor, String format,
			String level) {
		return dao.getCheckDraw(distributor,format,level);
	}
	@Override
	public void addChekDraw(String distributor, String format, String level,
			int number) {
		dao.addCheckDraw(distributor, format, level, number);
	}
	@Override
	public void updateCheckDrawNum(String distributor, String format,
			String level, int number) {
		dao.updateCheckDrawNum(distributor, format, level, number);
	}
	@Override
	public void updateCheckDrawByDrawnum(List<Draworder> draworders) {
		dao.updateCheckDrawByDrawnum(draworders);
		
	}

}
