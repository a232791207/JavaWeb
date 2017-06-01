<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">

    <title>首页</title>
    <link rel="stylesheet" href="css/style.css">

  </head>
  <body>
    <div class="nav">
		<img src="img/images/mobile_02.jpg" alt="">
    </div>
    <div class="header">
		<div class="head-con">
			<ul>
				<li><a href="#">用户人数</a></li>
				<li><a href="#">新增人数</a></li>		
				<li><a href="#">今日活跃</a></li>	
				<li><a href="#">今日消费</a></li>	
			</ul>
		</div>
    </div>
    <div class="header-btm">
		<h2>公告信息展示</h2>
    </div>
    <div class="menu">
    	<div class="menu-cent">
    		<ul>
				<li><a href="#">交易号</a></li>
				<li><a href="#">时间</a></li>
				<li><a href="#">游戏Id</a></li>
				<li><a href="#">数量</a></li>
				<li><a href="#">操作</a></li>
    		</ul>
    	</div>
		<div class="tab">
		<div class="tab-cen">
				<table >
				<tr>
				<td>时间</td>
				<td>时间</td>
					<td>时间</td>
						<td>时间</td>
							<td>时间</td>
				</tr>
				<tr>
				<td>时间</td>
				<td>时间</td>
					<td>时间</td>
						<td>时间</td>
							<td>时间</td>
				</tr>
				<tr>
				<td>时间</td>
				<td>时间</td>
					<td>时间</td>
						<td>时间</td>
							<td>时间</td>
			</tr>


				</table>
		</div>
		</div>

    </div>
    <div class="foot">
		<h2><a href="${pageContext.request.contextPath }/pages/agent/rechargeApply2.action"><center>更多</center></a></h2>
    </div>
   <div class="foot-list">
		<ul>
			<li><a href="index2.jsp">首页</a></li>
			<li><a href="${pageContext.request.contextPath }/pages/agent/subordinatePage2?thispage=1&supUserName=${sessionScope.user.id }&days=0&subLevel=0">用户</a></li>
			<li><a href="#">推广</a></li>
			<li><a href="billAction2!diamondBillPageAction?dthispage=1&stime=&etime=&userName=${sessionScope.user.userName }">钱包</a></li>
			<li><a href="basicInfo2.jsp">我的</a></li>
		</ul>
    </div>
  </body>
  
</html>