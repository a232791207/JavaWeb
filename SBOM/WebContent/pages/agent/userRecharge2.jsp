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
					<li><a href="${pageContext.request.contextPath }/pages/agent/subordinatePage2?thispage=1&supUserName=${sessionScope.user.id }&days=0&subLevel=0">用户详情</a></li>
					<li><a href="${pageContext.request.contextPath }/pages/agent/rechargeApply2.action">充值申请</a></li>
				</ul>
				</div>
			</div>
			<div class="yes">
			   <div class="yes-con">
			   <ul>
			   	<li><a href="rechargeApply2!applyed?thispage=1&stime=&etime=&supUserName=%{#session.user.userName }"><input type="button" value="已完成" style="width:180px;height:100px;font-size:40px;"/></a>
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
                                               			<s:property value="income"/>
                                               		</td>
                                               		<td>
                                               			<s:if test="state==0">
	                                               		<div>
	                                               			<div class="col-lg-5">
	                                               			<s:form action="rechargeApply2!accept" method="post">
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
	                                               			<s:form action="rechargeApply2!decline" method="post">
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
						</table>
					</div>
			</div>
			
			<div class="foot">
				<div class="foot-find">
					<ul>
						<li><label>请输入时间：</label>	<input type="text" placeholder="请输入时间查询" name="stime" style="width: 400px;height:40px ;"/>
						<a href="rechargeApply2.action"><input type="button" value="查询" style="font-size: 20px;width: 60px;height: 40px;"/></a>
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
