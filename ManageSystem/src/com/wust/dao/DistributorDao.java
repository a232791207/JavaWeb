package com.wust.dao;

import java.util.List;

import com.wust.domain.Distributor;

public interface DistributorDao {
	/**
	 * �����ṩ����ʼ��¼��λ�ã�����Ҫ���Ҽ�¼�ĸ������Ҿ�����
	 * @param from ��ʼ��¼��λ��
	 * @param count ��Ҫ���Ҽ�¼�ĸ���
	 * @return ����һ����װ�õ�DistributorPage bean
	 */
	public List<Distributor> findDistributorByPage(int from, int count);

	/**
	 * @return ���ؾ���������
	 */
	public int getCountRow();

	/**
	 * �����ṩ�ľ�������Ϣ������������
	 * @param distributor ��������Ϣ
	 */
	public void addDistributor(Distributor distributor);

	/**
	 * �����ṩ�ľ�����id,ɾ����Ӧ������
	 * @param id ������id
	 */
	public void deleteDistributor(String id);

	/**
	 * �����ṩ�ľ�����id�����Ҿ�������Ϣ����װ��bean
	 * @param id ������id
	 * @return ������bean
	 */
	public Distributor findDistributorById(String id);

	/**
	 * �����ṩ�ľ�������Ϣ���޸ľ�����
	 * @param distributor ��������Ϣ
	 */
	public void updateDistributor(Distributor distributor);

	public List<Object> findDistributorOption();

	public Distributor findDistributorByName(String name);

	public List<Distributor> selectDistributorPage(String name, int from,
			int count);

	public int getCountSelectDistributor(String name);

}
