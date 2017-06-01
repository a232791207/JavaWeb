package com.zhiqi.service;

import java.util.List;

import com.zhiqi.bean.Page;
import com.zhiqi.bean.Subordinate;

public interface SubordinateService {
	/**
	 * ��ȡ���û���
	 * @param supUserName �ϼ��û���
	 * @return ���û���
	 */
	int getSumNum(String supUserName);
	/**
	 * ��ȡ���ջ�Ծ
	 * @param supUserName �ϼ��û���
	 * @return ���ջ�Ծ
	 */
	int getTodayActive(String supUserName);
	/**
	 * ��ȡ�����û�
	 * @param supUserName �ϼ��û���
	 * @return �����û�
	 */
	int getNewIncrease(String supUserName);
	
	Page getSubordinatePage(int thispage, int rowperpage, String supUserName, String date, short subLevel);
	
	/**
	 * ����userName  like %subUserName% ���б�
	 * @param subUserName 
	 * @param supUserName 
	 * @param subLevel 
	 * @return
	 */
	List<Subordinate> getSubordinateByID(String subUserName, String supUserName, short subLevel);
	
	List<Subordinate> getNoConsumption(String supUserName, short subLevel);
	
	List<String> getSubUserNameList(String supUserName);
	
	void delete(String subUserName);
	
	/**
	 * ����userName  = subUserName ���б�
	 * @param subUserName 
	 * @return
	 */
	Subordinate getSubordinateByUserName(String subUserName);
	
	void update(Subordinate subordinate);
	
	void add(Subordinate subordinate);
	
	Page getAllUserPage(int thispage, int rowperpage);
	
	Page getSubUserPage(int thispage, int rowperpage, String supUserName);
	
	Page getLevelUserPage(int thispage, int rowperpage, short subLevel);
	
	Page getUserPageById(int thispage, int rowperpage, String subUserName);

}
