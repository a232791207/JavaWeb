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
import com.wust.domain.Draworder;
import com.wust.factory.BasicFactory;
import com.wust.service.CheckDrawService;
import com.wust.service.DraworderService;
import com.wust.service.ProductService;
import com.wust.service.ResultService;

public class CartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String op = request.getParameter("op");
		if("add".equals(op)){
			addCart(request,response);
		}
		else if("delAllItems".equals(op)){
			delAllItems(request,response);
		}
		else if("delOneItem".equals(op)){
			delOneItem(request,response);
		}
		else if("changeNum".equals(op)){
			changeNum(request,response);
		}
		else if("calCart".equals(op)){
			calCart(request,response);
		}
		else if("showCart".equals(op)){
			showCart(request,response);
		}
		else if("showAddDraworder".equals(op)){
			showAddDraworder(request,response);
		}
		else if("getCartItemsNum".equals(op)){
			getCartItemsNum(request,response);
		}
	}
	private void getCartItemsNum(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart!=null){
			response.getWriter().print(cart.getItems().size());
		}
		else{
			response.getWriter().print(0);
		}
		
	}
	private void showAddDraworder(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id");
		String orderid = request.getParameter("orderid");
		String time = request.getParameter("time");
		String distributor = request.getParameter("distributor");
		String salesman = request.getParameter("salesman");
		String customer = request.getParameter("customer");
		distributor = new String(distributor.getBytes("iso8859-1"),"utf-8");
		customer = new String(customer.getBytes("iso8859-1"),"utf-8");
		request.setAttribute("id", id);
		request.setAttribute("time", time);
		request.setAttribute("distributor", distributor);
		request.setAttribute("salesman", salesman);
		request.setAttribute("customer", customer);
		request.setAttribute("orderid", orderid);
		request.getRequestDispatcher("/user/drawer/addDraworder.jsp").forward(request, response);
		
	}
	private void showCart(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id");
		String orderid = request.getParameter("orderid");
		String time = request.getParameter("time");
		String distributor = request.getParameter("distributor");
		String salesman = request.getParameter("salesman");
		String customer = request.getParameter("customer");
		distributor = new String(distributor.getBytes("iso8859-1"),"utf-8");
		customer = new String(customer.getBytes("iso8859-1"),"utf-8");
		request.setAttribute("id", id);
		request.setAttribute("time", time);
		request.setAttribute("distributor", distributor);
		request.setAttribute("salesman", salesman);
		request.setAttribute("customer", customer);
		request.setAttribute("orderid", orderid);
		request.getRequestDispatcher("/user/drawer/showCart.jsp").forward(request, response);
		
	}
	private void calCart(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//开票时间 ,时间,发票号 ,经销商 ,业务员 :time,id,salesman,customer,distributor
		String time = request.getParameter("time");
		String id = request.getParameter("id");
		String orderid = request.getParameter("orderid");
		String salesman = request.getParameter("salesman");
		String customer = request.getParameter("customer");
		String distributor = request.getParameter("distributor");
		if(time==""||id==""||salesman==""||customer==""||distributor==""){
			String error="请输入正确的日期、发票号、业务员编号、开票单位和经销商！";
			if(distributor!=""){
				distributor =new  String(distributor.getBytes("iso8859-1"),"utf-8");
			}
			if(customer!=""){
				customer =new  String(customer.getBytes("iso8859-1"),"utf-8");
			}
			request.setAttribute("id", id);
			request.setAttribute("time", time);
			request.setAttribute("distributor", distributor);
			request.setAttribute("salesman", salesman);
			request.setAttribute("customer", customer);
			request.setAttribute("orderid", orderid);
			request.setAttribute("error", error);
			request.getRequestDispatcher("/user/drawer/showCart.jsp").forward(request, response);
		}else{
			distributor =new  String(distributor.getBytes("iso8859-1"),"utf-8");
			customer =new  String(customer.getBytes("iso8859-1"),"utf-8");
			Cart cart = (Cart)request.getSession().getAttribute("cart");
			if(orderid==""||orderid==null){
				orderid="无";
			}
			Map<String, CartItem> items = (HashMap<String, CartItem>)cart.getItems();
			List<Draworder> draworders = new ArrayList<Draworder>();
			ResultService resultService = BasicFactory.getFactory().getInstance(ResultService.class);
			CheckDrawService checkdrawService = BasicFactory.getFactory().getInstance(CheckDrawService.class);
			DraworderService service = BasicFactory.getFactory().getInstance(DraworderService.class);
			String error="此票中开过票的产品的有：;";
			for(Map.Entry<String, CartItem> me:items.entrySet()){
				me.getValue().getMoney();
				//规格，等级，单价，数量，总额
				String format = me.getValue().getProduct().getFormat();
				String level = me.getValue().getProduct().getLevel();
				double price = me.getValue().getPrice();
				Integer number = me.getValue().getQuantity();
				double sumprice= me.getValue().getMoney();
				double denomination=me.getValue().getCheckdenomination();
				double balance = me.getValue().getBalance();
				
				Draworder draworder = new Draworder();
				draworder.setId(id);
				draworder.setOrderid(orderid);
				draworder.setTime(time);
				draworder.setSalesman(salesman);
				draworder.setCustomer(customer);
				draworder.setDistributor(distributor);
				draworder.setFormat(format);
				draworder.setLevel(level);
				draworder.setPrice(price);
				draworder.setNumber(number);
				draworder.setSumprice(sumprice);
				draworder.setCheckdenomination(denomination);
				draworder.setBalance(balance);
				
				Draworder dorder = service.findDraworder(id, format, level, price);
				if(dorder==null){
					draworders.add(draworder);
					
				}else{
					//开过一摸一样的还开？换张发票吧，哥?（换个订单号吧）
					error=error+format+","+level+","+price+";";
					
				}
				
			}
			
			//distributor,format,level,number
			checkdrawService.updateCheckDrawByDrawnum(draworders);
			//distributor,salesman,sumprice
			resultService.updateResultByNum(draworders);
			service.addDraworder(draworders);
			request.getSession().setAttribute("cart", null);
			request.setAttribute("error", error);
			request.getRequestDispatcher("/servlet/DraworderListServlet?thispage=1").forward(request, response);
		}
		
	}
	private void changeNum(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String did = request.getParameter("did");
		String time = request.getParameter("time");
		
		String distributor = request.getParameter("distributor");
		String salesman = request.getParameter("salesman");
		String customer = request.getParameter("customer");
		distributor = new String(distributor.getBytes("iso8859-1"),"utf-8");
		customer = new String(customer.getBytes("iso8859-1"),"utf-8");
		request.setAttribute("id", did);
		String orderid = request.getParameter("orderid");
		request.setAttribute("orderid", orderid);
		request.setAttribute("time", time);
		request.setAttribute("distributor", distributor);
		request.setAttribute("salesman", salesman);
		request.setAttribute("customer", customer);
		Integer id = Integer.parseInt(request.getParameter("id"));
		double price = Double.parseDouble(request.getParameter("price"));
		String key = id+"-"+price;
		String num =request.getParameter("num");
		double sumprice=Double.parseDouble(request.getParameter("sumprice"));
		double denomination=Double.parseDouble(request.getParameter("denomination"));
		double balance=Double.parseDouble(request.getParameter("balance"));
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		Map<String, CartItem> items = (HashMap<String, CartItem>)cart.getItems();
		CartItem item = items.get(key);
		item.setQuantity(Integer.parseInt(num));
		item.setMoney(sumprice);
		item.setCheckdenomination(denomination);
		item.setBalance(balance);
		request.getRequestDispatcher("/user/drawer/showCart.jsp").forward(request, response);
	}
	
	private void delOneItem(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String did = request.getParameter("did");
		String time = request.getParameter("time");
		String distributor = request.getParameter("distributor");
		String salesman = request.getParameter("salesman");
		String customer = request.getParameter("customer");
		distributor = new String(distributor.getBytes("iso8859-1"),"utf-8");
		customer = new String(customer.getBytes("iso8859-1"),"utf-8");
		String orderid = request.getParameter("orderid");
		request.setAttribute("orderid", orderid);
		request.setAttribute("id", did);
		request.setAttribute("time", time);
		request.setAttribute("distributor", distributor);
		request.setAttribute("salesman", salesman);
		request.setAttribute("customer", customer);
		String key = request.getParameter("id");
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		cart.getItems().remove(key);
		request.getRequestDispatcher("/user/drawer/showCart.jsp").forward(request, response);
	}

	private void delAllItems(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String did = request.getParameter("did");
		String time = request.getParameter("time");
		String distributor = request.getParameter("distributor");
		String salesman = request.getParameter("salesman");
		String customer = request.getParameter("customer");
		distributor = new String(distributor.getBytes("iso8859-1"),"utf-8");
		customer = new String(customer.getBytes("iso8859-1"),"utf-8");
		String orderid = request.getParameter("orderid");
		request.setAttribute("orderid", orderid);
		request.setAttribute("id", did);
		request.setAttribute("time", time);
		request.setAttribute("distributor", distributor);
		request.setAttribute("salesman", salesman);
		request.setAttribute("customer", customer);
		request.getSession().removeAttribute("cart");
		request.getRequestDispatcher("/user/drawer/showCart.jsp").forward(request, response);
	}

	private void addCart(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String id =request.getParameter("id");
		String price = request.getParameter("price");
		String quantity = request.getParameter("number");
		String format = request.getParameter("format");
		String level = request.getParameter("level");
		String sumprice=request.getParameter("sumprice");
		String checkdenomination=request.getParameter("checkdenomination");
		String balance = request.getParameter("balance");
		Cart cart =(Cart)session.getAttribute("cart");
		ProductService service = BasicFactory.getFactory().getInstance(ProductService.class);
		DraworderService draworderService = BasicFactory.getFactory().getInstance(DraworderService.class);
		if(cart==null){
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		Draworder draworder = draworderService.findDraworder(id, format, level, Double.parseDouble(price));
		if(draworder==null){
			cart.addProduct(service.getProductInfo(format, level), Double.parseDouble(price), 
					Integer.parseInt(quantity),Double.parseDouble(sumprice),Double.parseDouble(checkdenomination),Double.parseDouble(balance));
			response.getWriter().print("成功加入开票队列！");
		}else{
			response.getWriter().print("此票已经开过该产品了,请修改相应信息！！");
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
