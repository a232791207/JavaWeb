package com.zhiqi.service;

import com.zhiqi.bean.CashBill;
import com.zhiqi.bean.Page;

public interface CashBillService {
	/**
	 * 根据当前页、每页行数、起始时间、截至时间查找CashBillPage
	 * @param thispage 当前页码
	 * @param rowperpage 每页行数
	 * @param stime 起始时间
	 * @param etime 截至时间
	 * @return CashBillPage
	 */
	Page findCashBillPageByTime(int thispage, int rowperpage, String stime, String etime, String userName);
	/**
	 * 插入一条记录
	 * @param cashBill cashBill记录
	 */
	void add(CashBill cashBill);
	/**
	 * 查找今天指定用户的订单数
	 * @param userName 用户名
	 * @param today 今天
	 * @return 订单数
	 */
	int todaysBillNum(String userName, String currentDate2);

}
