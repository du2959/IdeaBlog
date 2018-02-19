<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>编辑博客</title>
        <link rel="stylesheet" type="text/css" href="resources/css/base.css"/>
        <link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="resources/css/edit_blog.css"/>
        <script src="resources/js/jquery-3.2.1.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="resources/ueditor/ueditor.config.js" type="text/javascript" charset="utf-8"></script>
        <script src="resources/ueditor/ueditor.all.js" type="text/javascript" charset="utf-8"></script>
        <script src="resources/ueditor/lang/zh-cn/zh-cn.js" type="text/javascript" charset="utf-8"></script>
        <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="resources/js/edit_blog.js" type="text/javascript" charset="utf-8"></script>
    </head>
    <body>
        <h3>编辑博客</h3>
        <div class="input-group">
            <input type="text" id="blogtitle" name="blogtitle" class="form-control" value="" maxlength="50" placeholder="请输入博客标题（必填）" />
            <input type="text" id="blogtag" name="blogtag" class="form-control" value="" maxlength="50" placeholder="给你的博客加上标签吧" />
            <button id="saveblog" class="btn btn-info">保存修改</button>
            <button id="exit" class="btn btn-danger">退出编辑</button>
            <span id="status">${null==requestScope.status?"":requestScope.status}</span>
        </div>
        <div id="editor"></div>
        <script type="text/javascript">
            //实例化编辑器
            //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
            var ue = UE.getEditor('editor', {
                toolbars: [[
                    'fullscreen', 'source', '|', 'undo', 'redo', '|',
                    'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
                    'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
                    'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
                    'directionalityltr', 'directionalityrtl', 'indent', '|',
                    'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
                    'link', 'unlink', 'anchor', '|',
                    'emotion', 'insertframe', 'insertcode', 'pagebreak', 'template', '|',
                    'horizontal', 'date', 'time', 'spechars', '|',
                    'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', '|',
                    'print', 'preview', 'searchreplace', 'drafts', 'help'
                ]]
            });
        </script>
    </body>
</html>
