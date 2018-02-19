<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<script>
    var userId = "${null==sessionScope.userId?'':sessionScope.userId}";
    var userCity = "<%=session.getAttribute("userCity")%>";
</script>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="resources/css/base.css"/>
		<link rel="stylesheet" type="text/css" href="resources/css/blog_square.css"/>
		<script src="resources/js/jquery-3.2.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="resources/js/datetime.js" type="text/javascript" charset="utf-8"></script>
		<script src="resources/js/blog_square.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<h3>天气提醒</h3>
		<div id="weather">
			未获取到您城市的天气信息！请到【个人设置】修改[所在城市]
			------数据来自：和风天气API------
	    </div>
		<h3>博客广场</h3>
		<div id="blogsquare">
			<div id="blogs_filter">
				<a href="#" id="tabAll" class="selected">综合</a>
				<a href="#" id="tabNew">最新</a>
				<a href="#" id="tabHot">热门</a>
			</div>
			<div id="allblog">
				<ul id="bloglist"></ul>
                <input type="button" id="showmore" value="" />
			</div>
		</div>
	</body>
</html>
