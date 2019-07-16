package com.like.weblog.weblog.controller;

import com.like.weblog.weblog.dto.PageQuestionDTO;
import com.like.weblog.weblog.map.UserMapper;
import com.like.weblog.weblog.model.User;
import com.like.weblog.weblog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;
    @RequestMapping("/")
    public String index(HttpServletRequest request ,Model model,
                        @RequestParam(value = "page",defaultValue = "1") Integer page,
                        @RequestParam(value = "size",defaultValue = "7") Integer size){
        User user = (User)request.getAttribute("user");
        if (user!=null) {
            request.setAttribute("user", user.getName());
        }
        PageQuestionDTO questionListDTO = questionService.findPageQuestionDTO(page, size);
        model.addAttribute("pageQuestionsDTO",questionListDTO);
        return "index";
    }
}
