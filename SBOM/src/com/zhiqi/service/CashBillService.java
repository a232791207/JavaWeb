package com.zhiqi.service;

import com.zhiqi.bean.CashBill;
import com.zhiqi.bean.Page;

public interface CashBillService {
	/**
	 * ���ݵ�ǰҳ��ÿҳ��������ʼʱ�䡢����ʱ�����CashBillPage
	 * @param thispage ��ǰҳ��
	 * @param rowperpage ÿҳ����
	 * @param stime ��ʼʱ��
	 * @param etime ����ʱ��
	 * @return CashBillPage
	 */
	Page findCashBillPageByTime(int thispage, int rowperpage, String stime, String etime, String userName);
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
