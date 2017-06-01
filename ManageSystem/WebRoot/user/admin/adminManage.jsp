<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
  <head>
    <title>管理员后台</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${pageContext.request.contextPath }/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath }/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath }/assets/css/main-min.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
  	<c:if test="${sessionScope.admin==null }">
		<script type="text/javascript">
		window.parent.location.href="${pageContext.request.contextPath }/user/admin/adminLogin.jsp";
		</script>
	</c:if> 
    <div class="header">
    
      <div class="dl-title">
      </div>

    <div class="dl-log">
    	欢迎您，<span class="dl-log-user">管理员</span>
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
      var config = [{id:'1',menu:[{text:'人员管理',items:[{id:'11',text:'员工管理',href:'<%=request.getContextPath()%>/servlet/SelectClerk?thispage=1&name=&type='},
      				{id:'12',text:'经销商管理',href:'<%=request.getContextPath()%>/servlet/SelectDistributor?thispage=1&name='},
      				{id:'13',text:'客户管理',href:'<%=request.getContextPath()%>/servlet/SelectCustomer?thispage=1&name='}]},
      				{text:'系统表维护',items:[{id:'21',text:'回款管理',href:'<%=request.getContextPath()%>/servlet/findPaymentServlet?thispage=1&distributor='},
      				{id:'22',text:'调度费管理',href:'<%=request.getContextPath()%>/servlet/BasicFareListServlet?thispage=1'},
      				{id:'23',text:'运费管理',href:'<%=request.getContextPath()%>/servlet/BasicFreight2ListServlet?thispage=1'},
      				{id:'24',text:'已审核订单管理',href:'<%=request.getContextPath()%>/servlet/OrderList3Servlet?thispage=1'},
      				{id:'25',text:'开票管理',href:'<%=request.getContextPath()%>/servlet/DraworderListServlet?thispage=1'},
      				{id:'26',text:'产品管理',href:'<%=request.getContextPath()%>/servlet/ProductListServlet?thispage=1'}]},
      				{text:'已删除信息查询',items:[{id:'31',text:'已删除订单',href:'<%=request.getContextPath()%>/servlet/DeletedOrderListServlet'},
      				{id:'32',text:'已删除回款',href:'<%=request.getContextPath()%>/servlet/DeletedPaymentListServlet'},
      				{id:'33',text:'已删除开票',href:'<%=request.getContextPath()%>/servlet/DeletedDraworderListServlet'}]}
      				]}];
      new PageUtil.MainPage({
        modulesConfig : config
      });
    });
  </script>
  </body>
</html>
