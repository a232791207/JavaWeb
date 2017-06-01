package com.zhiqi.service;

import java.util.List;

import com.zhiqi.bean.DiamondBill;
import com.zhiqi.bean.Page;

public interface DiamondBillService {
	/**
	 * 根据当前页、每页行数、起始时间、截至时间查找DiamondBillPage
	 * @param thispage 当前页码
	 * @param rowperpage 每页行数
	 * @param stime 起始时间
	 * @param etime 截至时间
	 * @param string 
	 * @param string2 
	 * @return DiamondBillPage
	 */
	Page findDiamondBillPageByTime(int thispage, int rowperpage, String stime, String etime, int state, String userName, String string, String string2);
	/**
	 * 查找今天指定用户的订单数
	 * @param userName 用户名
	 * @param today 今天
	 * @return 订单数
	 */
	int todaysBillNum(String userName, String today);
	/**
	 * 插入一条记录
	 * @param diamondBill
	 */
	void add(DiamondBill diamondBill);
	
	List<DiamondBill> getRechargeApply(String stime, String etime, String subUserName);
	
	DiamondBill getDiamondBill(String id);
	
	void update(DiamondBill diamondBill);
	
	int getApplyingNum(String subUserName);
	
	int getDateConsumption(String subUserName, String date, String string);
	
}
