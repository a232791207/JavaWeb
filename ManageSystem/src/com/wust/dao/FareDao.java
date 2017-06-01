package com.wust.dao;

import java.util.List;

import com.wust.domain.Fare;

public interface FareDao {

	public List<Fare> findFareByPage(int from, int count);

	public int getCountRow();

	public void addFare(String id, String area, double fare, double num,
			double sumfare);

	public void delete(String id);

	public Fare getFare(String orderid);

	public void updateFare(Fare fare);

	public void delete2(String id);

	public List<Fare> findFreightByPage(int from, int count);

	public int getCountRow2();

	public Fare getFreight(String orderid);

	public void updateFreight(Fare fare);

	public void addFreight(String orderid, String area, double fare,
			double num, double sumfare);

}
