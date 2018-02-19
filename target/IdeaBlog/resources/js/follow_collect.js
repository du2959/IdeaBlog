$(document).ready(function(){
    showFollowFiles(5);
    showFollowBlogs(5);
    showCollectBlogs(10);
});
function showFollowFiles(liNum) {
    $.ajax({
        type:"post",
        url:"showFollowFiles.do",
        dataType:"json",
        async:true,
        success:function(data){
            var str = "";
            for(var i = 0;i < data.length && i < liNum;i++) {
                str += "<li><span class='blue'>" + data[i].username + "</span><span>共享文件：</span><a href='downloadFile.do?fileId=" + data[i].id + "' target='_blank'>" + data[i].fileName + "</a><p class='gray'>上传时间：<span>" + new Date(data[i].upTime).format("yyyy-MM-dd hh:mm:ss") + "</span></p></li>";
            }
            $("#followfilelist").html(str);
            $("#showmorefollowfiles").val("点击加载更多");
            $("#showmorefollowfiles").click(function () {
                showMoreFollowFiles("showFollowFiles.do");
            });
            console.log("关注文件列表已刷新");
        },
        error:function(){
            console.log("error");
        }
    });
}
function showMoreFollowFiles(action) {
    var liNum = $("#followfilelist li").length;
    $.ajax({
        type:"post",
        url:action,
        dataType:"json",
        async:true,
        success:function(data){
            if(liNum >= data.length) {
                $("#showmorefollowfiles").val("没有更多了，快去关注更多用户吧");
            }
            else {
                for(var i = liNum, len = i + 3;i < len && i < data.length;i++) {
                    var str = "<li><span class='blue'>" + data[i].username + "</span><span>共享文件：</span><a href='downloadFile.do?fileId=" + data[i].id + "' target='_blank'>" + data[i].fileName + "</a><p class='gray'>上传时间：<span>" + new Date(data[i].upTime).format("yyyy-MM-dd hh:mm:ss") + "</span></p></li>";
                    $("#followfilelist").append(str);
                }
                console.log("加载更多");
            }
        },
        error:function(){
            console.log("error");
        }
    });
}
function showFollowBlogs(liNum) {
    $.ajax({
        type:"post",
        url:"showFollowBlogs.do",
        dataType:"json",
        async:true,
        success:function(data){
            var str = "";
            for(var i = 0;i < data.length && i < liNum;i++) {
                str += "<li><span class='blue'>" + data[i].username + "</span><span>发布：</span><a href='blogPage?blogId=" + data[i].id + "' target='_blank'>" + data[i].title + "</a><p>标签：&nbsp;&nbsp;<span>" + data[i].tag + "</span></p><p>浏览：<span>" + data[i].views + "</span>&nbsp;&nbsp;收藏：<span>" + data[i].collects + "</span></p><p class='gray'>更新时间：<span>" + new Date(data[i].editTime).format("yyyy-MM-dd hh:mm:ss") + "</span></p></li>";
            }
            $("#followbloglist").html(str);
            $("#showmorefollowblogs").val("点击加载更多");
            $("#showmorefollowblogs").click(function () {
                showMoreFollowBlogs("showFollowBlogs.do");
            });
            console.log("关注博客列表已刷新");
        },
        error:function(){
            console.log("error");
        }
    });
}
function showMoreFollowBlogs(action) {
    var liNum = $("#followbloglist li").length;
    $.ajax({
        type:"post",
        url:action,
        dataType:"json",
        async:true,
        success:function(data){
            if(liNum >= data.length) {
                $("#showmorefollowblogs").val("没有更多了，快去关注更多用户吧");
            }
            else {
                for(var i = liNum, len = i + 3;i < len && i < data.length;i++) {
                    var str = "<li><span class='blue'>" + data[i].username + "</span><span>发布：</span><a href='blogPage?blogId=" + data[i].id + "' target='_blank'>" + data[i].title + "</a><p>标签：&nbsp;&nbsp;<span>" + data[i].tag + "</span></p><p>浏览：<span>" + data[i].views + "</span>&nbsp;&nbsp;收藏：<span>" + data[i].collects + "</span></p><p class='gray'>更新时间：<span>" + new Date(data[i].editTime).format("yyyy-MM-dd hh:mm:ss") + "</span></p></li>";
                    $("#followbloglist").append(str);
                }
                console.log("加载更多");
            }
        },
        error:function(){
            console.log("error");
        }
    });
}
function showCollectBlogs(liNum) {
    $.ajax({
        type:"post",
        url:"showCollectBlogs.do",
        dataType:"json",
        async:true,
        success:function(data){
            var str = "";
            for(var i = 0;i < data.length && i < liNum;i++) {
                str += "<li><span>标题：&nbsp;&nbsp;</span><a href='blogPage?blogId=" + data[i].id + "' target='_blank'>" + data[i].title + "</a><p>标签：&nbsp;&nbsp;<span>" + data[i].tag + "</span></p><p>浏览：<span>" + data[i].views + "</span>&nbsp;&nbsp;收藏：<span>" + data[i].collects + "</span></p><p class='gray'>更新时间：<span>" + new Date(data[i].editTime).format("yyyy-MM-dd hh:mm:ss") + "</span></p></li>";
            }
            $("#collectlist").html(str);
            $("#showmorecollects").val("点击加载更多");
            $("#showmorecollects").click(function () {
                showMoreCollects("showCollectBlogs.do");
            });
            console.log("收藏列表已刷新");
        },
        error:function(){
            console.log("error");
        }
    });
}
function showMoreCollects(action) {
    var liNum = $("#collectlist li").length;
    $.ajax({
        type:"post",
        url:action,
        dataType:"json",
        async:true,
        success:function(data){
            if(liNum >= data.length) {
                $("#showmorecollects").val("没有更多了，快去收藏更多博客吧");
            }
            else {
                for(var i = liNum, len = i + 3;i < len && i < data.length;i++) {
                    var str = "<li><span>标题：&nbsp;&nbsp;</span><a href='blogPage?blogId=" + data[i].id + "' target='_blank'>" + data[i].title + "</a><p>标签：&nbsp;&nbsp;<span>" + data[i].tag + "</span></p><p>浏览：<span>" + data[i].views + "</span>&nbsp;&nbsp;收藏：<span>" + data[i].collects + "</span></p><p class='gray'>更新时间：<span>" + new Date(data[i].editTime).format("yyyy-MM-dd hh:mm:ss") + "</span></p></li>";
                    $("#collectlist").append(str);
                }
                console.log("加载更多");
            }
        },
        error:function(){
            console.log("error");
        }
    });
}
