<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
  	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.8.1.min.js"></script>
	<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" type="text/css"/>
	<link href="/ManageSystem/css/basicStyle.css" rel="stylesheet" type="text/css" media="all"/>
	<script type="text/javascript">
		function del(id){
			if(confirm("确定要删除吗？")){
				window.location.href="${pageContext.request.contextPath }/servlet/DeleteBasicFareServlet?id="+id;
			}
		}
	</script>
  </head>
  
  <body>
  	<c:if test="${sessionScope.admin==null }">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/treasurer/treasurerLogin.jsp";
		</script>
	</c:if> 
	<h2 style="text-align:center;">调度费管理</h2><hr/>
	<div style="text-align:center;">
	<div style="float:left;">
	<input type="button" id="print" onclick="window.location.href='${pageContext.request.contextPath }/servlet/PrintBasicfareListServlet?from=1&&to=${requestScope.page.countpage}' " value="打印"/>
	</div>
	<input style="width:85px;height:30px;" type="button" value="新增调度费" onclick="addBasicFare()" /><br/>
	</div><br/>
	<table class="table table-striped center">
		<tr>
			<th>区域</th>
			<th>调度费单价(元/立方米)</th>
			<th>修改</th>
			<th>删除</th>
		</tr>
		<c:forEach items="${requestScope.page.list }" var="fares">
			<tr>
				<td><c:out value="${fares.area }" /></td>
				<td><c:out value="${fares.fare }" /></td>
				<td>
					<a href="${pageContext.request.contextPath }/user/treasurer/updateBasicFare.jsp?id=${fares.id}">修改</a>
				</td>
				<td>
					<a href="javascript:del(${fares.id})">删除</a>
				</td>
		  	</tr>
		</c:forEach>
	</table>

	<div style="text-align:center;">
	共${requestScope.page.countpage }页
	<a href="${pageContext.request.contextPath }/servlet/BasicFareListServlet?thispage=${requestScope.page.firstpage}">首页</a>
	<a href="${pageContext.request.contextPath }/servlet/BasicFareListServlet?thispage=${requestScope.page.prepage}">上一页</a>
	
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
			<a href="${pageContext.request.contextPath }/servlet/BasicFareListServlet?thispage=${i}">${i }</a>
		</c:if>
	</c:forEach>
	<!-- 分页逻辑结束 -->
	<a href="${pageContext.request.contextPath }/servlet/BasicFareListServlet?thispage=${requestScope.page.nextpage}">下一页</a>
	<a href="${pageContext.request.contextPath }/servlet/BasicFareListServlet?thispage=${requestScope.page.lastpage}">尾页</a>
	</div>
  </body>
  
  <script type="text/javascript">
  	function addBasicFare(){
  		window.location.href="${pageContext.request.contextPath }/user/treasurer/addBasicFare.jsp";
  	}
  </script>
  
</html>

