package com.like.weblog.weblog.controller;

import com.like.weblog.weblog.map.QuestionMap;
import com.like.weblog.weblog.map.UserMapper;
import com.like.weblog.weblog.model.Question;
import com.like.weblog.weblog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    QuestionMap questionMap;
    @Autowired
    UserMapper userMapper;

    @GetMapping("publish")
    public String publish(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    User user = userMapper.getUser(cookie.getValue());
                    request.setAttribute("user",user.getName());
                    break;
                }
            }
        }
        return "publish";
    }

    @PostMapping("publish")
    public String create(@RequestParam(value = "title",required = false) String title,
                         @RequestParam(value = "description",required = false)String description,
                         @RequestParam(value = "tag",required = false) String tag,
                         HttpServletRequest request,
                         Model model){
        User user = null;
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    user = userMapper.getUser(cookie.getValue());
                    request.setAttribute("user",user.getName());
                    break;
                }
            }
            if (user==null){
                request.setAttribute("publish_error","用户未登录");
                return "publish";
            }
            if(title==null){
                request.setAttribute("publish_error","title不能为空");
                return "publish";
            }
            if(description==null){
                request.setAttribute("publish_error","description不能为空");
                return "publish";
            }
            if(tag==null){
                request.setAttribute("publish_error","tag不能为空");
                return "publish";
            }
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getAcountId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setLikeCount(0);
        question.setCommentCount(0);
        question.setViewCount(0);
        questionMap.create(question);
        return "index";
    }
}
