package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.Order;
import com.wust.domain.OrderPage;
import com.wust.factory.BasicFactory;
import com.wust.service.CheckDrawService;
import com.wust.service.FareService;
import com.wust.service.OrderService;
import com.wust.service.ProductService;
import com.wust.service.ResultService;

public class OrderList3Servlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
		ResultService service1 = BasicFactory.getFactory().getInstance(ResultService.class);
		CheckDrawService service2 = BasicFactory.getFactory().getInstance(CheckDrawService.class);
		ProductService service3 = BasicFactory.getFactory().getInstance(ProductService.class);
		FareService service4 = BasicFactory.getFactory().getInstance(FareService.class);
		String id = request.getParameter("id");
		String format = request.getParameter("format");
		String level = request.getParameter("level");
		if(id!=null){
			level = new String(request.getParameter("level").getBytes("iso-8859-1"),"utf-8");
			Order order = service.getOrderInfo(id,format,level);
			//1.更新结余表信息
			order.setSumprice("-"+order.getSumprice());
			service1.updateResult(order);
			//2.更新开票查询表信息
			order.setNumber(-order.getNumber());
			service2.updateCheckDrawNum(order.getDistributor(),order.getFormat(),order.getLevel(),order.getNumber());
			//3.更新调度费信息
			if(order.getFare().trim().equalsIgnoreCase("是")){
				service4.deleteFare(order.getOrderid());
			}
			//4.更新库存
			service3.updateProduct(order.getFormat(),order.getLevel(),order.getNumber(),-order.getVolume());
			//5.删除订单
			service.deleteOrder(id,format,level);
			
		}
		int thispage =1;
		if(request.getParameter("thispage")!=null)
		{
			thispage = Integer.parseInt(request.getParameter("thispage"));
		}
		int rowperpage = 10;
		OrderPage page = service.pageOrderByRevStatement(thispage,rowperpage,"未审核");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/treasurer/orderListForTreasurer2.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
