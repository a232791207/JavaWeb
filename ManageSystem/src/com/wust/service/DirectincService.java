package com.wust.service;

import com.wust.domain.Directinc;
import com.wust.domain.DirectincPage;

public interface DirectincService {

	public Directinc findDirectinc(String time, String distributor, String area, String format, String level);

	public DirectincPage pageDirectinc(int thispage, int rowperpage);

	public Directinc getDirectincInfo(String id);

	public void updateDirectinc(Directinc directinc);

	public void addDirectinc(Directinc directinc);

	public void delDirectincById(String id);


}
