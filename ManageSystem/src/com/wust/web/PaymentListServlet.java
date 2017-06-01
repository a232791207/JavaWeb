package com.wust.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.wust.domain.Payment;
import com.wust.domain.PaymentPage;
import com.wust.factory.BasicFactory;
import com.wust.service.PaymentService;
import com.wust.service.ResultService;

public class PaymentListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		PaymentService service = BasicFactory.getFactory().getInstance(PaymentService.class);
		if(request.getParameter("id")!=null){
			int id = Integer.parseInt(request.getParameter("id"));
			ResultService resultService = BasicFactory.getFactory().getInstance(ResultService.class);
			Payment payment = service.getPaymentById(id);
			payment.setMoney(-payment.getMoney());
			payment.setInterest("-"+payment.getInterest());
			resultService.payUpdateResult(payment);
			service.deletePayment(id);
		}
		
		int thispage =1;
		if(request.getParameter("thispage")!=null){
			thispage=Integer.parseInt(request.getParameter("thispage"));
		}
		int rowperpage = 10;
		PaymentPage page = service.getPaymentByPage(thispage,rowperpage);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/treasurer/paymentList.jsp").forward(request, response);
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
