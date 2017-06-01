package com.sanker.service.game;


import com.sanker.entity.game.Round;
import com.sanker.service.core.BaseService;

/**
 * 回合  一盘
 * @author 滕洁
 * @date 2016-11-17
 */
public class RoundService extends BaseService{
	
	/**
	 * 创建
	 */
	public Round addRound(Round entity){
		try {
			this.getSession().save(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 修改
	 */
	public Round updateRound(Round entity){
		
		try {
			this.getSession().update(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 根据玩家Id查询
	 */
	public Round getInfoByPlayer(String playerId,String roomId){
		
		try {
			return (Round) this
					.getSession()
					.createQuery(
							"from Round r where r.playerId = ? and r.roomId = ? limit 1")
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}