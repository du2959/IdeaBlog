$(document).ready(function(){
	$("#visitor").click(function(){
		window.location.href="visit";
	});
});
function validate() {
	var name_str = /^[A-Za-z0-9]{4,}$/;
	var name_val = $("#username").val();
 	var pass_str = /^[A-Za-z0-9]{6,}$/;
	var pass_val = $("#password").val();
	if(!name_str.test(name_val)){
		alert("用户名格式错误！必须为4位以上的字母或数字组合");
		$('#username').focus();
		return false;
	}
	if(!pass_str.test(pass_val)){
		alert("密码格式错误！必须为6位以上的字母或数字组合");
		$('#password').focus();
		return false;
	}
	return true;
}