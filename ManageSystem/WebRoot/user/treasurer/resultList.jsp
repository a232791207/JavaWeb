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
  	<c:if test="${sessionScope.clerk.type!='财务员'&& sessionScope.admin==null}">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/treasurer/treasurerLogin.jsp";
		</script>
	</c:if> 
	<h2 style="text-align:center;">结余信息查询</h2><hr/>
	<div style="text-align:center;">
	<c:if test="${sessionScope.clerk.type=='财务员'}">
	<div style="float:left;">
	<input type="button" id="print" onclick="window.location.href='${pageContext.request.contextPath }/servlet/PrintResultListServlet?from=1&&to=${requestScope.page.countpage}' " value="打印"/>
	</div>
	<input style="width:70px;height:30px;" type="button" value="新增返利" onclick="addInterest()" />&nbsp;&nbsp;
	<input style="width:70px;height:30px;" type="button" value="新增赔偿" onclick="addPayback()"/><br/><br/>
	</c:if>
	<form action="${pageContext.request.contextPath }/servlet/findResultServlet?thispage=1" method="post">
		经销商：<input type="text" name="distributor" id="distributor" value="${requestScope.distributor }">
		<input type="submit" value="查询" >
	</form>
	</div><br/>
	<table class="table table-striped center">
		<tr>
			<th>经销商</th>
			<th>业务员</th>
			<th>累计销售额(元)</th>
			<th>累计开票额(元)</th>
			<th>累计回款(元)</th>
			<th>累计贴息(元)</th>
			<th>累计赔偿(元)</th>
			<th>累计返利(元)</th>
			<th>累计实际销售额(元)</th>
			<th>结余(元)</th>
			<c:if test="${sessionScope.admin!=null}">
				<th>操作</th>
			</c:if>
		</tr>
		<c:forEach items="${requestScope.page.list }" var="result">
			<tr>
				<td><a href="${pageContext.request.contextPath }/servlet/SelectDetailsServlet?distributor=${result.distributor }"><c:out value="${result.distributor }" /></a></td>
				<td><c:out value="${result.salesman }" /></td>
				<td><c:out value="${result.sumsprice }" /></td>
				<td><c:out value="${result.sumdprice }" /></td>
				<td><c:out value="${result.sumrprice }" /></td>
				<td><c:out value="${result.sumiprice }" /></td>
				<td><c:out value="${result.sumpprice }" /></td>
				<td><c:out value="${result.sumrinterest }" /></td>
				<td><c:out value="${result.sumaprice }" /></td>
				<td><c:out value="${result.result }" /></td>
				<c:if test="${sessionScope.admin!=null}">
					<td><a href="${pageContext.request.contextPath }/servlet/ResultListServlet?id=${result.id}">删除</a></td>
				</c:if>
		  	</tr>
		</c:forEach>
	</table>

    <div style="text-align:center;">
	共${requestScope.page.countpage }页
	<a href="${pageContext.request.contextPath }/servlet/findResultServlet?thispage=${requestScope.page.firstpage}&distributor=${requestScope.distributor }">首页</a>
	<a href="${pageContext.request.contextPath }/servlet/findResultServlet?thispage=${requestScope.page.prepage}&distributor=${requestScope.distributor }">上一页</a>
	
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
			<a href="${pageContext.request.contextPath }/servlet/findResultServlet?thispage=${i}&distributor=${requestScope.distributor }">${i }</a>
		</c:if>
	</c:forEach>
	<!-- 分页逻辑结束 -->
	<a href="${pageContext.request.contextPath }/servlet/findResultServlet?thispage=${requestScope.page.nextpage}&distributor=${requestScope.distributor }">下一页</a>
	<a href="${pageContext.request.contextPath }/servlet/findResultServlet?thispage=${requestScope.page.lastpage}&distributor=${requestScope.distributor }">尾页</a>
	</div>
  </body>
  <script type="text/javascript">
  	function addInterest(){
  		window.location.href="${pageContext.request.contextPath }/user/treasurer/addInterest.jsp";
  	}
  	function addPayback(){
  		window.location.href="${pageContext.request.contextPath }/user/treasurer/addPayback.jsp";
  	}
  	function details(distributor){
  		var url="<%=request.getContextPath() %>/servlet/SelectDetailsServlet";
  		$.post(url,
  			{
  				
  				distributor:distributor
  			},
  			function(data){
  				alert(data);
  			}
  		);
  	}
  </script>
</html>
