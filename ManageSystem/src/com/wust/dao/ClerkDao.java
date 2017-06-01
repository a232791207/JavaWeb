package com.wust.dao;

import java.util.List;

import com.wust.domain.Clerk;

public interface ClerkDao {
	/**
	 * �����ṩ����ʼ��¼��λ�ã�����Ҫ���Ҽ�¼�ĸ�������Ա��
	 * @param from ��ʼ��¼��λ��
	 * @param count ��Ҫ���Ҽ�¼�ĸ���
	 * @return ����һ����װ�õ�Page bean
	 */
	public List<Clerk> findClerkByPage(int from, int count);

	/**
	 * @return ����Ա������
	 */
	public int getCountRow();

	/**
	 * �����ṩ��Ա��id�������ݿ��в��ҵ���Ӧ��Ա����Ϣ	
	 * @param id	Ա��id
	 * @return	Clerk bean
	 */
	public Clerk findClerkById(String id);

	/**
	 * �����ṩ��Ա����Ϣ���޸�Ա����Ϣ
	 * @param clerk Ա����Ϣ
	 */
	public void updateClerk(Clerk clerk);

	/**
	 * �����ṩ��Ա��id��ɾ����ӦԱ����Ϣ	
	 * @param id Ա��id
	 */
	public void deleteClerk(String id);

	/**
	 * �����ṩ��Ա����Ϣ������Ա��
	 * @param clerk Ա����Ϣ
	 */
	public void addClerk(Clerk clerk);

	public Clerk findClerkByIdPasTyp(String id, String password, String type);

	public void updatePassword(String id, String password);

	public List<Object> findSalesmanOption();

	public List<Clerk> selectClerk(String name, String type, int from,
			int count);

	public int getCountSelectedClerk(String name, String type);

}
