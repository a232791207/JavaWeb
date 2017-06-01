
E={
	warper:function(url, lastRequestTime){
		var newUrl = url;
		if(lastRequestTime){
			if(url.match("[\?]")){
				newUrl += "&lastRequestTime=" + lastRequestTime;
			}else{
				newUrl += "?lastRequestTime=" + lastRequestTime;
			}
		}else{
			if(url.match("[\?]")){
				newUrl += "&lastRequestTime=" + $("#lastRequestTime").val();
			}else{
				newUrl += "?lastRequestTime=" + $("#lastRequestTime").val();
			}
		}
		return newUrl;
	},
	
	transition_:function(url){
		var newUrl = E.warper(url, "evaluation-2012") + "&t=" + (new Date()).valueOf();
		window.open(E.getContextPath() + newUrl, "mainFrame");
		if(EAjax.ajax(true, lastRequestTime)){return false;}
	},
	
	transition:function(url){
		var newUrl = url;
		var lastRequestTime = $("#mainFrame").contents().find("#lastRequestTime").val();
		if(lastRequestTime){
			newUrl = E.warper(url, lastRequestTime) + "&t=" + (new Date()).valueOf();
		}else{
			newUrl = E.warper(url) + "&t=" + (new Date()).valueOf();
		}
		if(EAjax.ajax(true, lastRequestTime)){return false;}
		window.open(E.getContextPath() + newUrl, "mainFrame");
	},
	
	getContextPath:function(){//获取上下文路径
		var pathname = document.location.pathname;
		pathname = pathname.substring(1);
		var pathnames = pathname.split("/");
		
		var protocol = document.location.protocol;
		var host = document.location.host;
		
		var proPath = protocol + "//" + host + "/" + pathnames[0] + "/";
		
		return proPath;
	}
};

EAjax = {
	result:"ok",
	
	/**
	 * @param isShowProcessBar：是否显示加载条，true表示显示，false表示不显示
	 * @return 当EAjax.result == "error"返回true
	 */
	ajax:function(isShowProcessBar, lastRequestTime){
	//限制同浏览器只能打开一个标签使用
		if(false){
			$.ajax({
			    url: E.warper(E.getContextPath() + "permissionAction!pageCheck.action", lastRequestTime),
			    type: 'post',
			    dataType: 'text',
			    data:{t:(new Date()).valueOf()},
			    timeout: 30000,
			    async: false,
			    beforeSend: function(){},
			    error: function(XMLHttpRequest, textStatus, errorThrown){
			    	EAjax.gotoErrorPage();
			    },
			    success: function(msg){
			    	if(msg == "error"){
			    		EAjax.result = "error";
			    		EAjax.gotoErrorPage();
				    }
			    },
			    complete: function (XMLHttpRequest, textStatus) {
					//this; // 调用本次AJAX请求时传递的options参数
				}
			});
		}
		var boo = (EAjax.result == "error");
		if(boo){
			EAjax.gotoErrorPage();
		}else{
			if(isShowProcessBar){
				EAjax.showProcessBar();
			}
		}
		return boo;
	},
	
	/*
	 * 跳转到错误页面
	 */
	gotoErrorPage:function(){
		window.location.href = E.getContextPath() + "jsp/index/login.jsp";
	},
	
	/*
	 * 显示“正在加载...”
	 */
	showProcessBar:function(){
		$("#showResult", parent.document.body).css("display", "block");
	},
	
	hideProcessBar:function(){
		$("#showResult", parent.document.body).css("display", "none");
	}
}

