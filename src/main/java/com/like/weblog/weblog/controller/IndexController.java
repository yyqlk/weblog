package com.like.weblog.weblog.controller;

import com.like.weblog.weblog.dto.PageQuestionDTO;
import com.like.weblog.weblog.map.NoticeMapper;
import com.like.weblog.weblog.map.UserMapper;
import com.like.weblog.weblog.model.User;
import com.like.weblog.weblog.service.NoticeService;
import com.like.weblog.weblog.service.QuestionService;
import org.omg.PortableServer.ServantActivator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.ListUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class IndexController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;

    @Autowired
    NoticeService noticeService;
    @RequestMapping("/")
    public String index(HttpServletRequest request ,Model model,
                        @RequestParam(value = "page",defaultValue = "1") Integer page,
                        @RequestParam(value = "size",defaultValue = "7") Integer size,
                        @RequestParam(value = "search",required = false) String search){
        User user = (User)request.getAttribute("user");
        if (user!=null) {
            //把uesr传到页面
            request.setAttribute("userName", user.getName());
            //把用户的通知传到也页面
            model.addAttribute("noticeCount",noticeService.countNotice(user.getAcountId()));
        }
        if (search!=null&&search!=""){
            //处理search
            String[] searchs = search.split(" ");
            search = Arrays.stream(searchs).collect(Collectors.joining("|"));
            PageQuestionDTO questionListDTO = questionService.findPageQuestionDTO(page, size,search);
            model.addAttribute("pageQuestionsDTO",questionListDTO);
        }else {
            PageQuestionDTO questionListDTO = questionService.findPageQuestionDTO(page, size);
            model.addAttribute("pageQuestionsDTO",questionListDTO);
        }
        return "index";
    }
}
