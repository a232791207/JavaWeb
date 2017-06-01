package com.zhiqi.service;

import org.springframework.stereotype.Service;

import com.zhiqi.bean.SuperUser;

@Service
public interface SuperUserService {
    /**
     * 根据用户名和密码查找的信息
     * @param userName 用户名
     * @param loginPassword 密码
     * @return
     */
	SuperUser findByUserName(String userName, String loginPassword);
}
