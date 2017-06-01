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
            <div class="row">
                <div class="col-lg-12">
                    <h4 class="page-header"></h4>
                    <div style="text-align: center;">
                    	<font color="red">${info }</font>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="panel-heading">
               		<form action="rechargeApply.action">
               		<input type="hidden" name="thispage" value="1"/>
               		<input type="hidden" name="supUserName" value="${sessionScope.user.userName }"/>
                   	起止时间<input type="text" id="stime" name="stime" value="${param.stime }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                   	到<input type="text" id="etime" name="etime" value="${param.etime }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                   	<input type="submit" value="查询"/>
                   </form>
               </div>
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> 充值申请
                            	未完成
                            <s:a href="rechargeApply!applyed?thispage=1&stime=&etime=&supUserName=%{#session.user.userName }">已完成</s:a>
                        </div>
                        
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover table-striped" style="text-align: center;">
                                            <thead>
                                                <tr>
                                                    <th style="text-align: center;">交易号</th>
                                                    <th style="text-align: center;">时间</th>
                                                    <th style="text-align: center;">用户名</th>
                                                    <th style="text-align: center;">备注</th>
                                                    <th style="text-align: center;">数量(颗)</th>
                                                    <th style="text-align: center;">操作</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            	<s:iterator value="#request.page.list" status="diamondBill">
                                               	<tr>
                                               		<td>
                                               			<s:property value="id"/>
                                               		</td>
                                               		<td>
                                               			<s:property value="time"/>
                                               		</td>
                                               		<td>
                                               			<s:property value="userName"/>
                                               		</td>
                                               		<td>
                                               			<s:property value="comment"/>
                                               		</td>
                                               		<td>
                                               			<s:property value="income"/>
                                               		</td>
                                               		<td>
                                               			<s:if test="state==0">
	                                               		<div>
	                                               			<div class="col-lg-5">
	                                               			<s:form action="rechargeApply!accept" method="post">
	                                               				<s:token></s:token>
	                                               				<input type="hidden" name = "id"  value="${id }"/>
	                                               				<input type="hidden" name = "thispage"  value="1"/>
	                                               				<input type="hidden" name = "stime"  value=""/>
	                                               				<input type="hidden" name = "etime"  value=""/>
	                                               				<input type="hidden" name = "supUserName"  value="${sessionScope.user.userName }"/>
	                                               				<s:submit value="确认" onclick="return confirmAccept()"></s:submit>
	                                               			</s:form>
	                                               			</div>
	                                               			<div class="col-lg-5">
	                                               			<s:form action="rechargeApply!decline" method="post">
	                                               				<s:token></s:token>
	                                               				<input type="hidden" name = "id"  value="${id }"/>
	                                               				<input type="hidden" name = "thispage"  value="1"/>
	                                               				<input type="hidden" name = "stime"  value=""/>
	                                               				<input type="hidden" name = "etime"  value=""/>
	                                               				<input type="hidden" name = "supUserName"  value="${sessionScope.user.userName }"/>
	                                               				<s:submit value="拒绝" onclick="return confirmDecline()"></s:submit>
	                                               			</s:form>
	                                               			</div>
	                                               		</div>
	                                               		</s:if>
	                                               		<s:if test="state==1">
	                                               			<font color="green">已接受</font>
	                                               		</s:if>
	                                               		<s:if test="state==2">
	                                               			<font color="red">已拒绝</font>
	                                               		</s:if>
                                               		</td>
                                               	</tr>
                                               </s:iterator>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div style="text-align:center;">
										共${requestScope.page.countpage }页
										<a href="rechargeApply.action?thispage=${requestScope.page.firstpage}&stime=${param.stime }&etime=${param.etime }&supUserName=${sessionScope.user.userName }">首页</a>
										<a href="rechargeApply.action?thispage=${requestScope.page.prepage}&stime=${param.stime }&etime=${param.etime }&supUserName=${sessionScope.user.userName }">上一页</a>
										
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
												<a href="rechargeApply.action?thispage=${i}&stime=${param.stime }&etime=${param.etime }&supUserName=${sessionScope.user.userName }">${i }</a>
											</c:if>
										</c:forEach>
										<!-- 分页逻辑结束 -->
										
										<a href="rechargeApply.action?thispage=${requestScope.page.nextpage}&stime=${param.stime }&etime=${param.etime }&supUserName=${sessionScope.user.userName }">下一页</a>
										<a href="rechargeApply.action?thispage=${requestScope.page.lastpage}&stime=${param.stime }&etime=${param.etime }&supUserName=${sessionScope.user.userName }">尾页</a>
									</div> 
                                    <!-- /.table-responsive -->
                                </div>
                            </div>
                            <!-- /.row -->
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
    
	<script type="text/javascript" src="${pageContext.request.contextPath }/My97DatePicker/WdatePicker.js"></script>

</body>

<script type="text/javascript">
	$(document).ready(function(){
		
	});
	function confirmAccept(){
		var msg = "确认接受本次购买申请？";
		return confirm(msg);
	}
	function confirmDecline(){
		var msg = "确认拒绝本次购买申请？";
		return confirm(msg);
	}
</script>
</html>