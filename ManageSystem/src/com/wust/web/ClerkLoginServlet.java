package com.wust.web;

import java.io.IOException;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.Clerk;
import com.wust.factory.BasicFactory;
import com.wust.service.ClerkService;

public class ClerkLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ClerkService service = BasicFactory.getFactory().getInstance(ClerkService.class);
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		Map<String, String[]> map = new HashMap<String, String[]>();
		String[][] str ={{"storeManager","storeManager"},{"salesman","salesmanManage"},
				{"treasurer","treasurerManage"},{"drawer","drawer"}};
		map.put("储管员", str[0]);
		map.put("业务员", str[1]);
		map.put("财务员", str[2]);
		map.put("开票员", str[3]);
		Clerk clerk = service.isClerk(id,password,type);
		if(clerk==null){
			request.setAttribute("msg", "用户名密码错误!");
			request.getRequestDispatcher("/user/"+(map.get(type))[0]+"/"+(map.get(type))[0]+"Login.jsp").forward(request, response);
		}else{
			request.getSession().setAttribute("clerk", clerk);
			response.sendRedirect(request.getContextPath()+"/user/"+(map.get(type))[0]+"/"+(map.get(type))[1]+".jsp");		
		}

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
