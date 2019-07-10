package com.like.weblog.weblog.advice;

import com.like.weblog.weblog.expection.CustomizeException;
import com.like.weblog.weblog.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(Throwable ex,Model model,HttpServletRequest request) {
        if(ex instanceof CustomizeException){
            model.addAttribute("message",ex.getMessage());
        }else{
            model.addAttribute("message","服务器出错，请稍后操作或联系管理员");
        }
        User user = (User) request.getAttribute("user");
        if (user != null) {
            request.setAttribute("user", user.getName());
        }
        return new ModelAndView("error");
    }

}
