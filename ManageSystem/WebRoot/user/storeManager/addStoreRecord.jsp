<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="/ManageSystem/css/bootstrap.css" rel="stylesheet"type="text/css" media="all"/>
    <link href="/ManageSystem/css/basicStyle.css" rel="stylesheet"type="text/css" media="all"/>
    <script type="text/javascript" src="${pageContext.request.contextPath }/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.1.min.js"></script>
  </head>
  
  <body style="text-align: center">
  	<c:if test="${sessionScope.clerk==null }">
  		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/storeManager/storeManagerLogin.jsp";
		</script>
    </c:if>
    <c:if test="${sessionScope.clerk!=null }">
    	<h2>新产品入库</h2><hr/>
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
					<input class="form-control" type="text" id="format" name="format" onkeyup="checkIsNotNull('format');checkProduct()" autocomplete="off"/>
                    <label id="formatNews"></label>
				</div>
				<label class="col-sm-2 control-label">等级</label>
				<div class="col-sm-2">
					<select class="form-control" name="level" id="level">
					<option value="" selected="selected">请选择</option>
				    </select>
				    <label></label>
				</div>			
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">长度</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="height" name="height" onkeyup="checkIsNotNull('height');checkProduct()" autocomplete="off"/>
                    <label id="heightNews"></label>
				</div>
				<label class="col-sm-2 control-label">宽度</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="width" name="width" onkeyup="checkIsNotNull('width');checkProduct()" autocomplete="off"/>
                    <label id="widthNews"></label>
				</div>			
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label">厚度(mm)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="thick" name="thick" onkeyup="isPFloat('thick');calculateV();checkProduct();" autocomplete="off"/>
                    <label id="thickNews" class="news"></label>
				</div>
				<label class="col-sm-2 control-label">数量(片)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="num" name="num" onkeyup="isPInt('num');calculateV();checkProduct()" autocomplete="off"/>
				    <label id="numNews" class="news"></label>
				</div>				
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">体积(立方米)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="volume" name="volume" readonly="readonly"/>
                    <label></label>
				</div>
				<label class="col-sm-2 control-label">每包片数</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="bagSlices" name="bagSlices" onkeyup="isPInt('bagSlices');checkProduct()" autocomplete="off"/>
				    <label id="bagSlicesNews" class="news"></label>
				</div>				
			</div>
			<div class="form-group">
			    <div style="text-align:center;">
                    <button type="submit" class="btn btn-default" id="confirm" disabled="disabled" onclick="alert('添加成功！')">确认添加</button>		
                </div>
			</div>
		</form>
	</c:if>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/validate.js" charset="gb2312"></script>
  </body> 
  <script type="text/javascript">
  	$(document).ready(function(){
  	url2 = "<%=request.getContextPath() %>/servlet/SelectLevel2Servlet";
		$.post(url2,
		  {},
		  function(data){
			var x = data.split(",");
			for(var i=0; i<x.length;i++){
				var option="<option value="+x[i]+">"+x[i]+"</option>";
				$(option).appendTo("#level");
			}
		  });
	});

  </script>
</html>
