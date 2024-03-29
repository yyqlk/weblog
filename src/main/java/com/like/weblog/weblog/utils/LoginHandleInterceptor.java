package com.like.weblog.weblog.utils;

import com.like.weblog.weblog.map.UserMapper;
import com.like.weblog.weblog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginHandleInterceptor implements HandlerInterceptor {
    @Autowired
    private  UserMapper userMapper;
    @Value("${github.root.url}")
    String path;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = new User();
        Cookie[] cookies = request.getCookies();
        request.setAttribute("path",path);
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    user = userMapper.getUser(cookie.getValue());
                    if (user.getToken() != null) {
                        request.setAttribute("user", user);
                        return true;
                    } else {
                        return true;
                    }
                }
            }
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
