<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>知奇科技</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script language="javascript">
    	window.location.href = "pages/agent/index.jsp"
	</script>
  </head>
  
  <body>
    This is my JSP page. <br>
    Go to <a href="pages/index.html">/pages/agent/index.jsp</a>
  </body>
</html>
