package com.sanker.weiXin.util;

import java.io.IOException;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sanker.entity.weixin.Access_token;
import com.sanker.entity.weixin.Jsapi_ticket;
import com.sanker.weiXin.entity.SNSUserInfo;
import com.sanker.weiXin.entity.WeixinOauth2Token;

public class AdvancedUtil {
	

	private static String appId = "wx828840fe9ad102ae";
	private static String appsecret = "26d018753a654e284352e597d1a617b1";
	
	
	/**
     * 获取网页授权凭证
     * 
     * @param appId 公众账号的唯一标识
     * @param appSecret 公众账号的密钥
     * @param code
     * @return WeixinAouth2Token
     */
    public static WeixinOauth2Token getOauth2AccessToken(String code) {
        WeixinOauth2Token wat = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appsecret);
        requestUrl = requestUrl.replace("CODE", code);
        // 获取网页授权凭证
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                wat = new WeixinOauth2Token();
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getInt("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                wat = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                System.out.println("获取网页授权凭证失败 errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
            }
        }
        return wat;
    }
    
    /**
     * 通过网页授权获取用户信息
     * 
     * @param accessToken 网页授权接口调用凭证
     * @param openId 用户标识
     * @return SNSUserInfo
     */
    @SuppressWarnings( { "deprecation", "unchecked" })
    public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
        
    	SNSUserInfo snsUserInfo = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        System.out.println("jsonobct "+jsonObject.toString()+"accessToken "+accessToken+"  openId"+openId);
        if (null != jsonObject) {
            try {
                snsUserInfo = new SNSUserInfo();
                // 用户的标识
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                // 昵称
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                // 性别（1是男性，2是女性，0是未知）
                snsUserInfo.setSex(jsonObject.getInt("sex"));
                // 用户所在国家
                snsUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                snsUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                snsUserInfo.setCity(jsonObject.getString("city"));
                // 用户头像
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
                // 用户特权信息
                snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
            } catch (Exception e) {
                snsUserInfo = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                System.out.println("获取用户信息失败 errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
            }
        }
        return snsUserInfo;
    }
    
    /**
     * 获取jssdk access_token
     */
    public static Access_token getAccess_token(){
    	
    	 // 拼接请求地址
    	String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        requestUrl = requestUrl.replace("APPID", appId).replace("APPSECRET", appsecret);
        // 获取token
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        Access_token access_token = new Access_token();
        access_token.setAccess_token(jsonObject.getString("access_token"));
        access_token.setExpires_in(jsonObject.getInt("expires_in"));
    	return access_token;
    }
    
    /**
     * 获取jssdk  jsapi_ticket
     */
    public static Jsapi_ticket getJsapi_ticket(String access_token){
    	
    	String requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";  

        // 获取token
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        Jsapi_ticket ticket = new Jsapi_ticket();
        ticket.setTicket(jsonObject.getString("ticket"));
        ticket.setExpires_in(jsonObject.getInt("expires_in"));
        return ticket;
    }
    
    /**
     * 获取预付单
     * @param openId
     * @param ip
     * @param money
     */
    public static SortedMap<Object, Object> wx_pay(String openId,String ip,Integer money){
        SortedMap<Object,Object> payMap = new TreeMap<Object,Object>();
    	String out_trade_no  = Weixin_rechargeUtil.getOut_trade_no();//商品订单号
    	
    	String notify_url = "http://www.knoque.cn";
    	
    	try {
			String resultXml = Weixin_rechargeUtil.unifiedorder(appId, "天府麻将-砖石充值", out_trade_no, money, ip, notify_url, openId);
			
			JSONObject jsonObj = JSONObject.fromObject(Weixin_rechargeUtil.parseXml(resultXml));
			
			if("SUCCESS".equals(jsonObj.getString("return_code"))&&"SUCCESS".equals(jsonObj.getString("result_code"))){//

				payMap.put("appId", jsonObj.get("appid"));
				payMap.put("timeStamp", Weixin_rechargeUtil.getTimeStamp());
				payMap.put("nonceStr", Weixin_rechargeUtil.getNonceStr());
				payMap.put("package", "prepay_id="+jsonObj.getString("prepay_id"));
				payMap.put("signType", "MD5");
				payMap.put("paySign", Weixin_rechargeUtil.createSign(payMap,  WeChatConst.KEY));
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return payMap;
    }
    
   
    
}
