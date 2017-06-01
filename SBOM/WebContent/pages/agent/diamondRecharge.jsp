<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>知奇科技</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath }/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath }/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath }/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="${pageContext.request.contextPath }/vendor/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath }/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    

</head>

<body>
	<c:if test="${sessionScope.user==null }">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/pages/agent/login.jsp";
		</script>
	</c:if>
	
    <div id="wrapper">

        <!-- Navigation -->
        <jsp:include page="struct.jsp"></jsp:include>

        <div id="page-wrapper" class="col-lg-10">
           <div style="text-align: center">
           	<br>
           	<font color="red">${info }</font>
           	<form action="diamondRecharge!userRecharge" method="post">
           		<s:token></s:token>
           		<input type="hidden" id="userName" name="userName" value="${sessionScope.user.userName }"/>
           		请输入您要充值的钻石数：<input type="text" name="diamondNum" id="diamondNum" onkeyup="checkNum()"/>
           		<input type="submit" value="充值" id="submit" disabled="disabled" onclick="return comfirmRecharge()"/>
           	</form>
           	<br>
           	<input type="checkbox" id="ifAutoRecharge" 
           		<c:if test="${sessionScope.user.autoRecharge > 0 }">
	    			checked="checked"
	    			value="1"
	    		</c:if>
	    		<c:if test="${sessionScope.user.autoRecharge == 0 }">
	    			disabled="disabled"
	    			value="0"
	    		</c:if>
           	 name="ifAutoRecharge"  onclick="ifAutoRecharge()"/>自动充值数量
           	<input type="text" id="autoRecharge" name="autoRecharge" onkeyup="autoRecharge()" value="${sessionScope.user.autoRecharge }"/>
           </div>
                    
               
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="${pageContext.request.contextPath }/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath }/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath }/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="${pageContext.request.contextPath }/vendor/raphael/raphael.min.js"></script>
    <script src="${pageContext.request.contextPath }/vendor/morrisjs/morris.min.js"></script>
    <script src="${pageContext.request.contextPath }/data/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath }/dist/js/sb-admin-2.js"></script>

</body>

<script type="text/javascript">
	function comfirmRecharge(){
		if($("#diamondNum").val()==""){
			alert("请先填写您要充值的钻石数目！");
			return false;
		}else{
			var msg = "是否确认充值"+$("#diamondNum").val()+"颗钻石";
			return confirm(msg);
		}
	}
	function ifAutoRecharge(){
		if($("#ifAutoRecharge").val()== "0"){
			$("#ifAutoRecharge").val("1");
			alert("您勾选了自动充值服务！\n当您钻石数目过低时，系统将自动为您充值指定额度的钻石！");
			url="autoRecharge!changeAutoRecharge";
			$.post(url,
	 			{
					userName:$("#userName").val(),
					autoRecharge:$("#autoRecharge").val()
				},
				function(data){
				}
			);
		}else{
			$("#ifAutoRecharge").val("0");
			$("#autoRecharge").val("0");
			$("#ifAutoRecharge").attr("disabled","disabled");
			alert("您取消了自动充值服务！");
			url="autoRecharge!changeAutoRecharge";
			$.post(url,
	 			{
					userName:$("#userName").val(),
					autoRecharge:0
				},
				function(data){
				}
			);
		}
	}
	function autoRecharge(){
		var v = $("#autoRecharge").val();
		$("#ifAutoRecharge").removeAttr("checked");
		if((/^(\+|-)?\d+$/.test(v))&&v>0){
			$("#ifAutoRecharge").removeAttr("disabled");
		}else{
			$("#ifAutoRecharge").attr("disabled","disabled");
		}
	}
	function checkNum(){
		var v = $("#diamondNum").val();
		if((/^(\+|-)?\d+$/.test(v))&&v>0){
			$("#submit").removeAttr("disabled");
		}else{
			$("#submit").attr("disabled","disabled");
		}
	}
</script>
</html>