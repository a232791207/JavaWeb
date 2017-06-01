package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.wust.domain.BasicFare;
import com.wust.factory.BasicFactory;
import com.wust.service.BasicFareService;

public class AddBasicFareServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BasicFareService service = BasicFactory.getFactory().getInstance(BasicFareService.class);
		try {	
			BasicFare basicFare = new BasicFare();
			BeanUtils.populate(basicFare, request.getParameterMap());
			if(service.findBasicfByArea(basicFare.getArea())!=null){
				request.setAttribute("error", basicFare.getArea()+"已经有了调度费！");
				request.getRequestDispatcher("/user/treasurer/addBasicFare.jsp").forward(request, response);
			}
			else{
				service.addBasicFare(basicFare);
				response.sendRedirect(request.getContextPath()+"/servlet/BasicFareListServlet?thispage=1");
			}
			
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
