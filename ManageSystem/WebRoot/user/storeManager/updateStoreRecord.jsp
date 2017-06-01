<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
  <script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.8.1.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/My97DatePicker/WdatePicker.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/validate.js" charset="gb2312"></script>
  <link href="/ManageSystem/css/bootstrap.css" rel="stylesheet"type="text/css" media="all"/>
  <link href="/ManageSystem/css/basicStyle.css" rel="stylesheet"type="text/css" media="all"/>
  </head>
  
  <body style="text-align: center;">
  	<c:if test="${sessionScope.clerk.type!='储管员'&& sessionScope.admin==null}">
    	<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/storeManager/storeManagerLogin.jsp";
		</script>
    </c:if>
	<h2>已有产品入库</h2><hr/>
	<br/><br/><br/><br/><br/>
	<form class="form-horizontal" action="${pageContext.request.contextPath }/servlet/AddStoreRecordServlet" method="post">
		<div class="form-group">
				<label class="col-sm-3 control-label">日期</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="time" name="time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					<label></label>
				</div>			
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">规格</label>
			<div class="col-sm-2">
				<input class="form-control" type="text" id="format" name="format" autocomplete="off" readonly="readonly"/>
				<label id="formatNews"></label>
			</div>
			<label class="col-sm-2 control-label">等级</label>
			<div class="col-sm-2">
				<input class="form-control" type="text" id="level" name="level" autocomplete="off" readonly="readonly"/>
				<label id="levelNews"></label>
			</div>
		</div>
		<div class="form-group">
				<label class="col-sm-3 control-label">长度</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="height" name="height" readonly="readonly"/>
                    <label id="heightNews"></label>
				</div>
				<label class="col-sm-2 control-label">宽度</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="width" name="width" readonly="readonly"/>
                    <label id="widthNews"></label>
				</div>			
			</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">厚度(mm)</label>
			<div class="col-sm-2">
				<input class="form-control" type="text" id="thick" name="thick" readonly="readonly"/>
				<label id="thickNews" class="news"></label>
			</div>
			<label class="col-sm-2 control-label">数量(片)</label>
			<div class="col-sm-2">
				<input class="form-control" type="text" id="num" name="num" onkeyup="isPInt('num');calculateV()" autocomplete="off" />
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
				<input class="form-control" type="text" id="bagSlices" name="bagSlices" readonly="readonly"/>
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
  
  <script type="text/javascript">
  $(document).ready(function(){
  		url = "<%=request.getContextPath() %>/servlet/StoreRecordInfoServlet";
  		var id = <%=request.getParameter("id") %>;
  		$.post(url,
		  {
			id:id
		  },
		  function(data){
		  	var x = data.split(",");
		  	$("#format").val(x[0]);
		  	$("#level").val(x[1]);
		  	$("#height").val(x[2]);
		  	$("#width").val(x[3]);
		  	$("#thick").val(x[4]);
		  	$("#volume").val(x[5]);
		  	$("#bagSlices").val(x[6]);
		  }
		);
  	});
  </script>
</html>
