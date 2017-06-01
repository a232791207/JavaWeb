package com.wust.service;

import java.util.List;

import com.wust.domain.Clerk;
import com.wust.domain.ClerkPage;

public interface ClerkService {
	/**
	 * �����ṩ�ĵ�ǰ��Ҫ���ҳ���Լ�ÿҳ����Ҫ������������ClerkDao�еķ�������Ա��
	 * @param thispage ��ǰ��Ҫ���ҳ��
	 * @param rowperpage ÿҳ������
	 * @return	���ط�װ�õ�Page bean
	 */
	public ClerkPage pageClerk(int thispage, int rowperpage);

	/**
	 * �����ṩ��Ա��id���ҵ���Ӧ��Ա��
	 * @param id ������Ա��id
	 * @return	���ط�װ�õ�Clerk bean
	 */
	public Clerk getClerkInfo(String id);

	/**
	 * �����ṩ��Ա����Ϣ�޸�Ա����Ϣ
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

	public Clerk isClerk(String id, String password, String type);

	public void updatePassword(String id, String password);

	public List<Object> findSalesmanOption();

	public ClerkPage selectClerk(String name, String type, int thispage,
			int rowperpage);



}
