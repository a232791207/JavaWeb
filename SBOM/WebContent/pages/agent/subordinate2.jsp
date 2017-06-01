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
	               		<form action="subordinatePage!subordinateByID" method="post">
	               			<input type="hidden" id="supUserName" name="supUserName" value="${sessionScope.user.userName }"/>
	               			<input type="hidden" id="subLevel" name="subLevel" value="0"/>
		                   	用户名<input type="text" id="subUserName" name="subUserName" value="${param.subUserName }"/>
		                   	<input type="submit" value="查询"/>
	                   </form>
	                   
              		</div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> 
                            <a href="subordinatePage.action?thispage=1&supUserName=${sessionScope.user.userName }&days=0&subLevel=0">用户列表</a>
                            <a href="subordinatePage.action?thispage=1&supUserName=${sessionScope.user.userName }&days=3&subLevel=0">3日未登录</a>
                            <a href="subordinatePage.action?thispage=1&supUserName=${sessionScope.user.userName }&days=7&subLevel=0">7日未登录</a>
                            <a href="subordinatePage.action?thispage=1&supUserName=${sessionScope.user.userName }&days=30&subLevel=0">30日未登录</a>
                            <a href="subordinatePage!noConsumption?supUserName=${sessionScope.user.userName }&subLevel=0">未消费</a>
                            
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover table-striped" style="text-align: center;">
                                            <thead>
                                                <tr>
                                                    <th style="text-align: center;">用户名</th>
                                                    <th style="text-align: center;">昵称</th>
                                                    <th style="text-align: center;">注册时间</th>
                                                    <th style="text-align: center;">上次登录时间</th>
                                                    <th style="text-align: center;">日消费</th>
                                                    <th style="text-align: center;">月消费</th>
                                                    <th style="text-align: center;">总消费</th>
                                                    <th style="text-align: center;">解除绑定</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                              <c:forEach items="${requestScope.list }" var="subordinate">
                                               	<tr>
                                               		<td>
                                               			<c:out value="${subordinate.subUserName }" />
                                               		</td>
                                               		<td>
                                               			<c:out value="${subordinate.nickName }" />
                                               			
                                               		</td>
                                               		<td>
                                               			<c:out value="${subordinate.registTime }" />
                                               		</td>
                                               		<td>
                                               			<c:out value="${subordinate.recentTime }" />
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
                                               			<s:form action="removeBinding!remove" method="post">
                                               				<input type="hidden" name = "subUserName"  value="${subordinate.subUserName }"/>
                                               				<input type="hidden" name = "thispage"  value="1"/>
                                               				<input type="hidden" name = "days"  value="0"/>
                                               				<input type="hidden" name = "subLevel"  value="0"/>
                                               				<input type="hidden" name = "supUserName"  value="${sessionScope.user.userName }"/>
                                               				<s:submit value="解绑" onclick="return confirmAccept()"></s:submit>
                                               			</s:form>
                                               		</td>
                                               	</tr>
                                               </c:forEach>
                                            </tbody>
                                        </table>
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
</script>
</html>