$(document).ready(function(){
    var myCity = "青岛";
    if(userId != "") {//用户登录状态将获取用户城市
        myCity = userCity;
    }
    showWeather();
	var tabAll = document.getElementById('tabAll');
	var tabNew = document.getElementById('tabNew');
	var tabHot = document.getElementById('tabHot');
	tabAll.style.backgroundColor = "#eee";
	tabNew.style.backgroundColor = "";
	tabHot.style.backgroundColor = "";
    showBlogs("showAllBlogs.do", 7);
	$("#tabAll").click(function () {
		tabAll.style.backgroundColor = "#eee";
		tabNew.style.backgroundColor = "";
		tabHot.style.backgroundColor = "";
        showBlogs("showAllBlogs.do", 7);
	});
	$("#tabNew").click(function () {
		tabAll.style.backgroundColor = "";
		tabNew.style.backgroundColor = "#eee";
		tabHot.style.backgroundColor = "";
		showBlogs("showNewBlogs.do", 7);
	});
	$("#tabHot").click(function () {
		tabAll.style.backgroundColor = "";
		tabNew.style.backgroundColor = "";
        tabHot.style.backgroundColor = "#eee";
        showBlogs("showHotBlogs.do", 7);
	});

    modifyCity = function() {
        myCity = prompt("请输入您的城市（如：青岛）");
        showWeather();
    };
    function showWeather() {
        $.ajax({
            type:"get",
            dataType:"json",
            url:"https://free-api.heweather.com/s6/weather?location="+myCity+"&key=edd972bb8adb4bf682de3a2b106a9cb1",
            async:true,
            success:function(data){
                var tag = "<img src='resources/img/weather_icon/" + data.HeWeather6[0].now.cond_code + ".png' alt='天气图片' style='display:block;float:left;width:100px;' /><div style='display:block;float:left;width:600px;margin-top:10px;margin-left:10px'><p><a href='#' onclick='modifyCity()'>" + "<img class='tabicon' src='resources/img/icons/map.svg'>" + data.HeWeather6[0].basic.admin_area + data.HeWeather6[0].basic.location + "</a></p><p>实况天气：" + data.HeWeather6[0].now.cond_txt + "，温度：" + data.HeWeather6[0].now.tmp + "℃，体感温度：" + data.HeWeather6[0].now.fl + "℃，" + data.HeWeather6[0].now.wind_dir + data.HeWeather6[0].now.wind_sc + "级。</p><p>舒适度指数：" + data.HeWeather6[0].lifestyle[0].brf + "。" + data.HeWeather6[0].lifestyle[0].txt + "</p></div>";
                $("#weather").html(tag);
            },
            error:function(){
                console.log("天气加载错误");
            }
        });
    }

});
function showBlogs(action, liNum) {
    $.ajax({
        type:"post",
        url:action,
        dataType:"json",
        async:true,
        success:function(data){
            var str = "";
            for(var i = 0;i < data.length && i < liNum;i++) {
                str += "<li><span class='blue'>" + data[i].username + "</span><span>发布：</span><a href='blogPage?blogId=" + data[i].id + "' target='_blank'>" + data[i].title + "</a><p><img class='bloglist' src='resources/img/icons/discount.svg'>&nbsp;<span>" + data[i].tag + "</span></p><p><img class='bloglist' src='resources/img/icons/browse.svg'>&nbsp;<span>" + data[i].views + "</span>&nbsp;&nbsp;&nbsp;&nbsp;<img class='bloglist' src='resources/img/icons/favorite-16.svg'>&nbsp;<span>" + data[i].collects + "</span></p><p class='gray'><img class='bloglist' src='resources/img/icons/clock-16-gray.svg'>&nbsp;<span>" + new Date(data[i].editTime).format("yyyy-MM-dd hh:mm:ss") + "</span></p></li>";
            }
            $("#bloglist").html(str);
            $("#showmore").val("点击加载更多");
            $("#showmore").click(function () {
                showMore(action);
            });
            console.log("列表已刷新");
        },
        error:function(){
            console.log("error");
        }
    });
}
function showMore(action) {
    var liNum = $("#bloglist li").length;
    $.ajax({
        type:"post",
        url:action,
        dataType:"json",
        async:true,
        success:function(data){
            if(liNum >= data.length) {
                $("#showmore").val("没有更多了，快发布自己的博客吧");
            }
            else {
                for(var i = liNum, len = i + 3;i < len && i < data.length;i++) {
                    var str = "<li><span class='blue'>" + data[i].username + "</span><span>发布：</span><a href='blogPage?blogId=" + data[i].id + "' target='_blank'>" + data[i].title + "</a><p><img class='bloglist' src='resources/img/icons/discount.svg'>&nbsp;<span>" + data[i].tag + "</span></p><p><img class='bloglist' src='resources/img/icons/browse.svg'>&nbsp;<span>" + data[i].views + "</span>&nbsp;&nbsp;&nbsp;&nbsp;<img class='bloglist' src='resources/img/icons/favorite-16.svg'>&nbsp;<span>" + data[i].collects + "</span></p><p class='gray'><img class='bloglist' src='resources/img/icons/clock-16-gray.svg'>&nbsp;<span>" + new Date(data[i].editTime).format("yyyy-MM-dd hh:mm:ss") + "</span></p></li>";
                    $("#bloglist").append(str);
                }
                console.log("加载更多");
            }
        },
        error:function(){
            console.log("error");
        }
    });
}
