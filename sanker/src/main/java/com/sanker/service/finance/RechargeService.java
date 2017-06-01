package com.sanker.service.finance;

import java.util.List;

import com.sanker.entity.finance.Recharge;
import com.sanker.service.core.BaseService;

/**
 * 充值记录service
 * @author 滕洁
 * @date 2016-11-17
 */
public class RechargeService extends BaseService{
	
	/**
	 * 添加充值记录
	 */
	public Recharge addRecharge(Recharge entity){
		
		this.getSession().save(entity);
		return entity;
	}
	
	/**
	 * 根据玩家Id查询充值记录
	 */
	@SuppressWarnings("unchecked")
	public List<Recharge> getRechargeListByPlayerId(String playerId){
		
		try {
			return (List<Recharge>) this
					.getSession()
					.createQuery(
							"from Recharge r where r.playerId = ? order by r.rechargeTime desc").list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 充值金额排名
	 */
	@SuppressWarnings("unchecked")
	public List<String> getPlayerIdListByMoney(){
		
		try {
			return this
					.getSession()
					.createSQLQuery(
							"SELECT playerId  FROM recharge GROUP BY playerId ORDER BY SUM(moneySum) DESC").list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	


}
