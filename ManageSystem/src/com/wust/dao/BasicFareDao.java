package com.wust.dao;

import java.util.List;

import com.wust.domain.BasicFare;

public interface BasicFareDao {

	public List<BasicFare> findBasicFareByPage(int from, int count);

	public int getCountRow();

	public void addBasicFare(BasicFare basicFare);

	public void deleteBasicFare(String id);

	public BasicFare getBasicFareInfo(String id);

	public void updateBasicFare(BasicFare basicFare);

	public BasicFare findBasicfByArea(String area);

	public List<BasicFare> findBasicFreightByPage(int from, int count);

	public int getCountRow2();

	public BasicFare findBasicf2ByArea(String area);

	public void addBasicFreight(BasicFare basicFare);

	public void deleteBasicFreight(String id);

	public BasicFare getBasicFreightInfo(String id);

}
