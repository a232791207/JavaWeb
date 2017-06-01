<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link href="/ManageSystem/css/bootstrap.css" rel="stylesheet"type="text/css" media="all"/>
  </head>
  
  <body style="text-align: center;">
  	<c:if test="${sessionScope.admin==null }">
    	<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/admin/adminLogin.jsp";
		</script>
    </c:if>
    
    <c:if test="${sessionScope.admin!=null }">
	  	<h2>修改员工信息</h2><hr/>
	  	<br/><br/><br/><br/><br/>
	    <form class="form-horizontal" action="${pageContext.request.contextPath }/servlet/UpdateClerkServlet" method="post">
	    	<div class="form-group">
				<label class="col-sm-3 control-label">编号（账号）</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="id" name="id" value="${requestScope.clerk.id }" readonly="readonly" style="background: silver;"/>
				    <label></label>
				</div>
				<label class="col-sm-2 control-label">密码</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="password" name="password" value="${requestScope.clerk.password }" onblur="checkIsNotNull('password')" onkeyup="checkIsNotNull('password');checkUpdateClerk()" autocomplete="off"/>
				    <label id="passwordNews"></label>
				</div>				
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">姓名</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="name" name="name" value="${requestScope.clerk.name }" readonly="readonly" style="background: silver;"/>
				    <label></label>
				</div>
				<label class="col-sm-2 control-label">职务</label>
				<div class="col-sm-2">
					<select class="form-control" name="type">
						<option value="业务员"
						    <c:if test="${requestScope.clerk.type == '业务员' }">
	    						selected="selected"
	    					</c:if>
						>业务员</option>
						<option value="财务员"
						    <c:if test="${requestScope.clerk.type == '财务员' }">
	    						selected="selected"
	    					</c:if>
						>财务员</option>
						<option value="开票员"
						    <c:if test="${requestScope.clerk.type == '开票员' }">
	    						selected="selected"
	    					</c:if>
						>开票员</option>
						<option value="储管员"
						    <c:if test="${requestScope.clerk.type == '储管员' }">
	    						selected="selected"
	    					</c:if>
						>储管员</option>
				    </select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">性别</label>
				<div class="col-sm-2">
				    <label class="checkbox-inline">
                        <input type="radio" name="gender" value="男"
                            <c:if test="${requestScope.clerk.gender == '男' }">
	    						checked="checked"
	    					</c:if>
	    				/> 男
                    </label>
                    <label class="checkbox-inline">
                        <input type="radio" name="gender" value="女"
                            <c:if test="${requestScope.clerk.gender == '女' }">
	    						checked="checked"
	    					</c:if>
                        /> 女
                    </label>
				</div>
				<label class="col-sm-2 control-label">联系方式</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="phonenumber" name="phonenumber" value="${requestScope.clerk.phonenumber }" onblur="checkIsNotNull('phonenumber')" onkeyup="checkIsNotNull('phonenumber');checkUpdateClerk()" autocomplete="off"/>
				    <label id="phonenumberNews"></label>
				</div>				
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">年龄</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="age" name="age" value="${requestScope.clerk.age }" onblur="checkIsNotNull('age')" onkeyup="checkIsNotNull('age');checkUpdateClerk()" autocomplete="off"/>
				    <label id="ageNews"></label>
				</div>
				<label class="col-sm-2 control-label">地址</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="address" name="address" value="${requestScope.clerk.address }" onblur="checkIsNotNull('address')" onkeyup="checkIsNotNull('address');checkUpdateClerk()" autocomplete="off"/>
				    <label id="addressNews"></label>
				</div>				
			</div>
			<div class="form-group">
			    <div style="text-align:center;">
                    <button type="submit" class="btn btn-default" id="confirm">确认修改</button>		
                </div>
			</div>	
	    </form>
    </c:if>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/validate.js" charset="gb2312"></script>
  </body>
</html>
