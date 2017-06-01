package com.sanker.service.game;

import java.util.List;

import com.sanker.entity.game.Ledger;
import com.sanker.service.core.BaseService;

/**
 * 流水记录
 * 
 * @author 滕洁
 * @date 2016-11-26
 */
public class LedgerService extends BaseService {

	/**
	 * 新增
	 */
	public Ledger addLedger(Ledger entity) {
		try {
			this.getSession().save(entity);

			return entity;
			// 修改玩家本身积分

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据玩家ID查询流水记录
	 */
	@SuppressWarnings("unchecked")
	public List<Ledger> getListByPlayer(String playerId) {

		try {
			return this.getSession().createQuery("from Ledger l where l.playerId = ?").setString(0, playerId).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 根据玩家和对局数查询流水
	 */
	@SuppressWarnings("unchecked")
	public List<Ledger> getListByPlayer(String playerId, Integer gameNum) {
		try {
			return this.getSession().createQuery("from Ledger l where l.playerId = ? and l.gameNum = ?").setString(0, playerId).setInteger(1, gameNum).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据用户编号获取流水总计
	 */
	public Integer getLedgerSumByPlayer(String playerId) {

		try {
			Object s = this.getSession().createSQLQuery("SELECT SUM(ledgerScore) FROM  ledger WHERE playerId = ? limit 1").setString(0, playerId).uniqueResult();
			if (s != null) {
				return Integer.valueOf(s.toString());
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 根据用户编号和当前对局数查询流水总计
	 */
	public Integer getLedgerSumByPlayer(String playerId, Integer gameNum) {

		try {
			return (Integer) this.getSession().createSQLQuery("SELECT SUM(ledgerScore) FROM  ledger WHERE playerId = ? and gameNum = ? limit 1").setString(0, playerId)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 删除流水记录
	 */
	public boolean deleteLedger(String playerId) {

		try {
			this.getSession().createQuery("delete from Ledger l where l.playerId = ?").setString(0, playerId).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 判断玩家是否赢
	 */
	public boolean isWin(String playerId, Integer gameNum) {
		System.out.println("ledgerService _ isWin ?  playerId - " + playerId + "  gameNum - " + gameNum);
		Object scoreStr = "";
		if (gameNum > 0) {

			scoreStr = this.getSession().createSQLQuery("SELECT SUM(ledgerScore) FROM ledger WHERE playerId = ? AND gameNum = ?").setString(0, playerId).setInteger(1, gameNum)
					.uniqueResult();
		} else {

			scoreStr = this.getSession().createSQLQuery("SELECT SUM(ledgerScore) FROM ledger WHERE playerId = ?").setString(0, playerId).uniqueResult();
		}
		if (scoreStr == null || Integer.valueOf(scoreStr.toString()) >= 0) {
			return true;
		}

		return false;
	}
	
	/**
	 * 获取玩家刮风/下雨详情
	 */
	@SuppressWarnings("unchecked")
	public List<Ledger> getFyList(String playerId,Integer gameNum,String target){
		String sql = "SELECT * FROM ledger WHERE (ledgerName LIKE '%刮风' OR  ledgerName LIKE '%下雨') and ledgerType = '1' and target = '"+target+"' AND playerId = '"+playerId+"'";
		
		if(gameNum>0){
			sql += " and gameNum = "+gameNum+"";
		}
		
		return this.getSession().createSQLQuery(sql).addEntity(Ledger.class).list();
	}
	
	/**
	 * 根据roomId 删除流水
	 */
	public void deleteLedger_roomId(String roomId){
		
		this.getSession().createSQLQuery("delete from ledger where roomId = ?").setString(0, roomId).executeUpdate();
	}
}