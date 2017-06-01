package com.wust.web;

import java.io.IOException;
import java.io.PrintWriter;

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

public class DeletedDraworderListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		DraworderService service = BasicFactory.getFactory().getInstance(DraworderService.class);
		int thispage =1;
		if(request.getParameter("thispage")!=null)
		{
			thispage = Integer.parseInt(request.getParameter("thispage"));
		}
		int rowperpage = 10;
		//1.调用ClerkService中的方法，查找当前所需页的所有信息
		DraworderPage page = service.deletedDraworderList(thispage,rowperpage);
		//2.将page信息存入request域中，并带回到clerkManage.jsp中
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/admin/deletedDraworderList.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
