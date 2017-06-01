<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/ManageSystem/css/bootstrap.css" rel="stylesheet"type="text/css" media="all"/>
</head>

<body style="text-align:center;">
	<c:if test="${sessionScope.admin==null }">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/admin/adminLogin.jsp";
		</script>
	</c:if>
	<c:if test="${sessionScope.admin!=null }">
		<h2>新增员工</h2><hr/>
		<font color="red">${msg }</font>
		<br/><br/><br/><br/><br/>
		<form class="form-horizontal" action="${pageContext.request.contextPath }/servlet/AddClerkServlet" method="post">
			<div class="form-group">
				<label class="col-sm-3 control-label">编号（账号）</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="id" name="id" onblur="checkIsNotNull('id')" onkeyup="checkIsNotNull('id');checkClerk()" autocomplete="off" />
				    <label id="idNews"></label>
				</div>
				<label class="col-sm-2 control-label">密码</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="password" name="password" onblur="checkIsNotNull('password')" onkeyup="checkIsNotNull('password');checkClerk()" autocomplete="off"/>
				    <label id="passwordNews"></label>
				</div>				
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">姓名</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="name" name="name" onblur="checkIsNotNull('name')" onkeyup="checkIsNotNull('name');checkClerk()" autocomplete="off" />
					<label id="nameNews"></label>
				</div>
				<label class="col-sm-2 control-label">职务</label>
				<div class="col-sm-2">
					<select class="form-control" name="type">
						<option value="业务员" selected="selected">业务员</option>
						<option value="财务员">财务员</option>
						<option value="开票员">开票员</option>
						<option value="储管员">储管员</option>
				    </select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">性别</label>
				<div class="col-sm-2">
				    <label class="checkbox-inline">
                        <input type="radio" name="gender" value="男" checked="checked"> 男
                    </label>
                    <label class="checkbox-inline">
                        <input type="radio" name="gender" value="女"> 女
                    </label>
				</div>
				<label class="col-sm-2 control-label">联系方式</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="phonenumber" name="phonenumber" onblur="checkIsNotNull('phonenumber')" onkeyup="checkIsNotNull('phonenumber');checkClerk()" autocomplete="off"/>
				    <label id="phonenumberNews"></label>
				</div>				
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">年龄</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="age" name="age" onblur="checkIsNotNull('age')" onkeyup="checkIsNotNull('age');checkClerk()" autocomplete="off" />
				    <label id="ageNews"></label>
				</div>
				<label class="col-sm-2 control-label">地址</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="address" name="address" onblur="checkIsNotNull('address')" onkeyup="checkIsNotNull('address');checkClerk()" autocomplete="off"/>
				    <label id="addressNews"></label>
				</div>				
			</div>
			<div class="form-group">
			    <div style="text-align:center;">
                    <button type="submit" class="btn btn-default" id="confirm" disabled="disabled">确认添加</button>		
                </div>
			</div>
		</form>
	</c:if>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/validate.js" charset="gb2312"></script>
</body>
</html>
