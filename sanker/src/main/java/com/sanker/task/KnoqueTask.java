package com.sanker.task;

import org.springframework.stereotype.Service;

import com.sanker.service.core.BaseService;

/**
 * 定时任务
 * @author 滕洁
 * @date 2017-2-1
 */
@Service
public class KnoqueTask extends BaseService{
	
	/**
	 * 每周清除点赞状态
	 */
	public void clearLikeFlag(){
		
		this.getSession().createSQLQuery("update player_relation set likeFlag = 0").executeUpdate();
		System.out.println("clearLikeFlag -- !");
		
	}
	
	/**
	 * 每周清除积分
	 */
	public void clearFriendScore(){
		
		this.getSession().createSQLQuery("UPDATE player SET score = 0").executeUpdate();
		System.out.println("clearFriendScore -- !");
		
	}

}
