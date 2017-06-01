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
  	<c:if test="${sessionScope.clerk.type!='财务员'&& sessionScope.admin==null }">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/treasurer/treasurerLogin.jsp";
		</script>
	</c:if> 
	<h2 style="text-align:center;">运费信息查询</h2><hr/>
	<c:if test="${sessionScope.admin!=null}">
	<a href="${pageContext.request.contextPath }/servlet/BasicFareListServlet?thispage=1" style="float: right;">运费管理</a>
	</c:if>
	<c:if test="${sessionScope.clerk.type=='财务员'}">
	<div style="float:left;">
	<input type="button" id="print" onclick="window.location.href='${pageContext.request.contextPath }/servlet/PrintFreightListServlet?from=1&&to=${requestScope.page.countpage}' " value="打印"/>
	</div><br/><br/>
	</c:if>
	
	<table class="table table-striped center">
		<tr>
			<th>订单号</th>
			<th>区域</th>
			<th>运费单价(元/立方米)</th>
			<th>体积(立方米)</th>
			<th>总运费(元)</th>
			<c:if test="${sessionScope.admin!=null}">
				<th>操作</th>
			</c:if>
		</tr>
		<c:forEach items="${requestScope.page.list }" var="fares">
			<tr>
				<td><c:out value="${fares.id }" /></td>
				<td><c:out value="${fares.area }" /></td>
				<td><c:out value="${fares.fare }" /></td>
				<td><c:out value="${fares.num }" /></td>
				<td><c:out value="${fares.sumfare }" /></td>
				<c:if test="${sessionScope.admin!=null}">
					<td><a href="${pageContext.request.contextPath }/servlet/Freight2ListServlet?id=${fares.id}">删除</a></td>
				</c:if>
		  	</tr>
		</c:forEach>
	</table>

	<div style="text-align:center;">
	共${requestScope.page.countpage }页
	<a href="${pageContext.request.contextPath }/servlet/Freight2ListServlet?thispage=${requestScope.page.firstpage}">首页</a>
	<a href="${pageContext.request.contextPath }/servlet/Freight2ListServlet?thispage=${requestScope.page.prepage}">上一页</a>
	
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
			<a href="${pageContext.request.contextPath }/servlet/Freight2ListServlet?thispage=${i}">${i }</a>
		</c:if>
	</c:forEach>
	<!-- 分页逻辑结束 -->
	<a href="${pageContext.request.contextPath }/servlet/Freight2ListServlet?thispage=${requestScope.page.nextpage}">下一页</a>
	<a href="${pageContext.request.contextPath }/servlet/Freight2ListServlet?thispage=${requestScope.page.lastpage}">尾页</a>
	</div>
  </body>
</html>
