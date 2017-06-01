<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理员登录页面</title>
<link href="/ManageSystem/css/loginStyle.css" rel="stylesheet" type="text/css" media="all" />
</head>

<body>
	<c:if test="${sessionScope.admin==null }">
		<div class="main">
			<div class="login">
			   <div class="inset">
				 <h1>管理员登录</h1>
				 <font color="red">${msg }</font>
				 <form action="${pageContext.request.contextPath }/servlet/AdminLoginServlet" method="post">
				    <div>
				        <span><label>用户名</label></span>
				        <span><input type="text" class="textbox" name="username" autocomplete="off"></span>
				    </div>
				    <div>
						<span><label>密码</label></span>
					    <span><input type="password" class="password" name="password" autocomplete="off"></span>
					 </div>
					 <div class="sign">
						<div class="submit">
						  <input type="submit" value="登录" >
						</div>
						<span class="forget-pass">
							<a href="${pageContext.request.contextPath }/index.jsp">返回首页</a>
						</span>
							<div class="clear"> </div>
					</div>
			     </form>
			   </div>
			</div>
		</div>	
		<div class="copy-right">
			<p>Copyright &copy; 2016.WUST All rights reserved.</p>
		</div>
	</c:if>
		<c:if test="${sessionScope.admin!=null }">
			<c:redirect url="/user/admin/adminManage.jsp"></c:redirect>
		</c:if>  
</body>
</html>