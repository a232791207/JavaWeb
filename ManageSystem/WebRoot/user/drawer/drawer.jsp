<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
  <head>
    <title>开票员后台</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${pageContext.request.contextPath }/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath }/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath }/assets/css/main-min.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
  	<c:if test="${sessionScope.clerk.type!='开票员' }">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/drawer/drawerLogin.jsp";
		</script>
	</c:if> 
	<c:if test="${sessionScope.clerk.type=='开票员' }">

    <div class="header">
      <div class="dl-title">
      </div>

    <div class="dl-log">
    	欢迎您，<span class="dl-log-user">${sessionScope.clerk.name }</span>
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
      var config = [{id:'1',menu:[{text:'开票管理',items:[
            {id:'11',text:'开票管理',href:'<%=request.getContextPath()%>/servlet/DraworderListServlet?thispage=1'},
      		{id:'12',text:'开票信息查询',href:'<%=request.getContextPath()%>/servlet/findDraworderInfoServlet?thispage=1&distributor='}]},
      		{text:'个人信息管理',items:[
      		{id:'21',text:'修改密码',href:'<%=request.getContextPath()%>/updatePassword.jsp'}
      		]}]}];
      new PageUtil.MainPage({
        modulesConfig : config
      });
    });
  </script>
	</c:if> 
  </body>
</html>
