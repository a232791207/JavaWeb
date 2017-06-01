// JavaScript Document

function checkEmail() {
	var msg;
	var email = document.getElementById("email").value;
	var emailreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if (email == "" || email == null) {
		msg = "*必填";
		msg = '<div style="font-size:80%;color:#FF0000">' + msg + '</div>';
		document.getElementById("emailNews").innerHTML = msg;
		return false;
	} else if (!emailreg.test(email)) {
		msg = "*请输入正确的邮箱";
		msg = '<div style="font-size:80%;color:#FF0000">' + msg + '</div>';
		document.getElementById("emailNews").innerHTML = msg;
		return false;
	} else {
		msg = "";
		document.getElementById("emailNews").innerHTML = msg;
		return true;
	}
}
function checkIsNotNull(id) {
	var msg;
	var news = id + "News";
	var info = document.getElementById(id).value;
	if (info == "") {
		msg = "*必填";
		msg = '<div style="font-size:80%;color:#FF0000">' + msg + '</div>';
		document.getElementById(news).innerHTML = msg;
		return false;
	} else {
		msg = "";
		document.getElementById(news).innerHTML = msg;
		return true;
	}
}
function checkClerk() {
	if (checkIsNotNull("id") && checkIsNotNull("name") && checkIsNotNull("age")
			&& checkIsNotNull("password") && checkIsNotNull("phonenumber")
			&& checkIsNotNull("address")) {
		document.getElementById("confirm").disabled = false;
	} else {
		document.getElementById("confirm").disabled = true;
	}
}
function checkUpdateClerk() {
	if (checkIsNotNull("age") && checkIsNotNull("password")
			&& checkIsNotNull("phonenumber") && checkIsNotNull("address")) {
		document.getElementById("confirm").disabled = false;
	} else {
		document.getElementById("confirm").disabled = true;
	}
}
function checkCustomer() {
	if (checkIsNotNull("name") && checkIsNotNull("phonenumber") && checkEmail()) {
		document.getElementById("confirm").disabled = false;
	} else {

		document.getElementById("confirm").disabled = true;
	}
}
function checkDistributor() {
	if (checkIsNotNull("name") && checkIsNotNull("area")
			&& checkIsNotNull("salesman") && checkIsNotNull("phonenumber")
			&& checkIsNotNull("creditlines")) {
		document.getElementById("confirm").disabled = false;
	} else {

		document.getElementById("confirm").disabled = true;
	}
}
function isPFloat(id) {
	var pFloatReg = /^(\d*\.)?\d+$/;
	var info = document.getElementById(id).value;
	if (!info.match(pFloatReg) || info == 0) {
		document.getElementById(id + "News").innerHTML = "*请输入正的整数或小数";
		document.getElementById("confirm").disabled = true;
	} else {
		document.getElementById(id + "News").innerHTML = "*通过";
		document.getElementById("confirm").disabled = false;
	}
}
function isPInt(id) {
	var pIntReg = /^\+?[1-9][0-9]*$/;
	var info = document.getElementById(id).value;
	if (!info.match(pIntReg)) {
		document.getElementById(id + "News").innerHTML = "*请输入正整数";;
		document.getElementById("confirm").disabled = true;
	} else {
		document.getElementById(id + "News").innerHTML = "*通过";
		document.getElementById("confirm").disabled = false;
	}
}
function calculateV() {
	if (document.getElementById("thick").value != ""
		&& document.getElementById("num").value != ""
			&&document.getElementById("height").value!=""
			&&document.getElementById("width").value!="") {
	
	var a = document.getElementById("height").value;//高度
	var b = document.getElementById("width").value;//宽度
	var thick = document.getElementById("thick").value / 1000.0;
	var num = document.getElementById("num").value;
	var v = a * b * thick * num ;
	
	v=v.toFixed(3);
	document.getElementById("volume").value=v;
}
}
function checkProduct() {
	if (checkIsNotNull("format")
			&& document.getElementById("thickNews").innerHTML == "*通过"
			&& document.getElementById("numNews").innerHTML == "*通过"
			&& document.getElementById("bagSlicesNews").innerHTML == "*通过") {
		document.getElementById("confirm").disabled = false;
	} else {
		document.getElementById("confirm").disabled = true;
	}
}
function checkUpdateProduct() {
	if (checkIsNotNull("format")
			&& document.getElementById("thickNews").innerHTML != "*请输入正的整数或小数"
			&& document.getElementById("numNews").innerHTML != "*请输入正整数"
			&& document.getElementById("bagSlicesNews").innerHTML != "*请输入正整数") {
		document.getElementById("confirm").disabled = false;
	} else {
		document.getElementById("confirm").disabled = true;
	}
}
function infoIsOk() {
	alert("操作成功！");
}