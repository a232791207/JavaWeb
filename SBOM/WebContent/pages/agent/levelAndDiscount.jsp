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
                    <h4 class="page-header">等级与优惠</h4>
                </div>
            </div>
            <div class="row">
               <div class="col-lg-10">
               <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">当前等级优惠
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                            	
                           		<div class="row">
                           			<div class="col-sm-12">
                           				<input type="hidden" id="ulevel" name="ulevel" value="${sessionScope.user.level }"/>
                           				<table width="100%" class="table table-striped table-bordered table-hover dataTable no-footer dtr-inline" id="dataTables-example" role="grid" aria-describedby="dataTables-example_info" style="width: 100%;">		                                
			                                <tbody>        
			                                <tr class="gradeA odd" role="row">
			                                        <td><input type="radio" name="level" id="level" value="1" disabled="disabled" 
			                                        	<c:if test="${sessionScope.user.level == '1' }">
	    													checked="checked"
	    												</c:if>>奇大使</td>
			                                        <td><input class="col-sm-12" type="text" id="discount1" name="discount1"   style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px ' disabled="disabled" value="钻石折扣90%"/></td>
			                                        
			                                    </tr>
			                                    <tr class="gradeA even" role="row">
			                                        <td><input type="radio" name="level" id="level" value="2" disabled="disabled" 
			                                        	<c:if test="${sessionScope.user.level == '2' }">
	    													checked="checked"
	    												</c:if>>副馆主</td>
			                                        <td><input class="col-sm-12" type="text" id="discount2" name="discount2"  style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px ' disabled="disabled" value="钻石折扣80%"/> </td>
			                                        
			                                    </tr>
			                                    <tr class="gradeA odd" role="row">
			                                        <td><input type="radio" name="level" id="level" value="3" disabled="disabled" 
			                                        	<c:if test="${sessionScope.user.level == '3' }">
	    													checked="checked"
	    												</c:if>>馆主</td>
			                                        <td><input class="col-sm-12" type="text" id="discount3" name="discount3"  style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px ' disabled="disabled" value="钻石折扣70%"/> </td>
			                                        
			                                    </tr>
			                                </tbody>
                           			</table>
                           			
                           		</div>
                           	</div>
                           	
                          </div>
                           					
                            <!-- /.table-responsive -->
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                </div>
                
               <div class="col-lg-10">
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">升级条件
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                            	
                           		<div class="row">
                           			<div class="col-sm-12">
                           				<table width="100%" class="table table-striped table-bordered table-hover dataTable no-footer dtr-inline" id="dataTables-example" role="grid" aria-describedby="dataTables-example_info" style="width: 100%;">		                                
			                                <tbody>        
			                                <tr class="gradeA odd" role="row">
			                                        <td>管理人数</td>
			                                        <td><input class="col-sm-12" type="text" id="discount1" name="discount1"   style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px ' disabled="disabled" value="28/40"/></td>
			                                        
			                                    </tr>
			                                    <tr class="gradeA even" role="row">
			                                        <td>每天游戏次数</td>
			                                        <td><input class="col-sm-12" type="text" id="discount2" name="discount2"  style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px ' disabled="disabled" value="55/40"/> </td>
			                                        
			                                    </tr>
			                                    <tr class="gradeA odd" role="row">
			                                        <td>累计充值金额</td>
			                                        <td><input class="col-sm-12" type="text" id="discount3" name="discount3"  style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px ' disabled="disabled" value="800/1000"/> </td>
			                                    </tr>
			                                    <tr class="gradeA even" role="row">
			                                        <td><input class="col-sm-12" type="submit"   style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px ' disabled="disabled" value="升级"/> </td>
			                                        <td></td>
			                                        
			                                    </tr>
			                                </tbody>
                           			</table>
                           			
                           		</div>
                           	</div>
                           	
                          </div>
                           					
                            <!-- /.table-responsive -->
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
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
	$(document).ready(function(){
		
	});
</script>
</html>