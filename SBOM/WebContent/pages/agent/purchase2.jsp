<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>购买</title>
		<link rel="stylesheet" href="css/purchase.css" />
	</head>
	<body>
		<div class="header">
			<div class="header-one">
				<ul>
					<li><input type="checkbox" value="100" name="diamondNum" style="width: 40px;height: 40px;"/><label style="font-size: 35px;">100钻石</label></li>
					<li><input type="checkbox" value="300" name="diamondNum" style="width: 40px;height: 40px;"/><label style="font-size: 35px;">300钻石</label></li>
					<li><input type="checkbox" value="500" name="diamondNum" style="width: 40px;height: 40px;"/><label style="font-size: 35px;">500钻石</label></li>
				</ul>

			</div>
			<div class="header-two">
				<div class="two-info">
					<a href="#"><input type="button" value="支付宝支付" style="width: 300px;height: 80px;font-size: 40px;"/></a>
					<a href="#"><input type="button" value="余额支付" style="width: 300px;height: 80px;font-size: 40px;"/></a>
				</div>
			</div>
			<div class="cent"></div>
			<div class="info">
				<label style="font-size: 30px;">为保障用户充值,请开通余额自动充值钻石</label><a href="#"><input type="button" value="开通" style="width: 60px;height: 40px;font-size: 20px;"/></a>
			</div>
		</div>
		<div class="foot-list">
		<ul>
			<li><a href="index2.jsp">首页</a></li>
			<li><a href="${pageContext.request.contextPath }/pages/agent/subordinatePage2?thispage=1&supUserName=${sessionScope.user.id }&days=0&subLevel=0">用户</a></li>
			<li><a href="#">推广</a></li>
			<li><a href="billAction2!diamondBillPageAction?dthispage=1&stime=&etime=&userName=${sessionScope.user.userName }">钱包</a></li>
			<li><a href="basicInfo2.jsp">我的</a></li>
		</ul>
    </div>
	</body>
</html>
