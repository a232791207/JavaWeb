package com.zhiqi.service.impl;

import java.util.List;

import com.zhiqi.bean.CashBill;
import com.zhiqi.bean.Page;
import com.zhiqi.dao.CashBillDao;
import com.zhiqi.service.CashBillService;
import com.zhiqi.utils.DateUtil;

public class CashBillServiceImpl implements CashBillService {
	private CashBillDao cashBillDao;
	public CashBillDao getCashBillDao() {
		return cashBillDao;
	}

	public void setCashBillDao(CashBillDao cashBillDao) {
		this.cashBillDao = cashBillDao;
	}

	@Override
	public Page findCashBillPageByTime(int thispage, int rowperpage, String stime, String etime,String userName) {
		Page page = new Page();
		List<CashBill> list = null;
		int countrow = 0;
		if(!stime.equals("")&&!etime.equals("")){
			etime = DateUtil.anotherDay(etime);
			list  = cashBillDao.findCashBillPageByTime((thispage-1)*rowperpage,rowperpage,stime,etime,userName);
			countrow = cashBillDao.getCountCashBillByTime(stime,etime,userName);
		}else{
			list = cashBillDao.findCashBillPage((thispage-1)*rowperpage, rowperpage,userName);
			countrow = cashBillDao.getCountCashBill(userName);
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
	public void add(CashBill cashBill) {
		cashBillDao.add(cashBill);
	}

	@Override
	public int todaysBillNum(String userName, String currentDate2) {
		return cashBillDao.todaysBillNum(userName,currentDate2);
	}
}
