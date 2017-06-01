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
					<li><a href="#">0.00<br/>账户余额</a></li>
					<li><a href="#">0<br/>钻石余额</a></li>
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
						<a href="#"><li>充值</li></a>
						<a href="#"><li>购买</li></a>
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
						<tr>
							<td>充值1000颗钻石</td>
							<td>2017-5-5</td>
						</tr>
						<tr>
							<td>充值1000颗钻石</td>
							<td>2017-5-5</td>
						</tr>
						<tr>
							<td>充值1000颗钻石</td>
							<td>2017-5-5</td>
						</tr>
						<tr>
							<td>充值1000颗钻石</td>
							<td>2017-5-5</td>
						</tr>
						<tr>
							<td>充值1000颗钻石</td>
							<td>2017-5-5</td>
						</tr>
						<tr>
							<td>充值1000颗钻石</td>
							<td>2017-5-5</td>
						</tr>
						<tr>
							<td>充值1000颗钻石</td>
							<td>2017-5-5</td>
						</tr>
						<tr>
							<td>充值1000颗钻石</td>
							<td>2017-5-5</td>
						</tr>
						<tr>
							<td>充值1000颗钻石</td>
							<td>2017-5-5</td>
						</tr>
						
					</table>
				</div>
			</div>
			<div class="more">
				<a href="#">更多</a>
			</div>
		<div class="foot-list">
		<ul>
			<li><a href="#">首页</a></li>
			<li><a href="#">用户</a></li>
			<li><a href="#">推广</a></li>
			<li><a href="#">钱包</a></li>
			<li><a href="#">我的</a></li>
		</ul>
    </div>
	</body>
</html>
