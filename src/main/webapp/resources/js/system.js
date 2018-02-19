$(document).ready(function() {
    $(function () {//初始页面
        $("#sysLogManage").click();
    })
    $("#sysLogManage").click(function () {
        $("#sysLogManage").addClass("active");
        $("#pubBlogManage").removeClass("active");
        $("#userManage").removeClass("active");
        $("#fileManage").removeClass("active");
        $("#table").bootstrapTable('destroy');
        $("#table").bootstrapTable({ // 对应table标签的id
            url: "showAllLogs.do",                           //请求后台的URL（*）
            method: 'post',                     //请求方式（*）
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                    //是否启用排序
            sortOrder: "asc",                   //排序方式
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [20, 50, 100],            //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            columns: [
                {
                    field: 'id', // 返回json数据中的id
                    title: '日志ID', // 表格表头显示文字
                    align: 'center', // 左右居中
                    valign: 'middle', // 上下居中
                    // visible: false // 设置隐藏
                },
                {
                    field: 'userId',
                    title: '用户ID',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'username',
                    title: '用户名',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'role',
                    title: '用户身份',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        var text = "普通用户";
                        if (value == 2) {
                            text = "管理员";
                        }
                        return text;
                    }
                },
                {
                    field: 'method',
                    title: '方法',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'description',
                    title: '描述',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'reqIp',
                    title: '请求IP',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'opTime',
                    title: '操作时间',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        var text = new Date(value).format("yyyy-MM-dd hh:mm:ss");
                        return text;
                    }
                }, {
                    title: "操作",
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        return "<button class='btn btn-danger' onclick='deleteLog(" + row.id + ");'>删除</button>";
                    }
                }
            ],
            onLoadSuccess: function () {  //加载成功时执行
                console.info("日志列表加载成功");
            },
            onLoadError: function () {  //加载失败时执行
                console.info("加载数据失败");
            }
        })
    });
    $("#pubBlogManage").click(function () {
        $("#pubBlogManage").addClass("active");
        $("#sysLogManage").removeClass("active");
        $("#userManage").removeClass("active");
        $("#fileManage").removeClass("active");
        $("#table").bootstrapTable('destroy');
        $("#table").bootstrapTable({ // 对应table标签的id
            url: "showNewBlogs.do",                           //请求后台的URL（*）
            method: 'post',                     //请求方式（*）
            // toolbar: toolbar,                   //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                    //是否启用排序
            sortOrder: "asc",                   //排序方式
            // queryParams: queryParams,           //传递参数（*），这里应该返回一个object，即形如{param1:val1,param2:val2}
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [20, 50, 100],            //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            // strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数决定表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            columns: [
                // {
                //     checkbox: true, // 显示一个勾选框
                //     align: 'center' // 居中显示
                // },
                {
                    field: 'id', // 返回json数据中的id
                    title: '博客ID', // 表格表头显示文字
                    align: 'center', // 左右居中
                    valign: 'middle', // 上下居中
                    // visible: false // 设置隐藏
                },
                {
                    field: 'userId',
                    title: '作者ID',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'username',
                    title: '用户名',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'title',
                    title: '标题',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'tag',
                    title: '标签',
                    align: 'center',
                    valign: 'middle'
                },
                // {
                //     field: 'content',
                //     title: '内容',
                //     align: 'center',
                //     valign: 'middle'
                // },
                {
                    field: 'views',
                    title: '浏览',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'collects',
                    title: '收藏',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'editTime',
                    title: '更新时间',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        var text = new Date(value).format("yyyy-MM-dd hh:mm:ss");
                        return text;
                    }
                }, {
                    field: 'status',
                    title: '发布状态',
                    align: 'center',
                    valign: 'middle',
                    width: 120,
                    formatter: function (value, row, index) { // 单元格格式化函数
                        var text = '-';
                        if (value == 1) {
                            text = "<button class='btn btn-warning' disabled>私密发布</button>";
                        } else if (value == 2) {
                            text = "<button class='btn btn-info' disabled>公开发布</button>";
                        }
                        return text;
                    }
                }, {
                    title: "操作",
                    align: 'center',
                    valign: 'middle',
                    width: 160, // 定义列的宽度，单位为像素px
                    formatter: function (value, row, index) {
                        return "<button class='btn btn-success' onclick='blogPage(" + row.id + ");'>查看</button>&nbsp;&nbsp;<button class='btn btn-danger' onclick='deleteBlog(" + row.id + ");'>删除</button>";
                    }
                }
            ],
            onLoadSuccess: function () {  //加载成功时执行
                console.info("博客列表加载成功");
            },
            onLoadError: function () {  //加载失败时执行
                console.info("加载数据失败");
            }
        })
    });
    $("#userManage").click(function () {
        $("#userManage").addClass("active");
        $("#sysLogManage").removeClass("active");
        $("#pubBlogManage").removeClass("active");
        $("#fileManage").removeClass("active");
        $("#table").bootstrapTable('destroy');
        $("#table").bootstrapTable({ // 对应table标签的id
            url: "showAllUsers.do",                           //请求后台的URL（*）
            method: 'post',                     //请求方式（*）
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                    //是否启用排序
            sortOrder: "asc",                   //排序方式
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [20, 50, 100],            //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            columns: [
                {
                    field: 'id', // 返回json数据中的id
                    title: '用户ID', // 表格表头显示文字
                    align: 'center', // 左右居中
                    valign: 'middle', // 上下居中
                    // visible: false // 设置隐藏
                },
                {
                    field: 'username',
                    title: '用户名',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'role',
                    title: '用户身份',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        var text = "-";
                        if (value == 1) {
                            text = "普通用户";
                        } else if (value == 2) {
                            text = "管理员";
                        }
                        return text;
                    }
                },
                {
                    field: 'email',
                    title: '邮箱',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'regTime',
                    title: '注册时间',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        var text = new Date(value).format("yyyy-MM-dd hh:mm:ss");
                        return text;
                    }
                },
                {
                    field: 'status',
                    title: '账号状态',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        var text = "-";
                        if (row.role == 1) {
                            if (value == 1) {
                                text = "<button class='btn btn-info' onclick='switchUserStatus(" + row.id + "," + row.status + ");'>正常</button>";
                            } else if (value == 2) {
                                text = "<button class='btn btn-warning' onclick='switchUserStatus(" + row.id + "," + row.status + ");'>冻结</button>";
                            }
                        }
                        return text;
                    }
                },
                {
                    title: "操作",
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        var text = "-";
                        if (row.role == 1) {
                            text = "<button class='btn btn-danger' onclick='deleteUser(" + row.id + ");'>删除</button>";
                        }
                        return text;
                    }
                }
            ],
            onLoadSuccess: function () {  //加载成功时执行
                console.info("用户列表加载成功");
            },
            onLoadError: function () {  //加载失败时执行
                console.info("加载数据失败");
            }
        })
    });
    $("#fileManage").click(function () {
        $("#fileManage").addClass("active");
        $("#sysLogManage").removeClass("active");
        $("#pubBlogManage").removeClass("active");
        $("#userManage").removeClass("active");
        $("#table").bootstrapTable('destroy');
        $("#table").bootstrapTable({ // 对应table标签的id
            url: "showAllFiles.do",                           //请求后台的URL（*）
            method: 'post',                     //请求方式（*）
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                    //是否启用排序
            sortOrder: "asc",                   //排序方式
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [20, 50, 100],            //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            columns: [
                {
                    field: 'id', // 返回json数据中的id
                    title: '文件ID', // 表格表头显示文字
                    align: 'center', // 左右居中
                    valign: 'middle', // 上下居中
                },
                {
                    field: 'userId',
                    title: '所有者ID',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'username',
                    title: '用户名',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'fileName',
                    title: '文件名',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        if (value.length > 30) {
                            return value.slice(0, 25) + "..." + value.slice(value.length - 5);
                        }
                        else {
                            return value;
                        }
                    }
                },
                {
                    field: 'fileSize',
                    title: '大小',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: 'upTime',
                    title: '上传时间',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        var text = new Date(value).format("yyyy-MM-dd hh:mm:ss");
                        return text;
                    }
                },
                {
                    field: 'status',
                    title: '状态',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) { // 单元格格式化函数
                        var text = '-';
                        if (value == 1) {
                            text = "<button class='btn btn-warning' disabled>私有</button>";
                        } else if (value == 2) {
                            text = "<button class='btn btn-info' disabled>共享</button>";
                        }
                        return text;
                    }
                },
                {
                    title: "操作",
                    align: 'center',
                    valign: 'middle',
                    width: 160, // 定义列的宽度，单位为像素px
                    formatter: function (value, row, index) {
                        return "<button class='btn btn-success' onclick='downloadFile(" + row.id + ");'>下载</button>&nbsp;&nbsp;<button class='btn btn-danger' onclick='deleteFile(" + row.id + ");'>删除</button>";
                    }
                }
            ],
            onLoadSuccess: function () {  //加载成功时执行
                console.info("文件列表加载成功");
            },
            onLoadError: function () {  //加载失败时执行
                console.info("加载数据失败");
            }
        })
    });
});

