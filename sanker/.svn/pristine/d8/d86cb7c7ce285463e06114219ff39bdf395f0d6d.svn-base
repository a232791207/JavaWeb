package com.sanker.service.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * 
 * <p>
 * Company: fsti.cn
 * </p>
 * 
 * @author tomax
 * @version 1.0
 */
public class StringUtil {
	//sms
	public static String[] toStringArray(String arraySTR,String mode){
		String[] result = null;
		if(arraySTR != null && mode != null){
			arraySTR = arraySTR.trim();
		    result = arraySTR.split(mode);
		}
		return result;
	} 
	
	public static boolean isNull(String str) {
		if (str == null || "".equals(str.trim()) || "null".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotNull(String str) {
		return !isNull(str);
	}

	public static java.lang.Long format2Long(String str) {
		if (isNull(str)) {
			return null;
		} else {
			return Long.valueOf(str);
		}
	}

	/**
	 * Split the given String into tokens.
	 * <P>
	 * This method is meant to be similar to the split function in other
	 * programming languages but it does not use regular expressions. Rather the
	 * String is split on a single String literal.
	 * <P>
	 * Unlike java.util.StringTokenizer which accepts multiple character tokens
	 * as delimiters, the delimiter here is a single String literal.
	 * <P>
	 * Each null token is returned as an empty String. Delimiters are never
	 * returned as tokens.
	 * <P>
	 * If there is no delimiter because it is either empty or null, the only
	 * element in the result is the original String.
	 * <P>
	 * StringHelper.split("1-2-3", "-");<br>
	 * result: {"1","2","3"}<br>
	 * StringHelper.split("-1--2-", "-");<br>
	 * result: {"","1","","2",""}<br>
	 * StringHelper.split("123", "");<br>
	 * result: {"123"}<br>
	 * StringHelper.split("1-2---3----4", "--");<br>
	 * result: {"1-2","-3","","4"}<br>
	 * 
	 * @param s
	 *            String to be split.
	 * @param delimiter
	 *            String literal on which to split.
	 * @return an array of tokens.
	 * @throws NullPointerException
	 *             if s is null.
	 * 
	 * @since ostermillerutils 1.00.00
	 */
	public static String[] split(String s, String delimiter) {
		int delimiterLength;
		// the next statement has the side effect of throwing a null pointer
		// exception if s is null.
		int stringLength = s.length();
		if (delimiter == null || (delimiterLength = delimiter.length()) == 0) {
			// it is not inherently clear what to do if there is no delimiter
			// On one hand it would make sense to return each character because
			// the null String can be found between each pair of characters in
			// a String. However, it can be found many times there and we don'
			// want to be returning multiple null tokens.
			// returning the whole String will be defined as the correct
			// behavior
			// in this instance.
			return new String[] { s };
		}

		// a two pass solution is used because a one pass solution would
		// require the possible resizing and copying of memory structures
		// In the worst case it would have to be resized n times with each
		// resize having a O(n) copy leading to an O(n^2) algorithm.

		int count;
		int start;
		int end;

		// Scan s and count the tokens.
		count = 0;
		start = 0;
		while ((end = s.indexOf(delimiter, start)) != -1) {
			count++;
			start = end + delimiterLength;
		}
		count++;

		// allocate an array to return the tokens,
		// we now know how big it should be
		String[] result = new String[count];

		// Scan s again, but this time pick out the tokens
		count = 0;
		start = 0;
		while ((end = s.indexOf(delimiter, start)) != -1) {
			result[count] = (s.substring(start, end));
			count++;
			start = end + delimiterLength;
		}
		end = stringLength;
		result[count] = s.substring(start, end);

		return (result);
	}

	/**
	 * Split the given String into tokens. Delimiters will be returned as
	 * tokens.
	 * <P>
	 * This method is meant to be similar to the split function in other
	 * programming languages but it does not use regular expressions. Rather the
	 * String is split on a single String literal.
	 * <P>
	 * Unlike java.util.StringTokenizer which accepts multiple character tokens
	 * as delimiters, the delimiter here is a single String literal.
	 * <P>
	 * Each null token is returned as an empty String. Delimiters are never
	 * returned as tokens.
	 * <P>
	 * If there is no delimiter because it is either empty or null, the only
	 * element in the result is the original String.
	 * <P>
	 * StringHelper.split("1-2-3", "-");<br>
	 * result: {"1","-","2","-","3"}<br>
	 * StringHelper.split("-1--2-", "-");<br>
	 * result: {"","-","1","-","","-","2","-",""}<br>
	 * StringHelper.split("123", "");<br>
	 * result: {"123"}<br>
	 * StringHelper.split("1-2--3---4----5", "--");<br>
	 * result: {"1-2","--","3","--","-4","--","","--","5"}<br>
	 * 
	 * @param s
	 *            String to be split.
	 * @param delimiter
	 *            String literal on which to split.
	 * @return an array of tokens.
	 * @throws NullPointerException
	 *             if s is null.
	 * 
	 * @since ostermillerutils 1.05.00
	 */
	public static String[] splitIncludeDelimiters(String s, String delimiter) {
		int delimiterLength;
		// the next statement has the side effect of throwing a null pointer
		// exception if s is null.
		int stringLength = s.length();
		if (delimiter == null || (delimiterLength = delimiter.length()) == 0) {
			// it is not inherently clear what to do if there is no delimiter
			// On one hand it would make sense to return each character because
			// the null String can be found between each pair of characters in
			// a String. However, it can be found many times there and we don'
			// want to be returning multiple null tokens.
			// returning the whole String will be defined as the correct
			// behavior
			// in this instance.
			return new String[] { s };
		}

		// a two pass solution is used because a one pass solution would
		// require the possible resizing and copying of memory structures
		// In the worst case it would have to be resized n times with each
		// resize having a O(n) copy leading to an O(n^2) algorithm.

		int count;
		int start;
		int end;

		// Scan s and count the tokens.
		count = 0;
		start = 0;
		while ((end = s.indexOf(delimiter, start)) != -1) {
			count += 2;
			start = end + delimiterLength;
		}
		count++;

		// allocate an array to return the tokens,
		// we now know how big it should be
		String[] result = new String[count];

		// Scan s again, but this time pick out the tokens
		count = 0;
		start = 0;
		while ((end = s.indexOf(delimiter, start)) != -1) {
			result[count] = (s.substring(start, end));
			count++;
			result[count] = delimiter;
			count++;
			start = end + delimiterLength;
		}
		end = stringLength;
		result[count] = s.substring(start, end);

		return (result);
	}
	
	public static String add0(int   n)   {   
		  if(n<10){
			  return "0"+String.valueOf(n);
		  }else{
			  return String.valueOf(n);
		  }
	}   
	
	//得到两日期之间的全部日期  如 2006-10 到2009-06之间的年月份  by llfzy
	public static List<String> getYearMonthList(String startDate,String endDate){
		List<String> dateList=new LinkedList<String>();
		String[] startDateArray=startDate.split("-");
		String[] endDateArray=endDate.split("-");
		
		int startYear=Integer.parseInt(startDateArray[0]);
		int endYear=Integer.parseInt(endDateArray[0]);
		
		int startMonth=Integer.parseInt(startDateArray[1]);
		int endMonth=Integer.parseInt(endDateArray[1]);
		
		if(startYear==endYear){
			for (int cdate =startMonth; cdate <= endMonth; cdate++) {
				dateList.add(String.valueOf(startYear)+StringUtil.add0(cdate));
			}
		}else{
			for(int cyear=startYear;cyear<=endYear;cyear++){
				if(cyear==startYear){
					for (int cdate =startMonth; cdate <= 12; cdate++) {
						dateList.add(String.valueOf(cyear)+StringUtil.add0(cdate));
					}
				}else if(cyear==endYear){
					for (int cdate =1; cdate <= endMonth; cdate++) {
						dateList.add(String.valueOf(cyear)+StringUtil.add0(cdate));
					}
				}else{
					for (int cdate =1; cdate <= 12; cdate++) {
						dateList.add(String.valueOf(cyear)+StringUtil.add0(cdate));
					}
				}
			}
		}
		return dateList;
	}
	
	public static String NullToString(Object obj){
		if(null==obj){
			return "";
		}else{
			return obj.toString().replaceAll("\r", "").replaceAll("\n", "").replaceAll("\"","&quot;");
				 /*  .replaceAll("\\(","&#40")
				   .replaceAll("\\)","&#41").replaceAll("\\[","&#91;").replaceAll("\\]","&#93")
				   .replaceAll("\\{","&#123").replaceAll("\\}","&#125")
				   .replaceAll("\\'","&#39").replaceAll("\"","&#34")
				   .replaceAll("\\;","&#59").replaceAll("\\:","&#58").replaceAll("\\,","&#44");*/
		}
	}
	
	public static String stringReplace(String str){
		if(str == null){
			return "";
		}else{
			return str.replaceAll("\"", "\\\\\"").replaceAll("\'", "\\\\\'");
		}
	}

	public static void main(String[] a){
		String string = "说的就发了多少积分\"上道具分类的说法\"";
		System.out.println(stringReplace(string));
	}

}

