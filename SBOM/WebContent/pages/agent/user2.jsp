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
					<li><a href="${pageContext.request.contextPath }/pages/agent/subordinatePage2?thispage=1&supUserName=${sessionScope.user.id }&days=0&subLevel=0">用户详情</a></li>
					<li><a href="${pageContext.request.contextPath }/pages/agent/rechargeApply2.action">充值申请</a></li>
				</ul>
				</div>
			</div>
			<div class="inputid">
				<center><input name="" type="text" style="width:300px;height:40px;"/><a href="subordinatePage2!subordinateByID"><input type="button" value="查询" style="width:80px;height:40px;"/></a></center>
			</div>
			<div class="dates">
				<div class="deats-num">
					<ul>
						<li><a href="subordinatePage2.action?thispage=1&supUserName=${sessionScope.user.userName }&days=0&subLevel=0">3日未登录</a></li>
						<li><a href="subordinatePage2.action?thispage=1&supUserName=${sessionScope.user.userName }&days=0&subLevel=0">7日未登录</a></li>
						<li><a href="subordinatePage2.action?thispage=1&supUserName=${sessionScope.user.userName }&days=0&subLevel=0">30日未登录</a></li>
						<li><a href="subordinatePage2!noConsumption?supUserName=${sessionScope.user.userName }&subLevel=0">未消费</a></li>
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
						<c:forEach items="${requestScope.page.list }" var="subordinate">
							<tr>
								<td>${subordinate.nickName }</td>
								<td>${subordinate.subUserName }</td>
								<td>${subordinate.sumConsumption }</td>
								<td><a href="#"><input type="button" value="解绑" style="width:60px;height:30px;"/></a></td>
								<td>购钻石</td>
							</tr>
							</c:forEach>
						</table>
					</div>
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
