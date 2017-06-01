package com.wust.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.Distributor;
import com.wust.domain.Result;
import com.wust.factory.BasicFactory;
import com.wust.service.DistributorService;
import com.wust.service.ResultService;

public class RemainCredServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		DistributorService service1 = BasicFactory.getFactory().getInstance(DistributorService.class);
		ResultService service2 = BasicFactory.getFactory().getInstance(ResultService.class);
		String name = request.getParameter("distributor");
		Distributor distributor = service1.findDistributorByName(name);
		List<Result> list = service2.findDistributorsByName(name); 
		double sumResult = 0;
		double sumRemainCred = 0;
		if(list.isEmpty()){
			sumResult = 0;
		}else{
			for(int i = 0; i < list.size(); i ++){
				sumResult = sumResult + Double.parseDouble(list.get(i).getResult());
			}
		}
		sumRemainCred = distributor.getCreditlines() - sumResult;
		response.getWriter().write(sumRemainCred+"");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
