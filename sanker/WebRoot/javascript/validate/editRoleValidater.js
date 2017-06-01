$(document).ready(function(){
	$.formValidator.initConfig({formid:"form",onerror:function(msg){alert(msg)},onsuccess:function(){document.getElementById("form").submit();return false;}});
	$("#cName").formValidator({onshow:"请输入中文名,2-40个字符",onfocus:"1-20个中文字符"}).inputValidator({min:2,max:40,onerror:"中文名非法,请确认"}).regexValidator({regexp:"[\u4E00-\u9FA5]",onerror:"请输入中文"});
});

