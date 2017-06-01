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
	<c:if test="${sessionScope.clerk.type!='开票员'&&sessionScope.clerk.type!='财务员'}">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/drawer/drawerLogin.jsp";
		</script>
	</c:if>
	<c:if test="${empty sessionScope.cart.items}">
		<h3 class="center">您的开票队列为空</h3>
	</c:if>
	<c:if test="${!empty sessionScope.cart.items}">
		<h2 class="center">您开票的产品如下</h2><hr>
    	<table class="table table-striped center">
    		<tr>
    			<th>规格</th>
    			<th>等级</th>
    			<th>单价</th>
    			<th>数量</th>
    			<th>总价</th>
    			<th>票额核对</th>
    			<th>差额</th>
    			<th>操作</th>
    		</tr>
    		<c:forEach items="${sessionScope.cart.items}" var="me" varStatus="vs">
    			<tr class="${vs.index%2==0?'odd':'even' }">
	    			<td>${me.value.product.format}</td>
	    			<td>${me.value.product.level}</td>
	    			<td>${me.value.price}</td>
	    			<td><input type="text" id="quantity" value="${me.value.quantity}" size="2" onchange="changeNum(this,${me.value.checkdenomination},${me.value.quantity},${me.value.product.id},${me.value.price},'${requestScope.time}','${requestScope.id}','${requestScope.distributor}','${requestScope.customer}','${requestScope.salesman}','${requestScope.orderid}')"/></td>
	    			<td>${me.value.money}</td>
	    			<td>${me.value.checkdenomination}</td>
	    			<td>${me.value.balance}</td>
	    			<td>
	    				<a href="javascript:delOneItem('${me.key}','${requestScope.time}','${requestScope.id}','${requestScope.distributor}','${requestScope.customer}','${requestScope.salesman}','${requestScope.orderid}')">删除</a>
	    			</td>
	    		</tr>
    		</c:forEach>
    		<tr>
    			<td colspan="6" align="right">
    				
    				<a href="javascript:delAllItems('${requestScope.time}','${requestScope.id}','${requestScope.distributor}','${requestScope.customer}','${requestScope.salesman}','${requestScope.orderid}')">清空队列</a>
    				总数量：${sessionScope.cart.totalQuantity}
    				总金额：${sessionScope.cart.totalMoney}
    				总票额核对：${sessionScope.cart.totalDenomination}
    				总差额：${sessionScope.cart.totalBalance}
    				<a href="javascript:cal()">
    				全部开票</a>
    			</td>
    		</tr>
    	</table>
	</c:if>
	<a href="${pageContext.request.contextPath}/servlet/CartServlet?op=showAddDraworder&time=${requestScope.time}&id=${requestScope.id}&distributor=${requestScope.distributor}&customer=${requestScope.customer}&salesman=${requestScope.salesman}&orderid=${requestScope.orderid}">返回</a>
	
	
</body>
<script type="text/javascript">
	if("${requestScope.error}"!="")
		alert("${requestScope.error}");
	
	function delAllItems(time,did,distributor,customer,salesman,orderid){
		var sure = window.confirm("确定要删除该项吗？");
    		if(sure){
    			var url="${pageContext.request.contextPath}/servlet/CartServlet?op=delAllItems";
    			url=url+"&time="+time+"&did="+did+"&distributor="+distributor+"&customer="+customer+"&salesman="+salesman+"&orderid="+orderid;;
    			window.location.href=url;
    		}
	}
	function delOneItem(id,time,did,distributor,customer,salesman,orderid){
    		var sure = window.confirm("确定要删除该项吗？");
    		if(sure){
    			var url="${pageContext.request.contextPath}/servlet/CartServlet?op=delOneItem&id="+id;
    			url=url+"&time="+time+"&did="+did+"&distributor="+distributor+"&customer="+customer+"&salesman="+salesman+"&orderid="+orderid;
    			window.location.href=url;
    		}
    	}
    function changeNum(inputObj,denomination,oldNum,id,price,time,did,distributor,customer,salesman,orderid){
    		var sure = window.confirm("确定要修改数量吗?");
    		if(sure){
    			//修改新数量
    			var num = inputObj.value;
    				//验证：用户输入的必须是自然数。 字母\1.1\-1排除
    			if(!/^[1-9][0-9]*$/.test(num)){
    				alert("请输入正确的数量");
    				return;
    			}
    			var sumprice=num*price;
    			var denomination=denomination*num/oldNum;
    			var balance=sumprice-1.17*denomination;
    			//提交给服务器修改该项的数量
    			var url="${pageContext.request.contextPath}/servlet/CartServlet?op=changeNum&num="+num+"&id="+id+"&price="+price;
    			url=url+"&time="+time+"&did="+did+"&distributor="+distributor+"&customer="+
    			customer+"&salesman="+salesman+"&orderid="+orderid+"&sumprice="+sumprice+"&denomination="+denomination+"&balance="+balance;
    			window.location.href=url;
    		}else{
    			//显示原来的值
    			inputObj.value = oldNum;
    		}
    	}
    function cal(){
    	var sure = window.confirm("确定要将队列里的产品都开票吗？");
    		if(sure){
				window.location.href="${pageContext.request.contextPath}/servlet/CartServlet?op=calCart&time=${requestScope.time}&id=${requestScope.id}&distributor=${requestScope.distributor}&customer=${requestScope.customer}&salesman=${requestScope.salesman}&orderid=${requestScope.orderid}";   
			}
	}
</script>
</html>