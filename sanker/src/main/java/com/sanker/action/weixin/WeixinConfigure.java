package com.sanker.action.weixin;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.codec.binary.Hex;

import com.sanker.action.DefaultAction;
import com.sanker.entity.player.Player;
import com.sanker.service.player.PlayerService;
import com.sanker.weiXin.entity.SNSUserInfo;
import com.sanker.weiXin.entity.WeixinOauth2Token;
import com.sanker.weiXin.util.AdvancedUtil;
import com.sanker.weiXin.util.HeadImgUtil;

/**
 * 微信登录
 * @author 滕洁
 * @date 2016-12-29
 */
public class WeixinConfigure extends DefaultAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * weixinToken
	 */
	public static final String token = "1fb4d307d06faa67cb4df6577bad507f";
	
	private PlayerService playerService;
	
	
	public PlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;
	}
	
	private String preURL;  
	  
	public String getPreURL() {  
	        return preURL;  
	}

	/**
	 * 微信配置
	 */
	public void getWeixinConfigure(){
		System.out.println(getRequest().getRequestURL()+"      xxx  url");
		
		 try {  
	            // 开发者提交信息后，微信服务器将发送GET请求到填写的服务器地址URL上，GET请求携带参数  
	            String signature = getString("signature");// 微信加密签名（token、timestamp、nonce。）  
	            String timestamp = getString("timestamp");// 时间戳  
	            String nonce = getString("nonce");// 随机数  
	            String echostr = getString("echostr");// 随机字符串  
	            // 将token、timestamp、nonce三个参数进行字典序排序  
	            String[] params = new String[] { WeixinConfigure.token, timestamp, nonce };  
	            Arrays.sort(params);
	            // 将三个参数字符串拼接成一个字符串进行sha1加密  
	            String clearText = params[0] + params[1] + params[2];  
	            String algorithm = "SHA-1";  
	            String sign = new String(  
	                    Hex.encodeHex(MessageDigest.getInstance(algorithm).digest((clearText).getBytes())));  
	            // 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信  
	            if (signature.equals(sign)) {  
	            	System.out.println(echostr);
	                Write(echostr);
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	}
	
	/**
	 * 登录
	 */
	public String login(){

        // 用户同意授权后，能获取到code
        String code = getString("code");
        System.out.println("code          "+code);
        
        String state = getString("state");
        System.out.println("statie          "+state);
        
        // 用户同意授权
        if (!"authdeny".equals(code)) {
            // 获取网页授权access_token
            WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(code);
            // 网页授权接口访问凭证
            String accessToken = weixinOauth2Token.getAccessToken();
            // 用户标识
            String openId = weixinOauth2Token.getOpenId();

            System.out.println(openId+" opendId");
            String playerId = "";
            if(openId!=null&&!openId.equals("")){
            	//和数据库中做比较
            	playerId = playerService.getPlayerIdByWeixinCode(openId);
            	if(playerId!=null&&!playerId.equals("")){
            		//如果存在该账户
            		System.out.println("该玩家信息已保存！");
            	}else{//不存在该账户，新建信息

    	            // 获取用户信息
    	            SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
            		
            		
            		Player player = new Player();
            		player.setWeiXinCode(snsUserInfo.getOpenId());
            		player.setWeiXinNickName(snsUserInfo.getNickname());
            		player.setCity(snsUserInfo.getCity());
            		player.setSex(snsUserInfo.getSex()==2?'女':'男');
            		player.setHeadImgUrl(HeadImgUtil.getImgFromUrl(snsUserInfo.getHeadImgUrl()));
            		player.setLevel("初出茅庐");
            		/*player.setGold(4000);
            		player.setDiamonds(20);*/
            		player.setGold(0);
            		player.setDiamonds(0);
            		player.setCharm(0);
            		player.setContinuityWin(0);
            		player.setScore(0);
            		player.setAchievement(0);
            		player.setLastWeekScore(0);
            		Date date = new Date();
            		player.setRegisterDate(new java.sql.Timestamp(date.getTime()));
            		this.playerService.addPlayer(player);
            		playerId = player.getId();
            	}
            }
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxgameWeb");
            preURL = "?playerId="+playerId;
            return "gameWeb";
        }
        return "error1";
	}
	
	
	/**
	 * 充值
	 */

}
