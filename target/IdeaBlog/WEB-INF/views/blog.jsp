<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>查看博客</title>
        <link rel="stylesheet" type="text/css" href="resources/css/base.css"/>
        <link rel="stylesheet" type="text/css" href="resources/css/blog.css"/>
        <script src="resources/js/jquery-3.2.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="resources/js/datetime.js" type="text/javascript" charset="utf-8"></script>
        <script src="resources/js/blog.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<div id="wrap">
	        <div id="top">
	            <div id="topleft">
	                <h3 id="title"></h3>
	                <p class="gray"><span>更新于：</span><span id="editTime"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span>标签：</span><span id="tag"></span></p>
	                <p><span>浏览：</span><span id="views"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span>收藏：</span><span id="collects"></span><input type="button" id="collect" class="button" value="收藏" onclick="collect()" /></p>
	            </div>
	            <div id="topright">
	                <img id="headicon" src="" alt="作者头像" onerror="this.src = 'resources/img/bulb.jpg'">
	                <div id="userinfo">
	                	<p id="username"></p>
	                	<p><input type="button" id="follow" class="button" value="关注" onclick="follow()" /></p>
	                </div>
	            </div>
	        </div>
	        <div id="mainleft">
		        <div id="content"></div>
		        <div id="comm">
			        <input type="text" id="comment" placeholder="我要说两句。。。" />
			        <input type="button" id="postcomment" class="button" value="评论" onclick="comment()" />
			        <p id="status"></p>
			    </div>
		        <div id="comments">
                    <h4>评论</h4>
                    <ul id="commentlist">
		            </ul>
		            <input type="button" id="showmore" value="" />
		        </div>
	        </div>
	        <div id="mainright">
	        	<p>相关推荐</p>
	        	<ul>
	        		<li></li>
	        	</ul>
	        </div>
		</div>
	</body>
    <script>
        var userId = "${null==sessionScope.userId?'':sessionScope.userId}";
        var userName = "${null==sessionScope.userName?'':sessionScope.userName}";
    </script>
</html>
