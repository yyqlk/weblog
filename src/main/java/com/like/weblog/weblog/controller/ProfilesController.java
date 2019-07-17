package com.like.weblog.weblog.controller;

import com.like.weblog.weblog.dto.PageNoticeDTO;
import com.like.weblog.weblog.dto.PageQuestionDTO;
import com.like.weblog.weblog.map.UserMapper;
import com.like.weblog.weblog.model.User;
import com.like.weblog.weblog.service.NoticeService;
import com.like.weblog.weblog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfilesController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;
    @Autowired
    NoticeService noticeService;

    @GetMapping("profiles/{profilesViews}")
    public String getQuestins(@PathVariable(name = "profilesViews") String profilesViews, Model model, HttpServletRequest request,
                              @RequestParam(value = "page",defaultValue = "1") Integer page,
                              @RequestParam(value = "size",defaultValue = "7") Integer size) {
        if (profilesViews.equals("question")) {
            model.addAttribute("profilesViews", "我的问题");
            model.addAttribute("section", "question");
        }
        if (profilesViews.equals("reversion")) {
            model.addAttribute("profilesViews", "最新回复");
            model.addAttribute("section", "reversion");
        }
        User user = (User) request.getAttribute("user");
        if(user!=null) {
            request.setAttribute("user", user.getName());
            model.addAttribute("noticeCount",noticeService.countNotice(user.getAcountId()));
        }
        //我的问题
        PageQuestionDTO questionListDTO = questionService.findQuestionDTOByCreater(page, size, user.getAcountId());
        model.addAttribute("pageQuestionsDTO",questionListDTO);
        //最新回复
        PageNoticeDTO pageNoticeDTO = noticeService.getNoticeByReceiver(user, page, size);
        model.addAttribute("pageNoticeDTO",pageNoticeDTO);
        return "profiles";
    }



}
