$(document).ready(function(){
	$.formValidator.initConfig({formid:"updateUserProfileForm",onerror:function(msg){alert(msg)},onsuccess:function(){ updateUserProfileAjax();return false;}});
	$("#txtOrginalPassword").formValidator({onshow:"请输入当前密码",
										onfocus:"当前密码不能为空",oncorrect:"当前密码合法"}).inputValidator({min:0,empty:{leftempty:false,rightempty:false,emptyerror:"当前密码两边不能有空符号"},
										onerror:"当前密码不能为空,请确认"});
	$("#txtNewPassword").formValidator({onshow:"请输入新密码",onfocus:"请输入6-16位的新密码",oncorrect:"新密码合法"})
										.inputValidator({min:6,empty:{leftempty:false,rightempty:false,emptyerror:"新密码两边不能有空符号"},onerror:"新密码不能少于6位,请确认"});
	$("#txtConfirmNewPassword").formValidator({onshow:"请再次输入新密码",
											onfocus:"两次新密码必须一致",oncorrect:"新密码一致"}).inputValidator({min:6,empty:{leftempty:false,rightempty:false,emptyerror:"重复新密码两边不能有空符号"},
											onerror:"重复新密码不能少于6位,请确认"}).compareValidator({desid:"txtNewPassword",operateor:"=",onerror:"两次新密码不一致,请确认"});
});

function updateUserProfileAjax(){
	$.ajax({
		url:"jsp/user/loginUserAction!updateUserProfile.action?passowrd="+$("#txtOrginalPassword").val()+"&newPassword="+$("#txtNewPassword").val(),
		type: 'post',
		data:'txt',
		timeout: 10000,
		error:function(){
			alert("操作失败，请检查你的网络连");
		},
		success:function(msg){
				    if(msg=="OK"){
				    	alert("修改密码成功");
				    	/*
				    	var DG = frameElement.lhgDG;
						if(DG){
							DG.cancel();
						}
						*/
				   	}else{
				    	alert("修改密码失败，请输入正确的原始密码！");
				    }		
		}
	});
}