package com.zhiqi.dao;

import java.util.List;

import com.zhiqi.bean.CashBill;

public interface CashBillDao {
	/**
	 * 根据时间查找第from行记录开始的count个CashBill
	 * @param from 从from开始
	 * @param count 去count个记录
	 * @param stime 起始时间
	 * @param etime 结束时间
	 * @return List<CashBill>
	 */
	List<CashBill> findCashBillPageByTime(int from, int count, String stime, String etime,String userName);
	
	/**
	 * 根据时间获取记录的个数
	 * @param stime 起始时间
	 * @param etime 结束时间
	 * @return 满足条件记录个数
	 */
	int getCountCashBillByTime(String stime, String etime,String userName);
	
	/**
	 * 查找第from行记录开始的count个CashBill
	 * @param from 从from开始
	 * @param count 去count个记录
	 * @return List<CashBill>
	 */
	List<CashBill> findCashBillPage(int from, int count,String userName);
	
	/**
	 * 获取记录的总个数
	 * @return 记录总个数
	 */
	int getCountCashBill(String userName);
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
