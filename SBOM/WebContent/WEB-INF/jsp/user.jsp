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
		<title>用户详情</title>
		<link rel="stylesheet" href="css/user.css"  type="text/css"/> 
	</head>
	<body>

			<div class="heads">
				<div class="heads-con">
				<ul>
					<li><a href="#">用户详情</a></li>
					<li><a href="#">充值申请</a></li>
				</ul>
				</div>
			</div>
			<div class="inputid">
				<center><input name="" type="text" style="width:300px;height:40px;"/><a href="#"><input type="button" value="查询" style="width:80px;height:40px;"/></a></center>
			</div>
			<div class="dates">
				<div class="deats-num">
					<ul>
						<li><a href="#">3日未登录</a></li>
						<li><a href="#">7日未登录</a></li>
						<li><a href="#">30日未登录</a></li>
						<li><a href="#">未消费</a></li>
					</ul>
					
				</div>
			</div>
			<div class="menu">
				<div class="menu-cent">
					<ul>
						<li>昵称</li>
						<li>ID</li>
						<li>总消费</li>
						<li>操作</li>
						<li>详情</li>
					</ul>
				</div>
				<div class="tab">
					<div class="tab-cen">
						<table>
							<tr>
								<td>啊啊</td>
								<td>123</td>
								<td>555</td>
								<td><a href="#"><input type="button" value="解绑" style="width:60px;height:30px;"/></a></td>
								<td>购钻石</td>
							</tr>
						</table>
					</div>
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
