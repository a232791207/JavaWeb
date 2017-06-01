package com.zhiqi.service;

import org.springframework.stereotype.Service;

import com.zhiqi.bean.User;

@Service
public interface UserService {
	/**
	 * �����û����͵�¼������Ҽ��ܺ��User
	 * @param userName �û���
	 * @param loginPassword ��¼����
	 * @return ���ܺ��User
	 */
	public User findEncodedUserByUserNameAndLoginPassword(String userName, String loginPassword);
	
	/**
	 * �����û������Ҽ��ܺ��User
	 * @param userName �û���
	 * @param loginPassword ��¼����
	 * @return ���ܺ��User
	 */
	public User findEncodedUserByUserName(String userName);
	
	/**
	 * �����û�������User
	 * @param userName �û���
	 * @param loginPassword ��¼����
	 * @return User
	 */
	public User findUserByUserName(String userName);
	
	/**
	 * �޸��û��ǳ�
	 * @param userName �û���
	 * @param nickName �ǳ�
	 * @return �޸ĺ���ܵ�User
	 */
	public void updateNickName(String userName, String nickName);
	
	/**
	 * �޸��û��ֻ���
	 * @param userName �û���
	 * @param phoneNum �ֻ���
	 * @return �޸ĺ���ܵ�User
	 */
	public void updatePhoneNum(String userName, String phoneNum);
	
	/**
	 * �޸��û�����
	 * @param userName �û���
	 * @param phoneNum ����
	 * @return �޸ĺ���ܵ�User
	 */
	public void updateEmail(String userName, String email);
	
	/**
	 * �޸��û����п���
	 * @param userName �û���
	 * @param phoneNum ���п���
	 * @return �޸ĺ���ܵ�User
	 */
	public void updateBankCode(String userName, String bankCode);
	
	/**
	 * �޸��û���ַ
	 * @param userName �û���
	 * @param phoneNum ��ַ
	 * @return �޸ĺ���ܵ�User
	 */
	public void updateAddress(String userName, String address);
	
	/**
	 * �޸��û���¼����
	 * @param userName �û���
	 * @param password ����
	 */
	public void updateLoginPasswordByUsername(String userName, String password);
	
	/**
	 * �޸��û�֧������
	 * @param userName �û���
	 * @param password ����
	 */
	public void updatePayPasswordByUsername(String userName, String password);
	/**
	 * �����û���Ϣ
	 * @param user �û�bean
	 */
	public void update(User user);

	
	public void add(User user);
	User getIdFindByUserInfo(String id);
}
