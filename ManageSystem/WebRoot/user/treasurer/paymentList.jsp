<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
  	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.8.1.min.js"></script>
	<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" type="text/css"/>
	<link href="/ManageSystem/css/basicStyle.css" rel="stylesheet" type="text/css" media="all"/>
	<script type="text/javascript">
		function del(id,distributor){
			if(confirm("确定要删除吗？")){
    			window.location.href="${pageContext.request.contextPath }/servlet/findPaymentServlet?id="+id+"&thispage=1&distributor="+distributor;
    		}
		
		}
	</script>
  </head>
  
  <body>
  	<c:if test="${sessionScope.clerk.type!='财务员'&&sessionScope.admin==null}">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/treasurer/treasurerLogin.jsp";
		</script>
	</c:if> 
	<h2 style="text-align:center;">回款管理</h2><hr>
	<div style="text-align:center;">
	<c:if test="${sessionScope.clerk.type=='财务员'}">
		<div style="float:left;">
	<input type="button" id="print" onclick="window.location.href='${pageContext.request.contextPath }/servlet/PrintPaymentListServlet?from=1&&to=${requestScope.page.countpage}' " value="打印"/>
	</div>
		<input style="width:70px;height:30px;" type="button" value="新增回款" onclick="addPrice()" /><br><br>
	</c:if> 
	<form action="${pageContext.request.contextPath }/servlet/findPaymentServlet?thispage=1" method="post">
		经销商：<input type="text" name="distributor" id="distributor" value="${requestScope.distributor }">
		<input type="submit" value="查询" >
	</form>
	</div><br/>
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
			<c:if test="${sessionScope.admin!=null}">
				<th>操作</th>
			</c:if>
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
				<c:if test="${sessionScope.admin!=null}">
				<td><a href="javascript:del(${payment.id},'${requestScope.distributor}')">删除</a></td>
				</c:if>
		  	</tr>
		</c:forEach>
	</table>

    <div style="text-align:center;">
	共${requestScope.page.countpage }页
	<a href="${pageContext.request.contextPath }/servlet/findPaymentServlet?thispage=${requestScope.page.firstpage}&distributor=${requestScope.distributor}">首页</a>
	<a href="${pageContext.request.contextPath }/servlet/findPaymentServlet?thispage=${requestScope.page.prepage}&distributor=${requestScope.distributor}">上一页</a>
	
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
			<a href="${pageContext.request.contextPath }/servlet/findPaymentServlet?thispage=${i}&distributor=${requestScope.distributor}">${i }</a>
		</c:if>
	</c:forEach>
	<!-- 分页逻辑结束 -->
	<a href="${pageContext.request.contextPath }/servlet/findPaymentServlet?thispage=${requestScope.page.nextpage}&distributor=${requestScope.distributor}">下一页</a>
	<a href="${pageContext.request.contextPath }/servlet/findPaymentServlet?thispage=${requestScope.page.lastpage}&distributor=${requestScope.distributor}">尾页</a>
	</div>
  </body>
  <script type="text/javascript">
  	function addPrice(){
  		window.location.href="${pageContext.request.contextPath }/user/treasurer/addPayment.jsp";
  	}
  </script>
</html>
