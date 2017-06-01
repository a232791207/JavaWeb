package com.zhiqi.dao;

import java.util.List;

import com.zhiqi.bean.Subordinate;

public interface SubordinateDao {
	int getSumNum(String supUserName);

	int getTodayActive(String supUserName, String today);

	int getNewIncrease(String supUserName, String today);

	List<Subordinate> findDiamondBillPageByTime(int from, int count, String supUserName, String date, short subLevel);

	int getCountDiamondBillByTime(String supUserName, String date);

	List<Subordinate> getSubordinateByID(String subUserName, String supUserName, short subLevel);

	List<Subordinate> getNoConsumption(String supUserName, short subLevel);

	List<String> getSubUserNameList(String supUserName);

	void delete(String subUserName);

	Subordinate getSubordinateByUserName(String subUserName);

	void update(Subordinate subordinate);

	void add(Subordinate subordinate);

	List<Subordinate> getAllUserPage(int from, int count);

	int getAllUserCount();

	List<Subordinate> getSubUserPage(int from, int count, String supUserName);

	int getSubUserCount(String supUserName);

	int getLevelUserCount(short subLevel);

	List<Subordinate> getLevelUserPage(int from, int count, short subLevel);

	List<Subordinate> getUserPageById(int from, int count, String subUserName);

	int getUserCountById(String subUserName);

}
