package com.sanker.weiXin.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * weixin充值
 * @author 滕洁
 * @date 2017-3-19
 */
public class Weixin_rechargeUtil {

	/**
	* 充值钻石返回
	*/
	public static Map<String, Object> get_rechargeDiamond_money(String sign) {

		Map<String, Object> recharge_map = new HashMap<>();
		Integer money_num = 0;
		Integer diamond_num = 0;
		switch (sign) {
		case "1":
			money_num = 3;
			diamond_num = 15;
			break;
		case "2":
			money_num = 6;
			diamond_num = 30;
			break;
		case "3":
			money_num = 18;
			diamond_num = 95;
			break;
		case "4":
			money_num = 36;
			diamond_num = 195;
			break;
		case "5":
			money_num = 60;
			diamond_num = 330;
			break;
		case "6":
			money_num = 128;
			diamond_num = 720;
			break;
		case "7":
			money_num = 328;
			diamond_num = 1900;
			break;
		case "8":
			money_num = 10;
			diamond_num = 75;
			break;
		}
		recharge_map.put("money_num", money_num);
		recharge_map.put("diamond_num", diamond_num);
		return recharge_map;
	}
	
	/**
	* 充值金豆返回
	*/
	public static Map<String, Integer> get_rechargeGold_money(String sign) {

		Map<String, Integer> recharge_map = new HashMap<>();
		Integer diamond_num = 0;
		Integer gold_num = 0;
		switch (sign) {
		case "1":
			diamond_num = 10;
			gold_num = 10000;
			break;
		case "2":
			diamond_num = 20;
			gold_num = 20000;
			break;
		case "3":
			diamond_num = 75;
			gold_num = 78000;
			break;
		case "4":
			diamond_num = 150;
			gold_num = 160000;
			break;
		case "5":
			diamond_num = 300;
			gold_num = 324000;
			break;
		case "6":
			diamond_num = 500;
			gold_num = 550000;
			break;
		case "7":
			diamond_num = 1500;
			gold_num = 1700000;
			break;
		case "8":
			diamond_num = 3000;
			gold_num = 3600000;
			break;
		}
		recharge_map.put("diamond_num", diamond_num);
		recharge_map.put("gold_num", gold_num);
		return recharge_map;
	}

	/**
	 * 统一下单
	 * 获得PrePayId
	 * @param body   商品或支付单简要描述
	 * @param out_trade_no 商户系统内部的订单号,32个字符内、可包含字母
	 * @param total_fee  订单总金额，单位为分
	 * @param IP    APP和网页支付提交用户端ip
	 * @param notify_url 接收微信支付异步通知回调地址
	 * @param openid 用户openId
	 * @throws IOException
	 */
	public static String unifiedorder(String appid, String body, String out_trade_no, Integer total_fee, String ip, String notify_url, String openId) throws IOException {
		/**
		 * 设置访问路径
		 */
		HttpPost httppost = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder");
		String nonce_str = getNonceStr();//随机数据
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		/**
		 * 组装请求参数
		 * 按照ASCII排序
		 */
		parameters.put("appid", appid);
		parameters.put("body", body);
		parameters.put("mch_id", WeChatConst.MCH_ID);
		parameters.put("nonce_str", nonce_str);
		parameters.put("out_trade_no", out_trade_no);
		parameters.put("notify_url", notify_url);
		parameters.put("spbill_create_ip", ip);
		parameters.put("total_fee", total_fee.toString());
		parameters.put("trade_type", WeChatConst.TRADE_TYPE);
		parameters.put("openid", openId);

		String sign = createSign(parameters, WeChatConst.KEY);

		/**
		 * 组装XML
		 */
		StringBuilder sb = new StringBuilder("");
		sb.append("<xml>");
		setXmlKV(sb, "appid", appid);
		setXmlKV(sb, "body", body);
		setXmlKV(sb, "mch_id", WeChatConst.MCH_ID);
		setXmlKV(sb, "nonce_str", nonce_str);
		setXmlKV(sb, "notify_url", notify_url);
		setXmlKV(sb, "out_trade_no", out_trade_no);
		setXmlKV(sb, "spbill_create_ip", ip);
		setXmlKV(sb, "total_fee", total_fee.toString());
		setXmlKV(sb, "trade_type", WeChatConst.TRADE_TYPE);
		setXmlKV(sb, "sign", sign);
		setXmlKV(sb, "openid", openId);
		sb.append("</xml>");

		StringEntity reqEntity = new StringEntity(new String(sb.toString().getBytes("UTF-8"), "ISO8859-1"));//这个处理是为了防止传中文的时候出现签名错误
		httppost.setEntity(reqEntity);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = httpclient.execute(httppost);
		String strResult = EntityUtils.toString(response.getEntity());

		return strResult;

	}

	//获得随机字符串
	public static String getNonceStr() {
		Random random = new Random();
		return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
	}

	/**
	* 创建签名
	* @param parameters
	* @param key
	* @return
	*/
	@SuppressWarnings("rawtypes")
	public static String createSign(SortedMap<Object, Object> parameters, String key) {
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + key);
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		return sign;
	}

	//插入XML标签
	public static StringBuilder setXmlKV(StringBuilder sb, String Key, String value) {
		sb.append("<");
		sb.append(Key);
		sb.append(">");

		sb.append(value);

		sb.append("</");
		sb.append(Key);
		sb.append(">");

		return sb;
	}

	//解析XML  获得 PrePayId
	public static String getPrePayId(String xml) {
		int start = xml.indexOf("<prepay_id>");
		int end = xml.indexOf("</prepay_id>");
		if (start < 0 && end < 0) {
			return null;
		}
		return xml.substring(start + "<prepay_id>".length(), end).replace("<![CDATA[", "").replace("]]>", "");
	}

	//商户订单号
	public static String getOut_trade_no() {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return df.format(new Date()) + buildRandom(6);
	}

	//时间戳
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

	//随机4位数字
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}

	public static String inputStream2String(InputStream inStream, String encoding) {
		String result = null;
		try {
			if (inStream != null) {
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				byte[] tempBytes = new byte[1024];
				int count = -1;
				while ((count = inStream.read(tempBytes, 0, 1024)) != -1) {
					outStream.write(tempBytes, 0, count);
				}
				tempBytes = null;
				outStream.flush();
				result = new String(outStream.toByteArray(), encoding);
			}
		} catch (Exception e) {
			result = null;
		}
		return result;
	}
	
	/**
	 * 解析xml
	 * @param args
	 */
	public static String parseXml(String xmlStr){
		StringReader sr = new StringReader(xmlStr); 
		InputSource is = new InputSource(sr); 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
		ByteArrayOutputStream bos = null;
		try {
			DocumentBuilder builder=factory.newDocumentBuilder(); 
			Document doc = builder.parse(is);
			//XML转字符串
			TransformerFactory  tf  =  TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			t.setOutputProperty("encoding","GB23121");//解决中文问题，试过用GBK不行
			bos = new  ByteArrayOutputStream();
			t.transform(new DOMSource(doc), new StreamResult(bos));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  bos.toString();
	}

	public static void main(String[] args) {
		System.out.println(getOut_trade_no());
	}
}
