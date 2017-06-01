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
		<title>修改支付密码</title>
		<link rel="stylesheet" href="css/upPayPassword.css" />
	</head>
	<body>
		<div class="head">
			<div class="head-btu">
				<table border="1">
					<form action="" method="post">
						<tr>
							<td colspan="2">修改支付密码(不要超过11位哦！)</td>
						</tr>
						<tr>
							<td>原支付密码</td>
							<td><input type="text" name="lo" style="width: 400px;height: 40px;"/></td>
						</tr>
						<tr>
							<td>新支付密码</td>
							<td><input type="text" name="" style="width: 400px;height: 40px;"/></td>
						</tr>
						<tr>
							<td>确认新密码</td>
							<td><input type="text" name="" style="width: 400px;height: 40px;"/></td>
						</tr>
						<tr>
							<td>提交</td>
							<td><input type="submit" name="" value="确认" style="width: 400px;height: 40px;font-size: 20px;"/></td>
						</tr>
					
				</form>
				</table>
				
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
