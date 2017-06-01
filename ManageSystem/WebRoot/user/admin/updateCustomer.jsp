<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="/ManageSystem/css/bootstrap.css" rel="stylesheet"type="text/css" media="all"/>
  </head>
  
  <body style="text-align: center;">
  	<c:if test="${sessionScope.admin==null }">
    	<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/admin/adminLogin.jsp";
		</script>
    </c:if>
    
    <c:if test="${sessionScope.admin!=null }">
	  	<h2>修改客户信息</h2><hr>
	  	<br/><br/><br/><br/><br/>
	    <form class="form-horizontal" action="${pageContext.request.contextPath }/servlet/UpdateCustomerServlet" method="post">
			<input class="form-control" type="hidden" name="id" value="${requestScope.customer.id }" readonly="readonly" style="background: silver;"/>
			<div class="form-group">
				<label class="col-sm-5 control-label">单位名称</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="name" value="${requestScope.customer.name }" readonly="readonly" style="background: silver;"/>
				    <label></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-5 control-label">联系方式</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="phonenumber" name="phonenumber" value="${requestScope.customer.phonenumber }" onblur="checkIsNotNull('phonenumber')" onkeyup="checkIsNotNull('phonenumber')" autocomplete="off"/>
				    <label id="phonenumberNews"></label>
				</div>				
			</div>
			<div class="form-group">
				<label class="col-sm-5 control-label">邮箱</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="email" name="email" value="${requestScope.customer.email }" onblur="checkEmail()" onkeyup="checkEmail()"/>
				    <label id="emailNews"></label>
				</div>		
			</div>
			<div class="form-group">
			    <div style="text-align:center;">
                    <button type="submit" class="btn btn-default" id="confirm">确认修改</button>		
                </div>
			</div>	
	    </form>
    </c:if>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/validate.js" charset="gb2312"></script>
  </body>
</html>
