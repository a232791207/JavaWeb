package com.sanker.service.template;

import java.util.List;

import com.sanker.entity.template.Template;
import com.sanker.service.core.BaseService;

/**
 * 模板Service
 * @author 滕洁
 * @date 2016-11-17
 */
public class TemplateService extends BaseService{

	/**
	 * 根据玩家Id获取模板
	 */
	@SuppressWarnings("unchecked")
	public List<Template> getTemplateListByPlayerId(String playerId){
		
		try {
			return (List<Template>) this.getSession()
					.createQuery("form Template t where t.playerId = ? order by t.useNum desc")
					.setString(0, playerId).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 创建模板
	 */
	public Template addTemplate(Template entity){
		
		try {
			this.getSession().save(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 根据模板Id查询模板
	 */
	public Template getTemplateById(String templateId){
		
		try {
			return (Template) this.getSession().get(Template.class, templateId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
