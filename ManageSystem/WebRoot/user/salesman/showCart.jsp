<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="/ManageSystem/css/basicStyle.css" rel="stylesheet" type="text/css" media="all"/>
</head>
<body>
	<c:if test="${sessionScope.clerk.type!='业务员' }">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/salesman/salesmanLogin.jsp";
		</script>
	</c:if>
	<c:if test="${empty sessionScope.cart.items}">
		<h3 class="center">订单队列为空</h3>
	</c:if>
	<c:if test="${!empty sessionScope.cart.items}">
		<h2 class="center">订单队列如下</h2><hr>
    	<table class="table table-striped center">
    		<tr>
    			<th>规格</th>
    			<th>等级</th>
    			<th>标准单价</th>
    			<th>实价</th>
    			<th>一票制运费</th>
    			<th>特殊优惠</th>
    			<th>直接优惠</th>
    			<th>数量(包)</th>
    			<th>数量(片)</th>
    			<th>体积(立方米)</th>
    			<th>是否收取调度费</th>
    			<th>是否参与返利</th>
    			<th>小计</th>
    			<th>操作</th>
    		</tr>
    		<c:forEach items="${sessionScope.cart.items}" var="me" varStatus="vs">
    			<tr class="${vs.index%2==0?'odd':'even' }">
	    			<td>${me.value.format}</td>
	    			<td>${me.value.level}</td>
	    			<td>${me.value.price}</td>
	    			<td>${me.value.realprice}</td>
	    			<td>${me.value.freight}</td>
	    			<td>${me.value.specialinc}</td>
	    			<td>${me.value.directinc}</td>
	    			<td><input type="text" id="quantity" value="${me.value.pack}" size="2" onchange="changeNum(this,${me.value.pack},'${me.value.format}','${me.value.level}','${requestScope.time}','${requestScope.id}','${requestScope.distributor}','${requestScope.area}','${requestScope.salesman}')"/></td>
	    			<td>${me.value.quantity}</td>
	    			<td>${me.value.volume}</td>
	    			<td>${me.value.fare}</td>
	    			<td>${me.value.ifprofit}</td>
	    			<td>${me.value.money}</td>
	    			<td>
	    				<a href="javascript:delOneItem('${me.key}','${requestScope.time}','${requestScope.id}','${requestScope.distributor}','${requestScope.area}','${requestScope.salesman}')">删除</a>
	    			</td>
	    		</tr>
    		</c:forEach>
    		<tr>
    			<td colspan="14" align="right">
    				
    				<a href="javascript:delAllItems('${requestScope.time}','${requestScope.id}','${requestScope.distributor}','${requestScope.area}','${requestScope.salesman}')">清空队列</a>
    				总数量：${sessionScope.cart.totalQuantity}
    				总体积：${sessionScope.cart.totalVolume}
    				应付金额：${sessionScope.cart.totalMoney}
    				<a href="javascript:cal()">
    				全部下单</a>
    			</td>
    		</tr>
    	</table>
	</c:if>
	<a href="${pageContext.request.contextPath}/servlet/OrderCartServlet?op=showAddOrder&time=${requestScope.time}&id=${requestScope.id}&distributor=${requestScope.distributor}&area=${requestScope.area}&salesman=${requestScope.salesman}">返回</a>
	
	
</body>
<script type="text/javascript">
	if("${requestScope.error}"!="")
		alert("${requestScope.error}");	
	
	function delAllItems(time,oid,distributor,area,salesman){
		var sure = window.confirm("确定要删除该项吗？");
    		if(sure){
    			var url="${pageContext.request.contextPath}/servlet/OrderCartServlet?op=delAllItems";
    			url=url+"&time="+time+"&oid="+oid+"&distributor="+distributor+"&area="+area+"&salesman="+salesman;
    			window.location.href=url;
    		}
	}
	function delOneItem(id,time,oid,distributor,area,salesman){
    		var sure = window.confirm("确定要删除该项吗？");
    		if(sure){
    			var url="${pageContext.request.contextPath}/servlet/OrderCartServlet?op=delOneItem&id="+id;
    			url=url+"&time="+time+"&oid="+oid+"&distributor="+distributor+"&area="+area+"&salesman="+salesman;
    			window.location.href=url;
    		}
    	}
    function changeNum(inputObj,oldNum,format,level,time,oid,distributor,area,salesman){
    		var sure = window.confirm("确定要修改数量吗?");
    		if(sure){
    			//修改新数量
    			var num = inputObj.value;
    				//验证：用户输入的必须是自然数。 字母\1.1\-1排除
    			if(!/^[1-9][0-9]*$/.test(num)){
    				alert("请输入正确的数量");
    				return;
    			}
    			//提交给服务器修改该项的数量
    			var url="${pageContext.request.contextPath}/servlet/OrderCartServlet?op=changeNum&num="+num+"&format="+format+"&level="+level;
    			url=url+"&time="+time+"&oid="+oid+"&distributor="+distributor+"&area="+area+"&salesman="+salesman;
    			window.location.href=url;
    		}else{
    			//显示原来的值
    			inputObj.value = oldNum;
    		}
    	}
    function cal(){
    	var sure = window.confirm("确定要将队列里的订单都下单吗？");
    		if(sure){
				window.location.href="${pageContext.request.contextPath}/servlet/OrderCartServlet?op=calCart&time=${requestScope.time}&id=${requestScope.id}&distributor=${requestScope.distributor}&area=${requestScope.area}&salesman=${requestScope.salesman}";   
				 		}
    	
    }
</script>
</html>