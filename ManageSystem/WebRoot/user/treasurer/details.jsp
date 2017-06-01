<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
  	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.8.1.min.js"></script>
	<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" type="text/css"/>
	<link href="/ManageSystem/css/basicStyle.css" rel="stylesheet" type="text/css" media="all"/>
  </head>
  
  <body>
  	<c:if test="${sessionScope.clerk.type!='财务员' }">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/treasurer/treasurerLogin.jsp";
		</script>
	</c:if> 
	<h2 style="text-align:center;">详细信息</h2><hr/>
	<div style="text-align:center;">
	</div><br/>
	<table class="table table-striped center">
		<tr>
			<th>客户</th>	
			<th>累计回款金额(元)</th>
			<th>累计开票金额(元)</th>		
		</tr>
		<c:forEach items="${requestScope.list }" var="array">
			<tr>
				<td><c:out value="${array[0] }" /></td>
				<td><c:out value="${array[1] }" /></td>
				<td><c:out value="${array[2] }" /></td>
		  	</tr>
		</c:forEach>
	</table>

  </body>
  
</html>
