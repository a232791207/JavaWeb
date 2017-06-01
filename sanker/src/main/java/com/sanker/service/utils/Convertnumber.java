package com.sanker.service.utils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;


/**
 * 小写金额转换为大写金额
 * @author Administrator
 *
 */
public class Convertnumber { // extends JRDefaultScriptlet
	// 构造函数
	public Convertnumber() {}

	// 传入数字小写，返回大写汉字
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static String convert(String number) {
		int num = 0;
		String numStr = null;
		ArrayList aList = new ArrayList();
		ArrayList bList = new ArrayList();

		int longNum = number.length();
		bList.add("零");
		bList.add("壹");
		bList.add("贰");
		bList.add("叁");
		bList.add("肆");
		bList.add("伍");
		bList.add("陆");
		bList.add("柒");
		bList.add("捌");
		bList.add("玖");
		bList.add("万");
		bList.add("仟");
		bList.add("佰");
		bList.add("拾");
		bList.add("元");
		bList.add("整");
		for (int i = 0; i < longNum; i++) {
			numStr = number.substring(i, i + 1);
			num = Integer.parseInt(numStr);
			numStr = (String) bList.get(num);
			aList.add(numStr);
			if (num != 0) {
				num = number.substring(i).length();
				if (num == 5) {
					aList.add(bList.get(10));
				}
				if (num == 4) {
					aList.add(bList.get(11));
				}
				if (num == 3) {
					aList.add(bList.get(12));
				}
				if (num == 2) {
					aList.add(bList.get(13));
				}
			}
		}

		longNum = aList.size();
		for (int i = longNum - 1; i >= 0; i--) {
			if (aList.get(i).equals(bList.get(0))) {
				aList.remove(i);
			} else {
				break;
			}
		}

		longNum = aList.size();
		for (int i = 0; i < longNum - 2; i++) {
			if (aList.get(i).equals(bList.get(0))
					&& aList.get(i + 1).equals(bList.get(0))
					&& aList.get(i + 2).equals(bList.get(0))) {
				aList.remove(i);
				aList.remove(i + 1);
				i++;
			}
		}

		longNum = aList.size();
		for (int i = 0; i < longNum - 1; i++) {
			if (aList.get(i).equals(bList.get(0))
					&& aList.get(i + 1).equals(bList.get(0))) {
				aList.remove(i);
			}
		}

		longNum = aList.size();
		numStr = "";
		for (int i = 0; i < longNum; i++) {
			numStr += (String) aList.get(i);
		}

		return numStr;
	}

