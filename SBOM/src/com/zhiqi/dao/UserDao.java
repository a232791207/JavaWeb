package com.zhiqi.dao;

import org.springframework.stereotype.Repository;

import com.zhiqi.bean.User;

@Repository
public interface UserDao {
	/**
	 * �����û����͵�¼�������User
	 * @param userName �û���
	 * @param loginPassword ��¼����
	 * @return User
	 */
	User findUserByUserNameAndLoginPassword(String userName, String loginPassword);
	
	/**
	 * �޸��û��ǳ�
	 * @param userName �û���
	 * @param nickName �ǳ�
	 */
	void updateNickName(String userName, String nickName);
	
	/**
	 * �޸��û��ֻ���
	 * @param userName �û���
	 * @param phoneNum �ֻ���
	 */
	void updatePhoneNum(String userName, String phoneNum);
	
	/**
	 * �޸��û�����
	 * @param userName �û���
	 * @param phoneNum ����
	 */
	void updateEmail(String userName, String email);
	
	/**
	 * �޸��û����п���
	 * @param userName �û���
	 * @param phoneNum ���п���
	 */
	void updateBankCode(String userName, String bankCode);
	
	/**
	 * �޸��û���ַ
	 * @param userName �û���
	 * @param phoneNum ��ַ
	 */
	void updateAddress(String userName, String address);
	
	/**
	 * �����û�������User
	 * @param userName �û���
	 * @param loginPassword ��¼����
	 * @return User
	 */
	User findUserByUserName(String userName);
	
	/**
	 * �޸��û���¼����
	 * @param userName �û���
	 * @param password ����
	 */
	void updateLoginPasswordByUsername(String userName, String password);
	
	/**
	 * �޸��û�֧������
	 * @param userName �û���
	 * @param password ����
	 */
	void updatePayPasswordByUsername(String userName, String password);
	
	/**
	 * �����û���Ϣ
	 * @param user �û�bean
	 */
	void update(User user);

	void add(User user);
	/**
	 * ��������ϷID��û��ʹ�ù�
	 * @param id ��ϷID
	 * @return
	 */
	User getIdFindByUserInfo(String id);
}
