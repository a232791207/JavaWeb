package com.zhiqi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private DateUtil() {
		
	}
	
	public static String anotherDay(String date){
		try {
			Date d = (new SimpleDateFormat("yyyy-MM-dd")).parse(date);	
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.DATE, 1);
			date = (new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return date;
	}
	
	public static String xTimeAgo(String date){
		String s = "just now";
		try {
			Date d = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(date);	
			Date now = new Date();
			long between = now.getTime() - d.getTime();
			long secs = between/1000;
			if(secs>0){
				s = secs+" secs ago";
				long mins = secs/60;
				if(mins>0){
					s = mins+" mins ago";
					long hrs = mins/60;
					if(hrs>0){
						s = hrs+" hors ago";
						long days = hrs/60;
						if(days>0){
							s = days+" days ago";
							long mons = days/30;
							if(mons>0){
								s = mons+" mons ago";
								long years = mons/12;
								if(years>0){
									s = years+" years ago";
								}
							}
						}
					}
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return s;
	}
	
	public static String xDaysAgo(String date,int xdays){
		try {
			Date d = (new SimpleDateFormat("yyyy-MM-dd")).parse(date);	
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.DATE, -xdays);
			date = (new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return date;
	}
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String currentTime(){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sd.format(new Date());
		
		return date;
	}
	/**
	 * yyyyMMdd
	 * @return
	 */
	public static String currentDate(){
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		String date = sd.format(new Date());
		
		return date;
		
	}
	/**
	 * 
	 */
	public static String currentMonth(){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");
		String date = sd.format(new Date());
		
		return date;
	}
	/**
	 * yyyy-MM-dd
	 * @return
	 */
	public static String currentDate2(){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String date = sd.format(new Date());
		
		return date;
		
	}
}
