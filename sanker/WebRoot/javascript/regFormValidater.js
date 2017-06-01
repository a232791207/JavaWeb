$(document).ready(function(){
	$.formValidator.initConfig({formid:"regForm",onerror:function(msg){alert(msg)},onsuccess:function(){  $('#refForm').submit();return true;}});
	$("#txtUserName").formValidator({onshow:"请输入用户名,3-16个字符",onfocus:"用户名至少3个字符,最多16个字符",oncorrect:"该用户名可以注册"}).inputValidator({min:3,max:16,onerror:"你输入的用户名非法,请确认"}).regexValidator({regexp:"username",datatype:"enum",onerror:"用户名格式不正确"})
	    .ajaxValidator({
	    type : "post",
		url : "jsp/user/loginUserAction!checkUserName.action",
		datatype : "json",
		success : function(msg){
			if(EAjax.ajax()){return false;}
            if( msg.isAvailable =="true")
			{
                return true;
			}
            else
			{
                return false;
			}
		},
		buttons: $("#button"),
		error: function(){alert("服务器没有返回数据，可能服务器忙，请重试");},
		onerror : "该用户名不可用，请更换用户名",
		onwait : "正在对用户名进行合法性校验，请稍候..."
	});
	$("#txtUserRealName").formValidator({onshow:"请输入您的真实姓名",onfocus:"真实姓名必须介于2-8个中文之间",oncorrect:"校验通过"}).inputValidator({min:4,max:16,empty:{leftempty:false,rightempty:false,emptyerror:"真实姓名两边不能有空符号"},onerror:"真实姓名必须介于2-8个中文之间,请确认"}).regexValidator({regexp:"[\u4E00-\u9FA5]",onerror:"请输入中文"});;
	$("#txtPassWord").formValidator({onshow:"请输入密码",onfocus:"密码不能为空",oncorrect:"密码合法"}).inputValidator({min:1,empty:{leftempty:false,rightempty:false,emptyerror:"密码两边不能有空符号"},onerror:"密码不能为空,请确认"});
	$("#comfirmPassWord").formValidator({onshow:"请输入重复密码",onfocus:"两次密码必须一致",oncorrect:"密码一致"}).inputValidator({min:1,empty:{leftempty:false,rightempty:false,emptyerror:"重复密码两边不能有空符号"},onerror:"重复密码不能为空,请确认"}).compareValidator({desid:"txtPassWord",operateor:"=",onerror:"2次密码不一致,请确认"});
	$("#txtPassWord2").formValidator({onshow:"请输入密码",onfocus:"密码不能为空",oncorrect:"密码合法"}).inputValidator({min:0,max:16,empty:{leftempty:false,rightempty:false,emptyerror:"密码两边不能有空符号"},onerror:"密码不能为空,请确认"});
	$("#comfirmPassWord2").formValidator({onshow:"请输入重复密码",onfocus:"两次密码必须一致",oncorrect:"密码一致"}).inputValidator({min:0,max:16,empty:{leftempty:false,rightempty:false,emptyerror:"重复密码两边不能有空符号"},onerror:"重复密码不能为空,请确认"}).compareValidator({desid:"txtPassWord2",operateor:"=",onerror:"两次密码不一致,请确认"});
	$("#txtEmail").formValidator({onshow:"请输入邮箱",onfocus:"邮箱6-100个字符",oncorrect:"校验通过"}).inputValidator({min:0,max:50,onerror:"你输入的邮箱长度非法,请确认"}).regexValidator({regexp:"^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$|^[0]{0}$",onerror:"你输入的邮箱格式不正确"});
	$("#txtTel").formValidator({onshow:"请输入你的联系电话",onfocus:"格式例如：028-8888888 或11位手机号码",oncorrect:"校验通过"}).regexValidator({regexp:"^[0-9]{3,4}[-]{1}[0-9]{7,9}$|^[0]{0}$|^[0-9]{7,9}$|^[[0-9]{11}$",onerror:"你输入的联系电话格式不正确"});
	$("#txtUnitMoney").formValidator({onshow:"请输入时薪",onfocus:"每小时工作薪酬，(1-5)位整数可跟两位小数",oncorrect:"校验通过"}).inputValidator({min:0,max:50,onerror:"你输入的时薪长度非法,请确认"}).regexValidator({regexp:"^[0-9]{1,5}$|^[0-9]{1,5}[.]{1}[0-9]{1,2}$",onerror:"你输入的时薪格式不正确"});
});
function GetRadioValue(RadioName){
    var obj;    
    obj=document.getElementsByName(RadioName);
    if(obj!=null){
        var i;
        for(i=0;i<obj.length;i++){
            if(obj[i].checked){
                return obj[i].value;            
            }
        }
    }
    return null;
}

function getSex(){
	document.getElementById("sex").value=GetRadioValue("sex");
}
