<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
    <script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.8.1.min.js"></script>
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
    	<h2>新增客户</h2><hr/>
    	<br/><br/><br/><br/><br/>
		<form class="form-horizontal" action="${pageContext.request.contextPath }/servlet/AddCustomerServlet" method="post">
		    <div class="form-group">
				<label class="col-sm-5 control-label">单位名称</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="name" name="name"/>
					<label></label>
				</div>			
			</div>
			 <div class="form-group">
				<label class="col-sm-5 control-label">联系方式</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="phonenumber" name="phonenumber"/>
					<label></label>
				</div>					
			</div>
			<div class="form-group">
				<label class="col-sm-5 control-label">邮箱</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="email" name="email" onblur="checkEmail()" onkeyup="checkEmail()"/>
				    <label id="emailNews"></label>
				</div>			
			</div>
			<div class="form-group">
				<div style="text-align:center;">
                    <button type="submit" class="btn btn-default" id="confirm" onclick="return confirmAdd()">确认添加</button>		
                </div>				
			</div>
		</form>
	</c:if>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/validate.js" charset="gb2312"></script>
  </body>
  <script type="text/javascript">
  	function confirmAdd(){
  		if($("#name").val()==""||$("#phonenumber").val()==""||$("#email").val()==""){
  			alert("您有未填的项目！请确认信息完整后再提交！");
  			return false;
	  	}else{
	  		var str = "请确认新增客户信息！\n";
	  		str=str+"单位名称："+$("#name").val()+"\n";
	  		str=str+"联系方式："+$("#phonenumber").val()+"\n";
	  		str=str+"邮箱："+$("#email").val()+"\n";
	  		return confirm(str);
  		}
  	}
  </script>
</html>
