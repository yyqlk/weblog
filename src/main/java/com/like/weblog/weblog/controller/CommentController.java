package com.like.weblog.weblog.controller;

import com.like.weblog.weblog.dto.CommentDTO;
import com.like.weblog.weblog.map.CommentMap;
import com.like.weblog.weblog.map.QuestionMap;
import com.like.weblog.weblog.model.Comment;
import com.like.weblog.weblog.model.Question;
import com.like.weblog.weblog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired
    QuestionMap questionMap;

    @Autowired
    CommentMap commentMap;

    @ResponseBody
    @PostMapping("/comment")
    public Object comment(@RequestBody() CommentDTO commentDTO, Model model, HttpServletRequest request){
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        User user =(User) request.getAttribute("user");
       if(user!=null) {
           comment.setCommentorId(user.getAcountId());
       } else{
            model.addAttribute("message","登陆以后才能评论");
        }
        comment.setGmtCreate(System.currentTimeMillis());
       comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0);
        Integer successTag = commentMap.create(comment);
        if (successTag==1) {
            questionMap.updateQuestionComment(commentDTO.getParentId());
        }else{
            model.addAttribute("message","评论失败");
        }
        return null;
    }
}
