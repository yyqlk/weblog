package com.like.weblog.weblog.dto;

import com.like.weblog.weblog.model.Comment;
import com.like.weblog.weblog.model.User;

public class CommentDTO {
    private Comment comment;
    private User user;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
