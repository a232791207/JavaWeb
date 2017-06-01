package com.sanker.weiXin.util;

import com.sanker.entity.weixin.Access_token;
import com.sanker.entity.weixin.Jsapi_ticket;

public class Token_ticket_Thread implements Runnable{
	
	public static Access_token access_token = null;
	public static Jsapi_ticket jsapi_ticket = null;
	@Override
	public void run() {
		while(true){
			try {
				access_token = AdvancedUtil.getAccess_token();
				if(access_token==null){
					Thread.sleep(6*1000);
				}else{
					jsapi_ticket = AdvancedUtil.getJsapi_ticket(access_token.getAccess_token());
					int sleepTime = 0;
					if(jsapi_ticket.getExpires_in()<access_token.getExpires_in()){
						sleepTime = jsapi_ticket.getExpires_in();
					}else{
						sleepTime = access_token.getExpires_in();
					}
					Thread.sleep((sleepTime-600)*1000);
				}
			} catch (InterruptedException e) {

				try {
					Thread.sleep(6*1000);
				} catch (InterruptedException e1) {
					System.out.println("access_token  jsapi_ticket  获取失败！");
				}
			}
			
		}
		
	}

}
