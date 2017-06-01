package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.ResultPage;
import com.wust.factory.BasicFactory;
import com.wust.service.ResultService;

public class ResultListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		ResultService service = BasicFactory.getFactory().getInstance(ResultService.class);
		if(request.getParameter("id")!=null){
			service.deleteResult(Integer.parseInt(request.getParameter("id")));
			
		}
		int thispage =1;
		if(request.getParameter("thispage")!=null)
		{
			thispage = Integer.parseInt(request.getParameter("thispage"));
		}
		int rowperpage = 10;
		ResultPage page = service.getResultByPage(thispage,rowperpage);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/treasurer/resultList.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
