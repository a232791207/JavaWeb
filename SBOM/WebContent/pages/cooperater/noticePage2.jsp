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
        
        <div id="page-wrapper">
           <div class="row">
              <div class="col-lg-12">
                  <h1 class="page-header"></h1>
                  <a href="${pageContext.request.contextPath }/pages/cooperater/index.jsp">返回</a>
              </div>
          </div>
          <!-- /.row -->
          <br>
        <!-- /#page-wrapper -->
        <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i>公告列表
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover table-striped" style="text-align: center;">
                                            <thead >
                                                <tr>
                                                    <th style="text-align: center;" class="col-lg-3">发布时间</th>
                                                    <th style="text-align: center;" class="col-lg-9">标题</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                              <c:forEach items="${requestScope.page.list }" var="notice">
                                               	<tr>
                                               		<td>
                                               			<c:out value="${notice.time }" />
                                               		</td>
                                               		<td>
                                               		  <a href="${pageContext.request.contextPath }/pages/cooperater/notice!noticeInfo?id=${notice.id }&user=cooperater">
                                               			<c:out value="${notice.title }" />
                                               		  </a>
                                               		</td>
                                               	</tr>
                                               </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div style="text-align:center;">
										共${requestScope.page.countpage }页
										<a href="notice!noticePage2?thispage=${requestScope.page.firstpage}&state=${requestScope.state}&user=cooperater">首页</a>
										<a href="notice!noticePage2?thispage=${requestScope.page.prepage}&state=${requestScope.state}&user=cooperater">上一页</a>
										
										<!-- 分页逻辑开始 -->
										<c:if test="${requestScope.page.countpage<=5 }">
											<c:set var="begin" value="1" scope="page"></c:set>
											<c:set var="end" value="${requestScope.page.countpage }" scope="page"></c:set>
										</c:if>
										<c:if test="${requestScope.page.countpage>5 }">
											<c:choose>
												<c:when test="${requestScope.page.thispage<=3 }">
													<c:set var="begin" value="1" scope="page"></c:set>
													<c:set var="end" value="5" scope="page"></c:set>
												</c:when>
												<c:when test="${requestScope.page.thispage>=requestScope.page.countpage-2 }">
													<c:set var="begin" value="${requestScope.page.countpage-4 }" scope="page"></c:set>
													<c:set var="end" value="${requestScope.page.countpage }" scope="page"></c:set>
												</c:when>
												<c:otherwise>
													<c:set var="begin" value="${requestScope.page.thispage-2 }" scope="page"></c:set>
													<c:set var="end" value="${requestScope.page.thispage+2 }" scope="page"></c:set>
												</c:otherwise>
											</c:choose>
										</c:if>
										<c:forEach begin="${begin }" end="${end }" step="1" var="i">
											<c:if test="${requestScope.page.thispage == i }">
												${i }
											</c:if>
											<c:if test="${requestScope.page.thispage != i }">
												<a href="notice!noticePage2?thispage=${i}&state=${requestScope.state}&user=cooperater">${i }</a>
											</c:if>
										</c:forEach>
										<!-- 分页逻辑结束 -->
										
										<a href="notice!noticePage2?thispage=${requestScope.page.nextpage}&state=${requestScope.state}&user=cooperater">下一页</a>
										<a href="notice!noticePage2?thispage=${requestScope.page.lastpage}&state=${requestScope.state}&user=cooperater">尾页</a>
									</div> 
                                    <!-- /.table-responsive -->
                                </div>
                            </div>
                            <!-- /.row -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                </div>
            </div>
    	</div>
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
	function confirmAccept(){
		return confirm("是否确认解除该用户的绑定？");
	}
</script>
</html>