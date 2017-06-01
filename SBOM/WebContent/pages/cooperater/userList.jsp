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
              </div>
          </div>
          <!-- /.row -->
          
        <!-- /#page-wrapper -->
        <div class="row">
                <div class="col-lg-12">
                    <!-- /.panel -->
                    <div class="panel-heading">
	               		<form action="userPage!userPageById?thispage=1&user=cooperater" method="post">
		                   	用户名<input type="text" id="subUserName" name="subUserName" value="${param.subUserName }"/>
		                   	<input type="submit" value="查询"/>
	                   </form>
	                   
              		</div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> 
                            <a href="userPage.action?thispage=1&user=cooperater">所有用户</a>
                            <a href="userPage!levelUserPage?thispage=1&subLevel=3&user=cooperater">馆主</a>
                            <a href="userPage!levelUserPage?thispage=1&subLevel=2&user=cooperater">副馆主</a>
                            <a href="userPage!levelUserPage?thispage=1&subLevel=1&user=cooperater">奇大使</a>
                            <a href="userPage!levelUserPage?thispage=1&subLevel=0&user=cooperater">普通用户</a>
                            
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover table-striped" style="text-align: center;">
                                            <thead >
                                                <tr>
                                                    <th style="text-align: center;">用户名</th>
                                                    <th style="text-align: center;">级别</th>
                                                    <th style="text-align: center;">日消费</th>
                                                    <th style="text-align: center;">月消费</th>
                                                    <th style="text-align: center;">总消费</th>
                                                    <th style="text-align: center;">详细信息</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                              <c:forEach items="${requestScope.page.list }" var="subordinate">
                                               	<tr>
                                               		<td>
                                               			<c:if test="${subordinate.subLevel>0 }">
                                               			  <a href="userPage!subUserPage?thispage=1&supUserName=${subordinate.subUserName }&user=cooperater">
                                               				<c:out value="${subordinate.subUserName }" />
                                               			  </a>
                                               			</c:if>
                                               			<c:if test="${subordinate.subLevel==0 }">
                                               				<c:out value="${subordinate.subUserName }" />
                                               			</c:if>
                                               		</td>
                                               		<td>
                                               			<c:if test="${subordinate.subLevel==0 }">
                                               				<c:out value="普通用户" />
                                               			</c:if>
                                               			<c:if test="${subordinate.subLevel==1 }">
                                               				<c:out value="奇大使" />
                                               			</c:if>
                                               			<c:if test="${subordinate.subLevel==2 }">
                                               				<c:out value="副馆主" />
                                               			</c:if>
                                               			<c:if test="${subordinate.subLevel==3 }">
                                               				<c:out value="馆主" />
                                               			</c:if>
                                               		</td>
                                               		<td>
                                               			<c:out value="${subordinate.todayConsumption }" />
                                               		</td>
                                               		<td>
                                               			<c:out value="${subordinate.monthConsumption }" />
                                               		</td>
                                               		<td>
                                               			<c:out value="${subordinate.sumConsumption }" />
                                               		</td>
                                               		<td>
                                               		  <c:if test="${subordinate.subLevel==0 }">
                                               			<c:out value="查看" />
                                               		  </c:if>
                                               		  <c:if test="${subordinate.subLevel!=0 }">
                                               			<a href="userInfo.action?userName=${subordinate.subUserName }&user=cooperater">查看</a>
                                               		  </c:if>
                                               		</td>
                                               	</tr>
                                               </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div style="text-align:center;">
										共${requestScope.page.countpage }页
										<a href="${requestScope.url }&thispage=${requestScope.page.firstpage}&user=cooperater">首页</a>
										<a href="${requestScope.url }&thispage=${requestScope.page.prepage}&user=cooperater">上一页</a>
										
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
												<a href="${requestScope.url }&thispage=${i}&user=cooperater">${i }</a>
											</c:if>
										</c:forEach>
										<!-- 分页逻辑结束 -->
										
										<a href="${requestScope.url }&thispage=${requestScope.page.nextpage}&user=cooperater">下一页</a>
										<a href="${requestScope.url }&thispage=${requestScope.page.lastpage}&user=cooperater">尾页</a>
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
</script>
</html>