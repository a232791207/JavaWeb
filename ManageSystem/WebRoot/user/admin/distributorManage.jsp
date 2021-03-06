<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
	<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" type="text/css"/>
	<link href="/ManageSystem/css/basicStyle.css" rel="stylesheet" type="text/css" media="all"/>
	<script type="text/javascript">
		function del(id){
			if(confirm("确定要删除吗？")){
    			window.location.href="${pageContext.request.contextPath }/servlet/DeleteDistributorServlet?id="+id;
    		}
		
		}
	</script>
  </head>
  
  <body>
    <c:if test="${sessionScope.admin==null }">
    	<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/admin/adminLogin.jsp";
		</script>
    </c:if>
    
    <c:if test="${sessionScope.admin!=null }">
    	<h2 style="text-align:center;">经销商管理</h2><hr/>
		<div style="text-align:center;"><input style="width:85px;height:30px;" type="button" value="新增经销商" onclick="window.location.href='${pageContext.request.contextPath }/user/admin/addDistributor.jsp'"/><br/><br>
		<form action="${pageContext.request.contextPath }/servlet/SelectDistributor?thispage=1" name="SelectDistributor" method="post">
			经销商姓名：<input type="text" name="name" id="name" value="${param.name }"/>
			<input style="width:50px;height:30px;" type="submit" value="查询"/>
		</form>
		</div><br>
	    	<table class="table table-striped center">
		  		<tr>
		  		   <!--  <th>经销商编号</th> -->
		  			<th>经销商姓名</th>
		  			<th>区域</th>
		  			<th>业务员</th>
		  			<th>联系方式</th>
		  			<th>信用额度(元)</th>
		  			<th>修改</th>
		  			<th>删除</th>
		  		</tr>
		  		<c:forEach items="${requestScope.page.list }" var="distributor">
		  			<tr>
		  			<!--   <td><c:out value="${distributor.id }" /></td> -->  
		  				<td><c:out value="${distributor.name }" /></td>
		  				<td><c:out value="${distributor.area }" /></td>
		  				<td><c:out value="${distributor.salesman }" /></td>
		  				<td><c:out value="${distributor.phonenumber }" /></td>
		  				<td><c:out value="${distributor.creditlines }" /></td>
		  				<td><a href="${pageContext.request.contextPath }/servlet/DistributorInfoServlet?id=${distributor.id}">修改</a></td>
		  				<td><a href="javascript:del(${distributor.id})">删除</a></td>
		  			</tr>
		  		</c:forEach>
	  		</table>
	  		
	  	<div style="text-align:center;">
  		共${requestScope.page.countpage }页
  		<a href="${pageContext.request.contextPath }/servlet/SelectDistributor?thispage=${requestScope.page.firstpage}&name=${param.name }">首页</a>
  		<a href="${pageContext.request.contextPath }/servlet/SelectDistributor?thispage=${requestScope.page.prepage}&name=${param.name }">上一页</a>
  		
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
  				<a href="${pageContext.request.contextPath }/servlet/SelectDistributor?thispage=${i}&name=${param.name }">${i }</a>
  			</c:if>
  		</c:forEach>
  		<!-- 分页逻辑结束 -->
  		
  		<a href="${pageContext.request.contextPath }/servlet/SelectDistributor?thispage=${requestScope.page.nextpage}&name=${param.name }">下一页</a>
  		<a href="${pageContext.request.contextPath }/servlet/SelectDistributor?thispage=${requestScope.page.lastpage}&name=${param.name }">尾页</a>
  		</div>
    </c:if>
  </body>
</html>
