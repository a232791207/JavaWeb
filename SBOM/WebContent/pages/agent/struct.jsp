<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	
	<s:action name="reloadSession!reload"> 
		<s:param name="userName" value="#session.user.userName"></s:param>
	</s:action>
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp">知奇</a>
            </div>
            <!-- /.navbar-header -->
			
            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">站内消息通知</a>
                    <ul class="dropdown-menu dropdown-messages">
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>Read All Messages</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-messages -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" href="${pageContext.request.contextPath }/pages/agent/wallet.jsp">财产</a>
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">建议</a> 
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">${sessionScope.user.nickName }</a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="${pageContext.request.contextPath }/pages/agent/profile.jsp"><i class="fa fa-user fa-fw"></i>&nbsp;个人信息</a>
                        </li>
                        <li><a href="subordinatePage.action?thispage=1&supUserName=${sessionScope.user.userName }&days=0"><i class="fa fa-gear fa-fw"></i>&nbsp;用户管理</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="${pageContext.request.contextPath }/pages/agent/userLogout.action"><i class="fa fa-sign-out fa-fw"></i>&nbsp;退出登录</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                   		<li>
                            <a href="${pageContext.request.contextPath }/pages/agent/index.jsp" class="glyphicon glyphicon-home"> 首页</a>
                        </li>
                        <li>
                            <a href="#" class="glyphicon glyphicon-star"> 推广中心<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="${pageContext.request.contextPath }/pages/agent/createUser.jsp">用户推广</a>
                                </li>
                                <s:if test="#session.user.level==2 || #session.user.level==3">
	                                <li>
	                                    <a href="${pageContext.request.contextPath }/pages/agent/createFuguanzhu.jsp">代理推广</a>
	                                </li>
	                                <li>
	                                    <a href="subordinatePage.action?thispage=1&supUserName=${sessionScope.user.userName }&days=0&subLevel=2">代理详情</a>
	                                </li>
                                </s:if>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#" class="glyphicon glyphicon-home"> 用户中心<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                            	<li>
                                    <a href="${pageContext.request.contextPath }/pages/agent/subordinatePage.action?thispage=1&supUserName=${sessionScope.user.id }&days=0&subLevel=0">用户详情</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath }/pages/agent/rechargeApply.action?thispage=1&stime=&etime=&supUserName=${sessionScope.user.userName }">充值申请</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#" class="glyphicon glyphicon-credit-card"> 钱包<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                            	<li>
                                    <a href="${pageContext.request.contextPath }/pages/agent/wallet.jsp">总览</a>
                                </li>
                                <li>
                                    <a href="#">收支明细<span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
		                                <li>
		                                    <a href="billAction!cashBillPageAction?cthispage=1&stime=&etime=&userName=${sessionScope.user.userName }">现金账单</a>
		                                </li>
		                                <li>
		                                    <a href="billAction!diamondBillPageAction?dthispage=1&stime=&etime=&userName=${sessionScope.user.userName }">钻石账单</a>
		                                </li>
                           			 </ul>
                                </li>
                                <li>
                                    <a href="#">充值<span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
		                                <li>
		                                    <a href="${pageContext.request.contextPath }/pages/agent/rmbRecharge.jsp">RMB充值</a>
		                                </li>
		                                <li>
		                                    <a href="${pageContext.request.contextPath }/pages/agent/diamondRecharge.jsp">钻石充值</a>
		                                </li>
                           			 </ul>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#" class="glyphicon glyphicon-user"> 个人中心<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="${pageContext.request.contextPath }/pages/agent/profile.jsp">基本资料</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath }/pages/agent/levelAndDiscount.jsp">等级与优惠</a>
                                </li>
                                <li>
                                    <a href="#">修改密码<span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
		                                <li>
		                                    <a href="${pageContext.request.contextPath }/pages/agent/updateLoginPassword.jsp">修改登录密码</a>
		                                </li>
		                                <li>
		                                    <a href="${pageContext.request.contextPath }/pages/agent/updatePayPassword.jsp">修改支付密码</a>
		                                </li>
                           			 </ul>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li></li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>