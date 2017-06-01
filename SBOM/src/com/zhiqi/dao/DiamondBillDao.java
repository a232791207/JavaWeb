package com.zhiqi.dao;

import java.util.List;

import com.zhiqi.bean.DiamondBill;

public interface DiamondBillDao {
	/**
	 * ����ʱ����ҵ�from�м�¼��ʼ��count��DiamondBill
	 * @param from ��from��ʼ
	 * @param count ȥcount����¼
	 * @param stime ��ʼʱ��
	 * @param etime ����ʱ��
	 * @param string 
	 * @param s 
	 * @return List<CashBill>
	 */
	List<DiamondBill> findDiamondBillPageByTime(int from, int count, String stime, String etime,int state,String userName, String string, String s);
	/**
	 * ����ʱ���ȡ��¼�ĸ���
	 * @param stime ��ʼʱ��
	 * @param etime ����ʱ��
	 * @param string 
	 * @param s 
	 * @return ����������¼����
	 */
	int getCountDiamondBillByTime(String stime, String etime,int state,String userName, String string, String s);

	/**
	 * ���ҵ�from�м�¼��ʼ��count��DiamondBill
	 * @param from ��from��ʼ
	 * @param count ȥcount����¼
	 * @param string 
	 * @param s 
	 * @return List<CashBill>
	 */
	List<DiamondBill> findDiamondBillPage(int from, int count,int state,String userName, String string, String s);

	/**
	 * ��ȡ��¼���ܸ���
	 * @param string 
	 * @param s 
	 * @return ��¼�ܸ���
	 */
	int getCountDiamondBill(int state,String userName, String string, String s);
	/**
	 * ���ҽ���ָ���û��Ķ�����
	 * @param userName �û���
	 * @param today ����
	 * @return ������
	 */
	int todaysBillNum(String userName,String today);
	/**
	 * ����һ����¼
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
