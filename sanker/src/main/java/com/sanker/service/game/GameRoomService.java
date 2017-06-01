package com.sanker.service.game;

import java.util.Date;
import java.util.List;

import org.hibernate.NonUniqueResultException;

import com.sanker.entity.game.GameRoom;
import com.sanker.param.Param;
import com.sanker.service.core.BaseService;

/**
 * 游戏房间Service
 * 
 * @author 滕洁
 * @date 2016-11-17
 */
public class GameRoomService extends BaseService {

	/**
	 * 新建
	 */
	public GameRoom addGameRoom(GameRoom entity) {

		try {
			this.getSession().save(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据玩家Id查询
	 */
	@SuppressWarnings("unchecked")
	public List<GameRoom> getRoomListByPlayer(String playerId) {
		try {
			return this.getSession().createQuery("from GameRoom g where g.player = ? order by g.createTime desc").setString(0, playerId).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据Id查询房间
	 */
	public GameRoom getGameRoomById(String roomId) {

		try {
			return   (GameRoom) this.getSession().createSQLQuery("SELECT * FROM gameroom WHERE id = '"+roomId+"' ").addEntity(GameRoom.class).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 根据房间Id查询房间人数
	 */
	public Integer getPlayerNumById(String roomId) {

		try {
			return (Integer) this.getSession().createSQLQuery("select playerNum from gameRoom where id = ? limit 1").setString(0, roomId).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 
	* @param type
	 *            （0:好友，1:金币，2:竞技）
	 * @param gameType
	 *            （0:血战、1:血流、2：倒到胡）
	 * @param houseOwnerId 房主
	 * @param gameNum 游戏局数
	 * @param playerNum 游戏人数
	 * @param gameArea  区域
	 * @param score 
	 * @param rule  规则
	 * @param topMultiple 翻数
	 * @return
	 */
	public GameRoom createRoom(Integer type, String houseOwnerId, Integer gameType, Integer gameNum, Integer playerNum, String gameArea, Integer score, String rule,
			Integer topMultiple) {

		GameRoom entity = new GameRoom();
		entity.setType(type);
		entity.setGameType(gameType);
		entity.setGameArea(gameArea);
		entity.setPlayerNum(playerNum);

		entity.setGameNum(gameNum);
		entity.setBaseScore(score);
		entity.setTopMultiple(topMultiple);

		/**
		 * 缺
		 */
		if (gameArea.equals("chengDu") || gameArea.equals("yiBin") || gameArea.equals("luZhou")
				|| gameArea.equals("deYang")) {
			
			rule += Param.QUE_H;
		}

		/**
		 * 摆
		 */
		if (gameArea.equals("langZhong") || gameArea.equals("xiChang")) {

			rule += Param.BAI_H;
		}

		/**
		 * 听
		 */
		if (gameArea.equals("langZhong") || gameArea.equals("ziGong") || gameArea.equals("xiChang") ||gameArea.equals("wanZhou")) {
			
			rule += Param.TING_H;
		}

		/**
		 * 飘
		 */
		/*if (gameArea.equals("") || gameArea.equals("")) {
			rule += Param.PIAO_H;
		}*/

		/**
		 * 刮风下雨
		 */
		if (gameArea.equals("chengDu") || gameArea.equals("xiChang") || 
				gameArea.equals("neiJiang") || gameArea.equals("guangAn")||
				gameArea.equals("wanZhou")) {
			
			rule += Param.FY_H;
		}

		entity.setRule(rule);
		entity.setHouseOwnerId(houseOwnerId);

		Date date = new Date();
		entity.setCreateTime(new java.sql.Timestamp(date.getTime()));

		try {
			
			//this.getSession().save(entity);
			return this.addGameRoom(entity);
			 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 创建玩家和房间关系
	 */
	public boolean createRelation(String playerId, String roomId) {

		try {
			this.getSession().createSQLQuery("insert into player_room_relation values(null,?,?)").setString(0, playerId).setString(1, roomId).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 对局结束后，根据房间ID，删除关系(正常结束)
	 */
	public boolean deleteRelationByRoomId(String roomId) {

		try {
			this.getSession().createSQLQuery("delete from player_room_relation where roomId = ?").setString(0, roomId).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 根据玩家Id和房间Id删除关系
	 */
	public boolean deleteRelationByPlayerId(String playerId, String roomId) {

		try {
			this.getSession().createSQLQuery("delete from player_room_relation where roomId = ? and playerId = ?").setString(0, roomId).setString(1, playerId).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	/**
	 * 根据玩家Id和房间Id删除关系
	 */
	public boolean deleteRelationByPlayerId(String playerId) {

		try {
			this.getSession().createSQLQuery("delete from player_room_relation where playerId = ?").setString(0, playerId).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 判断玩家是否存在于对局中
	 */
	public String isHavaRelation(String playerId) {

		try {
			String result = (String) this.getSession().createSQLQuery("select roomId from player_room_relation where playerId = ?").setString(0, playerId).uniqueResult();
			return result;
		}catch(NonUniqueResultException e){
			this.deleteRelationByPlayerId(playerId);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 删除房间
	 */
	public void deleteGameRoomById(String roomId) {

		this.getSession().createSQLQuery("DELETE FROM gameRoom WHERE id = ? LIMIT 1").setString(0, roomId).executeUpdate();
	}

}