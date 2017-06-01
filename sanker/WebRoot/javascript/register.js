
function login(basePath){
	$.ajax({
		type: "POST",
		url: "companyRegisterAction!login.action",
		data: $("#loginForm").serialize(),
		success: function(msg){
			if( msg == "loginSuccess" )
				window.location.href = basePath + "jsp/index/index.jsp";
			else
				alert(msg);
		}
	});
}

function showlogin(){
	$("#lgdiv").css("display", "block");
	$('#txtUserName').focus();
	hideDiv(0);
}

function hidelogin(v){
	if (v == 0) {
		$("#lgdiv").css("display", "none");
	}
	hideDiv(1);
}

function hideDiv(v){
	if (v == 0) {
		$("#hideDiv").css("display", "block");
	}
	else if (v == 1) {
		$("#hideDiv").css("display", "none");
	}
}

function confirmLogin(event){
	event = window.event||event;
	var ENTER_KEY_CODE = 13;
	var keyCode = event.which ? event.which : event.keyCode;
	if(ENTER_KEY_CODE==keyCode){
		document.getElementById("loginButton").click();   
		return false;                               
	}
}
