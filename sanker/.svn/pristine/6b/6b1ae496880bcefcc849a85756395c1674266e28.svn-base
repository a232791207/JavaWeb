package com.sanker.service.game;

import java.util.List;

import com.sanker.entity.game.GameRecord;
import com.sanker.service.core.BaseService;

/**
 * 每局战绩详情
 * @author 滕洁
 * @date 2016-11-18
 */
public class GameRecordService extends BaseService{
	
	/**
	 * 创建每局战绩
	 */
	public boolean addGameRecord(GameRecord entity){
		try {
			this.getSession().save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 查询战绩
	 */
	@SuppressWarnings("unchecked")
	public List<GameRecord> getRecordByPlayer(String player,String roomId){
		
		try {
			return (List<GameRecord>) this
					.getSession()
					.createQuery(
							"from GameRecord g where g.playerId = ? and g.roomId = ?").setString(0, player).setString(1,roomId)
					.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 查询最佳牌型和最大番刑
	 */
/*	public GameRecord getBestPaiList(){
		
		String sql = "select paiList,multiple playerId from gamerecord where roomId = ? group by playerId orderBy multiple limit 1";
		
		return null;
	}*/

}
