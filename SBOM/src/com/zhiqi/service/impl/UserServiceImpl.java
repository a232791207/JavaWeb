package com.zhiqi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiqi.bean.User;
import com.zhiqi.dao.UserDao;
import com.zhiqi.service.UserService;
import com.zhiqi.utils.EncodeUserUtil;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User findEncodedUserByUserNameAndLoginPassword(String userName, String loginPassword) {
		User user=userDao.findUserByUserNameAndLoginPassword(userName, loginPassword);
		User user2=EncodeUserUtil.encodeUser(user);
		return user2;
	}

	@Override
	public void updateNickName(String userName, String nickName) {
		userDao.updateNickName(userName,nickName);
	}

	@Override
	public void updatePhoneNum(String userName, String phoneNum) {
		userDao.updatePhoneNum(userName,phoneNum);
	}

	@Override
	public void updateEmail(String userName, String email) {
		userDao.updateEmail(userName,email);
	}

	@Override
	public void updateBankCode(String userName, String bankCode) {
		userDao.updateBankCode(userName,bankCode);
	}

	@Override
	public void updateAddress(String userName, String address) {
		userDao.updateAddress(userName,address);
	}

	@Override
	public User findEncodedUserByUserName(String userName) {
		User user = userDao.findUserByUserName(userName);
		User encodedUser = EncodeUserUtil.encodeUser(user);
		return encodedUser;
	}

	@Override
	public void updateLoginPasswordByUsername(String userName, String password) {
		userDao.updateLoginPasswordByUsername(userName,password);
	}

	@Override
	public User findUserByUserName(String userName) {
		return userDao.findUserByUserName(userName);
	}

	@Override
	public void updatePayPasswordByUsername(String userName, String password) {
		userDao.updatePayPasswordByUsername(userName,password);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void add(User user) {
		userDao.add(user);
	}

	@Override
	public User getIdFindByUserInfo(String id) {
		return userDao.getIdFindByUserInfo(id);
	}

}
