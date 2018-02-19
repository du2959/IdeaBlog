function saveBlog() {
    if(validate()) {
        $.ajax({
            type:"post",
            url:"saveBlog.do?blogtitle=" + $("#blogtitle").val() + "&blogtag=" + $("#blogtag").val() + "&blogcontent=" + encodeURIComponent(UE.getEditor('editor').getContent()),
            async:true,
            success:function(data){
                $("#status").html(data);
                console.log("success");
                reset();
            },
            error:function(){
                console.log("error");
            }
        });
    }
}
function postBlog() {
    if(validate()) {
        $.ajax({
            type:"post",
            url:"postBlog.do?blogtitle=" + $("#blogtitle").val() + "&blogtag=" + $("#blogtag").val() + "&blogcontent=" + encodeURIComponent(UE.getEditor('editor').getContent()),
            async:true,
            success:function(data){
                $("#status").html(data);
                console.log("success");
                reset();
            },
            error:function(){
                console.log("error");
            }
        });
    }
}
function validate(){
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
function reset() {
    document.getElementById("blogtitle").value = "";
    document.getElementById("blogtag").value = "";
    UE.getEditor('editor').setContent("");
}