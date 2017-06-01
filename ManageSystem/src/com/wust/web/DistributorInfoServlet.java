package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.Clerk;
import com.wust.domain.Distributor;
import com.wust.factory.BasicFactory;
import com.wust.service.ClerkService;
import com.wust.service.DistributorService;

public class DistributorInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		DistributorService service = BasicFactory.getFactory().getInstance(DistributorService.class);
		//1.调用DistributorService中的方法查找指定经销商id的经销商信息
		String id = request.getParameter("id");
		Distributor distributor = service.getDistributorInfo(id);
		//2.将经销商信息存到request域中并请求转发到updateDistributor.jsp页面展示信息
		request.setAttribute("distributor", distributor);
		request.getRequestDispatcher("/user/admin/updateDistributor.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
