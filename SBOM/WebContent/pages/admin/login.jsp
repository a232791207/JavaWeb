<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>知奇科技</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath }/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath }/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath }/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath }/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">管理登录</h3>
                        <font color="red">${info }</font>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="userLogin" method="post">
                            <fieldset>
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="用户名" id="userName" name="userName" autofocus>
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" placeholder="密码" id="loginPassword" name="loginPassword" >
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">记住帐号
                                    </label>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <input type="submit" value="登录" class="btn btn-lg btn-success btn-block">
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="${pageContext.request.contextPath }/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath }/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath }/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath }/dist/js/sb-admin-2.js"></script>

</body>

</html>