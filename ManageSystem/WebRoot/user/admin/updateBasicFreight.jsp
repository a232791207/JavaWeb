<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.8.1.min.js"></script>
	<link href="/ManageSystem/css/bootstrap.css" rel="stylesheet"type="text/css" media="all"/>
  </head>
  
  <body style="text-align: center;">
  	<c:if test="${sessionScope.admin==null  }">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/admin/adminLogin.jsp";
		</script>
	</c:if> 
  	<h2>修改基本运费项</h2><hr/>
  	<br/><br/><br/><br/><br/>
	<form class="form-horizontal" action="${pageContext.request.contextPath }/servlet/UpdateBasicFreightServlet" method="post">
			<input type="hidden" name="id" id="id"/>
	        <div class="form-group">
				<label class="col-sm-3 control-label">区域</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="area" id="area"/>
				    <label></label>
				</div>
				<label class="col-sm-2 control-label">运费单价(元/立方米)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="fare" id="fare" onkeyup="isPFloat('fare')"/>
					<label id="fareNews"></label>
				    <label></label>
				</div>				
			</div>
			<div class="form-group">
			    <div style="text-align:center;">
                    <button type="submit" class="btn btn-default" onclick="return confirmUpdate()">确认修改</button>		
                </div>
			</div>
	</form>
  </body>
   <script type="text/javascript">
  	function isPFloat(id){
		var pFloatReg=/^(\d*\.)?\d+$/;
		var info=document.getElementById(id).value;
		if(!info.match(pFloatReg)){
			msg = "*请输入小数";
			msg='<div style="font-size:80%;color:#FF0000">'+msg+'</div>';
			document.getElementById(id+"News").innerHTML=msg;
			$("#submit").attr("disabled","disabled");
		}else{
			document.getElementById(id+"News").innerHTML="";
			$("#submit").removeAttr("disabled");
		}
	}
  	function confirmUpdate(){
  		if($("#area").val()==""||$("#fare").val()==""){
  			alert("您有未填的项目！请确认信息完整后再提交！");
  			return false;
	  	}else{
	  		return confirm("是否确认修改？");
	  	}
  	}
  	$(document).ready(function(){
  		url = "<%=request.getContextPath() %>/servlet/BasicFreightInfoServlet";
  		var id = <%=request.getParameter("id") %>;
  		$.post(url,
		  {
			id:id
		  },
		  function(data){
		  	var x = data.split(",");
		  	$("#id").val(x[0]);
		  	$("#area").val(x[1]);
		  	$("#fare").val(x[2]);
		  }
		);
  	});
  </script>
</html>
