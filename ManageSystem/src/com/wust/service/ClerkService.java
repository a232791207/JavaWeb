package com.wust.service;

import java.util.List;

import com.wust.domain.Clerk;
import com.wust.domain.ClerkPage;

public interface ClerkService {
	/**
	 * 根据提供的当前需要查的页码以及每页所需要的行数，调用ClerkDao中的方法查找员工
	 * @param thispage 当前需要查的页码
	 * @param rowperpage 每页的行数
	 * @return	返回封装好的Page bean
	 */
	public ClerkPage pageClerk(int thispage, int rowperpage);

	/**
	 * 根据提供的员工id查找到对应的员工
	 * @param id 给定的员工id
	 * @return	返回封装好的Clerk bean
	 */
	public Clerk getClerkInfo(String id);

	/**
	 * 根据提供的员工信息修改员工信息
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

	public Clerk isClerk(String id, String password, String type);

	public void updatePassword(String id, String password);

	public List<Object> findSalesmanOption();

	public ClerkPage selectClerk(String name, String type, int thispage,
			int rowperpage);



}
