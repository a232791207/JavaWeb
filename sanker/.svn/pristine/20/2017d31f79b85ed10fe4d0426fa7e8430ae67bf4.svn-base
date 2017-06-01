package com.sanker.service.player;

import java.util.List;

import com.sanker.entity.player.PlayerRelation;
import com.sanker.service.core.BaseService;

/**
 * 好友
 * @author 滕洁
 * @date 2016-11-21
 */
public class PlayerRelationService extends BaseService{
	
	/**
	 * 创建
	 */
	public boolean addRelation(PlayerRelation entity){
		try {
			this.getSession().save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 查询
	 */
	@SuppressWarnings("unchecked")
	public List<PlayerRelation> getRelationListByPlayerId(String playerId){
		
		try {
			return this.getSession()
					.createQuery("from player_relation p where p.playerId = ?")
					.setString(0, playerId).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 查询两个玩家是否是好友，如果不是添加好友
	 */
	@SuppressWarnings("unchecked")
	public void addRelationByPlayerId(String player1,String player2){
		List<PlayerRelation> listP1 = this.getSession().createQuery(" from PlayerRelation p where p.playerId = ? and p.friendId = ?").
		setString(0, player1).setString(1, player2).list();
		if(listP1==null||listP1.size()==0){
			PlayerRelation re1 = new PlayerRelation();
			re1.setPlayerId(player1);
			re1.setFriendId(player2);
			re1.setLikeFlag(0);
			this.addRelation(re1);
		}
		List<PlayerRelation> listP2 = this.getSession().createQuery(" from PlayerRelation p where p.playerId = ? and p.friendId = ?").
		setString(0, player2).setString(1, player1).list();
		if(listP2==null||listP2.size()==0){
			PlayerRelation re2 = new PlayerRelation();
			re2.setPlayerId(player2);
			re2.setFriendId(player1);
			re2.setLikeFlag(0);
			this.addRelation(re2);
		}
		
	}
	/**
	 * 修改状态
	 */
	public void updateSendLikeFlag(String playerId,String friendId){
		
		this.getSession().createSQLQuery("update player_relation set likeFlag = 1 where playerId = ? and friendId = ? ").setString(0,playerId).
		setString(1, friendId).executeUpdate();

	}

}
