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
    <div id="wrapper">


        <div id="page-wrapper" class="col-lg-6">
            <div class="row">
                <div class="col-lg-12">
                    <h4 class="page-header">请确保个人信息正确完整，以方便我们跟好的为您服务！</h4>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">基本资料</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                            	
                           		<div class="row">
                           			<div class="col-sm-12">
                           			<form action="UpdateUserAction.action" method="post">
                           				<input type="hidden" id="userName" name="userName" value="${sessionScope.user.userName }"/>
                           				<table width="100%" class="table table-striped table-bordered table-hover dataTable no-footer dtr-inline" id="dataTables-example" role="grid" aria-describedby="dataTables-example_info" style="width: 100%;">		                                
			                                <tbody> 
			                                    <tr class="gradeA even" role="row">
			                                        <td>昵称</td>
			                                        <td><input class="col-sm-12" type="text" id="nickName" name="nickName"   style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '/> </td>
			                                   
			                                    </tr>       
			                              	  	<tr class="gradeA odd" role="row">
			                                        <td>真实姓名</td>
			                                        <td><input class="col-sm-12" type="text" id="realName" name="realName"   style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px ' /></td>
			                                       
			                                    </tr>
			                                    <tr class="gradeA even" role="row">
			                                        <td>身份证号</td>
			                                        <td><input class="col-sm-12" type="text" id="idNum" name="idNum"  style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '/> </td>
			                                
			                                    </tr>
			                                    <tr class="gradeA odd" role="row">
			                                        <td>手机</td>
			                                        <td><input class="col-sm-12" type="text" id="phoneNum" name="phoneNum"  style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '/> </td>
			                              
			                                    </tr>
			                                    <tr class="gradeA even" role="row">
			                                        <td> 邮箱</td>
			                                        <td><input class="col-sm-12" type="text" id="email" name="email"style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '/> </td>
			                                 
			                                    </tr>
			                                    <tr class="gradeA odd" role="row">
			                                        <td class="sorting_1">银行卡号</td>
			                                        <td><input class="col-sm-12" type="text" id="bankCode" name="bankCode" style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '/> </td>
			                               
			                                    </tr>
			                                    <tr class="gradeA even" role="row">
			                                        <td> 地址</td>
			                                        <td><input class="col-sm-12" type="text" id="address" name="address"  style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '/> </td>
			                               
			                                    </tr>
			                                    <tr class="gradeA odd" role="row">
			                                        <td class="sorting_1">支付密码</td>
			                                        <td><input class="col-sm-12" type="text" id="payPassword" name="payPassword"  style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '/> </td>
			                              
			                                    </tr>
			                                </tbody>
                           			</table>
                           			<input type="submit" onclick="return confirmComplete()" value="提交">
                           			</form>
                           		</div>
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
	function confirmComplete(){
		if($("#nickName").val()==""||$("#realName").val()==""||$("#idNum").val()==""||$("#phoneNum").val()==""
			||$("#email").val()==""||$("#bankCode").val()==""||$("#address").val()==""||$("#payPassword").val()==""){
			alert("请完善所有基本信息！");
			return false;
		}else
			return confirm("确认提交？");
		}
	}

</script>
</html>