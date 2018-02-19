<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="resources/bootstrap-fileinput/css/fileinput.min.css"/>
        <link rel="stylesheet" type="text/css" href="resources/bootstrap-table/bootstrap-table.min.css"/>
		<script src="resources/js/jquery-3.2.1.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="resources/bootstrap-fileinput/js/fileinput.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="resources/bootstrap-fileinput/js/locales/zh.js" type="text/javascript" charset="utf-8"></script>
        <script src="resources/bootstrap-table/bootstrap-table.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="resources/bootstrap-table/locale/bootstrap-table-zh-CN.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="resources/js/datetime.js" type="text/javascript" charset="utf-8"></script>
		<script src="resources/js/cloud_file.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
    	<h3 style="padding: 10px">上传文件</h3>
        <div class="file-loading">
            <input id="files" name="files" type="file" multiple>
        </div>
		<h3 style="padding: 10px">云端文件列表</h3>
		<table id="filetable"></table>
	</body>
</html>
