package com.wust.dao;

import java.util.List;

import com.wust.domain.Distributor;

public interface DistributorDao {
	/**
	 * 根据提供的起始记录的位置，和需要查找记录的个数，找经销商
	 * @param from 起始记录的位置
	 * @param count 需要查找记录的个数
	 * @return 返回一个封装好的DistributorPage bean
	 */
	public List<Distributor> findDistributorByPage(int from, int count);

	/**
	 * @return 返回经销商总数
	 */
	public int getCountRow();

	/**
	 * 根据提供的经销商信息，新增经销商
	 * @param distributor 经销商信息
	 */
	public void addDistributor(Distributor distributor);

	/**
	 * 根据提供的经销商id,删除对应经销商
	 * @param id 经销商id
	 */
	public void deleteDistributor(String id);

	/**
	 * 根据提供的经销商id，查找经销商信息，封装成bean
	 * @param id 经销商id
	 * @return 经销商bean
	 */
	public Distributor findDistributorById(String id);

	/**
	 * 根据提供的经销商信息，修改经销商
	 * @param distributor 经销商信息
	 */
	public void updateDistributor(Distributor distributor);

	public List<Object> findDistributorOption();

	public Distributor findDistributorByName(String name);

	public List<Distributor> selectDistributorPage(String name, int from,
			int count);

	public int getCountSelectDistributor(String name);

}
