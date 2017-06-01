package com.sanker.service.game;

import com.sanker.entity.game.GameAreaNum;
import com.sanker.service.core.BaseService;

/**
 * 地域次数
 * @author 滕洁
 * @date 2016-12-25
 */
public class GameAreaNumService extends BaseService{
	
	/**
	 * 新增
	 * @param entity
	 */
	public void addEntity(GameAreaNum entity){
		this.getSession().save(entity);
		
	}
	
	/**
	 * 根据玩家Id查询创建房间次数
	 * @return
	 */
	public GameAreaNum getEntityById(String playerId){
		
		return (GameAreaNum) this.getSession().createQuery("from GameAreaNum g where g.playerId = ? limit 1").setString(0, playerId).uniqueResult();
		
	}
	
	/**
	 * 根据地域新增
	 */
	public void addNumByGameArea(String gameArea,String playerId){
		
		this.getSession().createSQLQuery("UPDATE player_gamearea SET "+gameArea+" = " +
				"(SELECT * FROM  (SELECT "+gameArea+" FROM player_gamearea WHERE playerId = '"+playerId+"') " +
						"AS g)+1 WHERE playerId = '"+playerId+"'").executeUpdate();
		this.getSession().createSQLQuery("UPDATE player_gamearea SET lastArea = '"+gameArea+"' where playerId = '"+playerId+"'").executeUpdate();
		
	}
	
	

}
