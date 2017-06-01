package com.sanker.service.utils;

import org.doomdark.uuid.UUIDGenerator;

public class UUIDUtil {
	
	private static UUIDUtil uuidUtil = new UUIDUtil();
	
	private UUIDUtil(){
		
	}
	
	public static UUIDUtil getInstance(){
		return uuidUtil;
	}
	
	/**
	 * 生成UUID
	 * @param b：为true表示去掉UUID中“-”
	 * @return
	 */
	public String generatUUID(boolean b){
		String u = UUIDGenerator.getInstance().generateRandomBasedUUID().toString();  
		if(b){
			u = u.replaceAll("-", "");
		}
		return u;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
