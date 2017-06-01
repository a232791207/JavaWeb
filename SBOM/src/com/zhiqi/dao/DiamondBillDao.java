package com.zhiqi.dao;

import java.util.List;

import com.zhiqi.bean.DiamondBill;

public interface DiamondBillDao {
	/**
	 * 根据时间查找第from行记录开始的count个DiamondBill
	 * @param from 从from开始
	 * @param count 去count个记录
	 * @param stime 起始时间
	 * @param etime 结束时间
	 * @param string 
	 * @param s 
	 * @return List<CashBill>
	 */
	List<DiamondBill> findDiamondBillPageByTime(int from, int count, String stime, String etime,int state,String userName, String string, String s);
	/**
	 * 根据时间获取记录的个数
	 * @param stime 起始时间
	 * @param etime 结束时间
	 * @param string 
	 * @param s 
	 * @return 满足条件记录个数
	 */
	int getCountDiamondBillByTime(String stime, String etime,int state,String userName, String string, String s);

	/**
	 * 查找第from行记录开始的count个DiamondBill
	 * @param from 从from开始
	 * @param count 去count个记录
	 * @param string 
	 * @param s 
	 * @return List<CashBill>
	 */
	List<DiamondBill> findDiamondBillPage(int from, int count,int state,String userName, String string, String s);

	/**
	 * 获取记录的总个数
	 * @param string 
	 * @param s 
	 * @return 记录总个数
	 */
	int getCountDiamondBill(int state,String userName, String string, String s);
	/**
	 * 查找今天指定用户的订单数
	 * @param userName 用户名
	 * @param today 今天
	 * @return 订单数
	 */
	int todaysBillNum(String userName,String today);
	/**
	 * 插入一条记录
	 * @param diamondBill
	 */
	void add(DiamondBill diamondBill);
	
	List<DiamondBill> getRechargeApply(String stime, String etime, String subUserName);
	
	List<DiamondBill> getRechargeApply(String subUserName);
	
	DiamondBill getDiamondBill(String id);
	
	void update(DiamondBill diamondBill);
	
	int getApplyingNum(String subUserName);
	
	int getDateConsumption(String subUserName, String date, String string);

}
