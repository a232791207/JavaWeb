package com.wust.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wust.beans.Cart;
import com.wust.beans.CartItem;
import com.wust.beans.OrderCart;
import com.wust.beans.OrderItem;
import com.wust.domain.Draworder;
import com.wust.domain.Order;
import com.wust.domain.Product;
import com.wust.factory.BasicFactory;
import com.wust.service.BasicFareService;
import com.wust.service.CheckDrawService;
import com.wust.service.DraworderService;
import com.wust.service.OrderService;
import com.wust.service.ProductService;
import com.wust.service.ResultService;

public class OrderCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String op = request.getParameter("op");
		if("add".equals(op)){
			addCart(request,response);
		}
		else if("getCartItemsNum".equals(op)){
			getCartItemsNum(request,response);
		}
		else if("showCart".equals(op)){
			showCart(request,response);
		}
		else if("delOneItem".equals(op)){
			delOneItem(request,response);
		}
		else if("delAllItems".equals(op)){
			delAllItems(request,response);
		}
		else if("changeNum".equals(op)){
			changeNum(request,response);
		}
		else if("calCart".equals(op)){
			calCart(request,response);
		}
		else if("showAddOrder".equals(op)){
			showAddOrder(request,response);
		}
	}

	private void showAddOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id");
		String time = request.getParameter("time");
		String distributor = request.getParameter("distributor");
		String salesman = request.getParameter("salesman");
		String area = request.getParameter("area");
		distributor = new String(distributor.getBytes("iso8859-1"),"utf-8");
		area = new String(area.getBytes("iso8859-1"),"utf-8");
		request.setAttribute("id", id);
		request.setAttribute("time", time);
		request.setAttribute("distributor", distributor);
		request.setAttribute("salesman", salesman);
		request.setAttribute("area", area);
		request.getRequestDispatcher("/user/salesman/addOrder.jsp").forward(request, response);
		
	}

	private void calCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String time = request.getParameter("time");
		String orderid = request.getParameter("id");
		String salesman = request.getParameter("salesman");
		String area = request.getParameter("area");
		String distributor = request.getParameter("distributor");
		if(time==""||orderid==""||salesman==""||area==""||distributor==""){
			String error= "请输入时间、订单号、业务员编号、区域、经销商";
			if(distributor!=""){
				distributor =new  String(distributor.getBytes("iso8859-1"),"utf-8");
			}
			if(area!=""){
				area =new  String(area.getBytes("iso8859-1"),"utf-8");
				BasicFareService basicFareService = BasicFactory.getFactory().getInstance(BasicFareService.class);
				if(basicFareService.findBasicfByArea(area)==null){
					error=area+"暂无调度费!";
				}
			}
			request.setAttribute("id", orderid);
			request.setAttribute("time", time);
			request.setAttribute("distributor", distributor);
			request.setAttribute("salesman", salesman);
			request.setAttribute("area", area);
			request.setAttribute("error", error);
			request.getRequestDispatcher("/user/salesman/showCart.jsp").forward(request, response);
		}
		else{
			distributor =new  String(distributor.getBytes("iso8859-1"),"utf-8");
			area =new  String(area.getBytes("iso8859-1"),"utf-8");
			BasicFareService basicFareService = BasicFactory.getFactory().getInstance(BasicFareService.class);
			if(basicFareService.findBasicfByArea(area)==null){
				String error=area+"暂无调度费!";
				request.setAttribute("id", orderid);
				request.setAttribute("time", time);
				request.setAttribute("distributor", distributor);
				request.setAttribute("salesman", salesman);
				request.setAttribute("area", area);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/user/salesman/showCart.jsp").forward(request, response);
			}
			else{
				OrderCart cart = (OrderCart)request.getSession().getAttribute("cart");
				Map<String, OrderItem> items = (HashMap<String, OrderItem>)cart.getItems();
				List<Order> orders = new ArrayList<Order>();
				OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
				String error="此单中下过单的产品的有：;";
				for(Map.Entry<String, OrderItem> me:items.entrySet()){
					//orderid,time,salesman,distributor,area,format,level,price,
					//directinc,freight,specialinc,realprice,number,sumprice,ifprofit
					Order order = new Order();
					order.setArea(area);
					order.setDirectinc(me.getValue().getDirectinc()+"");
					order.setDistributor(distributor);
					order.setFormat(me.getValue().getFormat());
					order.setFreight(me.getValue().getFreight());
					order.setIfprofit(me.getValue().getIfprofit());
					order.setLevel(me.getValue().getLevel());
					order.setNumber(me.getValue().getQuantity());
					order.setOrderid(orderid);
					order.setPrice(me.getValue().getPrice());
					order.setRealprice(me.getValue().getRealprice());
					order.setSalesman(salesman);
					order.setSpecialinc(me.getValue().getSpecialinc()+"");
					order.setSumprice(me.getValue().getMoney()+"");
					order.setTime(time);
					
					order.setBags(me.getValue().getPack());
					order.setVolume(me.getValue().getVolume());
					order.setFare(me.getValue().getFare());
					Order order_d = service.getOrderInfo(orderid, order.getFormat(), order.getLevel());
					if(order_d==null){
						orders.add(order);
					}
					else{
						//开过一摸一样的还开？换张发票吧，哥?（换个订单号吧）
						error=error+order.getFormat()+","+order.getLevel()+";";
					}
					
				}
				
				service.addOrder(orders);
				request.getSession().setAttribute("cart", null);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/servlet/OrderListServlet?thispage=1&salesman="+salesman).forward(request, response);
			}
		}
				
	}

	private void changeNum(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String oid = request.getParameter("oid");
		String time = request.getParameter("time");
		String distributor = request.getParameter("distributor");
		String salesman = request.getParameter("salesman");
		String area = request.getParameter("area");
		distributor = new String(distributor.getBytes("iso8859-1"),"utf-8");
		area = new String(area.getBytes("iso8859-1"),"utf-8");
		request.setAttribute("id", oid);
		request.setAttribute("time", time);
		request.setAttribute("distributor", distributor);
		request.setAttribute("salesman", salesman);
		request.setAttribute("area", area);
		String format = request.getParameter("format");
		String level = new String(request.getParameter("level").getBytes("iso8859-1"),"utf-8");
		String key = format+"-"+level;
		int num =Integer.parseInt(request.getParameter("num"));
		OrderCart cart = (OrderCart)request.getSession().getAttribute("cart");
		Map<String, OrderItem> items = (HashMap<String, OrderItem>)cart.getItems();
		ProductService service = BasicFactory.getFactory().getInstance(ProductService.class);
		int quantity = service.getProductInfo(format, level).getBagSlices()*num;
		OrderItem item = items.get(key);
		item.setPack(num);
		item.setQuantity(quantity);
		request.getRequestDispatcher("/user/salesman/showCart.jsp").forward(request, response);
	}

	private void delAllItems(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String oid = request.getParameter("oid");
		String time = request.getParameter("time");
		String distributor = request.getParameter("distributor");
		String salesman = request.getParameter("salesman");
		String area = request.getParameter("area");
		distributor = new String(distributor.getBytes("iso8859-1"),"utf-8");
		area = new String(area.getBytes("iso8859-1"),"utf-8");
		request.setAttribute("id", oid);
		request.setAttribute("time", time);
		request.setAttribute("distributor", distributor);
		request.setAttribute("salesman", salesman);
		request.setAttribute("area", area);
		request.getSession().removeAttribute("cart");
		request.getRequestDispatcher("/user/salesman/showCart.jsp").forward(request, response);
	}

	private void delOneItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String oid = request.getParameter("oid");
		String time = request.getParameter("time");
		String distributor = request.getParameter("distributor");
		String salesman = request.getParameter("salesman");
		String area = request.getParameter("area");
		distributor = new String(distributor.getBytes("iso8859-1"),"utf-8");
		area = new String(area.getBytes("iso8859-1"),"utf-8");
		request.setAttribute("id", oid);
		request.setAttribute("time", time);
		request.setAttribute("distributor", distributor);
		request.setAttribute("salesman", salesman);
		request.setAttribute("area", area);
		String key = new String(request.getParameter("id").getBytes("iso8859-1"),"utf-8");
		OrderCart cart = (OrderCart)request.getSession().getAttribute("cart");
		cart.getItems().remove(key);
		request.getRequestDispatcher("/user/salesman/showCart.jsp").forward(request, response);
		
	}

	private void showCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("id");
		String time = request.getParameter("time");
		String distributor = request.getParameter("distributor");
		String salesman = request.getParameter("salesman");
		String area = request.getParameter("area");
		distributor = new String(distributor.getBytes("iso8859-1"),"utf-8");
		area = new String(area.getBytes("iso8859-1"),"utf-8");
		request.setAttribute("id", id);
		request.setAttribute("time", time);
		request.setAttribute("distributor", distributor);
		request.setAttribute("salesman", salesman);
		request.setAttribute("area", area);
		request.getRequestDispatcher("/user/salesman/showCart.jsp").forward(request, response);
	}

	private void getCartItemsNum(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		OrderCart cart = (OrderCart)request.getSession().getAttribute("cart");
		if(cart!=null){
			response.getWriter().print(cart.getItems().size());
		}
		else{
			response.getWriter().print(0);
		}
	}

	private void addCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		
		String format = request.getParameter("format");
		String level = request.getParameter("level");
		String ifprofit = request.getParameter("ifprofit");
		String id = request.getParameter("orderid");
		double volume = Double.parseDouble(request.getParameter("volume"));
		String fare = request.getParameter("fare");
		double price = Double.parseDouble(request.getParameter("price"));
		double realprice = Double.parseDouble(request.getParameter("realprice"));
		double freight = Double.parseDouble(request.getParameter("freight"));
		double specialinc = Double.parseDouble(request.getParameter("specialinc"));
		double directinc = Double.parseDouble(request.getParameter("directinc"));
		double money = Double.parseDouble(request.getParameter("money"));
		int pack = Integer.parseInt(request.getParameter("pack"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		OrderService orderService=BasicFactory.getFactory().getInstance(OrderService.class);
		OrderCart cart =(OrderCart)session.getAttribute("cart");
		if(cart==null){
			cart = new OrderCart();
			session.setAttribute("cart", cart);
		}
		OrderItem orderItem = new OrderItem(format, level, price, realprice, freight, specialinc, directinc, pack, quantity, money, ifprofit,fare,volume);
		Order order = orderService.getOrderInfo(id, format, level);
		if(order==null){
			cart.addOrderItem(orderItem);
			response.getWriter().print("成功加入下单队列！");
		}
		else{
			request.setAttribute("id", order.getOrderid());
			request.setAttribute("time", order.getTime());
			request.setAttribute("distributor", order.getDistributor());
			request.setAttribute("salesman", order.getSalesman());
			request.setAttribute("area", order.getArea());
			request.setAttribute("error", "此单已经买过该产品了,请修改相应信息！");
			request.getRequestDispatcher("/user/salesman/addOrder.jsp").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
