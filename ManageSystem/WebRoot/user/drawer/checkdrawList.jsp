<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
    <link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" type="text/css" />
  	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.8.1.min.js"></script>
  	<link href="/ManageSystem/css/basicStyle.css" rel="stylesheet" type="text/css" media="all"/>
  </head>
  
  <body>
  	<c:if test="${sessionScope.clerk.type!='开票员'&&sessionScope.clerk.type!='财务员'}">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/drawer/drawerLogin.jsp";
		</script>
	</c:if> 
	<h2 style="text-align:center;">经销商开票明细</h2><hr/>
	<table class="table table-striped center">
		<tr>
			<th>经销商</th>
			<th>规格</th>
			<th>等级</th>
			<th>出库数(片)</th>
			<th>已开票数(片)</th>
			<th>未开票数(片)</th>
		</tr>
		<c:forEach items="${requestScope.page.list }" var="checkdraw">
			<tr>
				<td><c:out value="${checkdraw.distributor }" /></td>
				<td><c:out value="${checkdraw.format }" /></td>
				<td><c:out value="${checkdraw.level }" /></td>
				<td><c:out value="${checkdraw.number }" /></td>
				<td><c:out value="${checkdraw.drawnum }" /></td>
				<td><c:out value="${checkdraw.undrawnum }" /></td>
		  	</tr>
		</c:forEach>
	</table>
	
	<div style="text-align:center;">
	共${requestScope.page.countpage }页
	<a href="${pageContext.request.contextPath }/servlet/findCheckDrawServlet?thispage=${requestScope.page.firstpage}&distributor=${requestScope.distributor}">首页</a>
	<a href="${pageContext.request.contextPath }/servlet/findCheckDrawServlet?thispage=${requestScope.page.prepage}&distributor=${requestScope.distributor}">上一页</a>
	
	<!-- 分页逻辑开始 -->
	<c:if test="${requestScope.page.countpage<=5 }">
		<c:set var="begin" value="1" scope="page"></c:set>
		<c:set var="end" value="${requestScope.page.countpage }" scope="page"></c:set>
	</c:if>
	<c:if test="${requestScope.page.countpage>5 }">
		<c:choose>
			<c:when test="${requestScope.page.thispage<=3 }">
				<c:set var="begin" value="1" scope="page"></c:set>
				<c:set var="end" value="5" scope="page"></c:set>
			</c:when>
			<c:when test="${requestScope.page.thispage>=requestScope.page.countpage-2 }">
				<c:set var="begin" value="${requestScope.page.countpage-4 }" scope="page"></c:set>
				<c:set var="end" value="${requestScope.page.countpage }" scope="page"></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="begin" value="${requestScope.page.thispage-2 }" scope="page"></c:set>
				<c:set var="end" value="${requestScope.page.thispage+2 }" scope="page"></c:set>
			</c:otherwise>
		</c:choose>
	</c:if>
	<c:forEach begin="${begin }" end="${end }" step="1" var="i">
		<c:if test="${requestScope.page.thispage == i }">
			${i }
		</c:if>
		<c:if test="${requestScope.page.thispage != i }">
			<a href="${pageContext.request.contextPath }/servlet/findCheckDrawServlet?thispage=${i}&distributor=${requestScope.distributor}">${i }</a>
		</c:if>
	</c:forEach>
	<!-- 分页逻辑结束 -->
	<a href="${pageContext.request.contextPath }/servlet/findCheckDrawServlet?thispage=${requestScope.page.nextpage}&distributor=${requestScope.distributor}">下一页</a>
	<a href="${pageContext.request.contextPath }/servlet/findCheckDrawServlet?thispage=${requestScope.page.lastpage}&distributor=${requestScope.distributor}">尾页</a>
	</div>
  </body>
</html>
