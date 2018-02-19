<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="resources/css/base.css"/>
		<link rel="stylesheet" type="text/css" href="resources/css/notice.css"/>
		<script src="resources/js/jquery-3.2.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="resources/js/datetime.js" type="text/javascript" charset="utf-8"></script>
		<script src="resources/js/notice.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<h3>数据统计</h3>
		<div id="statistics">
			<div id="blogs">
				<p>发布了</p><span id="blogCount">0</span><p>篇博客</p>
			</div>
			<div id="files">
				<p>上传了</p><span id="fileCount">0</span><p>个文件</p>
			</div>
			<div id="follows">
				<p>关注了</p><span id="followCount">0</span><p>位用户</p>
			</div>
			<div id="collects">
				<p>收藏了</p><span id="collectCount">0</span><p>篇博客</p>
			</div>
		</div>
		<h3>近期通知</h3>
		<div id="notice">
			<div id="comment">
				<h4>评论回复</h4>
				<ul id="commentreply"></ul>
				<input type="button" id="showmorecommentsnotice" value="" />
			</div>
			<div id="log">
				<h4>系统日志</h4>
				<ul id="systemlog">
					<li><span>某时间</span><span>执行了</span><span>某操作</span></li>
					<li><span>某时间</span><span>登录系统</span></li>
					<li><span>某时间</span><span>删除了</span><span>某博客</span></li>
					<li><span>某时间</span><span>上传了</span><span>某文件</span></li>
					<li><span>某时间</span><span>删除了</span><span>某文件</span></li>
					<li><span>某时间</span><span>更新了个人资料</span></li>
					<li><span>某时间</span><span>修改了密码</span></li>
				</ul>
                <input type="button" id="showmoresystemlog" value="showmoresystemlog" />
			</div>
		</div>
	</body>
</html>
