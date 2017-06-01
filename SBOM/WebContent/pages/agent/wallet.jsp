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

        <div id="page-wrapper" class="col-lg-12">
            <div class="row">
                <div class="col-lg-12">
                    <h4 class="page-header">钱包总览</h4>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">${sessionScope.user.nickName } ,您好！
                        	<font color="red">${info }</font>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                            	
                           		<div class="row">
                           			<form action="updatePassword!loginPassword" method="post">
                           			<div class="col-sm-12">
                           				<input type="hidden" id="userName" name="userName" value="${sessionScope.user.userName }"/>
                           				<table width="100%" class="table table-striped table-bordered table-hover dataTable no-footer dtr-inline" id="dataTables-example" role="grid" aria-describedby="dataTables-example_info" style="width: 100%;">		                                
			                                <tbody>        
			                                <tr class="gradeA odd" role="row">
			                                        <td>用户名</td>
			                                        <td><input class="col-sm-12" value="${sessionScope.user.userName }" disabled="disabled" type="text" id="oldPassword" name="oldPassword"   style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px ' /></td>
			                                        
			                                    </tr>
			                                    <tr class="gradeA even" role="row">
			                                        <td>真实身份</td>
			                                        <td><input class="col-sm-12" value="${sessionScope.user.realName }|${sessionScope.user.idNum }" disabled="disabled" type="text" id="password" name="password"  style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '/> </td>
			                                        
			                                    </tr>
			                                    <tr class="gradeA odd" role="row">
			                                        <td>注册时间</td>
			                                        <td><input class="col-sm-12" value="${sessionScope.user.registTime }" disabled="disabled" type="text" id="password2" name="password2"  style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '/> </td>
			                                        
			                                    </tr>
			                                    <tr class="gradeA even" role="row">
			                                        <td>账户余额(RMB)</td>
			                                        <td><input class="col-sm-12" value="${sessionScope.user.balance }(元)" disabled="disabled" pattern="#.00" type="text" id="password" name="password"  style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '/> </td>
			                                        
			                                    </tr>
			                                    
			                                    <tr class="gradeA odd" role="row">
			                                        <td>钻石余额</td>
			                                        <td><input class="col-sm-12" value="${sessionScope.user.diamond }(颗)" disabled="disabled" type="text" id="password2" name="password2"  style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '/> </td>
			                                        
			                                    </tr>
			                                </tbody>
                           			</table>
                           		</div>
                           		</form>
                           	</div>
                           	
                          </div>
                           					
                            <!-- /.table-responsive -->
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
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

</script>
</html>