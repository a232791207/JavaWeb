<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
  <script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.8.1.min.js"></script>
  <link href="/ManageSystem/css/bootstrap.css" rel="stylesheet"type="text/css" media="all"/>
  <link href="/ManageSystem/css/basicStyle.css" rel="stylesheet"type="text/css" media="all"/>
  </head>
  
  <body style="text-align: center;">
  	<c:if test="${sessionScope.clerk.type!='储管员'&& sessionScope.admin==null}">
    	<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/storeManager/storeManagerLogin.jsp";
		</script>
    </c:if>
	<h2>修改库存信息</h2><hr/>
	<br/><br/><br/><br/><br/>
	<form class="form-horizontal" action="${pageContext.request.contextPath }/servlet/UpdateProductServlet" method="post">
	    <input type="hidden" name="id" id="id"/>
		<div class="form-group">
			<label class="col-sm-3 control-label">规格</label>
			<div class="col-sm-2">
				<input class="form-control" type="text" id="format" name="format" onkeyup="checkIsNotNull('format');checkUpdateProduct()" autocomplete="off" />
				<label id="formatNews"></label>
			</div>
			<label class="col-sm-2 control-label">等级</label>
			<div class="col-sm-2">
				<select class="form-control" name="level" id="level">
					<option value="优等" selected="selected">优等</option>
					<option value="合格">合格</option>
				</select> <label></label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">厚度(mm)</label>
			<div class="col-sm-2">
				<input class="form-control" type="text" id="thick" name="thick" onkeyup="isPFloat('thick');calculateV();checkUpdateProduct()" autocomplete="off" />
				<label id="thickNews" class="news"></label>
			</div>
			<label class="col-sm-2 control-label">数量(片)</label>
			<div class="col-sm-2">
				<input class="form-control" type="text" id="num" name="num" onkeyup="isPInt('num');calculateV();checkUpdateProduct()" autocomplete="off" />
				<label id="numNews" class="news"></label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">体积(立方米)</label>
			<div class="col-sm-2">
				<input class="form-control" type="text" id="volume" name="volume" readonly="readonly" />
				<label></label>
			</div>
			<label class="col-sm-2 control-label">每包片数</label>
			<div class="col-sm-2">
				<input class="form-control" type="text" id="bagSlices" name="bagSlices" onkeyup="isPInt('bagSlices');checkUpdateProduct()" autocomplete="off" />
				<label id="bagSlicesNews" class="news"></label>
			</div>
		</div>
		<div class="form-group">
			<div style="text-align:center;">
				<button type="submit" class="btn btn-default" id="confirm" onclick="alert('修改成功！')">确认修改</button>
			</div>
		</div>
	</form>
</body>
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/validate.js" charset="gb2312"></script>
  <script type="text/javascript">
  $(document).ready(function(){
  		url = "<%=request.getContextPath() %>/servlet/ProductInfoServlet";
  		var id = <%=request.getParameter("id") %>;
  		$.post(url,
		  {
			id:id
		  },
		  function(data){
		  	var x = data.split(",");
		  	$("#id").val(x[0]);
		  	$("#format").val(x[1]);
		  	$("#level").val(x[2]);
		  	$("#thick").val(x[3]);
		  	$("#num").val(x[4]);
		  	$("#volume").val(x[5]);
		  	$("#bagSlices").val(x[6]);
		  }
		);
  	});
  </script>
</html>
