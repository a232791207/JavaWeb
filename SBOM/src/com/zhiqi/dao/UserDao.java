package com.zhiqi.dao;

import org.springframework.stereotype.Repository;

import com.zhiqi.bean.User;

@Repository
public interface UserDao {
	/**
	 * 根据用户名和登录密码查找User
	 * @param userName 用户名
	 * @param loginPassword 登录密码
	 * @return User
	 */
	User findUserByUserNameAndLoginPassword(String userName, String loginPassword);
	
	/**
	 * 修改用户昵称
	 * @param userName 用户名
	 * @param nickName 昵称
	 */
	void updateNickName(String userName, String nickName);
	
	/**
	 * 修改用户手机号
	 * @param userName 用户名
	 * @param phoneNum 手机号
	 */
	void updatePhoneNum(String userName, String phoneNum);
	
	/**
	 * 修改用户邮箱
	 * @param userName 用户名
	 * @param phoneNum 邮箱
	 */
	void updateEmail(String userName, String email);
	
	/**
	 * 修改用户银行卡号
	 * @param userName 用户名
	 * @param phoneNum 银行卡号
	 */
	void updateBankCode(String userName, String bankCode);
	
	/**
	 * 修改用户地址
	 * @param userName 用户名
	 * @param phoneNum 地址
	 */
	void updateAddress(String userName, String address);
	
	/**
	 * 根据用户名查找User
	 * @param userName 用户名
	 * @param loginPassword 登录密码
	 * @return User
	 */
	User findUserByUserName(String userName);
	
	/**
	 * 修改用户登录密码
	 * @param userName 用户名
	 * @param password 密码
	 */
	void updateLoginPasswordByUsername(String userName, String password);
	
	/**
	 * 修改用户支付密码
	 * @param userName 用户名
	 * @param password 密码
	 */
	void updatePayPasswordByUsername(String userName, String password);
	
	/**
	 * 更新用户信息
	 * @param user 用户bean
	 */
	void update(User user);

	void add(User user);
	/**
	 * 检测这个游戏ID有没有使用过
	 * @param id 游戏ID
	 * @return
	 */
	User getIdFindByUserInfo(String id);
}
