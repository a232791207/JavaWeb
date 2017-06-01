package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.wust.domain.Directinc;
import com.wust.domain.Price;
import com.wust.factory.BasicFactory;
import com.wust.service.DirectincService;
import com.wust.service.PriceService;

public class UpdateDirectincServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			DirectincService service = BasicFactory.getFactory().getInstance(DirectincService.class);
			Directinc directinc = new Directinc();
			BeanUtils.populate(directinc, request.getParameterMap());
			service.updateDirectinc(directinc);
			response.sendRedirect(request.getContextPath()+"/servlet/DirectincListServlet?thispage=1");
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
