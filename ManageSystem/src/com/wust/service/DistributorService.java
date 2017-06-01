package com.wust.service;

import java.util.List;

import com.wust.domain.Distributor;
import com.wust.domain.DistributorPage;

public interface DistributorService {

	/**
	 * 根据提供的当前需要查的页码以及每页所需要的行数，调用DistributorDao中的方法查找员工
	 * @param thispage 当前需要查的页码
	 * @param rowperpage 每页的行数
	 * @return	返回封装好的DistributorPage bean
	 */
	public DistributorPage pageDistributor(int thispage, int rowperpage);

	/**
	 * 根据提供的经销商信息，新增经销商
	 * @param distributor 经销商信息
	 */
	public void addDistributor(Distributor distributor);

	/**
	 * 根据提供的经销商id，删除对应经销商
	 * @param id 经销商id
	 */
	public void deleteDistributor(String id);

	/**
	 * 根据提供的经销商id，查找对应经销商信息
	 * @param id 经销商id
	 * @return 经销商信息
	 */
	public Distributor getDistributorInfo(String id);

	/**
	 * 根据提供的经销商信息，修改对应经销商信息
	 * @param distributor 经销商信息bean
	 */
	public void updateDistributor(Distributor distributor);

	public List<Object> findDistributorOption();

	public Distributor findDistributorByName(String name);

	public DistributorPage selectDistributor(String name, int thispage,
			int rowperpage);

}
