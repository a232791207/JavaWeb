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
    
    	<h2 style="text-align:center;">查看已删除开票信息</h2><hr/>
		<div style="text-align:center;">
		</div><br/>   	
	      
	      <div class="table-responsive" style="overflow-y:auto;">	
	    	<table class="table table-striped center">
	         
		  		<tr>
		  			<th>经销商</th>
		  			<th>业务员</th>
		  			<th>发票号</th>
		  			<th>开票单位</th>
		  			<th>开票日期</th>
		  			<th>规格</th>
		  			<th>等级</th>
		  			<th>单价</th>
		  			<th>数量</th>
		  			<th>总额</th>
		  		</tr>
		 
		  		<c:forEach items="${requestScope.page.list }" var="draworder">
		  		
		  			<tr>
		  				<td><c:out value="${draworder.distributor }" /></td>
		  				<td><c:out value="${draworder.salesman }" /></td>
		  				<td><c:out value="${draworder.id }" /></td>
		  				<td><c:out value="${draworder.customer}" /></td>
		  				<td><c:out value="${draworder.time }" /></td>
		  				<td><c:out value="${draworder.format }" /></td>
		  				<td><c:out value="${draworder.level }" /></td>
		  				<td><c:out value="${draworder.price }" /></td>
		  				<td><c:out value="${draworder.number }" /></td>
		  				<td><c:out value="${draworder.sumprice}" /></td>
		  			</tr>
		  
		  		</c:forEach>
	  		</table>	
	  	   </div>  	
	  		
	  	<div style="text-align:center;">
  		共${requestScope.page.countpage }页
  		<a href="${pageContext.request.contextPath }/servlet/DeletedDraworderListServlet?thispage=${requestScope.page.firstpage}">首页</a>
  		<a href="${pageContext.request.contextPath }/servlet/DeletedDraworderListServlet?thispage=${requestScope.page.prepage}">上一页</a>		
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
  				<a href="${pageContext.request.contextPath }/servlet/DeletedDraworderListServlet?thispage=${i}">${i }</a>
  			</c:if>
  		</c:forEach>
  		<!-- 分页逻辑结束 -->
  		
  		<a href="${pageContext.request.contextPath }/servlet/DeletedDraworderListServlet?thispage=${requestScope.page.nextpage}">下一页</a>
  		<a href="${pageContext.request.contextPath }/servlet/DeletedDraworderListServlet?thispage=${requestScope.page.lastpage}">尾页</a>
  		</div>
  </body>
</html>
