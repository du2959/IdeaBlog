$(document).ready(function () {
    $.ajax({
        type:"post",
        url:"showBlogById.do",
        dataType:"json",
        async:true,
        success:function(data){
            console.log(data);
            $("#blogtitle").val(data.title);
            $("#blogtag").val(data.tag);
            UE.getEditor('editor').execCommand('insertHtml', data.content);
        },
        error:function(){
            console.log("error");
        }
    });
    $("#saveblog").click(function () {
        if(validate()) {
            $.ajax({
                type:"post",
                url:"updateBlog.do?blogtitle=" + $("#blogtitle").val() + "&blogtag=" + $("#blogtag").val() + "&blogcontent=" + encodeURIComponent(UE.getEditor('editor').getContent()),
                async:true,
                success:function(data){
                    $("#status").html("保存成功！");
                    console.log("success");
                },
                error:function(){
                    console.log("error");
                }
            });
        }
    });
    $("#exit").click(function () {
        window.opener=null;
        window.open('','_self');
        window.close();
    });
});

function validate() {
    if(document.getElementById("blogtitle").value == ""){
        alert("标题不能为空！");
        document.getElementById("blogtitle").focus();
        return false;
    }
    if(!UE.getEditor('editor').hasContents()) {
        alert("亲，写点东西再提交吧！");
        document.getElementById("editor").focus();
        return false;
    }
    return true;
}
