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
  	<c:if test="${sessionScope.admin==null}">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/admin/adminLogin.jsp";
		</script>
	</c:if> 
	<h2 style="text-align:center;">回款管理</h2><hr>
	<table class="table table-striped center">
		<tr>
			<th>回款日期</th>
			<th>回款单位</th>
			<th>经销商</th>
			<th>业务员</th>
			<th>回款金额(元)</th>
			<th>回款方式</th>
			<th>单据号</th>
			<th>贴息(元)</th>
		</tr>
		<c:forEach items="${requestScope.page.list }" var="payment">
			<tr>
				<td><c:out value="${payment.time }" /></td>
				<td><c:out value="${payment.customer }" /></td>
				<td><c:out value="${payment.distributor }" /></td>
				<td><c:out value="${payment.salesman }" /></td>
				<td><c:out value="${payment.money }" /></td>
				<td><c:out value="${payment.type }" /></td>
				<td><c:out value="${payment.orderid }" /></td>
				<td><c:out value="${payment.interest }" /></td>
		  	</tr>
		</c:forEach>
	</table>

    <div style="text-align:center;">
	共${requestScope.page.countpage }页
	<a href="${pageContext.request.contextPath }/servlet/DeletedPaymentListServlet?thispage=${requestScope.page.firstpage}">首页</a>
	<a href="${pageContext.request.contextPath }/servlet/DeletedPaymentListServlet?thispage=${requestScope.page.prepage}">上一页</a>
	
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
			<a href="${pageContext.request.contextPath }/servlet/DeletedPaymentListServlet?thispage=${i}">${i }</a>
		</c:if>
	</c:forEach>
	<!-- 分页逻辑结束 -->
	<a href="${pageContext.request.contextPath }/servlet/DeletedPaymentListServlet?thispage=${requestScope.page.nextpage}">下一页</a>
	<a href="${pageContext.request.contextPath }/servlet/DeletedPaymentListServlet?thispage=${requestScope.page.lastpage}">尾页</a>
	</div>
  </body>
</html>
