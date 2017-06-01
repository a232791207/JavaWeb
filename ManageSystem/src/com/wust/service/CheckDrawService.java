package com.wust.service;

import java.util.List;

import com.wust.domain.CheckDraw;
import com.wust.domain.Draworder;

import com.wust.domain.CheckDrawPage;

public interface CheckDrawService {

	public CheckDrawPage getCheckDrawByPage(int thispage, int rowperpage);

	
	public CheckDrawPage getCheckDrawByDistributorPage(String distributor,int thispage, int rowperpage);

	public void updateCheckDrawByDrawnum(String distributor, String format,
			String level, int number);


	public void deleteCheckDraw(String distributor, String format,
			String level);


	public CheckDraw getCheckDraw(String distributor, String format,
			String level);


	public void addChekDraw(String distributor, String format, String level,
			int number);


	public void updateCheckDrawNum(String distributor, String format,
			String level, int number);


	public List<Object> findLevelOption(String distributor,String format);


	public void updateCheckDrawByDrawnum(List<Draworder> draworders);



}
