$(document).ready(function(){
    document.getElementById("userid").value = userId;
    document.getElementById("username").value = userName;
    document.getElementById("email").value = userEmail;
    document.getElementById("regtime").value = userRegTime;
    $("input[name=gender][value=" + userGender + "]").attr("checked", true);
    document.getElementById("age").value = userAge;
    document.getElementById("tel").value = userTel;
    document.getElementById("previewImg").src = headIconPath;
    $("#previewImg").click(function () {
        $("#myfile").click();
    });
});
function validatePass() {
    var pass_str = /^[A-Za-z0-9]{6,}$/;
    var pass_val = $("#password").val();
    if(!pass_str.test($("#oldpassword").val())) {
        alert("原密码格式错误！必须为6位以上的字母或数字组合");
        $('#oldpassword').focus();
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
function validateInfo(){
    var age_str = /^\d+$/;
    var age_val = $("#age").val();
    var tel_str = /^1[3,4,5,7,8]\d{9}$/;
    var tel_val = $("#tel").val();
    var city_str = /^[\u4e00-\u9fa5]{0,}$/;
    var city_val = $("#city").val();
    if(!age_str.test(age_val)){
        alert("年龄格式错误！请重新输入！");
        $('#age').focus();
        return false;
    }
    if(!tel_str.test(tel_val)){
        alert("手机号码格式错误！请重新输入！");
        $('#tel').focus();
        return false;
    }
    return true;
}
function showPreview(obj) {
    var pic = document.getElementById("previewImg");
    var file = document.getElementById("myfile");
    html5Reader(file,pic);
    $("#modifyicon").removeAttr("disabled");//激活"修改头像"按钮
    $("#modifyicon").val("点我保存修改");
}
function html5Reader(file,pic) {
    var file = file.files[0];
    // 判断图片格式
    if(!(file.type.indexOf('image')==0 && file.type && /\.(?:jpg|jpeg|png)$/.test(file.name)) ){
        alert('图片只能是jpg,jpeg,png格式');
        return ;
    }
    if(file.size>(1024*300)){
        alert("图片大小不能超过300KB");
        return ;
    }
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function(e){
        pic.src=this.result;
    }
}