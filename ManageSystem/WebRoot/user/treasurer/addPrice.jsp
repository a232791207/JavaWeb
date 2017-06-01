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
  	<h2>新增标准单价</h2><hr/>
  	<br/><br/><br/><br/><br/>
	<form class="form-horizontal" action="${pageContext.request.contextPath }/servlet/AddPriceServlet" method="post">
	        <div class="form-group">
				<label class="col-sm-3 control-label">时间</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="time" id="time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
				    <label></label>
				</div>
				<label class="col-sm-2 control-label">区域</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="area" id="area" autocomplete="off"/>
				    <label></label>
				</div>				
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">规格</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="format" id="format" autocomplete="off"/>
				    <label></label>
				</div>
				<label class="col-sm-2 control-label">等级</label>
				<div class="col-sm-2">
					<select class="form-control" name="level" id="level">
						<option value="" selected="selected">请选择</option>
				    </select>
				    <label></label>
				</div>					
			</div>
			<div class="form-group">		
				<label class="col-sm-3 control-label">标准单价(元/片)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="price" id="price" onkeyup="isPFloat('price')"/>
				    <label id="priceNews"></label>
				</div>	
				<label class="col-sm-2 control-label">变动范围(元/片)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="changelim" id="changelim" onkeyup="isPFloat('changelim')"/>
				    <label id="changelimNews"></label>
				</div>				
			</div>
			<div class="form-group">
			    <div style="text-align:center;">
                    <button type="submit" class="btn btn-default" onclick="return confirmAdd()">确认添加</button>		
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
  		if($("#time").val()==""||$("#area").val()==""||$("#format").val()==""||$("#level").val()==""
  		||$("#price").val()==""||$("#changelim").val()==""){
  			alert("您有未填的项目！请确认信息完整后再提交！");
  			return false;
	  	}else{
	  		var str = "请确认新增标准单价项！\n";
	  		str=str+"销售时间："+$("#time").val()+"\n";
	  		str=str+"区域："+$("#area").val()+"\n";
	  		str=str+"规格："+$("#format").val()+"\n";
	  		str=str+"等级："+$("#level").val()+"\n";
	  		str=str+"标准单价："+$("#price").val()+"元/件\n";
	  		str=str+"变动范围："+$("#changelim").val()+"元/件\n";
	  		return confirm(str);
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
