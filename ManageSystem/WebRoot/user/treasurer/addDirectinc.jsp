<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/My97DatePicker/WdatePicker.js"></script>
	<link href="/ManageSystem/css/bootstrap.css" rel="stylesheet"type="text/css" media="all"/>
  </head>
  
  <body style="text-align: center;">
  	<c:if test="${sessionScope.clerk.type!='财务员' }">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/treasurer/treasurerLogin.jsp";
		</script>
	</c:if> 
  	<h2>新增直接优惠</h2><hr/>
  	<br/><br/><br/><br/><br/>
	<form class="form-horizontal" action="${pageContext.request.contextPath }/servlet/AddDirectincServlet" method="post">
	        <div class="form-group">
				<label class="col-sm-3 control-label">区域</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="area" id="area" autocomplete="off"/>
				    <label></label>
				</div>	
				<label class="col-sm-2 control-label">开始时间</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="stime" id="stime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
				    <label></label>
				</div>			
			</div>
			<div class="form-group">
				
				<label class="col-sm-3 control-label">规格</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="format" id="format" autocomplete="off"/>
				    <label></label>
				</div>	
				<label class="col-sm-2 control-label">结束时间</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="etime" id="etime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
				    <label></label>
				</div>			
			</div>
			<div class="form-group">
				
				<label class="col-sm-3 control-label">等级</label>
				<div class="col-sm-2">
					<select class="form-control" name="level" id="level">
						<option value="" selected="selected">请选择</option>
				    </select>
				    <label></label>
				</div>	
				<label class="col-sm-2 control-label">经销商</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="distributor" id="distributor" autocomplete="off"/>
				    <label></label>
				</div>			
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">直接优惠(元/片)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="directinc" id="directinc" onkeyup="isPFloat('directinc')" autocomplete="off"/>
				    <label id="directincNews"></label>
				</div>				
			</div>
			<div class="form-group">
			    <div style="text-align:center;">
                    <button type="submit" class="btn btn-default" id="submit" onclick="return confirmAdd()">确认添加</button>		
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
	  function confirmAdd(){
 		if($("#stime").val()==""||$("#etime").val()==""||$("#distributor").val()==""||$("#area").val()==""
		||$("#format").val()==""||$("#level").val()==""||$("#directinc").val()==""){
 			alert("您有未填的项目！请确认信息完整后再提交！");
 			return false;
 		}else{
 			return confirm("是否确认新增？");
 		}
	  }
	  $(document).ready(function(){
  		url2 = "<%=request.getContextPath() %>/servlet/SelectLevel2Servlet";
		$.post(url2,
		  {},
		  function(data){
			var x = data.split(",");
			for(var i=0; i<x.length;i++){
				var option="<option value="+x[i]+">"+x[i]+"</option>";
				$(option).appendTo("#level");
			}
		  });
	});
  </script>
</html>
