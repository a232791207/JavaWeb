package com.wust.service;

import java.util.List;

import com.wust.domain.Distributor;
import com.wust.domain.DistributorPage;

public interface DistributorService {

	/**
	 * �����ṩ�ĵ�ǰ��Ҫ���ҳ���Լ�ÿҳ����Ҫ������������DistributorDao�еķ�������Ա��
	 * @param thispage ��ǰ��Ҫ���ҳ��
	 * @param rowperpage ÿҳ������
	 * @return	���ط�װ�õ�DistributorPage bean
	 */
	public DistributorPage pageDistributor(int thispage, int rowperpage);

	/**
	 * �����ṩ�ľ�������Ϣ������������
	 * @param distributor ��������Ϣ
	 */
	public void addDistributor(Distributor distributor);

	/**
	 * �����ṩ�ľ�����id��ɾ����Ӧ������
	 * @param id ������id
	 */
	public void deleteDistributor(String id);

	/**
	 * �����ṩ�ľ�����id�����Ҷ�Ӧ��������Ϣ
	 * @param id ������id
	 * @return ��������Ϣ
	 */
	public Distributor getDistributorInfo(String id);

	/**
	 * �����ṩ�ľ�������Ϣ���޸Ķ�Ӧ��������Ϣ
	 * @param distributor ��������Ϣbean
	 */
	public void updateDistributor(Distributor distributor);

	public List<Object> findDistributorOption();

	public Distributor findDistributorByName(String name);

	public DistributorPage selectDistributor(String name, int thispage,
			int rowperpage);

}
