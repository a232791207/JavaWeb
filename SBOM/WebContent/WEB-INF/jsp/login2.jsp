<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>登录</title>
		<link rel="stylesheet" href="css/login.css" type="text/css"/>
		<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js" ></script>
		<script type="text/javascript">
		
		
</script>
		
	</head>
	<body>
		<div class="nav">
			<div class="logo"><img src="img/images/11282817.png" height="auto" width="100%"/></div>
			<div class="login">
				<div class="login-info">
				<center><form action="" method="post">
					<font size="20">用户名：</font><input type="text" name="userName" height="20px" placeholder="请输入用户名" style="width: 300px;height: 40px;font-size: 30px;"/><br />
					<font size="20">密&nbsp;&nbsp;&nbsp;码：</font><input type="password" placeholder="请输入密码" name="loginPassword" style="width: 300px;height: 40px;font-size: 30px;"/><br />
					<input type="submit" value="登录" style="width: 100px;height: 30px; font-size: 20px;"/>
				</form></center>
				</div>
			</div>
		</div>
	</body>
</html>
