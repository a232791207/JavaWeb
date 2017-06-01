package com.sanker.service.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
   
	/**
	 * 将字符串转行成时间 
	 * @param str 字符串的格式必须为 YYYY-MM,否则转换失败
	 * @return  
	 */
	public static Date convertStringToDateMin(String str){
		Calendar calendar=Calendar.getInstance();
		try {
			String[] temp = str.split("-");
			calendar.set(Integer.valueOf(temp[0]),
					Integer.valueOf(temp[1]) - 1, Integer.valueOf(temp[2]));
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.clear(Calendar.MINUTE);
			calendar.clear(Calendar.SECOND);
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
		return calendar.getTime();
	}
	
	/**
	 * 将字符串转行成日期  
	 * @param str 字符串的格式必须为 YYYY-MM,否则转换失败
	 * @return  
	 */
	public static Date convertStringToDateMinWithoutTime(String str){
		Calendar calendar=Calendar.getInstance();
		try {
			String[] temp = str.split("-");
			calendar.set(Integer.valueOf(temp[0]),
					Integer.valueOf(temp[1]) - 1, Integer.valueOf(temp[2]));
			calendar.add(Calendar.DAY_OF_YEAR,-1);
			calendar.set(Calendar.HOUR_OF_DAY,23);
			calendar.set(Calendar.MINUTE,59);
			calendar.set(Calendar.SECOND,59);
		} catch (Exception e) {
			return null;
		}
		return calendar.getTime();
	}
	
	public static Date convertStringToDateMax(String str){
		Calendar calendar=Calendar.getInstance();
		try {
			String[] temp = str.split("-");
			calendar.set(Integer.valueOf(temp[0]),
					Integer.valueOf(temp[1]) - 1, Integer.valueOf(temp[2]));
			calendar.set(Calendar.HOUR_OF_DAY,23);
			calendar.set(Calendar.MINUTE,59);
			calendar.set(Calendar.SECOND,59);
		} catch (Exception e) {
			return null;
		}
		return calendar.getTime();
	}
	
	
	
	/**
	 * 获取输入时间的月份的最后一天
	 * @param date
	 * @return  返回的时间为输入的年月的第一天的时间
	 */
	public static Date getLastDateOfMonth(Date date){
		Calendar calendar=Calendar.getInstance();
		try {
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, 1);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
		} catch (Exception e) {
			 return null;
		}
		return calendar.getTime();
	}
	/**
	 * 获取输入时间的月份的最后一天
	 * @param str 字符串的格式必须为 YYYY-MM,否则转行失败
	 * @return  返回的时间为输入的年月的第一天的时间
	 */
	public static Date getLastDateOfMonth(String str){
		Calendar calendar=Calendar.getInstance();
		try {
			calendar.setTime(convertStringToDateMin(str));
			calendar.add(Calendar.MONTH, 1);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
		} catch (Exception e) {
			return null;
		}
		return calendar.getTime();
	}
	
	/**
	 * 获取输入时间的月份的最后一天
	 * @param str 字符串的格式必须为 YYYY-MM,否则转行失败
	 * @return  返回的时间为输入的年月的第一天的时间
	 */
	public static Date getFirstDateOfPreviousMonth(String  str){
		Calendar calendar=Calendar.getInstance();
		try {
			calendar.setTime(convertStringToDateMin(str));
			calendar.add(Calendar.MONTH, -1);
		} catch (Exception e) {
			return null;
		}
		return calendar.getTime();
	}
	
	/**
	 * 将日期转换为字符串
	 * @param date：日期
	 * @param pattern：格式
	 * @return
	 */
	public static String convertDateToString(Date date, String pattern){ 
		SimpleDateFormat sdf = null;
		String dateString = null;
		if(date != null && pattern != null){
			sdf = new SimpleDateFormat(pattern);
			dateString = sdf.format(date);
		}
		return dateString;
	}
	
	/**
	 * 将字符串转行成时间 
	 * @param str 字符串的格式必须为 yyyy-MM-dd HH:mm,否则转换失败
	 * @return  
	 */
	public static Date convertStringToDate(String str){
		Calendar calendar=Calendar.getInstance();
		try {
			String[] tempDateAndTime = str.split(" ");
			String[] tempDate = tempDateAndTime[0].split("-");
			String[] tempTime = tempDateAndTime[1].split(":");
			calendar.set(Integer.valueOf(tempDate[0]),
					Integer.valueOf(tempDate[1]) - 1, 
					Integer.valueOf(tempDate[2]), 
					Integer.valueOf(tempTime[0]), 
					Integer.valueOf(tempTime[1]));
			//calendar.set(Calendar.HOUR_OF_DAY, 0);
			//calendar.clear(Calendar.MINUTE);
			calendar.clear(Calendar.SECOND);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return calendar.getTime();
	}
	
	public static void main(String[] args) {
		//System.err.println(getLastDateOfMonth("2009-1"));
		System.out.println(convertStringToDate("2010-09-12 14:00:32"));
	}
}
