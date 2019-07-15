package com.like.weblog.weblog.controller;

import com.like.weblog.weblog.dto.CommentDTO;
import com.like.weblog.weblog.dto.QuestionDTO;
import com.like.weblog.weblog.enums.Tag;
import com.like.weblog.weblog.map.CommentMapper;
import com.like.weblog.weblog.model.Comment;
import com.like.weblog.weblog.model.Question;
import com.like.weblog.weblog.model.User;
import com.like.weblog.weblog.service.CommentService;
import com.like.weblog.weblog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    CommentService commentService;
    @Autowired
    CommentMapper commentMapper;

    @GetMapping("/question/{id}")
    public String getQuestin(@PathVariable("id") Integer id, Model model, HttpServletRequest request){
        //获取登陆用户
        User user = (User)request.getAttribute("user");
        if (user!=null) {
            request.setAttribute("user", user.getName());
            request.setAttribute("userId", user.getAcountId());
        }
        //获取问题详情
        QuestionDTO questionDTO = questionService.findQuestionById(id);
        model.addAttribute("questionDTO",questionDTO);
        //获取问题评论详情
        List<Comment> comments = commentService.getCommentByParentId(questionDTO.getQuestion().getId());
        if(comments!=null) {
            List<CommentDTO> commentDTOS = commentService.getCommentDTO(comments);
            model.addAttribute("commentDTOs", commentDTOS);
        }
        //获取二级评论的方法，第一种，直接从数据库中取出来放到页面，第二种，通过ajax点击再从数据库中取.，本次通过第二种
        questionService.incView(id);
        //获取相关问题
        List<Question> relatedQuestions = questionService.findQuestionByTag(id);
        model.addAttribute("relatedQuestions", relatedQuestions);
        model.addAttribute("pTags", Tag.PROGRAMMING.gettags());
        model.addAttribute("fTags", Tag.FRAME.gettags());
        return "question";
    }


}
