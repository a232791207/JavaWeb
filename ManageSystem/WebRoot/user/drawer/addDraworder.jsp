<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.1.min.js"></script>
<link href="/ManageSystem/css/bootstrap.css" rel="stylesheet"
	type="text/css" media="all" />
	<link href="/ManageSystem/css/basicStyle.css" rel="stylesheet" type="text/css" media="all"/>
</head>

<body style="text-align: center;">
	<c:if test="${sessionScope.clerk==null }">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/drawer/drawerLogin.jsp";
		</script>
	</c:if>
	<c:if test="${sessionScope.clerk!=null }">
		<h2>开票</h2>
		<hr />
		<br />
		<br />
		<br />
		<form class="form-horizontal"
			action="${pageContext.request.contextPath }/servlet/AddDraworderServlet"
			method="post" name="form1">
			<div class="form-group">
				<label class="col-sm-3 control-label">开票时间</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="time" id="time"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkTime()" value="${requestScope.time}"/> 
						<label id="timeNews" style="color:#FF0000"></label>
				</div>
				<label class="col-sm-2 control-label">发票号</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="id" name="id"
						onkeyup="isPInt('id')" autocomplete="off"value="${requestScope.id}" /> <label id="idNews"
						style="color:#FF0000"></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">经销商</label>
				<div class="col-sm-2">
					<%-- 注释内容 <input class="form-control" type="text" id="distributor"
						name="distributor" onkeyup="checkDistributor()"
						autocomplete="off" value="${requestScope.distributor}"/> <label id="distributorNews"
						style="color:#FF0000"></label>
					--%>
					<select class="form-control" name="distributor" id="distributor" onchange="checkDistributor()"> 
						<option value="" selected="selected">请选择</option>
				    </select>
				    <label id="distributorNews" style="color:#FF0000"></label>
				</div>
				<label class="col-sm-2 control-label">业务员</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="salesman"
						name="salesman" onkeyup="checkSalesman()" autocomplete="off" value="${requestScope.salesman}"/> <label
						id="salesmanNews" style="color:#FF0000"></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">开票单位</label>
				<div class="col-sm-2">
					<%-- 注释内容 <input class="form-control" type="text" id="customer"
						name="customer" onkeyup="checkCustomer()" autocomplete="off" value="${requestScope.customer}"/> 
					--%>
					<select class="form-control" name="customer" id="customer" onchange="checkCustomer()">
						<option value="" selected="selected">请选择</option>
				    </select>
					<label id="customerNews" style="color:#FF0000"></label>
				</div>
				<label class="col-sm-2 control-label">订单号</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="orderid" name="orderid"
						autocomplete="off"value="${requestScope.orderid}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">规格</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="format" name="format"
						onkeyup="checkFormat()" autocomplete="off" /> <label id="formatNews"
						style="color:#FF0000"></label>
				</div>
				<label class="col-sm-2 control-label">等级</label>
				<div class="col-sm-2">
					<select class="form-control" name="level" id="level" onchange="pricekeyup();numberkeyup()">
						<option value="" selected="selected">请选择</option>
					</select> <label></label>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">单价(元/片)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="price" name="price"
						onkeyup="isPFloat('price');pricekeyup()" autocomplete="off" /> 
						<label id="priceNews" style="color:#FF0000"></label>
				</div>
				<label class="col-sm-2 control-label">数量(片)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="number" name="number"
						onkeyup="isPInt('number');numberkeyup()" autocomplete="off" /> <label
						id="numberNews" style="color:#FF0000"></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">总额(元)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="sumprice"
						name="sumprice" onkeyup="sumpricekeyup()"/> 
					<label id="sumpriceNews" style="color:#FF0000"></label>
				</div>
				<label class="col-sm-2 control-label">票额核对</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="checkdenomination" name="checkdenomination"
						onkeyup="isPFloat('checkdenomination');checkdenominationkeyup()" autocomplete="off" /> <label
						id="checkdenominationNews" style="color:#FF0000"></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">差额</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="balance"
						name="balance" onkeyup="balancekeyup()"/> 
					<label id="balanceNews" style="color:#FF0000"></label>
				</div>
				
			</div>
			<div class="form-group">
				<div style="text-align:center;">
					<button type="button" class="btn btn-default" id="confirm"
						onclick="submitAble()">直接开票</button>
				</div>
			</div>
			<div class="cart">
			<input type="button" class="cart-button" value="加入开票队列" id="cart"><label id="cartItemsNum" style="color:#FF0000"></label>
			</div>
			<div class="cart">
			<input type="button" class="show-button" value="查看开票队列" id="show">
			</div>
		</form>
	</c:if>
