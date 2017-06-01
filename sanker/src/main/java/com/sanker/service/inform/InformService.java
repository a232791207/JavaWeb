package com.sanker.service.inform;

import java.util.List;

import com.sanker.entity.inform.Inform;
import com.sanker.service.core.BaseService;

/**
 * 通知
 * @author 滕洁
 * @date 2016-11-17
 */
public class InformService extends BaseService{
	
	/**
	 * 新建
	 */
	public Inform addInform(Inform entity){
		
		try {
			this.getSession().save(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 根据类型查询
	 */
	@SuppressWarnings("unchecked")
	public List<Inform> getInformListByType(String type){
		
		try {
			return (List<Inform>) this
					.getSession()
					.createQuery(
							"from Inform i where i.type = ? and i.enableType = ? order by i.informTime desc")
					.setString(0, type).setBoolean(1, true).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 根据Id查询通知
	 */
	public Inform getInformByInformId(String id){
		
		try {
			return (Inform) this.getSession().get(Inform.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
