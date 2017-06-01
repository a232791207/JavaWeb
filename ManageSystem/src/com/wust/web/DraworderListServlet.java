package com.wust.web;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.Draworder;
import com.wust.domain.DraworderPage;
import com.wust.factory.BasicFactory;
import com.wust.service.CheckDrawService;
import com.wust.service.DraworderService;
import com.wust.service.ResultService;

public class DraworderListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		DraworderService service = BasicFactory.getFactory().getInstance(DraworderService.class);
		CheckDrawService checkdrawService = BasicFactory.getFactory().getInstance(CheckDrawService.class);
		ResultService resultService = BasicFactory.getFactory().getInstance(ResultService.class);
		if(request.getAttribute("error")!=null){
			request.setAttribute("error", request.getAttribute("error"));
		}else{
			if(request.getParameter("id")!=null){
				String id = request.getParameter("id");
				String format = request.getParameter("format");
				double price = Double.parseDouble(request.getParameter("price"));
				String level = new String(request.getParameter("level").getBytes("ISO8859-1"),"utf-8");	
				Draworder draworder = service.findDraworder(id,format,level,price);
				checkdrawService.updateCheckDrawByDrawnum(draworder.getDistributor(), format, level, -draworder.getNumber());
				resultService.updateResultByNum(draworder.getDistributor(), draworder.getSalesman(),-draworder.getSumprice());
				service.delete(id,format,level,price);			
			}
		}
		
		
		int thispage =1;
		if(request.getParameter("thispage")!=null)
		{
			thispage = Integer.parseInt(request.getParameter("thispage"));
		}
		int rowperpage = 10;
		//1.调用ClerkService中的方法，查找当前所需页的所有信息
		DraworderPage page = service.pageDraworder(thispage,rowperpage);
		//2.将page信息存入request域中，并带回到clerkManage.jsp中
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/drawer/draworderManage.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
