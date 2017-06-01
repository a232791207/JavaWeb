package com.sanker.entity.player;

import java.io.Serializable;
import java.sql.Timestamp;

import com.google.gson.annotations.Expose;

/**
 * 玩家
 * @author 滕洁
 * @date 2016-11-16
 */
public class Player implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	private String id;
	@Expose
	private Integer playerId;
	@Expose
	private String weiXinCode;
	@Expose
	private String weiXinNickName;
	@Expose
	private char sex;
	@Expose
	private String phone;
	@Expose
	private Integer diamonds;
	@Expose
	private Integer gold;
	@Expose
	private Integer charm;//魅力值
	@Expose
	private String level;//等级
	@Expose
	private Integer status;//0:离线 1:在线 2:游戏中
	@Expose
	private String headImgUrl;//头像地址 在本地
	@Expose
	private String city;//城市
	@Expose
	private Integer continuityWin;//连胜
	@Expose
	private String ipAddress;
	@Expose
	private Integer score;//好友房积分
	@Expose
	private Integer lastWeekScore;//上周好友房积分
	@Expose
	private Integer achievement;//成就
	@Expose
	private Integer likeFlag;//点赞状态
	@Expose
	private Timestamp registerDate;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public String getWeiXinCode() {
		return weiXinCode;
	}

	public void setWeiXinCode(String weiXinCode) {
		this.weiXinCode = weiXinCode;
	}

	public String getWeiXinNickName() {
		return weiXinNickName;
	}

	public void setWeiXinNickName(String weiXinNickName) {
		this.weiXinNickName = weiXinNickName;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getDiamonds() {
		return diamonds;
	}

	public void setDiamonds(Integer diamonds) {
		this.diamonds = diamonds;
	}

	public Integer getGold() {
		return gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}

	public Integer getCharm() {
		return charm;
	}

	public void setCharm(Integer charm) {
		this.charm = charm;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getContinuityWin() {
		return continuityWin;
	}

	public void setContinuityWin(Integer continuityWin) {
		this.continuityWin = continuityWin;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getLastWeekScore() {
		return lastWeekScore;
	}

	public void setLastWeekScore(Integer lastWeekScore) {
		this.lastWeekScore = lastWeekScore;
	}

	public Integer getAchievement() {
		return achievement;
	}

	public void setAchievement(Integer achievement) {
		this.achievement = achievement;
	}

	public Integer getLikeFlag() {
		return likeFlag;
	}

	public void setLikeFlag(Integer likeFlag) {
		this.likeFlag = likeFlag;
	}

	public Timestamp getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	
}
