package com.like.weblog.weblog.controller;

import com.like.weblog.weblog.dto.QuestionDTO;
import com.like.weblog.weblog.enums.Tag;
import com.like.weblog.weblog.map.QuestionMap;
import com.like.weblog.weblog.map.UserMapper;
import com.like.weblog.weblog.model.Question;
import com.like.weblog.weblog.model.User;
import com.like.weblog.weblog.service.QuestionService;
import com.like.weblog.weblog.service.TagService;
import com.sun.xml.internal.ws.runtime.config.TubelineFeatureReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
public class PublishController {

    @Autowired
    QuestionMap questionMap;
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;
    @Autowired
    TagService tagService;

    @GetMapping("/publish")
    public String publish(HttpServletRequest request,Model model) {
        User user = (User) request.getAttribute("user");
        if (user != null) {
            request.setAttribute("user", user.getName());
        }
        model.addAttribute("pTags", Tag.PROGRAMMING.gettags());
        model.addAttribute("fTags", Tag.FRAME.gettags());
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
        boolean isContain = TagService.isContain(tag);
        if(!isContain){
            request.setAttribute("publish_error", "暂不支持自定义标签,请从下方标签中选择");
            model.addAttribute("pTags", Tag.PROGRAMMING.gettags());
            model.addAttribute("fTags", Tag.FRAME.gettags());
            return "publish";
        }

        if (title == null || title == "") {
            request.setAttribute("publish_error", "title不能为空");
            model.addAttribute("pTags", Tag.PROGRAMMING.gettags());
            model.addAttribute("fTags", Tag.FRAME.gettags());
            return "publish";
        }
        if (description == null || description == "") {
            request.setAttribute("publish_error", "description不能为空");
            model.addAttribute("pTags", Tag.PROGRAMMING.gettags());
            model.addAttribute("fTags", Tag.FRAME.gettags());
            return "publish";
        }
        if (tag == null || tag == "") {
            request.setAttribute("publish_error", "tag不能为空");
            model.addAttribute("pTags", Tag.PROGRAMMING.gettags());
            model.addAttribute("fTags", Tag.FRAME.gettags());
            return "publish";
        }

        if(id != null && id != ""){
            long modifiedTime = System.currentTimeMillis();
            questionService.updateQuestion(tag,title,description,id,modifiedTime);
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

    @GetMapping("/publish/{id}")
    public String getQuestin(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
        QuestionDTO questionDTO = questionService.findQuestionById(id);
        String title = questionDTO.getQuestion().getTitle();
        String description =questionDTO.getQuestion().getDescription();
        String tag = questionDTO.getQuestion().getTag();
        model.addAttribute("tag",tag);
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("id",id);
        User user = (User) request.getAttribute("user");
        request.setAttribute("user", user.getName());
        request.setAttribute("userId", user.getAcountId());
        model.addAttribute("pTags", Tag.PROGRAMMING.gettags());
        model.addAttribute("fTags", Tag.FRAME.gettags());
        return "publish";
    }

}
