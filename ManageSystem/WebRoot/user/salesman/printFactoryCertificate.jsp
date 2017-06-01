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
  	 <c:if test="${sessionScope.clerk.type!='业务员' }">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/salesman/salesmanLogin.jsp";
		</script>
	</c:if> 
	<h2 style="text-align:center;">丽人集团出厂凭证</h2><hr>
	<br>
	<form class="form-horizontal" action="${pageContext.request.contextPath }/servlet/PrintFactoryCertificate" method="post">
	        <div class="form-group">
	        	<label class="col-sm-3 control-label">订单号</label>
	        	<div class="col-sm-2">
					<input class="form-control" type="text" id="orderid" name="orderid" value=<%= request.getParameter("id") %> readonly="readonly"/>
					<label></label>
				</div>	
				<label class="col-sm-2 control-label">销售时间</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="time" name="time" readonly="readonly"/>
					<label></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">地址</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="area" name="area"/>
				    <label></label>
				</div>
				<label class="col-sm-2 control-label">联系人</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="contacts" name="contacts"/>
					<label></label>
				</div>	
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">电话</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="phone" name="phone" />
				    <label></label>
				</div>
				<label class="col-sm-2 control-label">车号</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="truck" name="truck"/>
					<label></label>
				</div>	
			</div>
			<div class="form-group">
			    <label class="col-sm-3 control-label">详情</label>
			    <div class="col-sm-2">
                    <textarea readonly="readonly" cols="40" rows="5" name="detail" id="detail"></textarea>		
                    <label></label>
				</div>
            </div>
			<div class="form-group">
			    <div style="text-align:center;">
                    <button type="submit" class="btn btn-default" id="submit" >提交打印</button>		
            	</div>
            </div>
	</form>
  </body>
  <script type="text/javascript">
  
  	$(document).ready(function(){ 
  		url = "<%=request.getContextPath() %>/servlet/GetOrderInfById";
  		$.post(url,
  		{
  			id:<%=request.getParameter("id") %>
  		},
  		function(data){
  			var x = data.split(",");
  			$("#time").val(x[0]);
  			$("#detail").val(x[1]);
  		}
  			
  			
  		);
  	});
  </script>
</html>
