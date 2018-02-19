<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=0" />
		<title>找回账号密码</title>
		<link rel="icon" type="image/x-icon" href="resources/img/bulb.ico"/>
		<link rel="stylesheet" type="text/css" href="resources/css/base.css"/>
		<link rel="stylesheet" type="text/css" href="resources/css/find.css"/>
		<script src="resources/js/jquery-3.2.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="resources/js/find.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<form id="main" action="find.do" method="post" onsubmit="return validate()">
			<img src="resources/img/welcome_logo.png" alt="welcome"/>
			<input type="email" id="email" name="email" placeholder="请输入您的邮箱" />
			<input type="button" id="send" value="发送邮箱验证码" onclick="sendCode()" />
			<input type="text" name="code" id="code" value="" placeholder="请查收邮件，输入验证码" />
			<input type="text" id="username" name="username" value="" maxlength="20" placeholder="您的用户名" readonly="readonly" />
			<input type="password" id="password" name="password" maxlength="20" placeholder="请输入新密码" />
			<input type="password" id="confirmpass" name="confirmpass" maxlength="20" placeholder="请确认新密码" />
			<p id="error">${null==requestScope.error?"":requestScope.error }</p>
			<input type="submit" id="find" value="提交" />
		</form>
	</body>
</html>
