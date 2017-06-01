<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
  	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.8.1.min.js"></script>
	<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" type="text/css"/>
	<link href="/ManageSystem/css/basicStyle.css" rel="stylesheet" type="text/css" media="all"/>
	<script type="text/javascript">		
	function del(id){
			if(confirm("确定要删除吗？")){
				
    			window.location.href="${pageContext.request.contextPath }/servlet/DeletePriceServlet?id="+id+"&thispage=${requestScope.page.thispage}&area=${param.area}&format=${param.format}&level=${param.level}";
    		}
		
		}
		function importByExls(){
			if($("#file").val()!=""){
				$("#form_file").submit();
			}
			else{
				alert("请选择合适的xls文件!");
			}
		}
	function delAllPrice(){
		if(confirm("确定要删除吗？")){
			window.location.href="${pageContext.request.contextPath }/servlet/DelAllPriceServlet";
		}
	}
	</script>
  </head>
  
  <body>
  	<c:if test="${sessionScope.clerk.type!='财务员' }">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/treasurer/treasurerLogin.jsp";
		</script>
	</c:if> 
	<h2 style="text-align:center;">标准单价管理</h2><hr/>
	<div style="text-align:center;">
	<div style="float:left;">
	<input type="button" id="print" onclick="window.location.href='${pageContext.request.contextPath }/servlet/PrintPriceListServlet?from=1&&to=${requestScope.page.countpage}' " value="打印"/>
	</div>
	<div style="text-align:center;">
	<input style="width:100px;height:30px;" type="button" value="新增标准单价" onclick="addPrice()" />
	<input style="width:100px;height:30px;" type="button" value="清空单价表" onclick="delAllPrice()"/><br><br>
	<form id="form_file" action="${pageContext.request.contextPath }/servlet/ImportPriceByXlsServlet" method="post" enctype="multipart/form-data">
	批量导入单价：<input id="file" type="file" name="file" accept=".xls">
	<input type="button" onclick="importByExls();" value="开始导入">
	</form>
	<br/>
	</div>
	
	<form name="SelectAndUpdate" method="post">
		区域：<input type="text" name="area" id="area" value="${param.area }"/>
		规格：<input type="text" name="format" id="format" value="${param.format }"/>
		等级：<input type="text" name="level" id="level" value="${param.level }"/>
		<input style="width:50px;height:30px;" type="button" value="查询" onclick="selectPrice()"/><br/><br/>
		单价：<input type="text" name="price" id="price" value="${param.price }"/>
		<input style="width:130px;height:30px;" type="button" id="UpdatePrice" value="批量修改标准单价" disabled="disabled" onclick="batchUpdatePrice()"/>&nbsp;&nbsp;
		变动范围：<input type="text" name="changelim" id="changelim" value="${param.changelim }"/>
		<input style="width:130px;height:30px;" type="button" id="UpdateChangelim" value="批量修改变动范围" disabled="disabled" onclick="batchUpdateChangelim()"/>
	</form>
	</div><br/>
	<table class="table table-striped center">
		<tr>
			<th>时间</th>
			<th>区域</th>
			<th>规格</th>
			<th>等级</th>
			<th>标准单价(元/片)</th>
			<th>变动范围(元/片)</th>
			<th>修改</th>	
			<th>删除</th>	
		</tr>
		<c:forEach items="${requestScope.page.list }" var="price">
			<tr>
				<td><c:out value="${price.time }" /></td>
				<td><c:out value="${price.area }" /></td>
				<td><c:out value="${price.format }" /></td>
				<td><c:out value="${price.level }" /></td>
				<td><c:out value="${price.price }" /></td>
				<td><c:out value="${price.changelim }" /></td>
				<td>
					<a href="${pageContext.request.contextPath }/user/treasurer/updatePrice.jsp?id=${price.id}">修改</a>
				</td>
				<td>
					<a href="javascript:del(${price.id})">删除</a>
				</td>
		  	</tr>
		</c:forEach>
	</table>

	<div style="text-align:center;">
	共${requestScope.page.countpage }页
	<a href="${pageContext.request.contextPath }/servlet/SelectPriceServlet?thispage=${requestScope.page.firstpage}&area=${param.area}&format=${param.format}&level=${param.level}">首页</a>
	<a href="${pageContext.request.contextPath }/servlet/SelectPriceServlet?thispage=${requestScope.page.prepage}&area=${param.area}&format=${param.format}&level=${param.level}">上一页</a>
	
	<!-- 分页逻辑开始 -->
	<c:if test="${requestScope.page.countpage<=5 }">
		<c:set var="begin" value="1" scope="page"></c:set>
		<c:set var="end" value="${requestScope.page.countpage }" scope="page"></c:set>
	</c:if>
	<c:if test="${requestScope.page.countpage>5 }">
		<c:choose>
			<c:when test="${requestScope.page.thispage<=3 }">
				<c:set var="begin" value="1" scope="page"></c:set>
				<c:set var="end" value="5" scope="page"></c:set>
			</c:when>
			<c:when test="${requestScope.page.thispage>=requestScope.page.countpage-2 }">
				<c:set var="begin" value="${requestScope.page.countpage-4 }" scope="page"></c:set>
				<c:set var="end" value="${requestScope.page.countpage }" scope="page"></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="begin" value="${requestScope.page.thispage-2 }" scope="page"></c:set>
				<c:set var="end" value="${requestScope.page.thispage+2 }" scope="page"></c:set>
			</c:otherwise>
		</c:choose>
	</c:if>
	<c:forEach begin="${begin }" end="${end }" step="1" var="i">
		<c:if test="${requestScope.page.thispage == i }">
			${i }
		</c:if>
		<c:if test="${requestScope.page.thispage != i }">
			<a href="${pageContext.request.contextPath }/servlet/SelectPriceServlet?thispage=${i}&area=${param.area}&format=${param.format}&level=${param.level}">${i }</a>
		</c:if>
	</c:forEach>
	<!-- 分页逻辑结束 -->
	<a href="${pageContext.request.contextPath }/servlet/SelectPriceServlet?thispage=${requestScope.page.nextpage}&area=${param.area}&format=${param.format}&level=${param.level}">下一页</a>
	<a href="${pageContext.request.contextPath }/servlet/SelectPriceServlet?thispage=${requestScope.page.lastpage}&area=${param.area}&format=${param.format}&level=${param.level}">尾页</a>
	</div>
  <script type="text/javascript">
  if("${requestScope.success}"!=""){
			alert("${requestScope.success}");
		}
  	$("#price").keyup(function(){
  		if($("#price").val()!=""){
  			$("#UpdatePrice").removeAttr("disabled");
  		}else{
  			$("#UpdatePrice").attr("disabled","disabled");
  		}
  	});
  	$("#changelim").keyup(function(){
  		if($("#changelim").val()!=""){
  			$("#UpdateChangelim").removeAttr("disabled");
  		}else{
  			$("#UpdateChangelim").attr("disabled","disabled");
  		}
  	});
  	function addPrice(){
  		window.location.href="${pageContext.request.contextPath }/user/treasurer/addPrice.jsp";
  	}
  	function selectPrice(){
  		document.SelectAndUpdate.action="${pageContext.request.contextPath }/servlet/SelectPriceServlet?thispage=1";
  		document.SelectAndUpdate.submit();
  	}
  	function batchUpdatePrice(){
  		var str = "";
  		if($("#area").val()==""&&$("#format").val()==""&&$("#level").val()==""){
  			str = str + "您将要把所有标准单价修改为：" + $("#price").val() + "元/片";
  		}else{
  			str = str + "您将要将满足下列条件的所有标准单价修改为：" + $("#price").val() + "元/片\n";
  			if(!$("#area").val()==""){
  				str = str + "区域：" + $("#area").val() + "\n";
  			}
  			if(!$("#format").val()==""){
  				str = str + "区域：" + $("#format").val() + "\n";
  			}
  			if(!$("#level").val()==""){
  				str = str + "区域：" + $("#level").val() + "\n";
  			}
  		}
  		if(confirm(str)){
  			document.SelectAndUpdate.action="${pageContext.request.contextPath }/servlet/BatchUpdatePriceServlet";
  			document.SelectAndUpdate.submit();
  			return true;
  		}else{
  			return false;
  		}
  	}
  	function batchUpdateChangelim(){
  		var str = "";
  		if($("#area").val()==""&&$("#format").val()==""&&$("#level").val()==""){
  			str = str + "您将要把所有变动范围修改为：" + $("#changelim").val() + "元/片";
  		}else{
  			str = str + "您将要将满足下列条件的所有变动范围修改为：" + $("#changelim").val() + "元/片\n";
  			if(!$("#area").val()==""){
  				str = str + "区域：" + $("#area").val() + "\n";
  			}
  			if(!$("#format").val()==""){
  				str = str + "区域：" + $("#format").val() + "\n";
  			}
  			if(!$("#level").val()==""){
  				str = str + "区域：" + $("#level").val() + "\n";
  			}
  		}
  		if(confirm(str)){
  			document.SelectAndUpdate.action="${pageContext.request.contextPath }/servlet/BatchUpdateChangelimServlet";
  			document.SelectAndUpdate.submit();
  			return true;
  		}else{
  			return false;
  		}
  	}
  	
  </script>
  </body>
  
</html>
