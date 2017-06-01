package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.FarePage;
import com.wust.factory.BasicFactory;
import com.wust.service.FareService;

public class FareListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		FareService service = BasicFactory.getFactory().getInstance(FareService.class);
		if(request.getParameter("id")!=null){
			service.deleteFare(request.getParameter("id"));
		}
		int thispage =1;
		if(request.getParameter("thispage")!=null)
		{
			thispage = Integer.parseInt(request.getParameter("thispage"));
		}
		int rowperpage = 10;
		FarePage page = service.getFareByPage(thispage,rowperpage);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/treasurer/fareList.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
