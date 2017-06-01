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
		//1.����DistributorService�еķ��������ҵ�ǰ����ҳ��������Ϣ
		DistributorPage page = service.pageDistributor(thispage,rowperpage);
		//2.��page��Ϣ����request���У������ص�distributorManage.jsp��
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/admin/distributorManage.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
