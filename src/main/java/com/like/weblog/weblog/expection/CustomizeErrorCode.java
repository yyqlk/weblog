package com.like.weblog.weblog.expection;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FIND("你找的问题不在了，换一个问题吧");
    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
