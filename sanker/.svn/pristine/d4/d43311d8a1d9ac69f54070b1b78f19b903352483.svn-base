package com.sanker.action.template;

import com.sanker.action.DefaultAction;
import com.sanker.service.template.TemplateService;
import com.sanker.service.utils.JSONHelper;

/**
 * 模板Action
 * @author 滕洁
 * @date 2016-11-22
 */
public class TemplateAction extends DefaultAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TemplateService templateService;

	public TemplateService getTemplateService() {
		return templateService;
	}

	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}
	
	/**
	 * 根据playerId获取模板
	 */
	public void getTemplateListByPlayer(){
		String playerId = getString("playerId");
		String json = JSONHelper.toJson(this.templateService.getTemplateListByPlayerId(playerId));
		System.out.println(json);
		Write(json);
	}

}
