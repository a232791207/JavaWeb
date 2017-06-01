package com.zhiqi.utils;

import java.io.FileReader;
import java.util.Properties;

public class DiscountUtil {
	private static Properties prop = null;
	private DiscountUtil(){}
	static{
		try{
			prop = new Properties();
			prop.load(new FileReader(DiscountUtil.class.getClassLoader().getResource("discount.properties").getPath()));
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	public static double getDiscount(int level){
		double discount = Double.parseDouble(prop.getProperty(level+""));
		return discount;
	}
	public static double getDiamondPrice(){
		double discount = Double.parseDouble(prop.getProperty("4"));
		return discount;
	}
}
