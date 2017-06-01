<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
  <head>
    <title>业务员后台</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${pageContext.request.contextPath }/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath }/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath }/assets/css/main-min.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
  	<c:if test="${sessionScope.clerk.type!='业务员' }">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/salesman/salesmanLogin.jsp";
		</script>
	</c:if> 
    <div class="header">
    
      <div class="dl-title">
      </div>

    <div class="dl-log">
    	欢迎您，<span class="dl-log-user">${sessionScope.clerk.name }</span>&nbsp;&nbsp;
    	待审核订单数：<font id="wait" color="blue">0</font>
    	审核未通过订单数：<font id="fail" color="red">0</font>
    	<a href="${pageContext.request.contextPath }/servlet/ClerkLogoutServlet" title="退出系统" class="dl-log-quit">[退出]</a>
    </div>
  </div>
   <div class="content">
    <div class="dl-main-nav">
      <div class="dl-inform"><div class="dl-inform-title"><del class="dl-inform-icon dl-up"></del></div></div>
      <ul id="J_Nav"  class="nav-list ks-clear">
        		<li class="nav-item dl-selected"><div class="nav-item-inner nav-home">系统管理</div></li>		       

      </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">

    </ul>
   </div>
  <script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.8.1.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/bui-min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/common/main-min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/config-min.js"></script>
  <script>
    BUI.use('common/main',function(){
      var config = [{id:'1',menu:[{text:'订单管理',items:[
      		{id:'11',text:'订单管理',href:'<%=request.getContextPath()%>/servlet/OrderListServlet?thispage=1&salesman=${sessionScope.clerk.id}'},
      		{id:'12',text:'新增订单',href:'<%=request.getContextPath()%>/user/salesman/addOrder.jsp'}]},
      		{text:'个人信息管理',items:[
      		{id:'21',text:'修改密码',href:'<%=request.getContextPath()%>/updatePassword.jsp'}
      	]}]}];
      new PageUtil.MainPage({
        modulesConfig : config
      });
    });
    
    $(function (){
        (function longPolling() {
            $.ajax({
                url: "${pageContext.request.contextPath}/servlet/GetFailOrderNumServlet",
                data: {"timed": new Date().getTime()},
                dataType: "text",
                cache:"false",
                timeout: 5000,
                type: "POST",
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    if (textStatus == "timeout") { // 请求超时
                            longPolling(); // 递归调用
                        // 其他错误，如网络错误等
                        } else { 
                            longPolling();
                        }
                    },
                success: function (data, textStatus) {
                	var num = data.split(",");
                	$("#fail").text(num[0]);
                	$("#wait").text(num[1]);
                    if (textStatus == "success") { // 请求成功
                        longPolling();
                    }
                }
            });
        })();   
   });
  </script>
  </body>
</html>
