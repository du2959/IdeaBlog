<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="resources/css/base.css"/>
		<link rel="stylesheet" type="text/css" href="resources/css/follow_collect.css"/>
		<script src="resources/js/jquery-3.2.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="resources/js/datetime.js" type="text/javascript" charset="utf-8"></script>
		<script src="resources/js/follow_collect.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<div id="follow">
			<h3>我的关注</h3>
			<ul id="followfilelist"></ul>
            <input type="button" id="showmorefollowfiles" value="" />
			<ul id="followbloglist"></ul>
            <input type="button" id="showmorefollowblogs" value="" />
		</div>
		<div id="collect">
			<h3>我的收藏</h3>
			<ul id="collectlist"></ul>
			<input type="button" id="showmorecollects" value="" />
		</div>
	</body>
</html>
