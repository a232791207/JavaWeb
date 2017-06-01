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
		<title>代理推广</title>
		<link rel="stylesheet" href="css/agentExtension.css" />
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
			<div class="register">
				<div class="register-con">
					<form action="" method="post">
						<h1>请注册代理</h1>
						<ul>
							<li><input type="text"  placeholder="游戏ID" name="id" style="width:200px ;height: 30px;"/></li>
							<li><input type="text"  placeholder="用户名" name="userName" style="width:200px ;height: 30px;"/></li>
							<li><input type="text"  placeholder="密码" name="loginPassword" style="width:200px ;height: 30px;"/></li>
						</ul>
						<input type="submit" value="注册" style="width:80px ;height:30px ;"/>
					</form>
				</div>
			</div>
			<div class="find">
				<div class="find-info">
					<input type="text" placeholder="请输入代理用户名" name="userName" style="width:400px ;height: 40px; font-size: 20px;"/>
					<a href="#"><input type="button" value="查询" style="width:60px ;height: 40px;"/></a>
				</div>
			</div>
			<div class="checkes">
				<div class="checkes-cen">
					<ul> 
						<li>用户名</li>
						<li>手机号</li>
						<li>日消费</li>
						<li>总消费</li>
						<li>操作</li>
					</ul>
				</div>
				<div class="checkes-con">
					<table width="100%">
						<tr>
							<td>lyc123</td>
							<td>12312412421</td>
							<td>1000</td>
							<td>1500</td>
							<td><a href="#"><input type="button" value="详情"/></a></td>
						</tr>
					</table>
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
