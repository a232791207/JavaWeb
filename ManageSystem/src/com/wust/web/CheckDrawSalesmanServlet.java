package com.wust.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.factory.BasicFactory;
import com.wust.service.CheckDrawService;
import com.wust.service.ResultService;

public class CheckDrawSalesmanServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String distributor = request.getParameter("distributor");
		String salesman = request.getParameter("salesman");
		ResultService resultService = BasicFactory.getFactory().getInstance(ResultService.class);
		if(resultService.getResultByDisAndSal(distributor, salesman)!=null){
			response.getWriter().write("通过");
		}
		else{
			response.getWriter().write("请输入正确的经销商");
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
