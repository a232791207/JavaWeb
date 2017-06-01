package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.DistributorPage;
import com.wust.factory.BasicFactory;
import com.wust.service.DistributorService;


public class DistributorListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		DistributorService service = BasicFactory.getFactory().getInstance(DistributorService.class);
		int thispage = Integer.parseInt(request.getParameter("thispage"));
		int rowperpage = 10;
		//1.调用DistributorService中的方法，查找当前所需页的所有信息
		DistributorPage page = service.pageDistributor(thispage,rowperpage);
		//2.将page信息存入request域中，并带回到distributorManage.jsp中
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/admin/distributorManage.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
