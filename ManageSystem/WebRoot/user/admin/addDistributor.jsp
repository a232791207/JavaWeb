<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
  <script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.8.1.min.js"></script>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="/ManageSystem/css/bootstrap.css" rel="stylesheet"type="text/css" media="all"/>
  </head>
  
  <body style="text-align: center;">
  	<c:if test="${sessionScope.admin==null }">
    	<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/admin/adminLogin.jsp";
		</script>
    </c:if>
    <c:if test="${sessionScope.admin!=null }">
    	<h2>新增经销商</h2><hr/>
    	<br/><br/><br/><br/><br/>
		<form class="form-horizontal" action="${pageContext.request.contextPath }/servlet/AddDistributorServlet" method="post">
			<div class="form-group">
				<label class="col-sm-3 control-label">经销商姓名</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="name" name="name"/>
					<label></label>
				</div>
				<label class="col-sm-2 control-label">区域</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="area" name="area"/>
					<label></label>
				</div>				
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">业务员</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="salesman" name="salesman"/>
					<label></label>
				</div>
				<label class="col-sm-2 control-label">联系方式</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="phonenumber" name="phonenumber"/>
					<label></label>
				</div>		
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">信用额度(元)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="creditlines" name="creditlines"/>
					<label></label>
				</div>		
			</div>
			<div class="form-group">
			    <div style="text-align:center;">
                    <button type="submit" class="btn btn-default" id="confirm" onclick="return confirmAdd()">确认添加</button>		
                </div>
			</div>
		</form>
	</c:if>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/validate.js" charset="gb2312"></script>
  </body>
  <script type="text/javascript">
  	function confirmAdd(){
  		if($("#name").val()==""||$("#area").val()==""||$("#salesman").val()==""||$("#phonenumber").val()==""||$("#creditlines").val()==""){
  			alert("您有未填的项目！请确认信息完整后再提交！");
  			return false;
	  	}else{
	  		var str = "请确认新增经销商信息！\n";
	  		str=str+"经销商姓名："+$("#name").val()+"\n";
	  		str=str+"区域："+$("#area").val()+"\n";
	  		str=str+"业务员："+$("#salesman").val()+"\n";
	  		str=str+"联系方式："+$("#phonenumber").val()+"\n";
	  		str=str+"信用额度："+$("#creditlines").val()+"\n";
	  		return confirm(str);
  		}
  	}
  </script>
</html>
