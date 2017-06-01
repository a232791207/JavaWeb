<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/My97DatePicker/WdatePicker.js"></script>
	<link href="/ManageSystem/css/bootstrap.css" rel="stylesheet"type="text/css" media="all"/>
	<link href="/ManageSystem/css/basicStyle.css" rel="stylesheet" type="text/css" media="all"/>
  </head>
  
  
  <h2 style="text-align:center;">产品等级编辑</h2><hr/>
  <form action="${pageContext.request.contextPath }/servlet/AddLevelServlet" method="post">
  	<div class="form-group">
		<div style="text-align:center;">
			<input type="text" name="level1" id="level1"/>
			<button type="submit" onclick="return confirmAdd()">直接开票</button>
		</div>
	</div>
			<%-- 
  	<input type="text" name="level1" id="level1"/>
  	<button type="submit" onclick="return confirmAdd()">新增等级</button>--%>
  </form>
  <form action="${pageContext.request.contextPath }/servlet/DeleteLevelServlet" method="post">
  	<div class="form-group">
		<div style="text-align:center;">
			<input type="text" name="level2" id="level2"/>
			<button type="submit" onclick="return confirmDel()">删除等级</button>
		</div>
	</div>
	<%-- 
  	<input type="text" name="level2" id="level2"/>
  	<button type="submit" onclick="return confirmDel()">删除等级</button>--%>
  </form>
  
  <span style="display:inline-block; width:100%; text-align:center;" id="level3" name="level3"></span>

  <script>
 	function confirmAdd(){
 		if($("#level1").val()==""){
 			alert("新增等级不能为空！");
 			return false;
 		}else{
 			return confirm("是否确认新增？");
 		}
	}
	function confirmDel(){
 		if($("#level2").val()==""){
 			alert("删除等级不能为空！");
 			return false;
 		}else{
 			return confirm("是否确认删除？");
 		}
	}
  $(document).ready(function(){
  		url1 = "<%=request.getContextPath() %>/servlet/SelectLevel2Servlet";
		$.post(url1,
		  {},
		  function(data){
			$("#level3").text("当前等级有:"+data);
		  });
  });
  </script>
</html>
