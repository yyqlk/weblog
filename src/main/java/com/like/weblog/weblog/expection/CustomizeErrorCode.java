package com.like.weblog.weblog.expection;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FIND(2001, "你找的问题不在了，换一个问题吧"),
    NO_LOGIN(2002,"请登录后评论"),
    COMMENT_NOT_FIND(2003,"你评论的评论不在了，不能评论了鸭");


    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
