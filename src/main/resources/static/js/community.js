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
    //获取元素一级评论
    var comment =$("#comment"+dataId);
    //判断展开还是关闭
    var mark = e.getAttribute("mark");
    if(mark){
        e.classList.remove("active");
        e.removeAttribute("mark");
        //点击关闭标签
         comment.removeClass("in");
         //然后把绘制的element删除
         var subreply = "#subcomment"+dataId;
         $("div").remove(subreply);
    }else {
       //点击获取数据
        var subcomment = $("<div>", {
            "class": "col-xs-12 col-sm-12 col-md-12 col-lg-12  collapse-relpy",
            "id":"subcomment"+dataId,
        });
        $.getJSON( "/comment/"+dataId, function(data) {
            var commentData = data.data.reverse();
             $.each(commentData,function(commentDTO,info) {
                 //绘制media-left
                 var mediaImg=$("<img>", {
                     "class": "media-object img-rounded",
                     "src":info.user.avatarUrl,
                 });
                 var mediaLeft = $("<div>", {
                     "class": "media-left",
                 }).append(mediaImg);
                 //绘制media-body
                 var mediaHead = $("<h5>", {
                     "class": "media-heading creator-min",
                     html: info.user.name
                 });
                 var mediaContent = $("<div>", {
                     "class": "media-body",
                     html: info.comment.content
                 });
                 var mediaSpan = $("<span>", {
                     "class": "glyphicon glyphicon-thumbs-up icon-img",
                 });

                 var mediaBody = $("<div>", {
                     "class": "media-body",
                 });
                 mediaBody.append(mediaHead);
                 mediaBody.append(mediaContent);
                 mediaBody.append(mediaSpan);

                 var media = $("<div>", {
                     "class": "media",
                 });
                 media.append(mediaLeft);
                 media.append(mediaBody);

                // comment.append(subcomment) //加到了最后面
                 subcomment.prepend(media);
                comment.prepend(subcomment)
            });
            });

        //点击展开二级评论
        comment.addClass("in");
        //保持常亮
        e.classList.add("active");
        //设置标记
        e.setAttribute("mark", true)
    }

}