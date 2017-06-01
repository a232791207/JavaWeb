<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
  <link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.8.1.min.js"></script>
  <link href="/ManageSystem/css/basicStyle.css" rel="stylesheet" type="text/css" media="all"/>
  <script type="text/javascript">
	var error="${requestScope.error}";
	if(error.indexOf(",")>-1){
		error=error.replace(/;/g, "\n");//“/g”代表全局匹配
		alert(error);
	}
	function del(id,format,level,price){
		if(confirm("确定要删除吗？")){
   			window.location.href="${pageContext.request.contextPath }/servlet/DraworderListServlet?id="+id+"&format="+format+"&level="+level+"&price="+price;
   		}
	
	}
	</script>
  </head>
  
  <body>
    <c:if test="${sessionScope.clerk.type!='开票员'&& sessionScope.admin==null }">
    	<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/drawer/drawerLogin.jsp";
		</script>
    </c:if>
    
    	<h2 style="text-align:center;">开票管理</h2><hr/>
		<div style="text-align:center;">
		<c:if test="${sessionScope.clerk.type=='开票员'}">
		<div style="float:left;">
	<input type="button" id="print" onclick="window.location.href='${pageContext.request.contextPath }/servlet/PrintDraworderListServlet?from=1&&to=${requestScope.page.countpage}' " value="打印"/>
	</div>
		<input type="button" value="开票" onclick="window.location.href='${pageContext.request.contextPath }/user/drawer/addDraworder.jsp'"/>
		</c:if>
		</div><br/>   	
	      
	      <div class="table-responsive" style="overflow-y:auto;">	
	    	<table class="table table-striped center">
	         
		  		<tr>
		  			<th>经销商</th>
		  			<th>业务员</th>
		  			<th>发票号</th>
		  			<th>订单号</th>
		  			<th>开票单位</th>
		  			<th>开票日期</th>
		  			<th>规格</th>
		  			<th>等级</th>
		  			<th>单价(元/片)</th>
		  			<th>数量(片)</th>
		  			<th>总额(元)</th>
		  			<th>票额核对</th>
		  			<th>差额</th>
		  			<c:if test="${sessionScope.admin!=null}">
						<th>操作</th>
					</c:if>
		  		</tr>
		 
		  		<c:forEach items="${requestScope.page.list }" var="draworder">
		  		
		  			<tr>
		  				<td><c:out value="${draworder.distributor }" /></td>
		  				<td><c:out value="${draworder.salesman }" /></td>
		  				<td><c:out value="${draworder.id }" /></td>
		  				<td><c:out value="${draworder.orderid }" /></td>
		  				<td><c:out value="${draworder.customer}" /></td>
		  				<td><c:out value="${draworder.time }" /></td>
		  				<td><c:out value="${draworder.format }" /></td>
		  				<td><c:out value="${draworder.level }" /></td>
		  				<td><c:out value="${draworder.price }" /></td>
		  				<td><c:out value="${draworder.number }" /></td>
		  				<td><c:out value="${draworder.sumprice}" /></td>
		  				<td><c:out value="${draworder.checkdenomination}" /></td>
		  				<td><c:out value="${draworder.balance}" /></td>
		  				<c:if test="${sessionScope.admin!=null}">
							<td><a href="javascript:del(${draworder.id},'${draworder.format}','${draworder.level}',${draworder.price})">删除</a></td>
						</c:if>
		  			</tr>
		  
		  		</c:forEach>
	  		</table>	
	  	   </div>  	
	  		
	  	<div style="text-align:center;">
  		共${requestScope.page.countpage }页
  		<a href="${pageContext.request.contextPath }/servlet/DraworderListServlet?thispage=${requestScope.page.firstpage}">首页</a>
  		<a href="${pageContext.request.contextPath }/servlet/DraworderListServlet?thispage=${requestScope.page.prepage}">上一页</a>		
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
  				<a href="${pageContext.request.contextPath }/servlet/DraworderListServlet?thispage=${i}">${i }</a>
  			</c:if>
  		</c:forEach>
  		<!-- 分页逻辑结束 -->
  		
  		<a href="${pageContext.request.contextPath }/servlet/DraworderListServlet?thispage=${requestScope.page.nextpage}">下一页</a>
  		<a href="${pageContext.request.contextPath }/servlet/DraworderListServlet?thispage=${requestScope.page.lastpage}">尾页</a>
  		</div>
  </body>
</html>
