package com.wust.service;

import com.wust.domain.BasicFare;
import com.wust.domain.BasicFarePage;

public interface BasicFareService {

	public BasicFarePage getBasicFareByPage(int thispage, int rowperpage);

	public void addBasicFare(BasicFare basicFare);

	public void deleteBasicFare(String id);

	public BasicFare getBasicFareInfo(String id);

	public void updateBasicFare(BasicFare basicFare);

	public BasicFare findBasicfByArea(String area);

	public BasicFarePage getBasicFreightByPage(int thispage, int rowperpage);

	public BasicFare findBasicf2ByArea(String area);

	public void addBasicFreight(BasicFare basicFare);

	public void deleteBasicFreight(String id);

	public BasicFare getBasicFreightInfo(String id);


}
