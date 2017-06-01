package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.Result;
import com.wust.factory.BasicFactory;
import com.wust.service.ResultService;

public class DistIsMatchWithSal extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ResultService service = BasicFactory.getFactory().getInstance(ResultService.class);
		String distributor = request.getParameter("distributor");
		String salesman = request.getParameter("salesman");
		Result result = service.getResultByDisAndSal(distributor, salesman);
		if(result==null){
			response.getWriter().write("0");
		}else{
			response.getWriter().write("1");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
