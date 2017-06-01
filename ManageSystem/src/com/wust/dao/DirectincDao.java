package com.wust.dao;

import java.util.List;

import com.wust.domain.Directinc;

public interface DirectincDao {

	public Directinc findDirectinc(String time, String distributor, String area, String format, String level);

	public List<Directinc> findDirectincByPage(int from, int count);

	public int getCountRow();

	public Directinc findDirectincById(String id);

	public void updateDirectinc(Directinc directinc);

	public void delDirectincById(String id);

	public void addDirectinc(Directinc directinc);

}