function deleteLog(logId) {
    if(confirm("确认删除该日志？（不可恢复）ID：" + logId)){
        $.ajax({
            type:"post",
            url:"deleteLog.do?logId=" + logId,
            async:true,
            success:function(data){
                console.log(data);
                $("#table").bootstrapTable('refresh', {url:'showAllLogs.do'});
            },
            error:function(){
                console.log("error");
            }
        });
    }
}
function blogPage(blogId){
    window.open("blogPage?blogId=" + blogId, "_blank");
}
function deleteBlog(blogId){
    if(confirm("确认删除该博客？（不可恢复）ID：" + blogId)){
        $.ajax({
            type:"post",
            url:"deleteBlog.do?blogId=" + blogId,
            async:true,
            success:function(data){
                console.log(data);
                $("#table").bootstrapTable('refresh', {url:'showNewBlogs.do'});
            },
            error:function(){
                console.log("error");
            }
        });
    }
}
function switchUserStatus(userId, status){
    if(confirm("确认切换用户账号状态？")){
        $.ajax({
            type:"post",
            url:"switchUserStatus.do?userId=" + userId + "&status=" +(status == 1 ? 2 : 1),
            async:true,
            success:function(data){
                console.log(data);
                $("#table").bootstrapTable('refresh', {url:'showAllUsers.do'});
            },
            error:function(){
                console.log("error");
            }
        });
    }
}
function deleteUser(userId){
    if(confirm("确认删除该用户？（不可恢复）ID：" + userId)){
        $.ajax({
            type:"post",
            url:"deleteUser.do?userId=" + userId,
            async:true,
            success:function(data){
                console.log(data);
                $("#table").bootstrapTable('refresh', {url:'showAllUsers.do'});
            },
            error:function(){
                console.log("error");
            }
        });
    }
}
function downloadFile(fileId){
    window.open("downloadFile.do?fileId=" + fileId, "_blank");
}
function deleteFile(fileId){
    if(confirm("确认删除该文件？（不可恢复）")){
        $.ajax({
            type:"post",
            url:"deleteFile.do?fileId=" + fileId,
            async:true,
            success:function(data){
                console.log(data);
                $("#table").bootstrapTable('refresh', {url:'showAllFiles.do'});
            },
            error:function(){
                console.log("error");
            }
        });
    }
}