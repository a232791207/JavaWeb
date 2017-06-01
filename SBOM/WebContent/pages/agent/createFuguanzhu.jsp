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
           <div style="text-align: center;">
           </div>
                    
           <div class="container">
	        <div class="row">
	            <div class="col-md-4 col-md-offset-4">
	                <div class="login-panel panel panel-default">
	                	
	                    <div class="panel-heading">
	                        <h4 class="panel-title">请注册代理</h4>
	                        <font color="red">${info }</font>
	                        <div id="add">${num }</div>
	                    </div>
	                    <div class="panel-body">
	                        <form role="form" action="addUser" method="post">
	                            <fieldset>
	                            	<s:token></s:token>
	                                <input type="hidden" id="supUserName" name="supUserName" value="${sessionScope.user.userName }">
	                                <input type="hidden" id="level" name="level" value="2">
	                                <input type="hidden" id="supLevel" name="supLevel" value="${sessionScope.user.level }">
	                                <div class="form-group">
	                                    <input type="text" class="form-control" placeholder="游戏ID" id="idnum" name="id" value="" autofocus>
	                                </div>
	                                <div class="form-group">
	                                    <input type="text" class="form-control" placeholder="用户名" id="userName" name="userName" value="">
	                                </div>
	                                <div class="form-group">
	                                    <input type="password" class="form-control" placeholder="密码" id="loginPassword" name="loginPassword" value="">
	                                </div>
	                                <!-- Change this to a button or input when using this as a form -->
	                                <input type="submit" value="注册" onclick="return confirmAdd()">
	                            </fieldset>
	                        </form>
	                    </div>
	                </div>
	            </div>
	        </div>
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
    
	function confirmAdd(){
		if($("#idnum").val()==""||$("#userName").val()==""||$("#loginPassword").val()==""){
			alert("您有未填写的项目，请确认信息完整后再注册！");
			return false;
		}else{
			return confirm("是否确认注册？");
		}
	}
	/* $(function(){
		$("#idnum").blur(function(){
			var idnum=$("#idnum").val();
			$.ajax({
				type:"POST",
				url:"findById.action",
				data:"idnum="+idnum,
				 success:function(data){
                    var num=data.num;
                    alert(num);
					if(num==0){
						alert("可以使用该游戏ID！");
					}else{
						alert("不可以使用该游戏ID，您在换一个！");
					}
				}

			});
			
		});
	}); */
</script>
</html>