package com.zhiqi.service;

import java.util.List;

import com.zhiqi.bean.Page;
import com.zhiqi.bean.Subordinate;

public interface SubordinateService {
	/**
	 * 获取总用户数
	 * @param supUserName 上级用户名
	 * @return 总用户数
	 */
	int getSumNum(String supUserName);
	/**
	 * 获取今日活跃
	 * @param supUserName 上级用户名
	 * @return 今日活跃
	 */
	int getTodayActive(String supUserName);
	/**
	 * 获取新增用户
	 * @param supUserName 上级用户名
	 * @return 新增用户
	 */
	int getNewIncrease(String supUserName);
	
	Page getSubordinatePage(int thispage, int rowperpage, String supUserName, String date, short subLevel);
	
	/**
	 * 查找userName  like %subUserName% 的列表
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
	 * 查找userName  = subUserName 的列表
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
