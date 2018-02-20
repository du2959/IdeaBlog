$(document).ready(function(){
    showStatistics();
    showCommentsNotice(7);
    showSystemLogs(10);
});
function showStatistics() {
    $.ajax({
        type:"post",
        url:"showStatistics.do",
        dataType:"json",
        async:true,
        success:function(data){
            $("#blogCount").html(data.blogCount);
            $("#fileCount").html(data.fileCount);
            $("#followCount").html(data.followCount);
            $("#collectCount").html(data.collectCount);
            console.log("统计数据已刷新");
        },
        error:function(){
            console.log("error");
        }
    });
}
function showCommentsNotice(liNum) {
    $.ajax({
        type:"post",
        url:"showCommentsNotice.do",
        dataType:"json",
        async:true,
        success:function(data){
            var str = "";
            for(var i = 0;i < data.length && i < liNum;i++) {
                var commentType = "评论";
                if(data[i].type == 2) {
                    commentType = "回复";
                }
                str += "<li><p class='gray'><span class='blue'>" + data[i].username + "</span>于<span>" + new Date(data[i].postTime).format("yyyy-MM-dd hh:mm:ss") + "</span>" + commentType + "：</p><p>" + data[i].content + "</p><p><span class='gray'>来自博客：</span><a href='blogPage?blogId=" + data[i].blogId + "' target='_blank'>" + data[i].blogTitle + "</a></p></li>";
            }
            $("#commentreply").html(str);
            $("#showmorecommentsnotice").val("点击加载更多");
            $("#showmorecommentsnotice").click(function () {
                showMoreCommentsNotice("showCommentsNotice.do");
            });
            console.log("评论回复通知已刷新");
        },
        error:function(){
            console.log("error");
        }
    });
}
function showMoreCommentsNotice(action) {
    var liNum = $("#commentreply li").length;
    $.ajax({
        type:"post",
        url:action,
        dataType:"json",
        async:true,
        success:function(data){
            if(liNum >= data.length) {
                $("#showmorecommentsnotice").val("没有更多评论回复通知了");
            }
            else {
                for(var i = liNum, len = i + 3;i < len && i < data.length;i++) {
                    var str = "<li><p class='gray'><span class='blue'>" + data[i].username + "</span>于<span>" + new Date(data[i].postTime).format("yyyy-MM-dd hh:mm:ss") + "</span>" + commentType + "：</p><p>" + data[i].content + "</p><p><span class='gray'>来自博客：</span><a href='blogPage?blogId=" + data[i].blogId + "' target='_blank'>" + data[i].blogTitle + "</a></p></li>";
                    $("#commentreply").append(str);
                }
                console.log("加载更多");
            }
        },
        error:function(){
            console.log("error");
        }
    });
}
function showSystemLogs(liNum) {
    $.ajax({
        type:"post",
        url:"showSystemLogs.do",
        dataType:"json",
        async:true,
        success:function(data){
            var str = "";
            for(var i = 0;i < data.length && i < liNum;i++) {
                str += "<li><p><span>" + new Date(data[i].opTime).format("yyyy-MM-dd hh:mm:ss") + "</span>：<span class='blue'>" + data[i].description + "</span></p></li>";
            }
            $("#systemlog").html(str);
            $("#showmoresystemlog").val("点击加载更多");
            $("#showmoresystemlog").click(function () {
                showMoreSystemLogs("showSystemLogs.do");
            });
            console.log("系统日志通知已刷新");
        },
        error:function(){
            console.log("error");
        }
    });
}
function showMoreSystemLogs(action) {
    var liNum = $("#systemlog li").length;
    $.ajax({
        type:"post",
        url:action,
        dataType:"json",
        async:true,
        success:function(data){
            if(liNum >= data.length) {
                $("#showmoresystemlog").val("没有更多系统日志通知了");
            }
            else {
                for(var i = liNum, len = i + 3;i < len && i < data.length;i++) {
                    var str = "<li><p><span>" + new Date(data[i].opTime).format("yyyy-MM-dd hh:mm:ss") + "</span>：<span class='blue'>" + data[i].description + "</span></p></li>";
                    $("#systemlog").append(str);
                }
                console.log("加载更多");
            }
        },
        error:function(){
            console.log("error");
        }
    });
}