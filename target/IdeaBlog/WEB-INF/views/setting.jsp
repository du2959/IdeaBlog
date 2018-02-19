<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<script>
    var userId = "<%=session.getAttribute("userId")%>";
    var userName = "<%=session.getAttribute("userName")%>";
    var userEmail = "<%=session.getAttribute("userEmail")%>";
    var userRegTime = "<%=session.getAttribute("userRegTime")%>";
    var userGender = "<%=session.getAttribute("userGender")%>";
    var userAge = "<%=session.getAttribute("userAge")%>";
    var userProvince = "<%=session.getAttribute("userProvince")%>";
    var userCity = "<%=session.getAttribute("userCity")%>";
    var userTel = "<%=session.getAttribute("userTel")%>";
    var headIconPath = "<%=session.getAttribute("headIconPath")%>";
</script>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="resources/css/base.css"/>
		<link rel="stylesheet" type="text/css" href="resources/css/setting.css"/>
		<script src="resources/js/jquery-3.2.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="resources/js/setting.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="resources/js/city.js"></script>
	</head>
	<body>
		<div id="accountinfo">
			<h3>账号信息</h3>
			<div id="accountinfoinput">
				<label>用户id</label>
				<input type="text" name="" id="userid" value="" readonly="readonly" />
				<label>用户名</label>
				<input type="text" name="" id="username" value="" readonly="readonly" />
				<label>邮箱</label>
				<input type="email" name="" id="email" value="" readonly="readonly" />
                <label>注册时间</label>
                <input type="text" name="" id="regtime" value="" readonly="readonly" />
                <form id="modifypassword" action="modifyPassword.do" method="post" onsubmit="return validatePass()">
                    <input type="password" name="oldpassword" id="oldpassword" value="" placeholder="请输入原密码" />
                    <input type="password" name="password" id="password" value="" placeholder="请输入新密码" />
                    <input type="password" name="confirmpass" id="confirmpass" value="" placeholder="请确认新密码" />
					<input type="submit" class="submit" value="修改密码" />
                    <p class="status">${null==requestScope.modifypasswordstatus?"":requestScope.modifypasswordstatus}</p>
                </form>
			</div>
		</div>
		<div id="personinfo">
			<h3>个人资料</h3>
                <div id="headicon">
                    <img id="previewImg" src="resources/img/bulb.jpg" alt="头像加载失败" onerror="this.src = 'resources/img/bulb.jpg'"/>
                    <form action="modifyIcon.do" method="post" enctype="multipart/form-data">
                        <input id="myfile" name="myfile" type="file" onchange="showPreview(this)"/>
                        <input type="submit" id="modifyicon" value="先点左侧选择图片" disabled />
                        <span id="modifyiconstatus" class="status">${null==requestScope.modifyiconstatus?"":requestScope.modifyiconstatus}</span>
                    </form>
                </div>
				<form id="personinfoinput" action="updateInfo.do" method="post" onsubmit="return validateInfo()">
					<table>
						<tr><td><label>性别</label></td>
							<td>
								<div id="gender">
									<input type="radio" name="gender" value="1" class="gender" checked="checked" />男
									<input type="radio" name="gender" value="2" class="gender" />女
								</div>
							</td>
						</tr>
						<tr><td><label>年龄</label></td>
							<td>
								<input type="text" name="age" id="age" value="" maxlength="3" />
							</td>
						</tr>
						<tr><td><label>手机号码</label></td>
							<td>
								<input type="tel" name="tel" id="tel" value="" maxlength="20" />
							</td>
						</tr>
						<tr><td><label>所在城市</label></td>
							<td>
								<div id="selectcity">
									<select id="provinceId" name="province" onChange = "select('provinceId','cityId')"></select>　
									<select id="cityId" name="city"></select>
									<script type="text/javascript" >
                                        initCities(userProvince, userCity, 'provinceId', 'cityId')
                                    </script>
								</div>
							</td>
						</tr>
					</table>
                    <input type="submit" class="submit" value="更新资料" />
                    <p class="status">${null==requestScope.status?"":requestScope.status}</p>
                </form>
		</div>
	</body>
</html>
