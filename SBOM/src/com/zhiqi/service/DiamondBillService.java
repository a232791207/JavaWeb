package com.zhiqi.service;

import java.util.List;

import com.zhiqi.bean.DiamondBill;
import com.zhiqi.bean.Page;

public interface DiamondBillService {
	/**
	 * ���ݵ�ǰҳ��ÿҳ��������ʼʱ�䡢����ʱ�����DiamondBillPage
	 * @param thispage ��ǰҳ��
	 * @param rowperpage ÿҳ����
	 * @param stime ��ʼʱ��
	 * @param etime ����ʱ��
	 * @param string 
	 * @param string2 
	 * @return DiamondBillPage
	 */
	Page findDiamondBillPageByTime(int thispage, int rowperpage, String stime, String etime, int state, String userName, String string, String string2);
	/**
	 * ���ҽ���ָ���û��Ķ�����
	 * @param userName �û���
	 * @param today ����
	 * @return ������
	 */
	int todaysBillNum(String userName, String today);
	/**
	 * ����һ����¼
	 * @param diamondBill
	 */
	void add(DiamondBill diamondBill);
	
	List<DiamondBill> getRechargeApply(String stime, String etime, String subUserName);
	
	DiamondBill getDiamondBill(String id);
	
	void update(DiamondBill diamondBill);
	
	int getApplyingNum(String subUserName);
	
	int getDateConsumption(String subUserName, String date, String string);
	
}
