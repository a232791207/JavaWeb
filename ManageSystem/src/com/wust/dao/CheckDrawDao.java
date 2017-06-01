package com.wust.dao;

import java.util.List;


import com.wust.domain.CheckDraw;
import com.wust.domain.Draworder;

public interface CheckDrawDao {

	public List<CheckDraw> findCheckDrawByPage(int from, int count);

	public int getCountRow();
	
	public List<CheckDraw> getCheckDrawByDistributorPage(String distributor,int from, int count);

	public void updateCheckDrawByDrawnum(String distributor, String format,
			String level, int number);

	public void deleteCheckDraw(String distributor, String format, String level);

	public CheckDraw getCheckDraw(String distributor, String format,
			String level);

	public void addCheckDraw(String distributor, String format, String level,
			int number);

	public void updateCheckDrawNum(String distributor, String format,
			String level, int number);

	public int getCountRowWithDistributor(String distributor);

	public List<Object> findLevelOption(String distributor, String format);

	public void updateCheckDrawByDrawnum(List<Draworder> draworders);
	

}
