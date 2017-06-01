package com.wust.web;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.wust.domain.Draworder;
import com.wust.domain.Order;
import com.wust.factory.BasicFactory;
import com.wust.service.CheckDrawService;
import com.wust.service.DraworderService;
import com.wust.service.OrderService;
import com.wust.service.ProductService;
import com.wust.service.ResultService;


public class AddDraworderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		DraworderService service = BasicFactory.getFactory().getInstance(DraworderService.class);
		ResultService resultService = BasicFactory.getFactory().getInstance(ResultService.class);
		CheckDrawService checkdrawService = BasicFactory.getFactory().getInstance(CheckDrawService.class);
		try {	
			//1.���ύ��Ա����Ϣ����װ��bean
			Draworder draworder = new Draworder();
			BeanUtils.populate(draworder, request.getParameterMap());
			//
			if(service.findDraworder(draworder.getId(), draworder.getFormat(), draworder.getLevel(), draworder.getPrice())==null){
				//2.����service�е����Ա���ķ���������װ�õ�Ա��bean���
				
				checkdrawService.updateCheckDrawByDrawnum(draworder.getDistributor(),draworder.getFormat(),draworder.getLevel(),draworder.getNumber());
				resultService.updateResultByNum(draworder.getDistributor(), draworder.getSalesman(),draworder.getSumprice());
				if(draworder.getOrderid()==""||draworder.getOrderid()==null){
					draworder.setOrderid("��");
				}
				service.addDraworder(draworder);
				
				//�޸Ľ������Ϣ
				
				//3.�����ض����Ա���������
				response.sendRedirect(request.getContextPath()+"/servlet/DraworderListServlet?thispage=1");
			}
			else{
				request.setAttribute("id", draworder.getId());
				request.setAttribute("time", draworder.getTime());
				request.setAttribute("distributor", draworder.getDistributor());
				request.setAttribute("salesman", draworder.getSalesman());
				request.setAttribute("customer", draworder.getCustomer());
				request.setAttribute("error", "��Ʊ�Ѿ������ò�Ʒ��,���޸���Ӧ��Ϣ��");
				request.getRequestDispatcher("/user/drawer/addDraworder.jsp").forward(request, response);
				
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
