package com.wust.service;

import java.util.List;

import com.wust.domain.Draworder;
import com.wust.domain.DraworderPage;


public interface DraworderService {

	public DraworderPage pageDraworder(int thispage, int rowperpage);
	public void addDraworder(Draworder draworder);
	public void delete(String id,String format,String level,double price);
	public Draworder findDraworder(String id, String format, String level,double price);
	public List<Object[]> getDrawDetailsByDist(String distributor);
	public DraworderPage deletedDraworderList(int thispage, int rowperpage);
	public DraworderPage findDistributorDraworderBypage(String distributor,
			int thispage, int rowperpage);
	public void addDraworder(List<Draworder> draworders);
}
