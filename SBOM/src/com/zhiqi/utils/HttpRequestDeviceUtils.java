package com.zhiqi.utils;

import javax.servlet.http.HttpServletRequest;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public class HttpRequestDeviceUtils {
	/** Wap网关Via头信息中特有的描述信息 */
	private static String[] mobileGateWayHeaders = new String[]{ "ZXWAP", "chinamobile.com", "monternet.com", "infoX",
			"XMS 724Solutions HTG", "wap.lizongbo.com", "Bytemobile" };
	/** 电脑上的IE或Firefox浏览器等的User-Agent关键词 */
	private static String[] pcHeaders = new String[]{ "Windows ME", "Windows 2000", "Windows XP", "Windows NT",
			"Ubuntu" };
	/** 手机浏览器的User-Agent里的关键词 */
	private static String[] mobileUserAgents = new String[]{ "Mobile","AppleWebKit","Android","iPhone","Mac","iPad","Windows Phone"};

	/**
	    * 根据当前请求的特征，判断该请求是否来自手机终端，主要检测特殊的头信息，以及user-Agent这个header
	    * @param request http请求
	    * @return 如果命中手机特征规则，则返回对应的特征字符串
	    */
    public static boolean isMobileDevice(HttpServletRequest request){
    	String userAgent = request.getHeader("user-agent");
    	System.out.println(userAgent);
    	for(int i = 0 ; i < mobileUserAgents.length ; i++){
    		 if(userAgent.contains(mobileUserAgents[i])) return true;	
    	}
    	return false;
    }
}