</body>
<script type="text/javascript">
  function isPFloat(id){
		var pFloatReg=/^(\d*\.)?\d+$/;
		var info=document.getElementById(id).value;
		if(!info.match(pFloatReg)||info==0){
			document.getElementById(id+"News").innerHTML="*请输入正的整数或小数";
		}else{
			document.getElementById(id+"News").innerHTML="*通过";
		}
	}
	  function isPInt(id){
		var pIntReg=/^\+?[0-9]*$/;
		var info=document.getElementById(id).value;
		if(!info.match(pIntReg)){
			document.getElementById(id+"News").innerHTML="*请输入有效的正整数";
		}else{
			document.getElementById(id+"News").innerHTML="*通过";
		}
	}
function submitAble(){
		var all =["time","id","distributor","customer","salesman","format","price","number","sumprice","checkdenomination","balance"];
		var flag =1;
		for ( var id in all) {
			if($("#"+all[id]+"News").html()!="*通过"){
				flag=0;
			}
		}
		if(flag==0){
			alert("请填写正确的信息!");
		}
		else{
			var sure = window.confirm("确定要开票吗？这里只开当前产品的票");
    		if(sure){
    			$(".form-horizontal").submit();
    		}
		}
		
	}
	function checkTime(){
	if($("#time").val()!="")
		$("#timeNews").html("*通过");
	else
		$("#timeNews").html("*必填");
	}
