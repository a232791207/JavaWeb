package com.sanker.service.webService;

import java.io.IOException;
import java.io.Writer;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.sanker.entity.login.UserInfo;
import com.sanker.service.core.BaseService;
import com.sanker.service.login.UserInfoService;
import com.sanker.service.utils.JSONHelper;
import com.sanker.service.core.json.JSONException;
import com.sanker.service.core.json.JSONObject;
/**
 * 登录用户WebService
 * @author 滕洁
 * @date 2016-10-30
 */
@WebService
public class UserInfoWebService extends BaseService{

	@SuppressWarnings("unused")
	private UserInfoService userInfoService;

	public UserInfoWebService(){}
	
	public UserInfoWebService(UserInfoService userInfoService){
		
		this.userInfoService = userInfoService;
		
	}
	/**
	 * 新增登录用户
	 * @param condition
	 * @return
	 */
	@WebMethod
	public void addUser(@WebParam(name = "condition")String condition){
		
		JSONObject obj;
		UserInfo entity = new UserInfo();
		
		try {
			obj = new JSONObject(condition);
			entity.setUserName(obj.getString("userName"));
			entity.setLoginName(obj.getString("loginName"));
			entity.setLoginPwd(obj.getString("loginPwd"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setCharacterEncoding("UTF-8");
		res.setHeader("Cache-Control", "no-cache");
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setContentType("text/html");
		Writer writer;
		try {
			writer = res.getWriter();
			writer.write(JSONHelper.toJson(entity));
			writer.flush();
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
