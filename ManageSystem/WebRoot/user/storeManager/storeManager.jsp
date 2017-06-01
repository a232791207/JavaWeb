<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
  <head>
    <title>储管员后台</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${pageContext.request.contextPath }/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath }/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath }/assets/css/main-min.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
  	<c:if test="${sessionScope.clerk.type!='储管员'&& sessionScope.admin==null }">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/storeManager/storeManagerLogin.jsp";
		</script>
	</c:if> 

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
      var config = [{id:'1',menu:[{text:'库存管理',items:[
            {id:'11',text:'库存查看',href:'<%=request.getContextPath()%>/servlet/FindProductServlet?thispage=1&format=&level='},
            {id:'12',text:'产品入库',href:'<%=request.getContextPath()%>/servlet/FindStoreRecordServlet?thispage=1&format=&level='}]},
      		{text:'个人信息管理',items:[
      		{id:'21',text:'修改密码',href:'<%=request.getContextPath()%>/updatePassword.jsp'}
      	]}]}];
      new PageUtil.MainPage({
        modulesConfig : config
      });
    });
  </script> 
  </body>
</html>
