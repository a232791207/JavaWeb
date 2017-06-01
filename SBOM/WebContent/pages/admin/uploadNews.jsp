<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
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

    <!-- Morris Charts CSS -->
    <link href="${pageContext.request.contextPath }/vendor/morrisjs/morris.css" rel="stylesheet">

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
	
    <div id="wrapper">

        <!-- Navigation -->
        <jsp:include page="struct.jsp"></jsp:include>

        <div id="page-wrapper">
        	<div class="row">
            	<div class="col-lg-12">
	            </div>
	        </div>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="${pageContext.request.contextPath }/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath }/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath }/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="${pageContext.request.contextPath }/vendor/raphael/raphael.min.js"></script>
    <script src="${pageContext.request.contextPath }/vendor/morrisjs/morris.min.js"></script>
    <script src="${pageContext.request.contextPath }/data/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath }/dist/js/sb-admin-2.js"></script>

</body>


<script type="text/javascript">
	function previewImage(file)
	{
	  var MAXWIDTH  = 500; 
	  var MAXHEIGHT = 400;
	  var div = document.getElementById('preview');
	  if (file.files && file.files[0])
	  {
	      div.innerHTML ='<img id=imghead onclick=$("#previewImg").click()>';
	      var img = document.getElementById('imghead');
	      img.onload = function(){
	        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
	        img.width  =  rect.width;
	        img.height =  rect.height;
	//         img.style.marginLeft = rect.left+'px';
	        img.style.marginTop = rect.top+'px';
	      }
	      var reader = new FileReader();
	      reader.onload = function(evt){img.src = evt.target.result;}
	      reader.readAsDataURL(file.files[0]);
	  }
	  else //兼容IE
	  {
	    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
	    file.select();
	    var src = document.selection.createRange().text;
	    div.innerHTML = '<img id=imghead>';
	    var img = document.getElementById('imghead');
	    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
	    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
	    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
	    div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
	  }
	}
	function clacImgZoomParam( maxWidth, maxHeight, width, height ){
	    var param = {top:0, left:0, width:width, height:height};
	    if( width>maxWidth || height>maxHeight ){
	        rateWidth = width / maxWidth;
	        rateHeight = height / maxHeight;
	        
	        if( rateWidth > rateHeight ){
	            param.width =  maxWidth;
	            param.height = Math.round(height / rateWidth);
	        }else{
	            param.width = Math.round(width / rateHeight);
	            param.height = maxHeight;
	        }
	    }
	    param.left = Math.round((maxWidth - param.width) / 2);
	    param.top = Math.round((maxHeight - param.height) / 2);
	    return param;
	}
</script>
</html>