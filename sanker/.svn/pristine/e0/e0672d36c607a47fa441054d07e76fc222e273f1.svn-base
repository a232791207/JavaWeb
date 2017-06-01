/*package com.sanker.service.websocket;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import net.sf.json.JSONObject;

import com.sanker.service.logic.HuPaiLogic;
import com.sanker.service.logic.Logic;
import com.sanker.service.utils.JSONHelper;

@ServerEndpoint("/websocket/{userId}")
public class WebSocket {

	private static final SimpleDateFormat date = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	// private static int onlineCount = 0;

	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	// private static CopyOnWriteArraySet<WebSocket> webSocketSet = new
	// CopyOnWriteArraySet<WebSocket>();

	// 链接用户
	private static final Map<String, WebSocket> connections = new LinkedHashMap<String, WebSocket>();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;
	
	private  static  Logic logic;
	
	private  static String lastUserId = "";//上一个出牌的人
	
	private  static List<String> userList = new ArrayList<String>();

	*//**
	 * 连接建立成功调用的方法
	 * 
	 * @param session
	 *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 * @throws IOException 
	 *//*
	@SuppressWarnings("static-access")
	@OnOpen
	public void onOpen(@PathParam("userId") String userId, Session session) throws IOException {
		this.session = session;
		String msg = null;
		if (connections.containsKey(userId)) {
			msg = "error";
		} else {
			connections.put(userId, this);
			System.out.println("有新连接加入！当前在线人数为" + connections.size());
			System.out.println("userId:" + userId);
			if (connections.containsKey(userId)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("title", "open");
				map.put("userId", userId);
				map.put("sort", connections.size());
				map.put("date", date.format(new Date()));
				// String msg =
				// JSONHelper.toJson("{userId:"+userId+",sort:"+connections.size()+"}");
				msg = JSONHelper.toJson(map);
			}
		}

		connections.get(userId).sendMessage(msg);
		System.out.println(msg);
		
		if(this.connections.size()==4){//如果有4个新的连接此时开始打麻将
			Set<String> userSet = this.connections.keySet();
			this.userList.addAll(userSet);
			this.logic = new Logic();
			this.logic.fapai(new ArrayList<String>(userList));
			
			for(String string:userList){//发牌
				List<String> paiList = logic.usermj.get(string);
				Map<String, Object> msgPai = new HashMap<String, Object>();
				msgPai.put("title", "fapai");
				msgPai.put("paiList", paiList);
				msgPai.put("userId", string);
				String jsonStr = JSONHelper.toJson(msgPai);
				System.out.println(jsonStr);
				connections.get(string).sendMessage(jsonStr);
			}
			
			
			//判断庄家是否能胡，能杠
			*//*********canHu*********//*
			List<String> zJ = new ArrayList<String>();
			zJ = logic.usermj.get(userList.get(0));
			String firstPai = zJ.get(0);
			this.mopaiCaozuo(userList.get(0), firstPai);
		}

	}
	

	

	*//**
	 * 连接关闭调用的方法
	 *//*
	@SuppressWarnings("static-access")
	@OnClose
	public void onClose(@PathParam("userId") String userId) {
		if(this.connections.containsKey(userId)){
			connections.remove(userId);
			// subOnlineCount(); //在线数减1
			System.out.println("有一连接关闭！当前在线人数为" + connections.size());
			
		}

	}

	*//**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message
	 *            客户端发送过来的消息
	 * @param session
	 *            可选的参数
	 * @throws IOException 
	 *//*
	@SuppressWarnings("static-access")
	@OnMessage
	public void onMessage(@PathParam("userId") String userId, String message,
			Session session) throws IOException {
		System.out.println("来自客户端的消息:" + message);
	
		JSONObject jsonObject = JSONObject.fromObject(message);
		
		 *//** 收到来自用户的消息的种类：
		 * 打牌
		 * 胡牌
		 * 杠牌
		 * 碰牌
		 * 摸牌
		 * （聊天消息）
		 * 。。。
		 * 
		 * *//*
		String title = jsonObject.getString("title");
		Set<String> userSet = this.connections.keySet();
		List<String> userList = new ArrayList<String>();
		userList.addAll(userSet);
		if(title.equals("da")){//打牌
			String pai = jsonObject.getString("pai");
			this.logic.daPai(pai, userId);
			this.lastUserId = userId;
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> map1 = new HashMap<String, Object>();
			//更新
			map1.put("title", "gengxing");
			map1.put("paiList", this.logic.usermj.get(userId));
			this.connections.get(userId).sendMessage(JSONHelper.toJson(map1));
			
			map.put("title", "otherDapai");
			map.put("userId", userId);
			map.put("pai", pai);
			for (String string : userList) {
				this.connections.get(string).sendMessage(JSONHelper.toJson(map));
			}
			boolean canNext = true;
			for (String string : userList) {//判断其余玩家是否能碰，胡，杠
				if(!userId.equals(string)){
					if(!this.logic.canPeng(pai, string).equals("false")){//能碰
						canNext = false;
						Map<String, Object> msg = new HashMap<String, Object>();
						msg.put("title", "peng");
						msg.put("pai", pai);
						this.connections.get(string).sendMessage(JSONHelper.toJson(msg));
						
					}
					if(!this.logic.canGang(pai, string).equals("false")){//能杠
						canNext = false;
						Map<String, Object> msg = new HashMap<String, Object>();
						msg.put("title", "gang");
						msg.put("pai", pai);
						this.connections.get(string).sendMessage(JSONHelper.toJson(msg));
						
						
					}
					List<String> hupaiList = HuPaiLogic.canHu(this.logic.usermj.get(string));
					for (String string2 : hupaiList) {
						if(pai.equals(string2)){
							canNext = false;
							//能胡
							Map<String, Object> msg = new HashMap<String, Object>();
							msg.put("title", "hu");
							msg.put("pai", pai);
							this.connections.get(string).sendMessage(JSONHelper.toJson(msg));
						}
					}
					
				}
					
			}
			if(canNext){//下一位玩家摸牌
				nextPlay();
			}
			
			
		}
		//摸牌
		if(title.equals("mopai")){
			String pai = jsonObject.getString("pai");
			this.mopaiCaozuo(userId, pai); 
		}
		
		//和牌
		if(title.equals("hu")){
			for (String string : userList) {
				if(!userId.equals("string")){
					//发送玩家的和牌消息
					Map<String, Object> msg = new HashMap<String, Object>();
					msg.put("title", "ishu");
					msg.put("userId", userId);
					this.connections.get(string).sendMessage(JSONHelper.toJson(msg));
					
					
				}
			}
		}
		//碰牌
		if(title.equals("peng")){
			String pai = jsonObject.getString("pai");
			//先执行牌的操作 
			this.logic.peng(pai, userId);
			// 再给玩家发送消息
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("title", "dapai");
			msg.put("paiList",this.logic.usermj.get(userId) );
			this.connections.get(userId).sendMessage(JSONHelper.toJson(msg));
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("title", "zjDapai");
			msg.put("paiList", this.logic.usermj.get(userId));
			this.connections.get(userId).sendMessage(JSONHelper.toJson(msg));
			
		}
		//杠牌
		if(title.equals("gang")){
			String pai = jsonObject.getString("pai");
			//String type = jsonObject.getString("type");
			*//**
			 * 此处后面需要考虑杠牌的类型
			 * *//*
			
			//先执行牌的操作
			this.logic.gang(pai, userId,null);
			String moPai = this.logic.mopai(userId);
			// 再给玩家发送消息
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("title", "mopai");
			msg.put("pai", moPai);
			msg.put("paiList",this.logic.usermj.get(userId) );
			this.connections.get(userId).sendMessage(JSONHelper.toJson(msg));
		}
	}
	//摸牌的操作
	@SuppressWarnings("static-access")
	private void mopaiCaozuo(String userId,String pai) throws IOException{
		List<String> paiList = new ArrayList<String>();
		
		paiList = logic.usermj.get(userId);
		List<String> hupaiList = HuPaiLogic.canHu(paiList);
		boolean isBoolean = true;
		if(hupaiList.size()>0){
			
			if(hupaiList.contains(pai)){
				isBoolean = false;
				//说明可以胡
				Map<String, Object> msgHu= new HashMap<String, Object>();
				msgHu.put("title", "hu");
				System.out.println(JSONHelper.toJson(msgHu));
				this.connections.get(userId).sendMessage(JSONHelper.toJson(msgHu));
				
			}
		}
		*//*********canHu end*********//*
		
		*//*********canGang*************//*
		List<String> gangPai = this.logic.canGangPlus(userId);
		if(gangPai.size()>0){
			isBoolean = false;
			//说明能杠
			Map<String, Object> msgGang= new HashMap<String, Object>();
			msgGang.put("title", "gang");
			msgGang.put("pai",gangPai.get(0));*//**暂时未考虑多个杠的情况**//*
			System.out.println(JSONHelper.toJson(msgGang));
			this.connections.get(userId).sendMessage(JSONHelper.toJson(msgGang));
		}
		*//*********canGang end*************//*

		if(isBoolean){//打牌
			Map<String, Object> zjMsg = new HashMap<String, Object>();
			zjMsg.put("title", "zjDapai");
			zjMsg.put("paiList", this.logic.usermj.get(userId));
			this.connections.get(userId).sendMessage(JSONHelper.toJson(zjMsg));
		}
		
	}
	

	*//**
	 * 发生错误时调用
	 * 
	 * @param session
	 * @param error
	 *//*
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}

	*//**
	 * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
	 * 
	 * @param message
	 * @throws IOException
	 *//*
	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}

	*//**
	 * 获取当前在线的用户ID
	 * 
	 * @return
	 *//*
	@SuppressWarnings("static-access")
	public Set<String> getOnlineUserId() {
		System.out.println(connections.size());
		System.out.println(connections.keySet().toString());
		return this.connections.keySet();
	}
	*//**
	 * 下一位玩家摸牌
	 * @throws IOException
	 *//*
	@SuppressWarnings("static-access")
	private void nextPlay() throws IOException{

		int thisUserId = userList.indexOf(lastUserId);
		String nextUser = "";
		if(thisUserId<3){
			nextUser = userList.get(thisUserId+1);
		}else{
			nextUser = userList.get(0);
		}
		//
		String mopai = this.logic.mopai(nextUser);
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("title", "mopai");
		msg.put("pai", mopai);
		msg.put("paiList",this.logic.usermj.get(nextUser));
		this.connections.get(nextUser).sendMessage(JSONHelper.toJson(msg));
		mopaiCaozuo(nextUser,mopai);
		
	}
	
	
	

	
}*/