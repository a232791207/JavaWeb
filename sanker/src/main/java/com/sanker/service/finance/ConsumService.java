package com.sanker.service.finance;

import java.util.List;

import com.sanker.entity.finance.Consume;
import com.sanker.service.core.BaseService;

/**
 * 消费记录Service
 * @author 滕洁
 * @date 2016-11-17
 */
public class ConsumService extends BaseService{
	
	/**
	 * 添加消费记录
	 */
	public Consume addConsume(Consume entity){
		
		try {
			this.getSession().save(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 根据玩家id查询消费情况
	 */
	@SuppressWarnings("unchecked")
	public List<Consume> getConsumeListByPlayerId(String playerId){
		
		try {
			return this
					.getSession()
					.createQuery(
							"from Consume c where c.playerId = ? order by c.consumeTime desc")
					.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
