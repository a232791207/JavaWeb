package com.wust.dao;

import java.util.List;

import com.wust.domain.Clerk;

public interface ClerkDao {
	/**
	 * 根据提供的起始记录的位置，和需要查找记录的个数，找员工
	 * @param from 起始记录的位置
	 * @param count 需要查找记录的个数
	 * @return 返回一个封装好的Page bean
	 */
	public List<Clerk> findClerkByPage(int from, int count);

	/**
	 * @return 返回员工总数
	 */
	public int getCountRow();

	/**
	 * 根据提供的员工id，在数据库中查找到对应的员工信息	
	 * @param id	员工id
	 * @return	Clerk bean
	 */
	public Clerk findClerkById(String id);

	/**
	 * 根据提供的员工信息，修改员工信息
	 * @param clerk 员工信息
	 */
	public void updateClerk(Clerk clerk);

	/**
	 * 根据提供的员工id，删除对应员工信息	
	 * @param id 员工id
	 */
	public void deleteClerk(String id);

	/**
	 * 根据提供的员工信息，新增员工
	 * @param clerk 员工信息
	 */
	public void addClerk(Clerk clerk);

	public Clerk findClerkByIdPasTyp(String id, String password, String type);

	public void updatePassword(String id, String password);

	public List<Object> findSalesmanOption();

	public List<Clerk> selectClerk(String name, String type, int from,
			int count);

	public int getCountSelectedClerk(String name, String type);

}
