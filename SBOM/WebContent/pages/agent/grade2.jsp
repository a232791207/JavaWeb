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
			 	<label>${sessionScope.user.userName }</label><br/><label>${sessionScope.user.nickName }</label>
			 </div>
		</div>
		<div class="tab">
			<ul>
				<li><a href="basicInfo2.jsp">基本资料</a></li>
				<li><a href="grade2.jsp">等级优惠</a></li>
				<li><a href="update2.jsp">修改密码</a></li>
			</ul>
		</div>
		<div class="cent">
			
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
