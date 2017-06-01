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
		<title>用户推广</title>
		<link rel="stylesheet" href="css/userExtension .css" />
	</head>
	<body>
		<div class="heads">
				<div class="heads-con">
				<ul>
					<li><a href="#">用户推广</a></li>
					<li><a href="#">代理推广</a></li>
				</ul>
				</div>
			</div>
			<div class="cent">
				<div class="cent-in" >
					<img src="img/images/qr.png" width="400px" height="400px"/>
					
				</div>
			</div>
			<div class="url">
				<div class="url-copy">
				<input type="text" readonly="readonly" name="" style="font-size: 50px; width: 600px;height: 75px;"/>&nbsp;&nbsp;&nbsp;
				<a href="#"><input type="button" value="复制" style="font-size: 50px; width: 130px;height: 80px;"/></a>
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
