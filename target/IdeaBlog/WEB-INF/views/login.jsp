<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=0" />
		<title>欢迎进入Idea Blog！</title>
		<link rel="icon" type="image/x-icon" href="resources/img/bulb.ico"/>
		<link rel="stylesheet" type="text/css" href="resources/css/base.css"/>
		<link rel="stylesheet" type="text/css" href="resources/css/login.css"/>
		<script src="resources/js/jquery-3.2.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="resources/js/login.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<form id="main" action="login.do" method="post" onsubmit="return validate()">
			<img src="resources/img/welcome_logo.png" alt="welcome"/>
			<input type="text" id="username" name="username" value="" placeholder="请输入用户名" />
			<input type="password" id="password" name="password" placeholder="请输入密码" />
            <input type="checkbox" name="remember" id="remember" value=""/><label for="remember"><span id="rem">记住我&nbsp;</span></label>
			<p id="error">${null==requestScope.error?"":requestScope.error}</p>
			<input type="submit" id="login" value="立即登录" />
			<input type="button" id="visitor" value="游客访问" />
			<a href="registerPage" id="register">新用户注册</a>
			<a href="findPage" id="find">找回账号密码</a>
		</form>
	</body>
</html>
