<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Idea Blog系统管理</title>
        <link rel="icon" type="image/x-icon" href="resources/img/bulb.ico"/>
        <link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="resources/bootstrap-table/bootstrap-table.min.css"/>
        <link rel="stylesheet" type="text/css" href="resources/css/system.css"/>
        <script src="resources/js/jquery-3.2.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="resources/bootstrap-table/bootstrap-table.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="resources/bootstrap-table/locale/bootstrap-table-zh-CN.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="resources/js/datetime.js" type="text/javascript" charset="utf-8"></script>
		<script src="resources/js/system.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
        <nav class="navbar navbar-inverse" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="home" title="点我回到主页">Idea Blog</a>
                </div>
                <div>
                    <ul class="nav navbar-nav">
                        <li id="sysLogManage"><a href="#">系统日志管理</a></li>
                        <li id="pubBlogManage"><a href="#">公开博客管理</a></li>
                        <li id="userManage"><a href="#">用户管理</a></li>
                        <li id="fileManage"><a href="#">共享文件管理</a></li>
                        <li id="feedbackManage"><a href="#">反馈管理</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <table id="table"></table>
	</body>
</html>
