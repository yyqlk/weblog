package com.like.weblog.weblog.controller;

import com.like.weblog.weblog.map.UserMapper;
import com.like.weblog.weblog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    UserMapper userMapper;
    @RequestMapping("index")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("token")){
                User user = userMapper.getUser(cookie.getValue());
                request.setAttribute("user",user.getName());
                return "index";
            }
        }
        return "index";
    }
}
