<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.8.1.min.js"></script>
  </head>
  
  <body style="text-align: center;">
  	<c:if test="${sessionScope.clerk.type!='财务员' }">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/treasurer/treasurerLogin.jsp";
		</script>
	</c:if> 
  	<h2 style="font-size: 30px;font-weight:500;">批量修改标准单价项</h2><hr>
	<form action="${pageContext.request.contextPath }/servlet/BatchUpdatePriceServlet" method="post">
		<table style="margin:auto;">
			<tr>
				<td>区域：</td>
				<td><input type="text" name="area" id="area"/></td>
			</tr>
			<tr>
				<td>规格：</td>
				<td><input type="text" name="format" id="format"/></td>
			</tr>
			<tr>
				<td>等级：</td>
				<td><input type="text" name="level" id="level"/></td>
			</tr>
			<tr>
				<td>标准单价：</td>
				<td><input type="text" name="price" id="price"/>元/片</td>
			</tr>
			<tr>
				<td><input type="submit" value="确认修改" onclick="confirmUpdate()"/></td>
			</tr>	
		</table>
	</form>
  </body>
  <script type="text/javascript">
  	function confirmUpdate(){
  		var str = "您将要把\n";
  		if($("#area").val()!=""){
  			str=str+"区域为："+$("#area").val()+"\n";
  		}
  		if($("#format").val()!=""){
  			str=str+"规格为："+$("#format").val()+"\n";
  		}
  		if($("#level").val()!=""){
  			str=str+"等级为："+$("#level").val()+"\n";
  		}
  		if($("#price").val()!=""){
  			str=str+"的标准单价修改为："+$("#price").val()+"元/片！";
  		}
  		return confirm(str);
  	}
  </script>
</html>
