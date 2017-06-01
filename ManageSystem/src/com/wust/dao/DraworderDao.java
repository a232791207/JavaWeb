package com.wust.dao;

import java.util.List;

import com.wust.domain.Draworder;



public interface DraworderDao {
	public int getCountRow();
	public List<Draworder> findDraworderByPage(int from, int count);
	public void addDraworder(Draworder draworder);
	public void delete(String id,String format,String level,double price);
	public List<Object[]> getDrawDetailsByDist(String distributor);
	public int getDeletedCountRow();
	public List<Draworder> findDeletedDraworderByPage(int from, int count);
	public List<Draworder> findDistributorDraworderByPage(String distributor,
			int i, int rowperpage);
	public int getDistributorCountRow(String distributor);
	public void addDraworder(List<Draworder> draworders);
	public Draworder findDraworder(String id, String format, String level,
			double price);
	
	
}
