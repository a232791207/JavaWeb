<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
	<script type="text/javascript" src="assets/js/jquery-1.8.1.min.js"></script>
	<link href="/ManageSystem/css/bootstrap.css" rel="stylesheet"type="text/css" media="all"/>
  </head>

  <body style="text-align: center;"> 
	<h2>修改密码</h2><hr/>
	<br/><br/><br/><br/><br/>
	<font color="red" id="font">${msg }</font>
	<form class="form-horizontal" action="${pageContext.request.contextPath}/servlet/updatePasswordServlet" method="post">
	        <input type="hidden" name="id" value="${sessionScope.clerk.id }"/>
	        <input type="hidden" name="type" value="${sessionScope.clerk.type }"/>
	        <div class="form-group">
				<label class="col-sm-5 control-label">旧密码</label>
				<div class="col-sm-2">
					<input class="form-control" type="password" name="password" autocomplete="off"/>
				    <label></label>
				</div>		
			</div>
			<div class="form-group">
				<label class="col-sm-5 control-label">新密码</label>
				<div class="col-sm-2">
					<input class="form-control" type="password" name="password2" id="password2" autocomplete="off"/>
					<font color="red" id="font1"></font>
					<label></label>
				</div>		
			</div>
			<div class="form-group">
				<label class="col-sm-5 control-label">确认新密码</label>
				<div class="col-sm-2">
					<input class="form-control" type="password" name="password3" id="password3" autocomplete="off"/>
				    <label></label>
				</div>		
			</div>
			<div class="form-group">
			    <div style="text-align:center;">
                    <button type="submit" class="btn btn-default" id="confirm" disabled="disabled">确认修改</button>		
                </div>
			</div>	
	</form>
  </body>
  <script type="text/javascript">
  	$("#password2").keyup(function(){
  		if($("#password2").val()!=$("#password3").val()){
  			$("#font1").text("两次密码不一致！");
  		}
  		if($("#password2").val()!=""){
	  		if($("#password2").val()==$("#password3").val()){
	  			$("#font1").text("ok!");
	  			$("#confirm").removeAttr("disabled");
	  		}
	  		else
			  	$("#confirm").attr("disabled","disabled");
  		}
  	});
  	$("#password3").keyup(function(){
  		if($("#password2").val()!=$("#password3").val()){
  			$("#font1").text("两次密码不一致！");
  		}
  		if($("#password2").val()!=""){
	  		if($("#password2").val()==$("#password3").val()){
	  			$("#font1").text("ok!");
	  			$("#confirm").removeAttr("disabled");
	  		}
	  		else
			  	$("#confirm").attr("disabled","disabled");
	  	}
  	});
  </script>
</html>
