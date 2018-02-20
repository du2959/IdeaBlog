$(document).ready(function(){
    if(userId != "") {//用户登录状态
        document.getElementById("visitoruser").style.display = "none";
        if(headIconPath != "") {
            document.getElementById("headiconimg").src = headIconPath;
        }
        if(userRole == 2) {
            document.getElementById("userrole").innerHTML = "<img class='infolist' src='resources/img/icons/set.svg'>系统管理与维护";
            document.getElementById("userrole").href = "system.do";
        }
        else {
            document.getElementById("userrole").innerHTML = "普通用户";
        }
        document.getElementById("username").innerHTML = "<img class='infolist' src='resources/img/icons/account.svg'>" + userName;
    }
    else {//游客访问状态
        document.getElementById("loginuser").style.display = "none";
    }
    showDaySentence();
	$("#navigation a").click(
		function(){
			var id = $(this).attr('id');
		    $('ul#navigation a.active').removeClass('active');
		    $('ul#navigation a#' + id).addClass('active');
		}
	);
    var personinfo=document.getElementById('personinfo');
    var personinfolist=document.getElementById('personinfolist');
    personinfo.onmouseover=function () {
        personinfolist.style.display="block";
    }
    personinfo.onmouseout=function () {
        personinfolist.style.display="none";
    }
    $("#username").click(function () {
        $('#setting').click();
    });
	$("#logout").click(function(){
			if(confirm("确认注销该账号？")){
				window.location.href="loginout.do";
			}
		}
	);
    $("#post_blog").click(function () {
        if(validate()){
            loadIframe("post_blog");
        }
    });
    $("#follow_collect").click(function () {
        if(validate()){
            loadIframe("follow_collect");
        }
    });
    $("#blog_manage").click(function () {
        if(validate()){
            loadIframe("blog_manage");
        }
    });
    $("#cloud_file").click(function () {
        if(validate()){
            loadIframe("cloud_file");
        }
    });
    $("#notice").click(function () {
        if(validate()){
            loadIframe("notice");
        }
    });
    $("#setting").click(function () {
        if(validate()){
            loadIframe("setting");
        }
    });
});

function showDaySentence(){
    $.ajax({
        type:"get",
        dataType:"jsonp",
        url:"http://open.iciba.com/dsapi",
        async:true,
        success:function(obj){
            var tag="<p title='每日一句："+obj.note+"'>"+obj.content+"</p>";
            $("#daysentence").html(tag);
        },
        error:function(){
            console.log("每日一句加载错误");
        }
    });
};
function loadIframe(iframeName){
    var iframe=document.getElementById("iframe");
    iframe.src=iframeName;
}
function validate() {
    if(userId != "") {//用户登录状态
        return true;
    }
    else {//游客访问状态
        document.getElementById("loginuser").style.display = "none";
        if(confirm("您尚未登录，是否立即登录？（游客操作受限）")) {
            window.location.href = "loginPage";
        }
        return false;
    }
}