function checkDistributor(){
	  $.post(	"<%=request.getContextPath() %>/servlet/CheckDrawDistributorServlet",
			  	{
					distributor:$("#distributor").val()
			  	},
			 	 function(data){
			  		$("#distributorNews").html("*"+data);
			  		if($("#distributorNews").html()=="*通过"){
			  			$("#customer").val($("#distributor").val());
			  			$("#customerNews").html("*通过");
			  			checkSalesman();
			  		}
			  		}
			 );
}
function checkSalesman(){
	if ($("#distributor").val() == "" || $("#distributorNews").html() != "*通过") {
		$("#distributorNews").html("*请先输入正确的经销商");
		return;
		}
	if($("#salesman").val()!=""){
		$.post("<%=request.getContextPath() %>/servlet/CheckDrawSalesmanServlet",
			  {
				distributor:$("#distributor").val(),
				salesman:$("#salesman").val()
			  },
			  function(data){
			  	$("#salesmanNews").html("*"+data);		
			  });
	}
	 
}
function checkCustomer(){
	if($("#customer").val()=="")
		$("#customerNews").html("*开票单位不能为空");
	else
		$("#customerNews").html("*通过");
}
function checkFormat() {
	if ($("#distributor").val() == "" || $("#distributorNews").html() != "*通过") {
		$("#distributorNews").html("*请先输入正确的经销商");
		$("#format").val("");
		return;
	}
	$.post("<%=request.getContextPath()%>/servlet/CheckDrawFormatServlet", 
	{
		distributor : $("#distributor").val(),
		format : $("#format").val()
	},
			function(data) {
				if (data == "") {
					$("#formatNews").html("*请填写正确的规格");
					$("#level").html("");
				} else {
					$("#formatNews").html("*通过");
					/*
					 * 改变level的option,先清空，再添加
					 */
					$("#level").html("");
					var x = data.split(",");
					for ( var i = 0; i < x.length; i++) {
						var option = "<option value=" + x[i] + ">" + x[i]
								+ "</option>";
						$(option).appendTo("#level");
					}
					// 让第一个被选中
					$("#level").option[0].selected = true;
				}
			});
}
  function pricekeyup(){
  //判断是否填写format
  	if($("#format").val()==""||$("#formatNews").html()!="*通过"){
  		$("#formatNews").html("*请先填写正确的产品规格");
  		$("#price").val("");
  		return ;
  	}
  	if($("#price").val()!=""&&$("#priceNews").html()!="*请输入正的整数或小数"){
  		  $.post("<%=request.getContextPath() %>/servlet/CheckDrawPriceServlet",
			  {
				distributor:$("#distributor").val(),
				format:$("#format").val(),
				level:$("#level").val(),
				price:$("#price").val()
			  },
			  function(data){
			  	$("#priceNews").html("*"+data);
			  	if($("#numberNews").html()=="*通过"&&$("#priceNews").html()=="*通过"){
			  			$("#sumprice").val($("#number").val()*$("#price").val());
			  			$.post("<%=request.getContextPath() %>/servlet/CheckDrawSumpriceServlet",
			  				{
			  				sumprice:$("#sumprice").val(),
			  				distributor:$("#distributor").val(),
			  				salesman:$("#salesman").val()
			  				},
			  				function(data){
			  					$("#sumpriceNews").html("*"+data);
			  			});
					}			
			  });	
  		}

  }
  function numberkeyup(){
  //判断是否填写format
  	if($("#format").val()==""||$("#formatNews").html()!="*通过"){
  		$("#formatNews").html("*请先填写正确的产品规格");
  		$("#number").val("");
  		return ;
  	}
  	if($("#format").val()!=""&&$("#numberNews").html()!="*请输入正整数"){
  		  	  $.post("<%=request.getContextPath() %>/servlet/CheckDrawNumServlet",
			  {
				distributor:$("#distributor").val(),
				format:$("#format").val(),
				level:$("#level").val(),
				number:$("#number").val()
			  },
			  function(data){
			  	$("#numberNews").html("*"+data);
			  	if($("#numberNews").html()=="*通过"&&$("#priceNews").html()=="*通过"){
			  		$("#sumprice").val($("#number").val()*$("#price").val());
			  		$.post("<%=request.getContextPath() %>/servlet/CheckDrawSumpriceServlet",
			  				{
			  				sumprice:$("#sumprice").val(),
			  				distributor:$("#distributor").val(),
			  				salesman:$("#salesman").val()
			  				},
			  				function(data){
			  					$("#sumpriceNews").html("*"+data);
			  			});
			  		
			  	}	
			  });
  	}

  }
  function sumpricekeyup(){
  	if($("#sumprice").val()==""){
  		$("#sumpriceNews").html("*总价不能为空");
  		return;
  	}
  	if($("#number").val()==""||$("#numberNews").html()!="*通过"){
  		$("#numberNews").html("*请先填写正确的产品数量");
  		$("#sumprice").val("");
  		return ;
  	}
  	$.post("<%=request.getContextPath() %>/servlet/CheckDrawSumpriceServlet",
		{
		sumprice:$("#sumprice").val(),
		distributor:$("#distributor").val(),
		salesman:$("#salesman").val()
		},
		function(data){
			$("#sumpriceNews").html("*"+data);
			if($("#sumpriceNews").html("*通过")){
				var price = $("#sumprice").val()/$("#number").val();
			  	$("#price").val(price.toFixed(2));
			  	$.post("<%=request.getContextPath() %>/servlet/CheckDrawPriceServlet",
				  {
					distributor:$("#distributor").val(),
					format:$("#format").val(),
					level:$("#level").val(),
					price:$("#price").val()
				  },
				  function(data){
				  	$("#priceNews").html("*"+data);			
				  });
			}
	});
  		
  }
