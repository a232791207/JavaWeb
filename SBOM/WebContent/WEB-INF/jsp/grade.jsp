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
		<title>等级与优惠</title>
		<link rel="stylesheet" href="css/grade.css" />
	</head>
	<body>
		<div class="all">
			<div class="head">
			 <div class="one">
			 	<img src="img/images/qr.png" width="100%" height="auto"/>
			 </div>
			 <div class="two">
			 	<label>用户名</label><br/><label>昵称</label>
			 </div>
		</div>
		<div class="tab">
			<ul>
				<li><a href="basicInfo.html">基本资料</a></li>
				<li><a href="grade.html">等级优惠</a></li>
				<li><a href="update.html">修改密码</a></li>
			</ul>
		</div>
		<div class="cent">
			
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
