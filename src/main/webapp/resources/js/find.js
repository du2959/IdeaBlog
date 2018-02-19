function sendCode() {
	var email_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
	var email_val = $("#email").val();
	if(!email_str.test(email_val)){
		alert("邮箱格式错误！请重新输入！");
		$('#email').focus();
	}
	else {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                $("#username").val(xmlhttp.responseText);
            }
        };
        xmlhttp.open("POST", "findUser.do", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("email=" + $("#email").val());

		time($('#send'), 60);
		function time(o,wait) {
			if(wait == 0) {
				$("#email").removeAttr("disabled");
				o.removeAttr("disabled");
				o.val("发送邮箱验证码");
				wait = 60;
			}
			else {
				$("#email").attr("disabled", true);
				o.attr("disabled", true);
				o.val("重新发送（" + wait + "）");
				wait--;
				setTimeout(function(){time(o, wait)}, 1000);
			}
		}
	}
}
function validate() {
	var code_str = /^[0-9]{6}$/;
	var code_val = $("#code").val();
 	var pass_str = /^[A-Za-z0-9]{6,}$/;
	var pass_val = $("#password").val();
	if(!code_str.test(code_val)) {
		alert("验证码格式错误！必须为6位数字");
		$('#code').focus();
		return false;
	}
	if($('#username').val() == '') {
        alert("未获取到用户名！请先发送邮箱验证码！");
        $('#email').focus();
        return false;
    }
	if(!pass_str.test(pass_val)) {
		alert("密码格式错误！必须为6位以上的字母或数字组合");
		$('#password').focus();
		return false;
	}
	if (pass_val != $("#confirmpass").val()) {
	    alert("两次密码输入不一致！");
	    $('#confirmpass').focus();
	    return false;
	}
	return true;
}