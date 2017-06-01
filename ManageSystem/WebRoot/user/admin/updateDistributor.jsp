<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
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
	  	<h2>修改经销商信息</h2><hr/>
	  	<br/><br/><br/><br/><br/>
	    <form class="form-horizontal" action="${pageContext.request.contextPath }/servlet/UpdateDistributorServlet" method="post">
	        <div class="form-group">
	    	    <label class="col-sm-3 control-label">经销商编号</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="id" value="${requestScope.distributor.id }" readonly="readonly" style="background: silver;"/>
				    <label></label>
				</div>
				<label class="col-sm-2 control-label">经销商姓名</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="name" name="name" value="${requestScope.distributor.name }" readonly="readonly" style="background: silver;"/>
				    <label></label>
				</div>		
			</div>
			<div class="form-group">
			    <label class="col-sm-3 control-label">区域</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="area" name="area" value="${requestScope.distributor.area }" onblur="checkIsNotNull('area')" onkeyup="checkIsNotNull('area')" autocomplete="off"/>
				    <label id="areaNews"></label>
				</div>		
				<label class="col-sm-2 control-label">业务员</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="salesman" name="salesman" value="${requestScope.distributor.salesman }" onblur="checkIsNotNull('salesman')" onkeyup="checkIsNotNull('salesman')" autocomplete="off"/>
				    <label id="salesmanNews"></label>
				</div>	
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">联系方式</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="phonenumber" name="phonenumber" value="${requestScope.distributor.phonenumber }" onblur="checkIsNotNull('phonenumber')" onkeyup="checkIsNotNull('phonenumber')" autocomplete="off"/>
				    <label id="phonenumberNews"></label>
				</div>	
				<label class="col-sm-2 control-label">信用额度(元)</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="creditlines" name="creditlines" value="${requestScope.distributor.creditlines }" onblur="checkIsNotNull('creditlines')" onkeyup="checkIsNotNull('creditlines')" autocomplete="off"/>
				    <label id="creditlinesNews"></label>
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
