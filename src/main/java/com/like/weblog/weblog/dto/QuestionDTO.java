package com.like.weblog.weblog.dto;

import com.like.weblog.weblog.model.Question;
import com.like.weblog.weblog.model.User;

public class QuestionDTO {
    private Question question;
    private User user;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
