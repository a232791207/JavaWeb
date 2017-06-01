<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
  <head>
  <link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.8.1.min.js"></script>
  <link href="/ManageSystem/css/basicStyle.css" rel="stylesheet" type="text/css" media="all"/>
  <script type="text/javascript">
		function del(id){
			if(confirm("确定要删除吗？")){
    			window.location.href="${pageContext.request.contextPath }/servlet/DeleteProductServlet?id="+id;
    		}
		
		}
	</script>
  </head>
  
  <body>
    <c:if test="${sessionScope.clerk.type!='储管员'&& sessionScope.admin==null }">
    	<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/storeManager/storeManagerLogin.jsp";
		</script>
    </c:if>
    
    	<h2 style="text-align:center;">产品入库记录</h2><hr>
    	<div style="text-align:center;">
    	<form action="${pageContext.request.contextPath }/servlet/FindStoreRecordServlet" method="post">
	    	<div style="float:left;">
				<input type="button" id="print" onclick="window.location.href='${pageContext.request.contextPath }/servlet/PrintStoreRecordServlet?from=1&&to=${requestScope.page.countpage}' " value="打印"/>
			</div>
    	<c:if test="${sessionScope.clerk.type=='储管员' }">
    		<input type="button" value="新产品入库" onclick="window.location.href='${pageContext.request.contextPath }/user/storeManager/addStoreRecord.jsp'"/><br/><br/>
   		</c:if>		
    	规格：<input type="text" name="format" id="format" value="${requestScope.format }"/>&nbsp;等级：<input type="text" id="level" name="level" value="${requestScope.level }"/>&nbsp;<input type="submit" value="查询"/>
    	</form></div><br/>
	    	<table class="table table-striped center">
		  		<tr>
		  		<!-- <th>编号</th> -->
		  			<th>时间</th>
		  			<th>规格</th>
		  			<th>等级</th>
		  			<th>长度</th>
		  			<th>宽度</th>
		  			<th>厚度(mm)</th>
		  			<th>数量(片)</th>
		  			<th>体积(立方米)</th>
		  			<th>包数</th>
		  			<th>片数/包</th>
						<th>已有产品入库</th>
					<c:if test="${sessionScope.admin!=null}">
		  				<th>删除</th>
		  			</c:if>
		  		</tr>
		  		<c:forEach items="${requestScope.page.list }" var="storerecord">
		  			<tr>
		  		<!-- 	<td><c:out value="${storerecord.id }" /></td>   -->	
		  				<td><c:out value="${storerecord.time }" /></td>
		  				<td><c:out value="${storerecord.format }" /></td>
		  				<td><c:out value="${storerecord.level }" /></td>
		  				<td><c:out value="${storerecord.height }" /></td>
		  				<td><c:out value="${storerecord.width }" /></td>
		  				<td><c:out value="${storerecord.thick }" /></td>
		  				<td><c:out value="${storerecord.num}" /></td>
		  				<td><c:out value="${storerecord.volume }" /></td>
		  				<fmt:formatNumber value="${storerecord.num/storerecord.bagSlices}" pattern=".000" var="result"/>
		  				<td><c:out value="${result }" /></td>
		  				<td><c:out value="${storerecord.bagSlices }" /></td>
		  				
		  				<td><a href="${pageContext.request.contextPath }/user/storeManager/updateStoreRecord.jsp?id=${storerecord.id}">入库</a></td>
		  				
		  			</tr>
		  		</c:forEach>
	  		</table>
	  	
	  	<div style="text-align:center;">
  		共${requestScope.page.countpage }页
  		<a href="${pageContext.request.contextPath }/servlet/FindStoreRecordServlet?thispage=${requestScope.page.firstpage}&format=${requestScope.format }&level=${requestScope.level }">首页</a>
  		<a href="${pageContext.request.contextPath }/servlet/FindStoreRecordServlet?thispage=${requestScope.page.prepage}&format=${requestScope.format }&level=${requestScope.level }">上一页</a>		
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
  				<a href="${pageContext.request.contextPath }/servlet/FindStoreRecordServlet?thispage=${i}&format=${requestScope.format }&level=${requestScope.level }">${i }</a>
  			</c:if>
  		</c:forEach>
  		<!-- 分页逻辑结束 -->
  		
  		<a href="${pageContext.request.contextPath }/servlet/FindStoreRecordServlet?thispage=${requestScope.page.nextpage}&format=${requestScope.format }&level=${requestScope.level }">下一页</a>
  		<a href="${pageContext.request.contextPath }/servlet/FindStoreRecordServlet?thispage=${requestScope.page.lastpage}&format=${requestScope.format }&level=${requestScope.level }">尾页</a>
  		</div>
  </body>
</html>

