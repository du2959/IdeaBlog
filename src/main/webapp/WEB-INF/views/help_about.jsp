<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="resources/css/base.css"/>
		<link rel="stylesheet" type="text/css" href="resources/css/help_about.css"/>
		<script src="resources/js/jquery-3.2.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="resources/js/help_about.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<h3>系统简介</h3>
		<div id="introduce">
			<p>&nbsp;&nbsp;&nbsp;&nbsp;本系统是基于Java EE的Idea博客系统，Idea意为“想法”，本系统旨在提供一个专门的、简洁的轻量级技术交流分享平台，供用户随时随地将自己好的想法发表为博客，并与其他用户讨论，达到相互学习，共同提高的目的。
			这是本人毕业设计的选题。主要技术：前端HTML5+CSS3+JavaScript，后台SSM(SpringMVC+Spring+MyBatis)框架，服务器Tomcat，数据库MySQL。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;如果您有好的意见或建议，欢迎反馈。感谢您的支持！</p>
		</div>
		<h3>意见反馈</h3>
        <p id="status">${null==requestScope.status?"":requestScope.status}</p>
        <form id="feedback" action="feedback.do" method="post" onsubmit="return validate()">
            <input type="text" id="title" name="title" value="" placeholder="请输入标题（必填）" />
            <input type="email" name="email" id="email" value="" placeholder="请留下您的邮箱（必填）" />
            <input type="submit" id="submit" value="提交" />
			<textarea id="content" name="content" placeholder="请留下您的意见。您的关注是我最大的动力！"></textarea>
		</form>
		<div id="copyright">
			<p>© 2018 DuBaoxiang. All rights reserved.</p>
			<p>有问题可联系官方邮箱：ideablog@163.com</p>
		</div>
	</body>
</html>
