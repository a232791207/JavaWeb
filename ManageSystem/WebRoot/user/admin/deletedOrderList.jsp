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
  	<c:if test="${sessionScope.admin==null }">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/admin/adminLogin.jsp";
		</script>
	</c:if> 
	<h2 style="text-align:center;">查看已删除订单</h2><hr/>
	<table class="table table-striped center">
		<tr>
			<th>订单号</th>
			<th>销售时间</th>
			<th>业务员</th>
			<th>经销商</th>
			<th>区域</th>
			<th>规格</th>
			<th>等级</th>
			<th>标准单价(元/片)</th>
			<th>直接优惠(元/片)</th>
			<th>一票制运费(元/片)</th>
			<th>特殊优惠(元/片)</th>
			<th>调整后单价(元/片)</th>
			<th>数量(片)</th>
			<th>数量(包)</th>
			<th>体积(立方米)</th>
			<th>销售额(元)</th>
			<th>是否返利</th>
			<th>是否收取调度费</th>
			<th>是否收取运费</th>
			<th>审核状态</th>
		</tr>
		<c:forEach items="${requestScope.page.list }" var="order">
			<tr>
				<td><c:out value="${order.orderid }" /></td>
				<td><c:out value="${order.time }" /></td>
				<td><c:out value="${order.salesman}" /></td>
				<td><c:out value="${order.distributor }" /></td>
				<td><c:out value="${order.area }" /></td>
				<td><c:out value="${order.format }" /></td>
				<td><c:out value="${order.level }" /></td>
				<td><c:out value="${order.price }" /></td>
				<td><c:out value="${order.directinc }" /></td>
				<td><c:out value="${order.freight }" /></td>
				<td><c:out value="${order.specialinc }" /></td>
				<td><c:out value="${order.realprice }" /></td>
				<td><c:out value="${order.number }" /></td>
				<td><c:out value="${order.bags }" /></td>
				<td><c:out value="${order.volume }" /></td>
				<td><c:out value="${order.sumprice }" /></td>
				<td><c:out value="${order.ifprofit }" /></td>
				<td><c:out value="${order.fare }" /></td>
				<td><c:out value="${order.freight2 }" /></td>
				<td>
					<font color="blue"><c:out value="${order.statement }" /></font>
				</td>
		  	</tr>
		</c:forEach>
	</table>
	
	<div style="text-align:center;">
	共${requestScope.page.countpage }页
	<a href="${pageContext.request.contextPath }/servlet/DeletedOrderListServlet?thispage=${requestScope.page.firstpage}">首页</a>
	<a href="${pageContext.request.contextPath }/servlet/DeletedOrderListServlet?thispage=${requestScope.page.prepage}">上一页</a>
	
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
			<a href="${pageContext.request.contextPath }/servlet/DeletedOrderListServlet?thispage=${i}">${i }</a>
		</c:if>
	</c:forEach>
	<!-- 分页逻辑结束 -->
	
	<a href="${pageContext.request.contextPath }/servlet/DeletedOrderListServlet?thispage=${requestScope.page.nextpage}">下一页</a>
	<a href="${pageContext.request.contextPath }/servlet/DeletedOrderListServlet?thispage=${requestScope.page.lastpage}">尾页</a>
	</div>  
  </body>
</html>
