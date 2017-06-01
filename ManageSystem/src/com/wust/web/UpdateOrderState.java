package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.BasicFare;
import com.wust.domain.CheckDraw;
import com.wust.domain.Fare;
import com.wust.domain.Order;
import com.wust.domain.Product;
import com.wust.domain.Result;
import com.wust.factory.BasicFactory;
import com.wust.service.BasicFareService;
import com.wust.service.CheckDrawService;
import com.wust.service.FareService;
import com.wust.service.OrderService;
import com.wust.service.ProductService;
import com.wust.service.ResultService;

public class UpdateOrderState extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
		ResultService service1 = BasicFactory.getFactory().getInstance(ResultService.class);
		CheckDrawService service2 = BasicFactory.getFactory().getInstance(CheckDrawService.class);
		ProductService service3 = BasicFactory.getFactory().getInstance(ProductService.class);
		FareService service4 = BasicFactory.getFactory().getInstance(FareService.class);
		BasicFareService service5 = BasicFactory.getFactory().getInstance(BasicFareService.class);
		String id = request.getParameter("id");
		String format = request.getParameter("format");
		String level = new String(request.getParameter("level").getBytes("iso8859-1"),"utf-8");
		String state = new String(request.getParameter("state").getBytes("iso-8859-1"),"utf-8");
		String check = request.getParameter("check");
		
		Order order = service.getOrderInfo(id,format,level);
		if(state.equals("审核通过")){
			//1.更新结余表信息
			Result result = service1.getResultByDisAndSal(order.getDistributor(),order.getSalesman());
			if(result==null){
				service1.addResult(order);
			}else{
				service1.updateResult(order);
			}
			//2.更新开票查询表信息
			CheckDraw checkDraw = service2.getCheckDraw(order.getDistributor(), order.getFormat(), order.getLevel());
			if(checkDraw==null){
				service2.addChekDraw(order.getDistributor(),order.getFormat(),order.getLevel(),order.getNumber());
			}else{
				service2.updateCheckDrawNum(order.getDistributor(),order.getFormat(),order.getLevel(),order.getNumber());
			}
			//3.更新调度费信息
			if(order.getFare().trim().equalsIgnoreCase("是")){
//				Product product = service3.getProductInfo(order.getFormat(), order.getLevel());
//				int PiceNumPerPackage = product.getBagSlices();
				double PackageNum = order.getVolume();
				BasicFare basicFare = service5.findBasicfByArea(order.getArea());
				Fare fare = service4.getFare(order.getOrderid());
				if(fare!=null){
					fare.setNum(fare.getNum()+PackageNum);
					fare.setSumfare(fare.getSumfare()+PackageNum*basicFare.getFare());
					service4.updateFare(fare);
				}else{
					service4.addFare(order.getOrderid(),order.getArea(),basicFare.getFare(),PackageNum,PackageNum*basicFare.getFare());
				}	
			}
			//更新运费信息
			if(order.getFreight2().trim().equalsIgnoreCase("是")){
//				Product product = service3.getProductInfo(order.getFormat(), order.getLevel());
//				int PiceNumPerPackage = product.getBagSlices();
				double PackageNum = order.getVolume();
				BasicFare basicFare = service5.findBasicf2ByArea(order.getArea());
				Fare fare = service4.getFreight(order.getOrderid());
				if(fare!=null){
					fare.setNum(fare.getNum()+PackageNum);
					fare.setSumfare(fare.getSumfare()+PackageNum*basicFare.getFare());
					service4.updateFreight(fare);
				}else{
					service4.addFreight(order.getOrderid(),order.getArea(),basicFare.getFare(),PackageNum,PackageNum*basicFare.getFare());
				}	
			}
			//4.更新库存
			service3.updateProduct(order.getFormat(),order.getLevel(),order.getNumber(),order.getVolume());
			//5.更新订单状态
			service.updateState(id,format,level,state);
		}else if(state.equals("审核未通过")&&check.equals("check")){
			service.updateState(id,format,level,state);
		}
		if(check.equals("check")){
			response.sendRedirect(request.getContextPath()+"/servlet/OrderList2Servlet?thispage=1");
		}else if(check.equals("recheck")){
			response.sendRedirect(request.getContextPath()+"/servlet/OrderList3Servlet?thispage=1");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
