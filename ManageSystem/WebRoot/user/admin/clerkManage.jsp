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
    		window.location.href="${pageContext.request.contextPath }/servlet/DeleteClerkServlet?id="+id;
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
    	<h2 style="text-align:center;">员工管理</h2><hr>
    	<div style="text-align:center;">
    	<input style="width:70px;height:30px;" type="button" value="新增员工" onclick="window.location.href='${pageContext.request.contextPath }/user/admin/addClerk.jsp'"/><br/><br/>
    	<form class="form-horizontal" action="${pageContext.request.contextPath }/servlet/SelectClerk?thispage=1" name="SelectClerk" method="post">
			姓名：<input type="text" name="name" id="name" value="${param.name }"/>&nbsp;&nbsp;
			职务：<select name="type">
					<option value="">请选择</option>
    				<option value="业务员" 
    					<c:if test="${param.type == '业务员' }">
    						selected="selected"
    					</c:if>
    				>业务员</option>
    				<option value="财务员" 
    					<c:if test="${param.type == '财务员' }">
    						selected="selected"
    					</c:if>
    				>财务员</option>
    				<option value="开票员" 
    					<c:if test="${param.type == '开票员' }">
    						selected="selected"
    					</c:if>
    				>开票员</option>
    				<option value="储管员" 
    					<c:if test="${param.type == '储管员' }">
    						selected="selected"
    					</c:if>
    				>储管员</option>
	    		</select>&nbsp;&nbsp;
			<input style="width:50px;height:30px;" type="submit" value="查询"/>
		</form>
    	</div><br/>
	 	<table class="table table-striped center">
		  		<tr>
		  			<th>编号</th>
		  			<th>密码</th>
		  			<th>姓名</th>
		  			<th>职务</th>
		  			<th>性别</th>
		  			<th>年龄</th>
		  			<th>联系方式</th>
		  			<th>地址</th>
		  			<th>修改</th>
		  			<th>删除</th>
		  		</tr>
		  		<c:forEach items="${requestScope.page.list }" var="clerk">
		  			<tr>
		  				<td><c:out value="${clerk.id }" /></td>
		  				<td><c:out value="${clerk.password }" /></td>
		  				<td><c:out value="${clerk.name }" /></td>
		  				<td><c:out value="${clerk.type}" /></td>
		  				<td><c:out value="${clerk.gender }" /></td>
		  				<td><c:out value="${clerk.age }" /></td>
		  				<td><c:out value="${clerk.phonenumber }" /></td>
		  				<td><c:out value="${clerk.address }" /></td>
		  				<td><a href="${pageContext.request.contextPath }/servlet/ClerkInfoServlet?id=${clerk.id}">修改</a></td>
		  				<td><a href="javascript:del(${clerk.id });">删除</a></td>
		  			</tr>
		  		</c:forEach>
	  		</table>
	  		
	  	<div style="text-align:center;">
  		共${requestScope.page.countpage }页
  		<a href="${pageContext.request.contextPath }/servlet/SelectClerk?thispage=${requestScope.page.firstpage}&name=${param.name }&type=${param.type }">首页</a>
  		<a href="${pageContext.request.contextPath }/servlet/SelectClerk?thispage=${requestScope.page.prepage}&name=${param.name }&type=${param.type }">上一页</a>		
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
  				<a href="${pageContext.request.contextPath }/servlet/SelectClerk?thispage=${i}&name=${param.name }&type=${param.type }">${i }</a>
  			</c:if>
  		</c:forEach>
  		<!-- 分页逻辑结束 -->
  		
  		<a href="${pageContext.request.contextPath }/servlet/SelectClerk?thispage=${requestScope.page.nextpage}&name=${param.name }&type=${param.type }">下一页</a>
  		<a href="${pageContext.request.contextPath }/servlet/SelectClerk?thispage=${requestScope.page.lastpage}&name=${param.name }&type=${param.type }">尾页</a>
  		</div>
    </c:if>
  </body>
</html>
