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
  	<h2>新增回款项</h2><hr/>
  	<br/><br/><br/><br/><br/>
	<form class="form-horizontal" action="${pageContext.request.contextPath }/servlet/AddPaymentServlet" method="post">
	        <div class="form-group">
				<label class="col-sm-3 control-label">回款日期</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="time" id="time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
				    <label></label>
				</div>
				<label class="col-sm-2 control-label">回款单位</label>
				<div class="col-sm-2">
					<select class="form-control" name="customer" id="customer">
						<option value="" selected="selected">请选择</option>
				    </select>
				    <label></label>
				</div>				
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">经销商</label>
				<div class="col-sm-2">
					<select class="form-control" name="distributor" id="distributor">
						<option value="" selected="selected">请选择</option>
				    </select>
				</div>
				<label class="col-sm-2 control-label">业务员</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="salesman" id="salesman"/>
				    <label id="salesmanF" style="color: red;"></label>
				</div>				
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">回款金额(元)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="money" id="money" onkeyup="isPFloat('money')"/>
				    <label id="moneyNews"></label>
				</div>
				<label class="col-sm-2 control-label">回款方式</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="type" id="type"/>
				    <label></label>
				</div>				
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">单据号</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="orderid" id="orderid"/>
				    <label id="moneyNews"></label>
				</div>
				<label class="col-sm-2 control-label">贴息(元)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="interest" id="interest" onkeyup="isPFloat('interest')"/>
				    <label id="interestNews"></label>
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
  		if($("#time").val()==""||$("#customer").val()==""||$("#distributor").val()==""||$("#salesman").val()==""||$("#money").val()==""
  		||$("#type").val()==""||$("#orderid").val()==""||$("#interest").val()==""){
  			alert("您有未填的项目！请确认信息完整后再提交！");
  			return false;
	  	}else{
	  		var str = "请确认新增回款项！\n";
	  		str=str+"回款日期："+$("#time").val()+"\n";
	  		str=str+"回款单位："+$("#customer").val()+"\n";
	  		str=str+"经销商："+$("#distributor").val()+"\n";
	  		str=str+"业务员："+$("#salesman").val()+"\n";
	  		str=str+"回款金额："+$("#money").val()+"元\n";
	  		str=str+"回款方式："+$("#type").val()+"\n";
	  		str=str+"单据号："+$("#orderid").val()+"\n";
	  		str=str+"贴息："+$("#interest").val()+"元\n";
	  		return confirm(str);
	  	}
  	}
  	$("#distributor").change(function (){
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
  	$(document).ready(function(){ 
		url3 = "<%=request.getContextPath() %>/servlet/SelectDistributorServlet";
		$.post(url3,
		  {},
		  function(data){
			var x = data.split(",");
			for(var i=0; i<x.length;i++){
				var option="<option value="+x[i]+">"+x[i]+"</option>";
				$(option).appendTo("#distributor");
			$("#distributor option:contains('${requestScope.distributor }')").attr('selected', true);
			}
		  });
		url2 = "<%=request.getContextPath() %>/servlet/SelectCustomerServlet";
		$.post(url2,
		  {},
		  function(data){
			var x = data.split(",");
			for(var i=0; i<x.length;i++){
				var option="<option value="+x[i]+">"+x[i]+"</option>";
				$(option).appendTo("#customer");
			$("#customer option:contains('${requestScope.customer }')").attr('selected', true);
			}
		  });
	});
  </script>
</html>
