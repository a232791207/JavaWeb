package com.zhiqi.action.agent;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.utils.HttpRequestDeviceUtils;


public class judgeRequestAction{

//private HttpServletRequest request;

 
 public String judge(){
	 HttpServletRequest request = ServletActionContext.getRequest();
	 if(HttpRequestDeviceUtils.isMobileDevice(request)){
		 
		 return "yi";
	 }else{
		 return "pc";
	 }


 }


/*public void setRequest(HttpServletRequest request) {
	this.request = request;
}


public HttpServletRequest getRequest() {
	return request;
}*/


//
//@Override
//public void setRequest(Map request) {
//	this.request=request;
//}
// 
 
 
 
}
