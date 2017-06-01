package com.wust.web;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.CheckDraw;
import com.wust.domain.Order;
import com.wust.domain.Result;
import com.wust.factory.BasicFactory;
import com.wust.service.CheckDrawService;
import com.wust.service.OrderService;
import com.wust.service.ResultService;

public class CheckDrawSumpriceServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String distributor = request.getParameter("distributor");
		String salesman = request.getParameter("salesman");
		double sumprice = Double.parseDouble(request.getParameter("sumprice"));
		ResultService resultService = BasicFactory.getFactory().getInstance(
				ResultService.class);
		Result result = resultService.getResultByDisAndSal(distributor, salesman);
		double sumdprice = Double.parseDouble(result.getSumdprice());
		double sumaprice = Double.parseDouble(result.getSumaprice());
		double beSumprice = sumaprice - sumdprice;
		if ((sumdprice+sumprice) > sumaprice) {
			response.getWriter().write("本次开票额不应超过"+beSumprice+"(累计实际销售额-前累计开票额)");
		} else
			response.getWriter().write("通过");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
