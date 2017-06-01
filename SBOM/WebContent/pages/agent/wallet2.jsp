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
		<title>钱包</title>
		<link rel="stylesheet" href="css/wallet .css" />
	</head>
	<body>
		<div class="heads">
				<div class="heads-con">
				<ul>
					<li><a href="#">${sessionScope.user.balance }元<br/>账户余额</a></li>
					<li><a href="#">${sessionScope.user.diamond }颗<br/>钻石余额</a></li>
				</ul>
				</div>
			</div>
			<div class="header">
				<div class="header-btm">
					<font>你未（已）开通钻石不足自动充值</font>
					<a href="#"><input type="button" value="设置" style="width: 60px;height: 40px; font-size: 18px;"/></a>
				</div>
			</div>
			<div class="cent">
				<div class="cent-con">
					<ul>
						<a href="recharge2.jsp"><li>充值</li></a>
						<a href="purchase2.jsp"><li>购买</li></a>
						<a href="#"><li>提现</li></a>
						<a href="#"><li>明细</li></a>
					</ul>
				</div>
			</div>
			<div class="info">
				<div class="info-menu">
					<ul>
						<li>充值</li>
						<li>日期</li>
					</ul>
				</div>
				<div class="info-num">
					<table>
					<s:iterator value="#request.diamondBillPage.list" status="diamondBill">
						<tr>
							<td>
							<s:property value="comment"/>
								
								</td>
                             <td>
                             <s:property value="time"/>
                            </td>
						</tr>
						
						</s:iterator>
					</table>
				</div>
			</div>
			<div class="more">
				<a href="#">更多</a>
			</div>
		<div class="foot-list">
		<ul>
			<li><a href="#">首页</a></li>
			<li><a href="${pageContext.request.contextPath }/pages/agent/subordinatePage2?thispage=1&supUserName=${sessionScope.user.id }&days=0&subLevel=0">用户</a></li>
			<li><a href="#">推广</a></li>
			<li><a href="billAction2!diamondBillPageAction?dthispage=1&stime=&etime=&userName=${sessionScope.user.userName }">钱包</a></li>
			<li><a href="basicInfo2.jsp">我的</a></li>
		</ul>
    </div>
	</body>
</html>
