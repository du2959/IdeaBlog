$(document).ready(function(){
	$("#submit").click(function(){
		console.log(document.getElementById("title").value+document.getElementById("email").value+document.getElementById("feedbackcontent").value);
	});
});
function validate(){
    var email_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
    var email_val = $("#email").val();
    if($("#title").val() == ""){
        alert("标题不能为空！");
        $('#title').focus();
        return false;
    }
    if(!email_str.test(email_val)){
        alert("邮箱格式错误！请重新输入！");
        $('#email').focus();
        return false;
    }
    if($("#content").val() == "") {
        alert("亲，写点东西再提交吧！");
        $('#content').focus();
        return false;
    }
    return true;
}