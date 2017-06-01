package com.zhiqi.service;

import org.springframework.stereotype.Service;

import com.zhiqi.bean.SuperUser;

@Service
public interface SuperUserService {
    /**
     * �����û�����������ҵ���Ϣ
     * @param userName �û���
     * @param loginPassword ����
     * @return
     */
	SuperUser findByUserName(String userName, String loginPassword);
}
