package com.sanker.service.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.core.io.ClassPathResource;


public class Permission {
	
	private static Permission permission = new Permission();
	
	private Permission(){
		super();
	}
	
	public static Permission instance(){
		return permission;
	}
	
	/**
	 * 判断是否有权限访问此uri
	 * @param role
	 * @param uri
	 * @return
	 */
	public boolean hasPermission(String role, String uri){
		Document doc = null;
		boolean b = false;
		try {
			if(role != null && uri != null){
				doc = XmlUtil.getDocument(new ClassPathResource("permission.xml").getFile());
				Element root = doc.getRootElement();
				String text = root.element(role).getText();
				b = contains(uri, text.trim().split(","));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	private boolean contains(String uri, String[] permission){
		boolean b = false;
		if(uri != null && permission != null){
			for(String s: permission){
				if(uri.startsWith(s) || uri.endsWith(s)){
					b = true;
					break;
				}
			}
		}
		return b;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean b = Permission.instance().hasPermission("anonymous", "/evaluationFrame/jsp/index/login.jsp");
		System.out.println(b);
		b = Permission.instance().hasPermission("anonymous", "/evaluationFrame/javascript/register.js");
		System.out.println(b);
	}

}
