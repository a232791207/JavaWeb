package com.sanker.service.gift;

import java.util.List;

import com.sanker.entity.gift.GiftGiving;
import com.sanker.service.core.BaseService;

/**
 * 礼物赠送
 * @author 滕洁
 * @date 2016-11-18
 */
public class GiftGivingService extends BaseService{
	
	/**
	 * 新增
	 */
	public boolean addGiftGiving(GiftGiving entity){
		try {
			this.getSession().save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 根据玩家Id查询送礼记录
	 */
	@SuppressWarnings("unchecked")
	public List<GiftGiving> getGiftListBySender(String player){
		
		try {
			return this
					.getSession()
					.createQuery(
							"from GiftGiving g where g.senderId = ? order by givingTime desc")
					.setString(0, player).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 根据玩家编号查询玩家收礼记录
	 */
	@SuppressWarnings("unchecked")
	public List<GiftGiving> getGiftListByReceiver(String playerId){
		try {
			return this
					.getSession()
					.createQuery(
							"from GiftGiving g where g.receiverId = ? order by givingTime desc")
					.setString(0, playerId).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 送礼榜
	 */
	
	/**
	 * 收礼榜
	 */

}
