package com.zhiqi.dao;

import java.util.List;

import com.zhiqi.bean.CashBill;

public interface CashBillDao {
	/**
	 * ����ʱ����ҵ�from�м�¼��ʼ��count��CashBill
	 * @param from ��from��ʼ
	 * @param count ȥcount����¼
	 * @param stime ��ʼʱ��
	 * @param etime ����ʱ��
	 * @return List<CashBill>
	 */
	List<CashBill> findCashBillPageByTime(int from, int count, String stime, String etime,String userName);
	
	/**
	 * ����ʱ���ȡ��¼�ĸ���
	 * @param stime ��ʼʱ��
	 * @param etime ����ʱ��
	 * @return ����������¼����
	 */
	int getCountCashBillByTime(String stime, String etime,String userName);
	
	/**
	 * ���ҵ�from�м�¼��ʼ��count��CashBill
	 * @param from ��from��ʼ
	 * @param count ȥcount����¼
	 * @return List<CashBill>
	 */
	List<CashBill> findCashBillPage(int from, int count,String userName);
	
	/**
	 * ��ȡ��¼���ܸ���
	 * @return ��¼�ܸ���
	 */
	int getCountCashBill(String userName);
	/**
	 * ����һ����¼
	 * @param cashBill cashBill��¼
	 */
	void add(CashBill cashBill);
	/**
	 * ���ҽ���ָ���û��Ķ�����
	 * @param userName �û���
	 * @param today ����
	 * @return ������
	 */
	int todaysBillNum(String userName, String currentDate2);

}
