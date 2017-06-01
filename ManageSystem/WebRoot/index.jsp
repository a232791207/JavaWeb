<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>丽人木业</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"media="all">
<link href="css/homeStyle.css" rel="stylesheet" type="text/css" media="all">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script src="js/jquery-1.11.1.min.js"></script>
<!---- start-smoth-scrolling---->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1200);
		});
	});
</script>
<!---End-smoth-scrolling---->
<script src="js/responsiveslides.min.js"></script>
<script>
	$(function() {
		$("#slider").responsiveSlides({
			auto : true,
			nav : true,
			speed : 500,
			namespace : "callbacks",
			pager : true,
		});
	});
</script>
<script src="js/bootstrap.js"></script>
</head>

<body>
	<!--start-header-section-->
	<div class="header">
		<div class="container">
			<div class="header-top">
				<div class="logo">
					<a href="index.jsp">丽人木业销售管理系统</a>
				</div>
			</div>
			<div class="header-bottom">
				<nav class="navbar navbar-default">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
					</div>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav cl-effect-16">
							<li class="active"><a href="index.jsp" data-hover="首页">首页
									<span class="sr-only">(current)</span> </a></li>
							<li><a
								href="${pageContext.request.contextPath }/user/admin/adminLogin.jsp"
								data-hover="管理员登录">管理员登录</a></li>
							<li><a
								href="${pageContext.request.contextPath }/user/salesman/salesmanLogin.jsp"
								data-hover="业务员登录">业务员登录</a></li>
							<li><a
								href="${pageContext.request.contextPath }/user/treasurer/treasurerLogin.jsp"
								data-hover="财务员登录">财务员登录</a></li>
							<li><a
								href="${pageContext.request.contextPath }/user/drawer/drawerLogin.jsp"
								data-hover="开票员登录">开票员登录</a></li>
							<li><a
								href="${pageContext.request.contextPath }/user/storeManager/storeManagerLogin.jsp"
								data-hover="储管员登录">储管员登录</a></li>
						</ul>
					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid --> </nav>
				<script>
					$("span.menu").click(function() {
						$(".top-menu ul").slideToggle("slow", function() {
						});
					});
				</script>
				<!-- //script for menu -->
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--end header-section-->
	<div class="header-slider">
		<div class="slider">
			<div class="callbacks_container">
				<ul class="rslides" id="slider">
					<li><img src="images/banner.jpg" alt="">
						<div class="caption">
							<h3>natural material</h3>
							<p>FOURNIER Timber carefully selects from a wide range of
								quality hardwoods to customers exact requirements which
								minimises wastage; we do not supply in packs</p>
						</div>
					</li>
					<li><img src="images/banner1.jpg" alt="">
						<div class="caption">
							<h3>Natural material</h3>
							<p>FOURNIER Timber carefully selects from a wide range of
								quality hardwoods to customers exact requirements which
								minimises wastage; we do not supply in packs</p>
						</div>
					</li>
					<li><img src="images/banner2.jpg" alt="">
						<div class="caption">
							<h3>Natural material</h3>
							<p>FOURNIER Timber carefully selects from a wide range of
								quality hardwoods to customers exact requirements which
								minimises wastage; we do not supply in packs</p>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="footer-section">
		<div class="container">
			<div class="footer-top">
				<p>
					Copyright &copy; 2016.WUST All rights reserved.
				</p>
			</div>
		</div>
	</div>
</body>
</html>
