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
        <div id="page-wrapper">
        
            <div class="row">
                <br>
            </div>
        	<div class="row">
                <div class="col-lg-12">
                    <!-- /.panel -->
                    
                    
                    <!-- /.panel -->
                    <div class="panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-comments fa-fw"></i> 公告
                            <a href="${pageContext.request.contextPath }/pages/cooperater/notice!noticePage2?thispage=1&state=1&user=cooperater">更多</a>
                            
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <ul class="chat">
                                <li class="left clearfix">
                                    <div class="chat-body clearfix">
                                        <div class="header">
                                            <strong class="primary-font" id="title1"></strong>
                                            <small class="pull-right text-muted" id="time1">
                                            </small>
                                        </div>
                                        <p id="content1">暂无</p>
                                    </div>
                                </li>
                                <li class="left clearfix">
                                    <div class="chat-body clearfix">
                                        <div class="header">
                                            <strong class="primary-font" id="title2"></strong>
                                            <small class="pull-right text-muted" id="time2"> </small>
                                        </div>
                                        <p id="content2">暂无</p>
                                    </div>
                                </li>
                                <li class="left clearfix">
                                    <div class="chat-body clearfix">
                                        <div class="header">
                                            <strong class="primary-font" id="title3"></strong>
                                            <small class="pull-right text-muted" id="time3"></small>
                                        </div>
                                        <p id="content3">暂无</p>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- /.panel .chat-panel -->
                    
                </div>
                <!-- /.col-lg-4 -->
            </div>
            <div class="row">
            	<div class="col-lg-6">
            	<div class="chat-panel panel panel-default">
                    <div class="panel-heading">新闻
                    </div>
				    <div id="myCarousel" class="carousel slide">  
						<div class="carousel-inner">
							<div class="item active">
							<img src="${pageContext.request.contextPath }/images/news/news.png?<%=date.toLocaleString() %>" alt="First slide">
							</div>
						</div>
					</div>
				</div>
	            </div>
	            <div class="col-lg-6">
                <div class="chat-panel panel panel-default">
                    <div class="panel-heading">广告
                    </div>
				    <div id="myCarouse2" class="carousel slide">
						<!-- 轮播（Carousel）指标 -->
						<ol class="carousel-indicators">
							<li data-target="#myCarouse2" data-slide-to="0" class="active"></li>
							<li data-target="#myCarouse2" data-slide-to="1"></li>
							<li data-target="#myCarouse2" data-slide-to="2"></li>
						</ol>   
					
						<!-- 轮播（Carousel）项目 -->
						<div class="carousel-inner">
							<div class="item active">
							<img src="${pageContext.request.contextPath }/images/ads/ads1.png?<%=date.toLocaleString() %>" alt="First slide">
							</div>
							<div class="item">
							<img src="${pageContext.request.contextPath }/images/ads/ads2.png?<%=date.toLocaleString() %>" alt="Second slide">
							</div>
							<div class="item">
							<img src="${pageContext.request.contextPath }/images/ads/ads3.png?<%=date.toLocaleString() %>" alt="Third slide">
							</div>
						</div>
						<!-- 轮播（Carousel）导航 -->
						<a class="carousel-control left" href="#myCarouse2" data-slide="prev">‹</a>
						<a class="carousel-control right" href="#myCarouse2" data-slide="next">›</a>
					</div>
	            </div>
	            </div>
            </div>
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
		$("#myCarouse2").carousel('cycle');
		url="<%=request.getContextPath()%>/pages/cooperater/notice!noticeBriefInfo";
		$.post(url,
 			{
				
			},
			function(data){
				var x = data.split(",");
				if(x.length>1){
					$("#title1").text(x[0]);
					$("#content1").text(x[1]);
					$("#time1").text(x[2]);
					  if(x.length>4){
						$("#title2").text(x[3]);
						$("#content2").text(x[4]);
						$("#time2").text(x[5]);
						if(x.length>7){
							$("#title3").text(x[6]);
							$("#content3").text(x[7]);
							$("#time3").text(x[8]);
						}
					}  
				}
			}
		);
	});
</script>
</html>