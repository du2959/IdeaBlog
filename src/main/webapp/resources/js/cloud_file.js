$(document).ready(function(){
    $("#files").fileinput({
        language: 'zh',
        uploadUrl: 'uploadFiles.do',
        maxFileCount : 10, //表示允许同时上传的最大文件个数
        maxFileSize: 102400,//100MB，单位为KB，如果为0表示不限制文件大小
        maxFilePreviewSize: 10240,//10MB，最大预览大小
        browseOnZoneClick: true//点击打开目录
    }).on("fileuploaded", function (event, data, previewId, index){
        $("#filetable").bootstrapTable('refresh', {url:'showUserFiles.do'});
    });
    $("#filetable").bootstrapTable({ // 对应table标签的id
        url: "showUserFiles.do",                           //请求后台的URL（*）
        method: 'post',                     //请求方式（*）
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                    //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [20, 50, 100],            //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        columns: [
            {
                field: 'id', // 返回json数据中的id
                title: '文件ID', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle', // 上下居中
                visible: false // 设置隐藏
            },
            {
                field: 'fileName',
                title: '文件名',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index){
                    if(value.length > 30) {
                        return value.slice(0, 25) + "..." + value.slice(value.length-5);
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
                formatter: function (value, row, index){
                    var text = new Date(value).format("yyyy-MM-dd hh:mm:ss");
                    return text;
                }
            },
            {
                field: 'status',
                title: '状态',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index){ // 单元格格式化函数
                    var text = '-';
                    if (value == 1) {
                        text = "<button class='btn btn-warning' onclick='switchFileStatus(" + row.id + "," + row.status + ");'>私有</button>";
                    } else if (value == 2) {
                        text = "<button class='btn btn-info' onclick='switchFileStatus(" + row.id + "," + row.status + ");'>共享</button>";
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
        onLoadSuccess: function(){  //加载成功时执行
            console.info("文件列表加载成功");
        },
        onLoadError: function(){  //加载失败时执行
            console.info("加载数据失败");
        }
    })
});
function switchFileStatus(fileId, status){
    if(confirm("确认切换文件状态？")){
        $.ajax({
            type:"post",
            url:"switchFileStatus.do?fileId=" + fileId + "&status=" +(status == 1 ? 2 : 1),
            async:true,
            success:function(data){
                console.log(data);
                $("#filetable").bootstrapTable('refresh', {url:'showUserFiles.do'});
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
                $("#filetable").bootstrapTable('refresh', {url:'showUserFiles.do'});
            },
            error:function(){
                console.log("error");
            }
        });
    }
}
