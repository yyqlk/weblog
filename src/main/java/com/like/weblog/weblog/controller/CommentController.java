package com.like.weblog.weblog.controller;

import com.like.weblog.weblog.dto.CommentDTO;
import com.like.weblog.weblog.dto.ResultDTO;
import com.like.weblog.weblog.map.CommentMap;
import com.like.weblog.weblog.map.QuestionMap;
import com.like.weblog.weblog.model.Comment;
import com.like.weblog.weblog.model.Question;
import com.like.weblog.weblog.model.User;
import com.like.weblog.weblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @ResponseBody
    @PostMapping("/comment")
    public Object comment(@RequestBody() CommentDTO commentDTO, HttpServletRequest request){

        Comment comment = new Comment();
        ResultDTO resultDTO = commentService.createUpdate(comment,commentDTO,request);
        return resultDTO;
    }
}
