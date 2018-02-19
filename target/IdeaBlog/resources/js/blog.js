$(document).ready(function () {
    $.ajax({
        type:"post",
        url:"showBlogById.do",
        dataType:"json",
        async:true,
        success:function(data){
            $("#title").html(data.title);
            $("#editTime").html(new Date(data.editTime).format("yyyy-MM-dd hh:mm:ss"));
            $("#tag").html(data.tag);
            $("#views").html(data.views);
            $("#collects").html(data.collects);
            $("#content").html(data.content);
            $("#username").html(data.username);
            document.getElementById("headicon").src = data.headIconPath;
            if(userName == data.username) {
                $("#follow").val("自己");
                $("#follow").attr("disabled");
            }
        },
        error:function(){
            console.log("error");
        }
    });
    $.ajax({
        type:"post",
        url:"increaseViews.do",
        async:true,
        success:function(data){
            console.log("浏览数+1");
        },
        error:function(){
            console.log("error");
        }
    });
    $.ajax({
        type:"post",
        url:"getRCollect.do",
        async:true,
        success:function(data){
            if(data == 1){
                $("#collect").val("已收藏");
                $("#collect").css({"color": "dodgerblue", "background-color": "#EBEBEB", "border": "1px solid dodgerblue"});
            }
        },
        error:function(){
            console.log("error");
        }
    });
    $.ajax({
        type:"post",
        url:"getRFollow.do",
        async:true,
        success:function(data){
            if(data == 1){
                $("#follow").val("已关注");
                $("#follow").css({"color": "dodgerblue", "background-color": "#EBEBEB", "border": "1px solid dodgerblue"});
            }
        },
        error:function(){
            console.log("error");
        }
    });
    $.ajax({
        type:"post",
        url:"getHeadIconPath.do",
        async:true,
        success:function(data){
            document.getElementById("headicon").src = data;
        },
        error:function(){
            console.log("error");
        }
    });
    showComments("showComments.do", 7);
});
function showComments(action, liNum) {
    $.ajax({
        type:"post",
        url:action,
        dataType:"json",
        async:true,
        success:function(data){
            var str = "";
            for(var i = 0;i < data.length && i < liNum;i++) {
                var reply = "";
                if(data[i].type == 2) {
                    reply = "回复<span class='blue'>" + data[i].replyUsername + "</span>";
                }
                str += "<li><p><span class='blue'>" + data[i].username + "</span>" + reply + "：<span>" + data[i].content + "</span></p><p><span class='gray'>" + new Date(data[i].postTime).format("yyyy-MM-dd hh:mm:ss") + "</span><a href='#' onclick='reply(" + data[i].userId + ")'>回复</a></p></li>";
            }
            $("#commentlist").html(str);
            $("#showmore").val("点击加载更多");
            $("#showmore").click(function () {
                showMore(action);
            });
            console.log("评论列表已刷新");
        },
        error:function(){
            console.log("error");
        }
    });
}
function showMore(action) {
    var liNum = $("#commentlist li").length;
    $.ajax({
        type:"post",
        url:action,
        dataType:"json",
        async:true,
        success:function(data){
            if(liNum >= data.length) {
                $("#showmore").val("没有更多了，快发布自己的评论吧");
            }
            else {
                for(var i = liNum, len = i + 3;i < len && i < data.length;i++) {
                    var reply = "";
                    if(data[i].type == 2) {
                        reply = "回复<span class='blue'>" + data[i].replyUsername + "</span>";
                    }
                    var str = "<li><p><span class='blue'>" + data[i].username + "</span>" + reply + "：<span>" + data[i].content + "</span></p><p><span class='gray'>" + new Date(data[i].postTime).format("yyyy-MM-dd hh:mm:ss") + "</span><a href='#' onclick='reply(" + data[i].userId + ")'>回复</a></p></li>";
                    $("#commentlist").append(str);
                }
                console.log("加载更多");
            }
        },
        error:function(){
            console.log("error");
        }
    });
}
function collect() {
    if(validate()) {
        if($("#collect").val() == "收藏"){
            //收藏该博客
            $.ajax({
                type:"post",
                url:"collect.do",
                async:true,
                success:function(data){
                    if(data == 1){
                        $("#collect").val("已收藏");
                        $("#collect").css({"color": "dodgerblue", "background-color": "#EBEBEB", "border": "1px solid dodgerblue"});
                    }
                },
                error:function(){
                    console.log("error");
                }
            });
        }
        else {
            //取消收藏
            $.ajax({
                type:"post",
                url:"uncollect.do",
                async:true,
                success:function(data){
                    if(data == 0){
                        $("#collect").val("收藏");
                        $("#collect").css({"color": "#fff", "background-color": "#2AABD2", "border": "none"});
                    }
                },
                error:function(){
                    console.log("error");
                }
            });
        }
    }
}
function follow() {
    if(validate()) {
        if($("#follow").val() == "关注"){
            //关注该用户
            $.ajax({
                type:"post",
                url:"follow.do",
                async:true,
                success:function(data){
                    if(data == 1){
                        $("#follow").val("已关注");
                        $("#follow").css({"color": "dodgerblue", "background-color": "#EBEBEB", "border": "1px solid dodgerblue"});
                    }
                },
                error:function(){
                    console.log("error");
                }
            });
        }
        else {
            //取消关注
            $.ajax({
                type:"post",
                url:"unfollow.do",
                async:true,
                success:function(data){
                    if(data == 0){
                        $("#follow").val("关注");
                        $("#follow").css({"color": "#fff", "background-color": "#2AABD2", "border": "none"});
                    }
                },
                error:function(){
                    console.log("error");
                }
            });
        }
    }
}
function comment() {
    if(validate()) {
        if($("#comment").val() == ""){
            alert("写点儿东西再提交吧！");
            $("#comment").focus();
        }
        else {
            $.ajax({
                type:"post",
                url:"comment.do?content=" + $("#comment").val() + "&type=1&replyUserId=0",
                async:true,
                success:function(data){
                    $("#status").html(data);
                    showComments("showComments.do", 7);
                },
                error:function(){
                    console.log("error");
                }
            });
        }
    }
}
function reply(replyUserId) {
    if(validate()) {
        var content = prompt("回复该评论");
        if(content != "" && content != null) {
            console.log(content);
            $.ajax({
                type:"post",
                url:"comment.do?content=" + content + "&type=2&replyUserId=" + replyUserId,
                async:true,
                success:function(data){
                    $("#status").html(data);
                    showComments("showComments.do", 7);
                },
                error:function(){
                    console.log("error");
                }
            });
        }
    }
}
function validate() {
    if(userId != "") {//用户登录状态
        return true;
    }
    else {//游客访问状态
        if(confirm("您尚未登录，是否立即登录？")) {
            window.location.href = "loginPage";
        }
        return false;
    }
}