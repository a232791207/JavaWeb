package com.zhiqi.service.impl;

import java.util.List;

import com.zhiqi.bean.DiamondBill;
import com.zhiqi.bean.Page;
import com.zhiqi.dao.DiamondBillDao;
import com.zhiqi.service.DiamondBillService;
import com.zhiqi.utils.DateUtil;

public class DiamondBillServiceImpl implements DiamondBillService {
	private DiamondBillDao diamondBillDao;

	public DiamondBillDao getDiamondBillDao() {
		return diamondBillDao;
	}

	public void setDiamondBillDao(DiamondBillDao diamondBillDao) {
		this.diamondBillDao = diamondBillDao;
	}

	@Override
	public Page findDiamondBillPageByTime(int thispage, int rowperpage, String stime, String etime, int state, String userName,String string,String s) {
		Page page = new Page();
		List<DiamondBill> list = null;
		int countrow = 0;
		if(!stime.equals("")&&!etime.equals("")){//如果传进的起始时间和结束时间不为空
			etime = DateUtil.anotherDay(etime);
			list  = diamondBillDao.findDiamondBillPageByTime((thispage-1)*rowperpage,rowperpage,stime,etime,state,userName,string,s);
			countrow = diamondBillDao.getCountDiamondBillByTime(stime,etime,state,userName,string,s);
		}else{//如果传进的起始时间和结束时间为空
			list = diamondBillDao.findDiamondBillPage((thispage-1)*rowperpage, rowperpage,state,userName,string,s);
			countrow = diamondBillDao.getCountDiamondBill(state,userName,string,s);
		}
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
	public int todaysBillNum(String userName, String today) {
		return diamondBillDao.todaysBillNum(userName,today);
	}

	@Override
	public void add(DiamondBill diamondBill) {
		diamondBillDao.add(diamondBill);
	}

	@Override
	public List<DiamondBill> getRechargeApply(String stime, String etime, String subUserName) {
		List<DiamondBill> list = null;
		if(!stime.equals("")&&!etime.equals("")){//如果传进的起始时间和结束时间不为空
			etime = DateUtil.anotherDay(etime);
			list  = diamondBillDao.getRechargeApply(stime,etime,subUserName);
		}else{//如果传进的起始时间和结束时间为空
			list = diamondBillDao.getRechargeApply(subUserName);
		}
		return list;
	}

	@Override
	public DiamondBill getDiamondBill(String id) {
		return diamondBillDao.getDiamondBill(id);
	}

	@Override
	public void update(DiamondBill diamondBill) {
		// TODO Auto-generated method stub
		diamondBillDao.update(diamondBill);
	}

	@Override
	public int getApplyingNum(String subUserName) {
		return diamondBillDao.getApplyingNum(subUserName);
	}

	@Override
	public int getDateConsumption(String subUserName, String date, String string) {
		return diamondBillDao.getDateConsumption(subUserName,date,string);
	}

	
}
