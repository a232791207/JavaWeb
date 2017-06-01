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
		<title>充值</title>
		<link rel="stylesheet" href="css/recharge.css" />
	</head>
	<body>
		<div class="head">
			<div class="head-com">
				<input type="number" placeholder="请输入银行卡号" name="" style="width: 800px;height: 60px; font-size: 30px;" min="0" max="9"/>
			</div>
		</div>
		<div class="monery">
			<div class="monery-num">
				<label style="font-size: 30px;">金额</label>&nbsp;<input type="number" name="" style="width:710px ;height: 50px; font-size: 30px;" value="0"/>
			</div>
		</div>
		<div class="next">
			<div class="next-con">
				<a href="#"><input type="button" value="下一步" style="width: 800px;height: 60px; font-size: 30px;"/></a>
			</div>
		</div>
		<div class="cent">
			<div class="cent-dete">
				<label style="font-size: 30px;">支付密码</label>&nbsp;<input type="password" name="" style="width: 300px;height: 40px;"/>
			</div>
			<div class="cen-yes">
				<a href="#"><input type="button" value="确定" style="width: 250px; height: 60px; font-size: 30px;"/></a>
			</div>
		</div>
		<div class="foot-list">
		<ul>
			<li><a href="#">首页</a></li>
			<li><a href="#">用户</a></li>
			<li><a href="#">推广</a></li>
			<li><a href="#">钱包</a></li>
			<li><a href="#">我的</a></li>
		</ul>
    </div>
	</body>
</html>
