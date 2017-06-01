$(document).ready(function(){
	$("#eName").keyup(function(e){$("#eName").val($("#eName").val().toUpperCase())});
	$.formValidator.initConfig({formid:"form",onerror:function(msg){alert(msg)},onsuccess:function(){document.getElementById("form").submit();return false;}});
	$("#eName").formValidator({onshow:"请输入简称,1-20个字符",onfocus:"1至20个字符",oncorrect:"该简称可以使用"}).inputValidator({min:1,max:20,onerror:"简称非法,请确认"})
	    .ajaxValidator({
	    type : "post",
		url : "jsp/role/roleAction!validateEnName.action",
		datatype : "json",
		success : function(msg){
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
		onerror : "该简称不可用，请更换简称",
		onwait : "正在对用户名进行合法性校验，请稍候..."
	});
	$("#cName").formValidator({onshow:"请输入中文名,2-40个字符",onfocus:"1-20个中文字符"}).inputValidator({min:2,max:40,onerror:"中文名非法,请确认"}).regexValidator({regexp:"[\u4E00-\u9FA5]",onerror:"请输入中文"});
	//$("#movementDirection").formValidator({onshow:"请输入中文名,2-40个字符",onfocus:"1-20个中文字符"}).inputValidator({min:2,max:40,onerror:"中文名非法,请确认"}).regexValidator({regexp:"[\u4E00-\u9FA5]",onerror:"请输入中文"});

});

