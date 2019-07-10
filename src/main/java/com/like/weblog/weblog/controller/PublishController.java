package com.like.weblog.weblog.controller;

import com.like.weblog.weblog.dto.QuestionDTO;
import com.like.weblog.weblog.map.QuestionMap;
import com.like.weblog.weblog.map.UserMapper;
import com.like.weblog.weblog.model.Question;
import com.like.weblog.weblog.model.User;
import com.like.weblog.weblog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    QuestionMap questionMap;
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;

    @GetMapping("/publish")
    public String publish(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        if (user != null) {
            request.setAttribute("user", user.getName());
        }
        return "publish";
    }

    @PostMapping("/publish")
    public String create(@RequestParam(value = "title", required = false) String title,
                         @RequestParam(value = "description", required = false) String description,
                         @RequestParam(value = "tag", required = false) String tag,
                         @RequestParam(value = "id",required = false) String id,
                         HttpServletRequest request,
                         Model model) {

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        User user = (User) request.getAttribute("user");
        if (user != null) {
            request.setAttribute("user", user.getName());
        }
        if (title == null || title == "") {
            request.setAttribute("publish_error", "title不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            request.setAttribute("publish_error", "description不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            request.setAttribute("publish_error", "tag不能为空");
            return "publish";
        }

        if(id != null){
            long modifiedTime = System.currentTimeMillis();
            questionService.updateQuestion(tag, title, description, id, modifiedTime);
        }else {
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getAcountId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setLikeCount(1);
            question.setCommentCount(1);
            question.setViewCount(1);
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMap.create(question);
        }
        return "redirect:/";
    }

}
