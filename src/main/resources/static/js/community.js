function post() {
    var questionId = $("#question_id").val();
    var commentContent = $("#comment-content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType:"application/json",
        data:JSON.stringify({
            "parentId":questionId,
            "type":1,
            "content":commentContent
        }),
        success: function (result) {
            if(result.code == 2000) {
                $("#comment-section").hide();
            }
            if(result.code==2002){
                var isAccepted = confirm(result.message);
                if(isAccepted){
                    window.open("https://github.com/login/oauth/authorize?client_id=e26e64f3b5e62950726c&redirect_uri=http://127.0.0.1:8080/callback&scope=user&state=1")
                    window.localStorage.setItem("closeble",true)
                }
            }
        },
        dataType: "json"
    });
}