	// 转换小数部分
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String convertSmlnumber(String number) {
		ArrayList cList = new ArrayList();
		ArrayList bList = new ArrayList();
		bList.add("零");
		bList.add("壹");
		bList.add("贰");
		bList.add("叁");
		bList.add("肆");
		bList.add("伍");
		bList.add("陆");
		bList.add("柒");
		bList.add("捌");
		bList.add("玖");

		cList.add("元");
		cList.add("角");
		cList.add("分");
		cList.add("厘");

		String strnum = "";
		String rtnstr = "";
		int num = 0;
		if (number.length() > 3) {
			strnum = number.substring(0, 3);
		} else {
			strnum = number;
		}
		for (int i = 0; i < strnum.length(); i++) {
			if (i == 0) {
				rtnstr += (String) cList.get(i);
			}
			num = Integer.parseInt(strnum.substring(i, i + 1));
			rtnstr += (String) bList.get(num);
			if (num != 0) {
				rtnstr += (String) cList.get(i + 1);
			}
		}
		// 去掉末尾联系的"零"
		while (rtnstr.substring(rtnstr.length() - 1, rtnstr.length()).equals(
				bList.get(0))) {
			rtnstr = rtnstr.substring(0, rtnstr.length() - 1);
		}
		// 如果中间两个联系为零,则去掉一个
		for (int m = 0; m < rtnstr.length() - 1; m++) {
			if (rtnstr.substring(m, m + 1).equals(
					rtnstr.substring(m + 1, m + 2))) {
				rtnstr = rtnstr.substring(0, m)
						+ rtnstr.substring(m + 1, rtnstr.length());
			}
		}
		return rtnstr;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String convertNumber(String number) {
		String numberStr = "";
		boolean flag = false;
		ArrayList bList = new ArrayList();
		bList.add("元整");
		bList.add("万");
		bList.add("亿");

		// 判断是否为浮点数，如果是则“见角进一”
		int num = number.indexOf(".");
		String substr = "";
		if (num != -1) {
			substr = number.substring(num + 1, number.length());
			flag = true;
			number = "" + Integer.parseInt(number.substring(0, num));
		}

		num = number.length();
		ArrayList aList = new ArrayList();
		while (num > 0) {
			if (num >= 4) {
				aList.add(number.substring(num - 4, num));
			}
			if (num < 4 && num > 0) {
				aList.add(number.substring(0, num));
			}
			num = num - 4;
		}
		num = aList.size();
		while (num > 0) {
			numberStr += Convertnumber.convert(aList.get(num - 1).toString())
					+ bList.get(num - 1);
			num--;
		}
		// 处理浮点数
		if (flag == true && Integer.parseInt(substr) != 0) {
			numberStr = numberStr.substring(0, numberStr.length() - 2)
					+ convertSmlnumber(substr);
		}
		return numberStr;
	}
	
	/**
	 * 格式化金额
	 * @param total
	 * @return
	 */
	public static String numberFormat(float total){
		String t = null;
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(1);
		nf.setGroupingUsed(false);
		Currency c = nf.getCurrency();
		System.out.println(c.getCurrencyCode());
		System.out.println(c.getDefaultFractionDigits());
		System.out.println(c.getSymbol());
		t = nf.format(total);
		return t;
	}
	
	/**
	 * 格式化金额
	 * @param total
	 * @return
	 */
	public static Float numberFormatToFloat(float total){
		String t = null;
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		nf.setMaximumFractionDigits(2);
		nf.setGroupingUsed(false);
		Currency c = nf.getCurrency();
		System.out.println(c.getCurrencyCode());
		System.out.println(c.getDefaultFractionDigits());
		System.out.println(c.getSymbol());
		t = nf.format(total);
		t = t!=null?t.substring(1):"0";
		return Float.valueOf(t);
	}
	
	/**
	 * 格式化金额
	 * @param total
	 * @return
	 */
	public static String formatNumber(String total){
		if(total == null)
			total = "0";
		float f = Float.parseFloat(total);
		String t = null;
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		nf.setGroupingUsed(false);
		Currency c = nf.getCurrency();
		System.out.println(c.getCurrencyCode());
		System.out.println(c.getDefaultFractionDigits());
		System.out.println(c.getSymbol());
		t = nf.format(f);
		t = t!=null?t.substring(1):"0";
		return t;
	}
	
	/**
	 * 验证字符串是否是数字
	 * @param str
	 * @param type：类型，“I”表示int，“L”表示long，“F”表示float，“D”表示double
	 * @return
	 */
	public static boolean isNumeric(String str, String type){  
		boolean b = false;
//		Pattern pattern = Pattern.compile("[1-9][0-9]*");
		try {
			if("I".equals(type)){
				Integer.parseInt(str);
				b = true;
			}else if("L".equals(type)){
				Long.parseLong(str);
				b = true;
			}else if("F".equals(type)){
				Float.parseFloat(str);
				b = true;
			}else if("D".equals(type)){
				Double.parseDouble(str);
				b = true;
			}
		} catch (NumberFormatException e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
			b = false;
//			Logs.messageToLogFile(ServersUtil.class, e);
		}
		return b;
	}
	
	/**
	 * 生成项目编号
	 * @param num
	 * @return
	 */
	public static String generateNO(int num){
		Calendar ca = Calendar.getInstance();
		
//		String number = String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS-%2$04d", ca.getTime(), num);
		String number = String.format("%1$ty-%2$04d", ca.getTime(), num);
		ca = null;
		return number;
	}
	
	/**
	 * 格式化时间 - 使用的是String.format()
	 * @param pattern
	 * @return
	 */
	public static String generateDateToString(String pattern){
		if(pattern != null){
			Calendar ca = Calendar.getInstance();
			return String.format(pattern, ca.getTime());
		}else{
			return "";
		}
	}
	
	public static String redenerateNO(Integer i){
		String number = String.format("%1$-4d", i);
		return number;
	}
	
	public static void main(String[] args) {
		/*Convertnumber cnum = new Convertnumber();
		String strnum = "45.56";
		String str = cnum.convertNumber(strnum);
		System.out.println(str);*/
		
//		float f = 32423.495f;
//		System.out.println(numberFormat(f).substring(1));
		
//		System.out.println(isNumeric("323.32", "L"));
		System.out.println(generateNO(32));
//		System.out.println(redenerateNO(Integer.parseInt("21-0032".substring("21-0032".lastIndexOf("-")+1))));
	}
}
