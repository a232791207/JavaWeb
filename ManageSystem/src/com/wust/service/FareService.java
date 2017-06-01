package com.wust.service;

import com.wust.domain.Fare;
import com.wust.domain.FarePage;

public interface FareService {

	public FarePage getFareByPage(int thispage, int rowperpage);

	public void addFare(String id, String area, double fare,
			double num, double sumfare);

	public void deleteFare(String id);

	public Fare getFare(String orderid);

	public void updateFare(Fare fare);

	public void deleteFreight(String id);

	public FarePage getFreightByPage(int thispage, int rowperpage);

	public Fare getFreight(String orderid);

	public void updateFreight(Fare fare);

	public void addFreight(String orderid, String area, double fare,
			double packageNum, double d);

}
