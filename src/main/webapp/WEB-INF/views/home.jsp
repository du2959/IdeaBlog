<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Idea Blog</title>
        <link rel="icon" type="image/x-icon" href="resources/img/bulb.ico"/>
        <script src="resources/js/jquery-3.2.1.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="resources/js/home.js" type="text/javascript" charset="utf-8"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/base.css"/>
        <link rel="stylesheet" type="text/css" href="resources/css/home.css"/>
    </head>
    <body>
        <div id="wrap">
            <div id="sidebar">
                <a href="home" id="logo" title="点我刷新主页"><img src="resources/img/logo.png" alt="logo" /></a>
                <ul id="navigation">
                    <li><a href="#" id="blog_square" onclick="loadIframe('blog_square')"><img src="resources/img/icons/home.svg">博客广场</a></li>
                    <li><a href="#" id="post_blog"><img src="resources/img/icons/lights.svg">发布博客</a></li>
                    <li><a href="#" id="follow_collect"><img src="resources/img/icons/favorites.svg">关注收藏</a></li>
                    <li><a href="#" id="blog_manage"><img src="resources/img/icons/nav-list.svg">博客管理</a></li>
                    <li><a href="#" id="cloud_file"><img src="resources/img/icons/upload.svg">云端文件</a></li>
                    <li><a href="#" id="notice"><img src="resources/img/icons/remind.svg">通知中心</a></li>
                    <li><a href="#" id="setting"><img src="resources/img/icons/personal-center.svg">个人设置</a></li>
                    <li><a href="#" id="help_about" onclick="loadIframe('help_about')"><img src="resources/img/icons/help.svg">帮助关于</a></li>
                </ul>
            </div>

            <div id="container">
                <div id="topbar">
                    <div id="daysentence"></div>
                    <div id="visitoruser">
                        <a href="loginPage" id="login"> 登录或注册</a>
                    </div>
                    <div id="loginuser">
                        <a href="#" id="logout"> 注销</a>
                        <div id="personinfo">
                            <a href="#" id="headicon"><img id="showinfo" src="resources/img/icons/down.svg"><img id="headiconimg" src="" alt="头像" onerror="this.src = 'resources/img/bulb.jpg'"/></a>
                            <div id="personinfolist">
                                <a href="#" id="userrole">用户身份</a>
                                <a href="#" id="username">用户名</a>
                            </div>
                        </div>
                    </div>
                    <div id="music">
                        <iframe frameborder="no" border="0" marginwidth="0" marginheight="0" width=280 height=52 src="//music.163.com/outchain/player?type=2&id=26270153&auto=0&height=32"></iframe>
                    </div>
                </div>
                <div id="iframepage">
                    <iframe id="iframe" src="blog_square" frameborder="no"></iframe>
                </div>
            </div>
        </div>
    </body>
    <script>
        var userId = "${null==sessionScope.userId?'':sessionScope.userId}";
        var userName = "${null==sessionScope.userName?'':sessionScope.userName}";
        var userEmail = "${null==sessionScope.userEmail?'':sessionScope.userEmail}";
        var userRole = "${null==sessionScope.userRole?'':sessionScope.userRole}";
        var headIconPath = "${null==sessionScope.headIconPath?'':sessionScope.headIconPath}";
    </script>
</html>

