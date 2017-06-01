package com.wust.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.Payment;
import com.wust.domain.PaymentPage;
import com.wust.factory.BasicFactory;
import com.wust.service.PaymentService;
import com.wust.service.ResultService;

public class DeletedPaymentListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PaymentService service = BasicFactory.getFactory().getInstance(PaymentService.class);
		
		int thispage =1;
		if(request.getParameter("thispage")!=null){
			thispage=Integer.parseInt(request.getParameter("thispage"));
		}
		int rowperpage = 10;
		PaymentPage page = service.getDeletedPaymentByPage(thispage,rowperpage);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/admin/deletedPaymentList.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
