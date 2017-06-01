package com.zhiqi.utils;

import com.zhiqi.bean.User;

public class EncodeUserUtil {
	private EncodeUserUtil(){}
	
	public static User encodeUser(User user){
		if(user!=null){
			if(user.getIdNum()!=null&&user.getBankCode()!=null&&user.getPayPassword()!=null
					&&!user.getIdNum().equals("")&&!user.getBankCode().equals("")&&!user.getPayPassword().equals("")){
				String idNum = user.getIdNum();
				String loginPassword = "******";
				String payPassword = "******";
				String bankCode = user.getBankCode();
				
				idNum = idNum.substring(0, 4)+"******"+idNum.substring(idNum.length()-4,idNum.length());
				bankCode = bankCode.substring(0, 4)+"******"+bankCode.substring(bankCode.length()-4,bankCode.length());
				
				user.setIdNum(idNum);
				user.setLoginPassword(loginPassword);
				user.setPayPassword(payPassword);
				user.setBankCode(bankCode);
			}
		}
		
		return user;
	}
}
