<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=0" />
		<title>欢迎注册Idea Blog！</title>
		<link rel="icon" type="image/x-icon" href="resources/img/bulb.ico"/>
		<link rel="stylesheet" type="text/css" href="resources/css/base.css"/>
		<link rel="stylesheet" type="text/css" href="resources/css/register.css"/>
		<script src="resources/js/jquery-3.2.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="resources/js/register.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<form id="main" action="register.do" method="post" onsubmit="return validate()">
			<img src="resources/img/welcome_logo.png" alt="welcome"/>
			<input type="text" id="username" name="username" value="" maxlength="20" placeholder="请输入用户名" />
			<input type="password" id="password" name="password" maxlength="20" placeholder="请输入密码" />
			<input type="password" id="confirmpass" name="confirmpass" maxlength="20" placeholder="请确认密码" />
			<input type="email" id="email" name="email" placeholder="请输入邮箱（用于找回账号密码）" />
			<p id="error">${null==requestScope.error?"":requestScope.error }</p>
			<input type="submit" id="register" value="立即注册" />
			<input type="reset" id="reset" value="重置" />
		</form>
	</body>
</html>