function  checkdenominationkeyup(){
//1.检验总额填好没
	if($("#sumprice").val()==""||$("#sumpriceNews").html()!="*通过"){
		$("#checkdenominationNews").html("*请先填写正确的总价");
  		$("#checkdenomination").val("");
	}
	else{
		//计算差额=总额-票额核对*1.17
		var number=$("#sumprice").val()-$("#checkdenomination").val()*1.17;
		$("#balance").val(number);
		$("#balanceNews").html("*通过");
	}

}
function  balancekeyup(){
	if($("#balance").val()==""){
		$("#balanceNews").html("*总价不能为空");
	  	return;
	}
  	if($("#checkdenomination").val()==""||$("#checkdenominationNews").html()!="*通过"){
  		$("#checkdenominationNews").html("*请先填写正确的票额核对");
  		$("#balance").val("");
  		return ;
  	}
  	if($("#sumprice").val()==""||$("#sumpriceNews").html()!="*通过"){
		$("#checkdenominationNews").html("*请先填写正确的总价");
  		$("#checkdenomination").val("");
	}
	else{
		//计算差额=总额-票额核对*1.17
		var number=$("#sumprice").val()-$("#checkdenomination").val()*1.17;
		$("#balance").val(number);
		$("#balanceNews").html("*通过");
	}

}
  $("#cart").click(function(){
  	//检查规格等级，单价，数目是否合格
  	var all =["time","id","distributor","customer","salesman","format","price","number","sumprice","checkdenomination","balance"];
		var flag =1;
		for ( var id in all) {
			if($("#"+all[id]+"News").html()!="*通过"){
				flag=0;
			}
		}
		if(flag==0){
			alert("请填写正确的信息!");
		}
		else{
			//合格，加入购物车
			$.post("<%=request.getContextPath() %>/servlet/CartServlet",
			  				{	
			  					op:"add",
			  					format:$("#format").val(),
			  					level:$("#level").val(),
			  					price:$("#price").val(),
			  					number:$("#number").val(),
			  					sumprice:$("#sumprice").val(),
			  					checkdenomination:$("#checkdenomination").val(),
			  					balance:$("#balance").val(),
			  					id:$("#id").val()
			  				},
			  				function(data){
			  					alert(data);
			  					$.post("<%=request.getContextPath() %>/servlet/CartServlet",
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
  var all =["time","id","distributor","customer","salesman"];
		var flag =1;
		for ( var id in all) {
			if($("#"+all[id]+"News").html()!="*通过"&&$("#"+all[id]+"News").html()!=""){
				flag=0;
			}
		}
		if(flag==0){
			alert("请填写正确的日期、发票号、经销商、开票单位和业务员!");
		}
		else{
			var time = $("#time").val();
			var id = $("#id").val();
			var distributor = $("#distributor").val();
			var customer = $("#customer").val();
			var salesman = $("#salesman").val();
			var orderid=$("#orderid").val();
			window.location.href="<%=request.getContextPath() %>/servlet/CartServlet?op=showCart&time="+time+"&id="+id+"&distributor="+distributor+"&customer="+customer+"&salesman="+salesman+"&orderid="+orderid;
		}
  	
  });
  $(document).ready(function(){
  		if($("#customer").val()!=""){
  			var all =["time","id","distributor","customer","salesman"];
  			for ( var id in all) {
				$("#"+all[id]+"News").html("*通过");
			}
		}
		$.post("<%=request.getContextPath() %>/servlet/CartServlet",
			  				{
			  					op:"getCartItemsNum"	
			  				},
			  				function(data){
			  					$("#cartItemsNum").html(data);
			  			});
		if("${requestScope.error}"!=""){
			alert("${requestScope.error}");
		}
		
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
		url2 = "<%=request.getContextPath() %>/servlet/SelectCustomerServlet";
		$.post(url2,
		  {},
		  function(data){
			var x = data.split(",");
			for(var i=0; i<x.length;i++){
				var option="<option value="+x[i]+">"+x[i]+"</option>";
				$(option).appendTo("#customer");
			}
		  });
		  });
		
</script>
</html>
