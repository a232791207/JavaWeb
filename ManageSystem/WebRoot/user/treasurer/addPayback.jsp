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
  	<h2>新增赔偿</h2><hr/>
  	<br/><br/><br/><br/><br/>
	<form class="form-horizontal" action="${pageContext.request.contextPath }/servlet/AddPaybackServlet" method="post">
	        <div class="form-group">
				<label class="col-sm-5 control-label">经销商</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="distributor" id="distributor"/>
				    <label id="distributorF" style="color: red;"></label>
				</div>			
			</div>
			<div class="form-group">
				<label class="col-sm-5 control-label">业务员</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="salesman" id="salesman"/>
				    <label id="salesmanF" style="color: red;"></label>
				</div>		
			</div>
			<div class="form-group">
				<label class="col-sm-5 control-label">赔偿金额(元)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="money" id="money" onkeyup="isPFloat('money')"/>
				    <label id="moneyNews"></label>
				</div>		
			</div>
			<div class="form-group">
			    <div style="text-align:center;">
                    <button id="submit" type="submit" class="btn btn-default" onclick="return confirmAdd()">确认添加</button>		
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
  		if($("#distributor").val()==""||$("#salesman").val()==""||$("#money").val()==""){
  			alert("您有未填的项目！请确认信息完整后再提交！");
  			return false;
	  	}else{
	  		var str = "请确认新增赔偿信息！\n";
	  		str=str+"经销商："+$("#distributor").val()+"\n";
	  		str=str+"业务员："+$("#salesman").val()+"\n";
	  		str=str+"赔偿金额："+$("#money").val()+"\n";
	  		return confirm(str);
  		}
  	}
  	$("#distributor").keyup(function (){
  		if($("#distributor").val()!="" && $("#salesman").val()!=""){
  			url="<%=request.getContextPath() %>/servlet/DistIsMatchWithSal";
  			$.post(url,
  				{
  					distributor:$("#distributor").val(),
  					salesman:$("#salesman").val()
  				},
  				function(data){
  					if(data=="0"){
  						$("#distributorF").text("经销商与业务员不匹配！请确认并修改!");
  						$("#submit").attr("disabled","disabled");
  					}else{
  						$("#salesmanF").text("");
  						$("#distributorF").text("");
  						$("#submit").removeAttr("disabled");
  					}
  				}
  			);
  		}
  	});
  	$("#salesman").keyup(function (){
  		if($("#distributor").val()!=""&&$("#salesman").val()!=""){
  			url="<%=request.getContextPath() %>/servlet/DistIsMatchWithSal";
  			$.post(url,
  				{
  					distributor:$("#distributor").val(),
  					salesman:$("#salesman").val()
  				},
  				function(data){
  					if(data=="0"){
  						$("#salesmanF").text("业务员与经销商不匹配！请确认并修改!");
  						$("#submit").attr("disabled","disabled");
  					}else{
  						$("#salesmanF").text("");
  						$("#distributorF").text("");
  						$("#submit").removeAttr("disabled");
  					}
  				}
  			);
  		}
  	});
  </script>
</html>
