package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.wust.domain.Payment;
import com.wust.factory.BasicFactory;
import com.wust.service.PaymentService;
import com.wust.service.ResultService;

public class AddPaymentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			PaymentService service = BasicFactory.getFactory().getInstance(PaymentService.class);
			ResultService service1 = BasicFactory.getFactory().getInstance(ResultService.class);
			Payment payment = new Payment();
			BeanUtils.populate(payment, request.getParameterMap());
			//1.向回款列表插入数据
			service.addPayment(payment);
			//2.向结余表中修改数据
			service1.payUpdateResult(payment);
			response.sendRedirect(request.getContextPath()+"/servlet/PaymentListServlet?thispage=1");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
