package com.zhiqi.service;

import org.springframework.stereotype.Service;

import com.zhiqi.bean.User;

@Service
public interface UserService {
	/**
	 * 根据用户名和登录密码查找加密后的User
	 * @param userName 用户名
	 * @param loginPassword 登录密码
	 * @return 加密后的User
	 */
	public User findEncodedUserByUserNameAndLoginPassword(String userName, String loginPassword);
	
	/**
	 * 根据用户名查找加密后的User
	 * @param userName 用户名
	 * @param loginPassword 登录密码
	 * @return 加密后的User
	 */
	public User findEncodedUserByUserName(String userName);
	
	/**
	 * 根据用户名查找User
	 * @param userName 用户名
	 * @param loginPassword 登录密码
	 * @return User
	 */
	public User findUserByUserName(String userName);
	
	/**
	 * 修改用户昵称
	 * @param userName 用户名
	 * @param nickName 昵称
	 * @return 修改后加密的User
	 */
	public void updateNickName(String userName, String nickName);
	
	/**
	 * 修改用户手机号
	 * @param userName 用户名
	 * @param phoneNum 手机号
	 * @return 修改后加密的User
	 */
	public void updatePhoneNum(String userName, String phoneNum);
	
	/**
	 * 修改用户邮箱
	 * @param userName 用户名
	 * @param phoneNum 邮箱
	 * @return 修改后加密的User
	 */
	public void updateEmail(String userName, String email);
	
	/**
	 * 修改用户银行卡号
	 * @param userName 用户名
	 * @param phoneNum 银行卡号
	 * @return 修改后加密的User
	 */
	public void updateBankCode(String userName, String bankCode);
	
	/**
	 * 修改用户地址
	 * @param userName 用户名
	 * @param phoneNum 地址
	 * @return 修改后加密的User
	 */
	public void updateAddress(String userName, String address);
	
	/**
	 * 修改用户登录密码
	 * @param userName 用户名
	 * @param password 密码
	 */
	public void updateLoginPasswordByUsername(String userName, String password);
	
	/**
	 * 修改用户支付密码
	 * @param userName 用户名
	 * @param password 密码
	 */
	public void updatePayPasswordByUsername(String userName, String password);
	/**
	 * 更新用户信息
	 * @param user 用户bean
	 */
	public void update(User user);

	
	public void add(User user);
	User getIdFindByUserInfo(String id);
}
