<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/My97DatePicker/WdatePicker.js"></script>
	<link href="/ManageSystem/css/bootstrap.css" rel="stylesheet"type="text/css" media="all"/>
	<link href="/ManageSystem/css/basicStyle.css" rel="stylesheet" type="text/css" media="all"/>
  </head>
  
  <body style="text-align: center;">
  	<c:if test="${sessionScope.clerk.type!='业务员' }">
  		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/salesman/salesmanLogin.jsp";
		</script>
	</c:if> 
  	<h2>新增订单</h2><hr/>
  	<br/>
	<form class="form-horizontal" action="${pageContext.request.contextPath }/servlet/AddOrderServlet" method="post">
	        <div class="form-group">
				<label class="col-sm-3 control-label">销售时间</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="time" name="time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					<label></label>
				</div>
				<label class="col-sm-2 control-label">订单号</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="orderid" name="orderid" />
					<label></label>
				</div>			
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">经销商</label>
				<div class="col-sm-2">
					<select class="form-control" name="distributor" id="distributor">
						<option value="" selected="selected">请选择</option>
				    </select>
				    <label></label>
				</div>
				<label class="col-sm-2 control-label">业务员</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="salesman" name="salesman" value="${sessionScope.clerk.id}"/>
					<label></label>
				</div>	
			</div>
			<div class="form-group">
			    <label class="col-sm-3 control-label">区域</label>
				<div class="col-sm-2">
					<select class="form-control" name="area" id="area">
						<option value="" selected="selected">请选择</option>
				    </select>
				    <label></label>
				</div>
				<label class="col-sm-2 control-label">规格</label>
				<div class="col-sm-2">
					<select class="form-control" name="format" id="format" disabled="disabled">
						<option value="" selected="selected">请选择</option>
				    </select>
				    <label></label>
				</div>
			</div>
			 <div class="form-group">
			    <label class="col-sm-3 control-label">等级</label>
				<div class="col-sm-2">
					<select class="form-control" name="level" id="level" disabled="disabled">
						<option value="" selected="selected">请选择</option>
				    </select>
				    <label></label>
				</div>
				<label class="col-sm-2 control-label">标准单价(元/片)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="price" name="price" value ="0" readonly="readonly" style="background-color: silver;"/>
				    <label></label>
				</div>			
			</div>
			<div class="form-group">
			    <label class="col-sm-3 control-label">直接优惠(元/片)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="directinc" id="directinc" value ="0" readonly="readonly" style="background-color: silver;"/>
				    <label></label>
				</div>	
				<label class="col-sm-2 control-label">一票制运费(元/片)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="freight" id="freight" value ="0" disabled="disabled" onkeyup="isPFloat('freight')" autocomplete="off"/>
				    <label id="freightNews"></label>
				</div>						
			</div>
			<div class="form-group">
			    <label class="col-sm-3 control-label">特殊优惠(元/片)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="specialinc" id="specialinc" value ="0" disabled="disabled" onkeyup="isPFloat('specialinc')" autocomplete="off"/>
				    <label id="specialincNews"></label>
				</div>		
				<label class="col-sm-2 control-label">实际单价(元/片)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="realprice" id="realprice" value ="0" readonly="readonly" style="background-color: silver;"/>
                    <label></label>
				</div>			
			</div>
			<div class="form-group">
			    <label class="col-sm-3 control-label">数量(包)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" min="1" max="99" name="package" id="package" value ="0" disabled="disabled" onkeyup="isPInt('package')" autocomplete="off"/>
				    <label id="packageNews"></label>
				</div>	
				<label class="col-sm-2 control-label">数量(片)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="number" id="number" value ="0" readonly="readonly" style="background-color: silver;"/>
                    <font color="red" id="numberf"></font>
				</div>
			</div>
			<div class="form-group">
			    <label class="col-sm-3 control-label">体积(立方米)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="volume" id="volume" value ="0" readonly="readonly" style="background-color: silver;"/>
                    <font color="red" id="volumef"></font>
				</div>
				<label class="col-sm-2 control-label" >总价(元)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="sumprice" id="sumprice" value ="0" readonly="readonly" style="background-color: silver;"/>
				    <font color="red" id="sumpricef"></font>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label">是否参与返利</label>
				
				<div class="col-sm-2">
				    <label class="checkbox-inline">
                        <input type="radio" name="ifprofit" id="ifprofit"value="是" checked="checked"> 是
                    </label>
                    <label class="checkbox-inline">
                        <input type="radio" name="ifprofit" id="ifprofit"value="否"> 否
                    </label>
				</div>
				<label class="col-sm-2 control-label">是否收取调度费</label>
				<div class="col-sm-2">
				    <label class="checkbox-inline">
                        <input type="radio" name="fare" id="fare"value="是" checked="checked"> 是
                    </label>
                    <label class="checkbox-inline">
                        <input type="radio" name="fare" id="fare"value="否"> 否
                    </label>
				</div>					
			</div>
			<div class="form-group" >
				<label class="col-sm-3 control-label">是否收取运费</label>
				<div class="col-sm-2">
				    <label class="checkbox-inline">
                        <input type="radio" name="freight2" id="freight2"value="是" checked="checked"> 是
                    </label>
                    <label class="checkbox-inline">
                        <input type="radio" name="freight2" id="freight2"value="否"> 否
                    </label>
				</div>
			</div>
			<div class="form-group">
			    <div style="text-align:center;">
                    <button type="submit" class="btn btn-default" id="submit" disabled="disabled" onclick="return confirmAdd()">提交审核</button>		
                </div>
			</div>
			<div class="cart">
			<input type="button" class="cart-button" value="加入下单队列" id="cart"><label id="cartItemsNum"style="color:#FF0000"></label>
			</div>
			<div class="cart">
			<input type="button" class="show-button" value="查看下单队列" id="show">
			</div>
	</form>
	<input type="hidden" id="drive_fare">
	<input type="hidden" id="order_id">
  </body>
  <script type="text/javascript">
    function isPInt(id){
		var pIntReg=/^\+?[1-9][0-9]*$/;
		var info=document.getElementById(id).value;
		if(!info.match(pIntReg)){
			msg = "*请输入正整数";
			msg='<div style="font-size:80%;color:#FF0000">'+msg+'</div>';
			document.getElementById(id+"News").innerHTML=msg;
			$("#submit").attr("disabled","disabled");
		}else{
			document.getElementById(id+"News").innerHTML="";
			$("#submit").removeAttr("disabled");
		}
	}
	function isPFloat(id){
		var pFloatReg=/^[+-]?([0-9]*\.?[0-9]+|[0-9]+\.?[0-9]*)([eE][+-]?[0-9]+)?$/;
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
  		if($("#orderid").val()==""||$("#time").val()==""||$("#salesman").val()==""||$("#distributor").val()==""||$("#area").val()==""
		||$("#format").val()==""||$("#level").val()==""||$("#price").val()==""||$("#directinc").val()==""||$("#freight").val()==""
		||$("#specialinc").val()==""||$("#realprice").val()==""||$("#package").val()==""||$("#number").val()==""||$("#sumprice").val()==""||$("#number").val()=="0"||$("#package").val()=="0"||isNaN($("#number").val())){
  			alert("您有未填的项目！请确认信息完整后再提交！");
  			return false;
  		}else if($("#drive_fare").val()!=null&&$("#drive_fare").val()!=""){
  			alert("请换一个有调度费的地区!");
  			return false;
  		}
  		else if($("#order_id").val()!=null&&$("#order_id").val()!=""){
  			alert("该订单已存在!");
  			return false;
  		}
  		else{
  			return confirm("是否确认新增？");
  		}
  	}
  	$("#area").change(function(){
  		area_change();
  	});
  	function area_change(){
  	if($("#area").val()!=""){
  			//验证产品能运送（地区在不在基本调度费表里）
  		url = "<%=request.getContextPath() %>/servlet/CheckBasicFare";
  		
  			$.post(url,
			  {
				area:$("#area").val()
			  },
			  function(data){
			  	if(data!=null&&data!=""){
			  	alert(data);
			  		$("#drive_fare").val("no");
			  	}
			  	else{
			  		$("#drive_fare").val("");
			  	}
			  });
  			$("#format").removeAttr("disabled");
  			$("#format").empty();
  			var option="<option value=''>请选择</option>";
			$(option).appendTo("#format");
  			$("#format").val("");
  			$("#level").empty();
  			var option="<option value=''>请选择</option>";
  			$(option).appendTo("#level");
  			$("#level").attr("disabled","disabled");
  			url = "<%=request.getContextPath() %>/servlet/SelectFormatServlet";
  			$.post(url,
			  {
				area:$("#area").val()
			  },
			  function(data){
				  	if(data!=""){
				  		var x = data.split(",");
						for(var i=0; i<x.length;i++){
							var option="<option value="+x[i]+">"+x[i]+"</option>";
							$(option).appendTo("#format");
					}
			  	}
			  	
			  });
  		}
  		else{
  			$("#format").empty();
  			var option="<option value=''>请选择</option>";
  			$(option).appendTo("#format");
  			$("#format").attr("disabled","disabled");
  			$("#level").empty();
  			var option="<option value=''>请选择</option>";
  			$(option).appendTo("#level");
  			$("#level").attr("disabled","disabled");
  		}
  	}
  	$("#format").change(function(){
  		if($("#format").val()!=""){
  			$("#level").removeAttr("disabled");
  			$("#level").empty();
  			var option="<option value=''>请选择</option>";
			$(option).appendTo("#level");
  			$("#level").val("");
  			url = "<%=request.getContextPath() %>/servlet/SelectLevelServlet";
  			$.post(url,
			  {
				area:$("#area").val(),
				format:$("#format").val()
			  },
			  function(data){
			  	if(data!=""){
			  		var x = data.split(",");
					for(var i=0; i<x.length;i++){
						var option="<option value="+x[i]+">"+x[i]+"</option>";
						$(option).appendTo("#level");
				  	}
				}
			  });
  		}
  		else{
  			$("#level").empty();
  			var option="<option value=''>请选择</option>";
  			$(option).appendTo("#level");
  			$("#level").attr("disabled","disabled");
  		}
  	});
  	$("#level").change(function(){
  		if($("#level").val()!=""){
  			$("#freight").removeAttr("disabled");
  			$("#specialinc").removeAttr("disabled");
  			$("#package").removeAttr("disabled");
  		}
  		else{
  			$("#freight").val("");
  			$("#specialinc").val("");
  			$("#package").val("");
  			$("#freight").attr("disabled","disabled");
  			$("#specialinc").attr("disabled","disabled");
  			$("#package").attr("disabled","disabled");
  		}
  		if($("#orderid").val()!=""&&$("#orderid").val()!=null&&$("#level").val()!=""&&$("#level").val()!=null){
			$.post("${pageContext.request.contextPath }/servlet/CheckOrderIdServlet",
			{
				orderid:$("#orderid").val(),
				format:$("#format").val(),
				level:$("#level").val(),
			},
			function(data){
				if(data=="通过"){
					$("#order_id").val("no");
				}
				else{
					$("#order_id").val("");
				}
				
			});
		}
  	});
  	
  	$("#package").keyup(function(){
  		if($("#package").val()!=""&&$("#package").val()>0){
  			if(isNaN($("#number").val())){
  				var number = $("#number").val();
  				number=number.substring(number.indexOf("：")+1);
  				if(number<=0){
  					alert("没有库存了!");
  					return;
  				}
  			}
  			url="<%=request.getContextPath() %>/servlet/SelectPackageNumServlet";
  			$.post(url,
			  {
				format:$("#format").val(),
				level:$("#level").val()
			  },
			  function(data){
			  	var x = data.split(",");
			  	var pk = $("#package").val();
			  	var rp = $("#realprice").val();
	  			$("#number").val(pk*x[0]);
	  			$("#sumprice").val((rp*pk*x[0]).toFixed(2));
	  			$("#volume").val(($("#number").val()*x[1]).toFixed(3));
			  });
			  
  		}else{
  			$("#number").val(0);
  			return;
  		}
  		if($("#format").val()!=""&&$("#level").val()!=""){
  			url = "<%=request.getContextPath() %>/servlet/ProductNumServlet";
  			$.post(url,
			  {
				format:$("#format").val(),
				level:$("#level").val()
			  },
			  function(data){
			  	var number = parseInt($("#number").val());
			  	var sumnumber = parseInt(data);
				if(number>sumnumber){
					$("#numberf").text("数量超出限制数："+data);
					$("#submit").attr("disabled","disabled");
				}else{
					$("#numberf").text("");
				}
		  	});
  		}
  		if($("#distributor").val()!=""){
  			url = "<%=request.getContextPath() %>/servlet/RemainCredServlet";
  			$.post(url,
			  {
				distributor:$("#distributor").val()
			  },
			  function(data){
			  	var sumprice = $("#sumprice").val()*1;
			  	var remainCred = data*1;
				if(sumprice>remainCred){
					$("#sumpricef").text("金额超出经销商剩余额度："+data);
					$("#submit").attr("disabled","disabled");
				}else{
					$("#sumpricef").text("");
				}
		  	});
  		}
  	});
  	
  	
  	$("#specialinc").focusout(function(){
  		var p = $("#price").val();
  		var fr = $("#freight").val();
  		var sl = $("#specialinc").val();
  		var dl = $("#directinc").val();
  		var num = $("#number").val();
  		$("#realprice").val((p-sl-dl+fr*1).toFixed(2));
  		$("#sumprice").val((p-sl-dl+fr*1).toFixed(2)*num);
  	});
  	$("#freight").focusout(function(){
  		var p = $("#price").val();
  		var fr = $("#freight").val();
  		var sl = $("#specialinc").val();
  		var dl = $("#directinc").val();
  		var num = $("#number").val();
  		$("#realprice").val((p-sl-dl+fr*1).toFixed(2));
  		$("#sumprice").val((p-sl-dl+fr*1).toFixed(2)*num);
  	});
  	$("#time").focusout(function(){
  		if($("#area").val()!=""&&$("#format").val()!=""&&$("#level").val()!=""&&$("#distributor").val()!=""&&$("#time").val()!=""){
  			url = "<%=request.getContextPath() %>/servlet/SelectPriceDirectincServlet";
  			$.post(url,
			  {
			  	time:$("#time").val(),
			  	distributor:$("#distributor").val(),
				area:$("#area").val(),
				format:$("#format").val(),
				level:$("#level").val()
			  },
			  function(data){
			  	var x = data.split(",");
				$("#price").val(x[0]);
				$("#directinc").val(x[1]);
				$("#realprice").val((x[0]-x[1]).toFixed(2));
			  });
  		}
  	});
  	$("select").change(function(){
  		if($("#area").val()!=""&&$("#format").val()!=""&&$("#level").val()!=""&&$("#distributor").val()!=""&&$("#time").val()!=""){
  			url = "<%=request.getContextPath() %>/servlet/SelectPriceDirectincServlet";
  			$.post(url,
			  {
			  	time:$("#time").val(),
			  	distributor:$("#distributor").val(),
				area:$("#area").val(),
				format:$("#format").val(),
				level:$("#level").val()
			  },
			  function(data){
			  	var x = data.split(",");
				$("#price").val(x[0]);
				$("#directinc").val(x[1]);
				$("#realprice").val((x[0]-x[1]).toFixed(2));
			  });
  		}
  		if($("#format").val()!=""&&$("#level").val()!=""){
  			url = "<%=request.getContextPath() %>/servlet/ProductNumServlet";
  			$.post(url,
			  {
				format:$("#format").val(),
				level:$("#level").val()
			  },
			  function(data){
				$("#number").val("当前库存为："+data);
			  });
  		}
  		else{
  			$("#price").val("0");
  			$("#freight").val("0");
  			$("#specialinc").val("0");
  			$("#package").val("0");
  			$("#sumprice").val("0");
			$("#directinc").val("0");
			$("#realprice").val("0");
			$("#number").val("0");
			$("#numberf").text("");
			$("#sumpricef").text("");
  		}
  	});
  	$(document).ready(function(){ 
		url1 = "<%=request.getContextPath() %>/servlet/SelectAreaServlet";
		$.post(url1,
		  {},
		  function(data){
			var x = data.split(",");
			for(var i=0; i<x.length;i++){
				var option="<option value="+x[i]+">"+x[i]+"</option>";
				$(option).appendTo("#area");
			}
			$("#area option:contains('${requestScope.area }')").attr('selected', true);
			area_change();
		  });
		
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
		  come_show();
		  if("${requestScope.time }"!=""){
		  	$("#time").val("${requestScope.time }");
		  	$("#orderid").val("${requestScope.id }");
		  }
		  if("${requestScope.error }"!=""){
		  alert("${requestScope.error }");
		  }
		  
	});
	$("#orderid").blur(function(){
		if($("#orderid").val()!=""&&$("#orderid").val()!=null&&$("#level").val()!=""&&$("#level").val()!=null){
			$.post("${pageContext.request.contextPath }/servlet/CheckOrderIdServlet",
			{
				orderid:$("#orderid").val(),
				format:$("#format").val(),
				level:$("#level").val(),
			},
			function(data){
				if(data=="通过"){
					$("#order_id").val("no");
				}
				else{
					$("#order_id").val("");
				}
				
			});
		}
	});
	 $("#cart").click(function(){
  	//加入购物车
  	//1.检查是否全部填写合格
  	if($("#orderid").val()==""||$("#time").val()==""||$("#salesman").val()==""||$("#distributor").val()==""||$("#area").val()==""
		||$("#format").val()==""||$("#level").val()==""||$("#price").val()==""||$("#directinc").val()==""||$("#freight").val()==""
		||$("#specialinc").val()==""||$("#realprice").val()==""||$("#package").val()==""||$("#number").val()==""||$("#sumprice").val()==""||$("#number").val()==0||$("#package").val()=="0"||isNaN($("#number").val())){
  			alert("您有未填的项目！请确认信息完整后再提交！");
  			return ;
  		}else if($("#drive_fare").val()!=null&&$("#drive_fare").val()!=""){
  			alert("请换一个有调度费的地区!");
  			return ;
  		}
  		else if($("#order_id").val()!=null&&$("#order_id").val()!=""){
  			alert("该订单已存在!");
  			return ;
  		}
  		else{
  			//2.加入购物车
  			$.post("<%=request.getContextPath() %>/servlet/OrderCartServlet",
			  				{	
			  					op:"add",
			  					id:$("#format").val(),
			  					format:$("#format").val(), 
			  					level:$("#level").val(),
			  					price:$("#price").val(),
			  					realprice:$("#realprice").val(),
			  					freight:$("#freight").val(),
			  					specialinc:$("#specialinc").val(),
			  					directinc:$("#directinc").val(),
			  					pack:$("#package").val(),
			  					quantity:$("#number").val(),
			  					money:$("#sumprice").val(),
			  					ifprofit:$("#ifprofit").val(),
			  					volume:$("#volume").val(),
			  					fare:$("#fare").val(),
			  					freight2:$("#freight2").val()
			  				},
			  				function(data){
			  					alert(data);
			  					$.post("<%=request.getContextPath() %>/servlet/OrderCartServlet",
			  				{
			  					op:"getCartItemsNum"	
			  				},
			  				function(data){
			  					$("#cartItemsNum").html(data);
			  			});
			  					
			  			});
  		}
  	
  });
    $("#show").click(function(){
	var time = $("#time").val();
	var id = $("#orderid").val();
	var distributor = $("#distributor").val();
	var area = $("#area").val();
	var salesman = $("#salesman").val();
	window.location.href="<%=request.getContextPath() %>/servlet/OrderCartServlet?op=showCart&time="+time+"&id="+id+"&distributor="+distributor+"&area="+area+"&salesman="+salesman;
  });
function come_show(){
		$.post("<%=request.getContextPath() %>/servlet/OrderCartServlet",
			  				{
			  					op:"getCartItemsNum"	
			  				},
			  				function(data){
			  					$("#cartItemsNum").html(data);
			  			});
}

  </script>
</html>
