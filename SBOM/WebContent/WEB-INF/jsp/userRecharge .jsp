<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>充值申请</title>
		<link rel="stylesheet" href="css/userRecharge.css" />
	</head>
	<body>
		<div class="heads">
				<div class="heads-con">
				<ul>
					<li><a href="#">用户详情</a></li>
					<li><a href="#">充值申请</a></li>
				</ul>
				</div>
			</div>
			<div class="yes">
			   <div class="yes-con">
			   <ul>
			   	<li><a href="#"><input type="button" value="已完成" style="width:180px;height:100px;font-size:40px;"/></a>
			   	&nbsp;&nbsp;&nbsp;<a href="#"><input type="button" value="未完成" style="width:180px;height:100px;font-size:40px;"/></a></li>
			   </ul>	
			   </div>
			</div>
			<div class="menu">
				<div class="menu-cent">
					<ul>
						<li>交易号</li>
						<li>时间</li>
						<li>游戏ID</li>
						<li>数量</li>
						<li>操作</li>
					</ul>
				</div>
				<div class="tab">
					<div class="tab-cen">
						<table>
							<tr>
								<td>acs123</td>
								<td>5-22</td>
								<td>10086</td>
								<td>1000</td>
								<td><a href="#"><input type="button" value="接受" style="width:60px;height:30px;"/></a>
									<a href="#"><input type="button" value="拒绝" style="width:60px;height:30px;"/></a>
								</td>
							</tr>
						</table>
					</div>
			</div>
			
			<div class="foot">
				<div class="foot-find">
					<ul>
						<li><label>请输入时间：</label>	<input type="text" placeholder="请输入时间查询" name="stime" style="width: 400px;height:40px ;"/>
						<a href="#"><input type="button" value="查询" style="font-size: 20px;width: 60px;height: 40px;"/></a>
						</li>
					</ul>
			
			</div>
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
