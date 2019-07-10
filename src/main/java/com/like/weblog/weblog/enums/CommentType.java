package com.like.weblog.weblog.enums;

public enum CommentType {
    QUESTION(1),
    COMMENT(2);
    private Integer type;
    CommentType(Integer type){
        this.type=type;
    }

    public Integer getType() {
        return type;
    }
}
