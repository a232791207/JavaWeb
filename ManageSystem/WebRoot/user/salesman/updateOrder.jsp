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
  	<c:if test="${sessionScope.clerk.type!='业务员' }">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/salesman/salesmanLogin.jsp";
		</script>
	</c:if> 
  	<h2>修改订单</h2><hr/>
  	<br/><br/>
	<form class="form-horizontal" action="${pageContext.request.contextPath }/servlet/UpdateOrderServlet" method="post">
	        <input type="hidden" name="orderid" id="orderid" value="${requestScope.id }"/>
	        <input type="hidden" name="oldformat" id="oldformat" value="${requestScope.format }"/>
	        <input type="hidden" name="oldlevel" id="oldlevel" value="${requestScope.level }"/>
	        <div class="form-group">
				<label class="col-sm-3 control-label">销售时间</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="time" name="time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					<label></label>
				</div>
				<label class="col-sm-2 control-label">业务员</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="salesman" name="salesman" value="${sessionScope.clerk.name }"/>
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
				<label class="col-sm-2 control-label">区域</label>
				<div class="col-sm-2">
					<select class="form-control" name="area" id="area">
						<option value="" selected="selected">请选择</option>
				    </select>
				    <label></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">规格</label>
				<div class="col-sm-2">
					<select class="form-control" name="format" id="format">
						<option value="" selected="selected">请选择</option>
				    </select>
				    <font id="formatF" color="red" size="2px"></font>
				</div>
				<label class="col-sm-2 control-label">等级</label>
				<div class="col-sm-2">
					<select class="form-control" name="level" id="level">
						<option value="" selected="selected">请选择</option>
				    </select>
				    <font id="levelF" color="red" size="2px"></font>
				</div>
			</div>
			 <div class="form-group">
				<label class="col-sm-3 control-label">标准单价(元/片)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="price" name="price" readonly="readonly" style="background-color: silver;"/>
				    <label></label>
				</div>
				<label class="col-sm-2 control-label">直接优惠(元/片)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="directinc" id="directinc" readonly="readonly" style="background-color: silver;"/>
				    <label></label>
				</div>				
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">一票制运费(元/片)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="freight" id="freight" value ="0"/>
				    <label></label>
				</div>
				<label class="col-sm-2 control-label">特殊优惠(元/片)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="specialinc" id="specialinc" value ="0"/>
				    <label></label>
				</div>				
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">实际单价(元/片)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="realprice" id="realprice" readonly="readonly" style="background-color: silver;"/>
                    <label></label>
				</div>
				<label class="col-sm-2 control-label">数量(包)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="package" id="package" value ="0"/>
				    <label></label>
				</div>				
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">数量(片)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="number" id="number" value ="0" style="background-color: silver;"/>
                    <font color="red" id="numberf"></font>
                    <label></label>
				</div>
				<label class="col-sm-2 control-label">体积(立方米)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="volume" id="volume" value ="0" readonly="readonly" style="background-color: silver;"/>
                    <font color="red" id="volumef"></font>
                    <label></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">运费(元)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="freight2" id="freight2" value ="0" onkeyup="isPFloat('freight2')"/>
				    <label id="freight2News"></label>
				</div>	
				<label class="col-sm-2 control-label">总价(元)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="sumprice" id="sumprice" readonly="readonly" style="background-color: silver;"/>
				    <font color="red" id="sumpricef"></font>
				</div>		
						
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">是否参与返利</label>
				<div class="col-sm-2">
				    <label class="checkbox-inline">
                        <input type="radio" name="ifprofit" id="ifprofit" value="是"> 是
                    </label>
                    <label class="checkbox-inline">
                        <input type="radio" name="ifprofit" id="ifprofit" value="否"> 否
                    </label><br/>
                    <font id="ifprofitF" color="red" size="2px"></font>
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
			<div class="form-group">
			    <div style="text-align:center;">
                    <button type="submit" class="btn btn-default" id="submit" onclick="return confirmUpdate()">确认修改</button>		
                </div>
			</div>
	</form>
  </body>
  <script type="text/javascript">
  	function confirmUpdate(){
  		if($("#orderid").val()==""||$("#time").val()==""||$("#salesman").val()==""||$("#distributor").val()==""||$("#area").val()==""
		||$("#format").val()==""||$("#level").val()==""||$("#price").val()==""||$("#directinc").val()==""||$("#freight").val()==""
		||$("#specialinc").val()==""||$("#realprice").val()==""||$("#package").val()==""||$("#number").val()==""||$("#sumprice").val()==""||$("#package").val()=="0"||isNaN($("#number").val())){
  			alert("您有未填的项目！请确认信息完整后再提交！");
  			return false;
  		}else{
  			return confirm("是否确认修改？");
  		}
  	}
  	$("#area").change(function(){
  		if($("#area").val()!=""){
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
			  	var x = data.split(",");
				for(var i=0; i<x.length;i++){
					var option="<option value="+x[i]+">"+x[i]+"</option>";
					$(option).appendTo("#format");
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
  	});
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
			  	var x = data.split(",");
				for(var i=0; i<x.length;i++){
					var option="<option value="+x[i]+">"+x[i]+"</option>";
					$(option).appendTo("#level");
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
			  url2="<%=request.getContextPath() %>/servlet/GetBasicFareServlet";
			  $.post(url2,
			  {
				area:$("#area").val()
			  },
			  function(data){
	  			$("#freight2").val(($("#volume").val()*data).toFixed(2));
	  			var x = $("#sumprice").val();
	  			$("#sumprice").val(x*1+$("#freight2").val()*1);
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
  	$("#freight2").focusout(function(){
  		var p = $("#price").val();
  		var fr = $("#freight").val();
  		var sl = $("#specialinc").val();
  		var dl = $("#directinc").val();
  		var num = $("#number").val();
  		$("#sumprice").val((p-sl-dl+fr*1).toFixed(2)*num+$("#freight2").val()*1);
  	});
  	$("#sumprice").focus(function(){
  		var rp = $("#realprice").val();
  		var num = $("#number").val();
  		$("#sumprice").val(rp*num);
  	});
  	$("#specialinc").focusout(function(){
  		var p = $("#price").val();
  		var fr = $("#freight").val();
  		var sl = $("#specialinc").val();
  		var dl = $("#directinc").val();
  		var num = $("#number").val();
  		$("#realprice").val((p-sl-dl+fr*1).toFixed(2));
  		$("#sumprice").val((p-sl-dl+fr*1).toFixed(2)*num+$("#freight2").val()*1);
  	});
  	$("#freight").focusout(function(){
  		var p = $("#price").val();
  		var fr = $("#freight").val();
  		var sl = $("#specialinc").val();
  		var dl = $("#directinc").val();
  		var num = $("#number").val();
  		$("#realprice").val((p-sl-dl+fr*1).toFixed(2));
  		$("#sumprice").val((p-sl-dl+fr*1).toFixed(2)*num+$("#freight2").val()*1);
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
		  });
		url2 = "<%=request.getContextPath() %>/servlet/SelectSalesmanServlet";
		$.post(url2,
		  {},
		  function(data){
			var x = data.split(",");
			for(var i=0; i<x.length;i++){
				var option="<option value="+x[i]+">"+x[i]+"</option>";
				$(option).appendTo("#salesman");
			}
		  });
		url3 = "<%=request.getContextPath() %>/servlet/SelectDistributorServlet";
		$.post(url3,
		  {},
		  function(data){
			var x = data.split(",");
			for(var i=0; i<x.length;i++){
				var option="<option value="+x[i]+">"+x[i]+"</option>";
				$(option).appendTo("#distributor");
			}
		  });
		url = "<%=request.getContextPath() %>/servlet/OrderInfoServlet";
		var id = "${requestScope.id}";
		$.post(url,
		  {
			id:id,
			format:"${requestScope.format}",
			level:"${requestScope.level}"
		  },
		  function(data){
			var x = data.split(",");
			$("#orderid").val(x[0]);
			$("#time").val(x[1]);
			$("#salesman").val(x[2]);
			$("#distributor").val(x[3]);
			$("#area").val(x[4]);
			if($("#area").val()!=""){
	  			$("#format").removeAttr("disabled");
	  			url = "<%=request.getContextPath() %>/servlet/SelectFormatServlet";
	  			$.post(url,
				  {
					area:$("#area").val()
				  },
				  function(data){
				  	var x = data.split(",");
					for(var i=0; i<x.length;i++){
						var option="<option value="+x[i]+">"+x[i]+"</option>";
						$(option).appendTo("#format");
					}
				 });
  			}
			$("#formatF").text("历史值为："+x[5]);
			$("#levelF").text("历史值为："+x[6]);
			$("#freight").val(x[9]);
			$("#specialinc").val(x[10]);
			$("#numberF").text("历史值为："+x[12]);
			$("#ifprofitF").text("历史值为："+x[14]);
			$("#ifprofit[value="+x[14]+"]").attr("checked",true);
		  });
	});
	</script>
</html>

