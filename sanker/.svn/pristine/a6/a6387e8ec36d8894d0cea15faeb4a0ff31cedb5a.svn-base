package com.sanker.service.utils;

import java.io.File;
import java.util.UUID;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.acegisecurity.providers.encoding.PasswordEncoder;
import org.apache.commons.lang.StringEscapeUtils;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.doomdark.uuid.UUIDGenerator;

/**
 * @Title: Test.java
 * @Package com.xinxinsoft.service.utils
 * @author zhangp QQ：361229957 E-mail：zhangp@xinxinsoft.com
 * @date 2012-2-4 上午11:11:42
 * @version V1.0
 */
public class Test {

	public static void main(String[] args) {
//		test();
//		uuid();
//		escapeHtml();
		PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		System.out.println(passwordEncoder.encodePassword("", null));
	}
	
	public static void uuid(){
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid);
		String u = UUIDGenerator.getInstance().generateRandomBasedUUID().toString();  
		System.out.println(u);
	}
	
	public static void test(){
		Document document = null;
		try {
			String path = getClassesPath();
			System.out.println(path);
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\evaluation\\WEB-INF\\classes\\com\\xinxinsoft\\process\\evaluate\\Evaluate_File_TaskName.xml"));
			System.out.println(document.asXML());
		} catch (Exception ex) {
			ex.printStackTrace(); 
		}
	}
	
	private static String getClassesPath(){
		String path = Test.class.getResource("/").getPath();       
		int lastNum = path.lastIndexOf("/WEB-INF/");    
		return path.substring(0,lastNum);    
	}
	
	public static void escapeHtml(){
		String html = "<html></html>";
		System.out.println(StringEscapeUtils.escapeJavaScript(html));
	}

}
