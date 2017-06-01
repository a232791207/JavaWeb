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
		
        <!-- Navigation -->
        <jsp:include page="struct.jsp"></jsp:include>
		<%  
			Date date = new Date();
		%>
        <div id="page-wrapper" class="col-lg-12">  
        	<div class="row">
                <div class="col-lg-12">
                    <h4 class="page-header">待审核的广告</h4>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">广告信息
                        	<font color="red" id="info">${info }</font>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                            	
		                   		<div class="row">
		                   			<div class="col-sm-12">
		                   			<c:if test="${num==0}"><font color="#FF0000">暂时没有需要审核的广告！</font></c:if>
		                   			<c:if test="${num!=0}">
		                   				<table width="100%" class="table table-striped table-bordered table-hover dataTable no-footer dtr-inline" id="dataTables-example" role="grid" aria-describedby="dataTables-example_info" style="width: 100%;">		                                
		                           <tbody>   
		                           <form action="${pageContext.request.contextPath }/pages/admin/upAdvertisement?id=${news.id}" method="post">      
		                           	<tr class="gradeA odd" role="row">
	                                   <td>样图</td>
	                                   <td>                      		
										    <div id="myCarousel" class="carousel slide">  
												<div class="carousel-inner">
													<div class="item active">
													<img src="${pageContext.request.contextPath }/images/ads/tmp/ads.png?<%=date.toLocaleString() %>>" alt="First slide">
													</div>
												</div>
											</div>
	                                   </td>
	                               </tr>
	                               <tr class="gradeA even" role="row">
	                                   <td>描述</td>
	                                   <td><textarea class="col-sm-12" style="background-color: white;" disabled="disabled" cols="60" id="comment" rows="10" name="comment" >${news.comment } </textarea> </td>
	                               </tr>
	                               <tr class="gradeA odd" role="row">
	                                   <td>建议</td>
	                                   <td> <textarea class="col-sm-12" style="background-color: white;" cols="60" id="suggest" rows="10" name="suggest" > </textarea>
	                                   </td>
	                               </tr>
	                                <input type="submit" value="审核"/><font color="#FF0000">待审核${num}条。</font>
		                              </form>
		                          </tbody>
		                   			</table>
		                   			</c:if>
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
</script>
</html>