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
		<title>提现</title>
		<link rel="stylesheet" href="css/carryMoney.css" />
	</head>
	<body>
		<div class="head">
			<div class="head-y">
				<input type="number" placeholder="请输入银行卡号" name="" style="width: 800px;height: 60px; font-size: 30px;"/>
			</div>
			<div class="toMoney">
				<input type="number" placeholder="请输入提现金额" name="" style="width: 800px;height: 60px; font-size: 30px;"/>
			</div>
			<div class="yes">
				<a href="#"><input type="button" value="3个工作日到账,确认提现" style="width: 600px;height:80px ;font-size: 40px;"/></a>
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
