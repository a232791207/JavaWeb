package com.zhiqi.service.impl;

import java.util.List;

import com.zhiqi.bean.Page;
import com.zhiqi.bean.Subordinate;
import com.zhiqi.dao.SubordinateDao;
import com.zhiqi.service.SubordinateService;
import com.zhiqi.utils.DateUtil;

public class SubordinateServiceImpl implements SubordinateService {
	private SubordinateDao subordinateDao;

	public SubordinateDao getSubordinateDao() {
		return subordinateDao;
	}

	public void setSubordinateDao(SubordinateDao subordinateDao) {
		this.subordinateDao = subordinateDao;
	}

	@Override
	public int getSumNum(String supUserName) {
		// TODO Auto-generated method stub
		return subordinateDao.getSumNum(supUserName);
	}

	@Override
	public int getTodayActive(String supUserName) {
		String today = DateUtil.currentDate2();
		return subordinateDao.getTodayActive(supUserName,today);
	}

	@Override
	public int getNewIncrease(String supUserName) {
		String today = DateUtil.currentDate2();
		return subordinateDao.getNewIncrease(supUserName,today);
	}

	@Override
	public Page getSubordinatePage(int thispage, int rowperpage, String supUserName, String date,short subLevel) {
		Page page = new Page();
		List<Subordinate> list = null;
		int countrow = 0;
		list  = subordinateDao.findDiamondBillPageByTime((thispage-1)*rowperpage,rowperpage,supUserName,date,subLevel);
		countrow = subordinateDao.getCountDiamondBillByTime(supUserName,date);
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
	public List<Subordinate> getSubordinateByID(String subUserName,String supUserName,short subLevel) {
		return subordinateDao.getSubordinateByID(subUserName,supUserName,subLevel);
	}

	@Override
	public List<Subordinate> getNoConsumption(String supUserName,short subLevel) {
		return subordinateDao.getNoConsumption(supUserName,subLevel);
	}

	@Override
	public List<String> getSubUserNameList(String supUserName) {
		return subordinateDao.getSubUserNameList(supUserName);
	}

	@Override
	public void delete(String subUserName) {
		subordinateDao.delete(subUserName);
	}

	@Override
	public Subordinate getSubordinateByUserName(String subUserName) {
		return subordinateDao.getSubordinateByUserName(subUserName);
	}

	@Override
	public void update(Subordinate subordinate) {
		subordinateDao.update(subordinate);
	}

	@Override
	public void add(Subordinate subordinate) {
		subordinateDao.add(subordinate);
	}

	@Override
	public Page getAllUserPage(int thispage, int rowperpage) {
		Page page = new Page();
		List<Subordinate> list = null;
		int countrow = 0;
		list  = subordinateDao.getAllUserPage((thispage-1)*rowperpage,rowperpage);
		countrow = subordinateDao.getAllUserCount();
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
	public Page getSubUserPage(int thispage, int rowperpage, String supUserName) {
		Page page = new Page();
		List<Subordinate> list = null;
		int countrow = 0;
		list  = subordinateDao.getSubUserPage((thispage-1)*rowperpage,rowperpage,supUserName);
		countrow = subordinateDao.getSubUserCount(supUserName);
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
	public Page getLevelUserPage(int thispage, int rowperpage, short subLevel) {
		Page page = new Page();
		List<Subordinate> list = null;
		int countrow = 0;
		list  = subordinateDao.getLevelUserPage((thispage-1)*rowperpage,rowperpage,subLevel);
		countrow = subordinateDao.getLevelUserCount(subLevel);
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
	public Page getUserPageById(int thispage, int rowperpage, String subUserName) {
		Page page = new Page();
		List<Subordinate> list = null;
		int countrow = 0;
		list  = subordinateDao.getUserPageById((thispage-1)*rowperpage,rowperpage,subUserName);
		countrow = subordinateDao.getUserCountById(subUserName);
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

	
}
