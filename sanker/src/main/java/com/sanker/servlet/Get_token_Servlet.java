package com.sanker.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.sanker.weiXin.util.Token_ticket_Thread;

public class Get_token_Servlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void init() throws ServletException {
		System.out.println("get_token_servlet");
		new Thread(new Token_ticket_Thread()).start();
	}

}
