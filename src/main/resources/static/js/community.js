/**
 * ajax
 * post方式提交数据
 */


function c_comment(e) {
    var commentId = e.getAttribute("data-id")
    var text_id = $("#replay"+commentId);
    var commentContent = text_id.val();
    var type=2;
    post(commentId,commentContent,type)


}
function q_comment() {
    var questionId = $("#question_id").val();
    var commentContent = $("#comment-content").val();
    var type =1;
    post(questionId,commentContent,type)
}


function post(parentId,commentContent,type) {
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType:"application/json",
        data:JSON.stringify({
            "parentId":parentId,
            "type":type,
            "content":commentContent
        }),
        success: function (result) {
            if(result.code == 2000) {
                // $("#comment-section").hide();
                window.location.reload()
            }else if(result.code==2002){
                var isAccepted = confirm(result.message);
                if(isAccepted){
                    window.open("https://github.com/login/oauth/authorize?client_id=e26e64f3b5e62950726c&redirect_uri=http://127.0.0.1:8080/callback&scope=user&state=1")
                    // window.localStorage.setItem("closeble",true)
                    window.location.reload()
                }
            }else{
                alert(result.message)
            }
        },
        dataType: "json"
    });
}




/**
 * js展开二级评论
 * @param e
 */
function collapseComment(e){
    var dataId = e.getAttribute("data-id");
    //获取元素 dataId是一级评论的id
    var commentId =$("#comment"+dataId);
    //判断展开还是关闭
    var mark = e.getAttribute("mark");
    if(mark){
        e.classList.remove("active");
        e.removeAttribute("mark")
        //点击关闭标签
         commentId.removeClass("in");
         var reply = "#comment"+dataId
        $("div").remove(reply);
    }else {
       //点击获取数据
        $.getJSON( "/comment/"+dataId, function(data) {
            var items = [];
            var commentBody = $("#comment-body-"+dataId);
            var data1 = data.data;
            $.each(data1,function(commentDTO,info) {
                var subcomment = $("<div>", {
                    "class": "col-xs-12 col-sm-12 col-md-12 col-lg-12  collapse-relpy",
                    html:items.push(info.comment.content)
                });
                items.join(subcomment);
            });
            $("<div>",{
                "class":"col-xs-12 col-sm-12 col-md-12 col-lg-12  collapse in",
                "id":"comment"+dataId,
                html:items.join("")
            }).appendTo(commentBody)
        });
        //点击展开二级评论
        // commentId.addClass("in");
        //保持常亮
        e.classList.add("active")
        //设置标记
         e.setAttribute("mark", true)
    }

}