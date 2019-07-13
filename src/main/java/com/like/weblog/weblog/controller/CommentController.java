package com.like.weblog.weblog.controller;

import com.like.weblog.weblog.dto.CommentCreateDTO;
import com.like.weblog.weblog.dto.CommentDTO;
import com.like.weblog.weblog.dto.ResultDTO;
import com.like.weblog.weblog.model.Comment;
import com.like.weblog.weblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;


    @ResponseBody
    @PostMapping("/comment")
    public Object comment2question(@RequestBody() CommentCreateDTO commentCreateDTO, HttpServletRequest request){

        Comment comment = new Comment();
        ResultDTO resultDTO = commentService.createUpdate(comment, commentCreateDTO,request);
        return resultDTO;
    }

    @ResponseBody
    @GetMapping("/comment/{id}")
    public Object comment2comment(@PathVariable("id") Integer id){
        List<Comment> comments = commentService.getCommentByComment(id);
        List<CommentDTO> commentDTOS = commentService.getCommentDTO(comments);
        ResultDTO<List<CommentDTO>> resultDTO = ResultDTO.okOf(2000, "success", commentDTOS);
        return  resultDTO;
    }

}
