package com.sanker.action.inform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sanker.action.DefaultAction;
import com.sanker.entity.inform.Mail;
import com.sanker.service.inform.MailService;
import com.sanker.service.utils.JSONHelper;

/**
 * 邮件
 * @author 滕洁
 * @date 2016-11-22
 */
public class MailAction extends DefaultAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MailService mailService;

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	
	/**
	 * 获取未读邮件
	 */
	public void getMailList(){
		String playerId = getString("playerId");
		List<Mail> mailList = this.mailService.getMailByReceiverId(playerId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", "list");
		map.put("value", mailList);
		String json = JSONHelper.toJson(map,"yyyy-MM-dd");
		System.out.println(json);
		Write(json);
	}
	
	/**
	 * 修改邮件状态
	 */
	public void updateMailStatus(){
		String mailId = getString("mailId");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", "update");
		if(this.mailService.updateMail(mailId)){
			map.put("value", "t");
			String json = JSONHelper.toJson(map);
			Write(json);
		}else{
			map.put("value", "f");
			String json = JSONHelper.toJson(map);
			Write(json);
		}
	}

}
