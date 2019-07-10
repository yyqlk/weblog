package com.like.weblog.weblog.controller;

import com.like.weblog.weblog.dto.QuestionDTO;
import com.like.weblog.weblog.model.Question;
import com.like.weblog.weblog.model.User;
import com.like.weblog.weblog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/question/{id}")
    public String getQuestin(@PathVariable("id") String id, Model model, HttpServletRequest request){
        QuestionDTO questionDTO = questionService.findQuestionById(id);
        model.addAttribute("questionDTO",questionDTO);
        User user = (User)request.getAttribute("user");
        if (user!=null) {
            request.setAttribute("user", user.getName());
            request.setAttribute("userId", user.getAcountId());
        }
        questionService.incView(id);
        return "question";
    }


}
