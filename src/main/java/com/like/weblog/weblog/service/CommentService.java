package com.like.weblog.weblog.service;

import com.like.weblog.weblog.dto.CommentDTO;
import com.like.weblog.weblog.dto.ResultDTO;
import com.like.weblog.weblog.enums.CommentType;
import com.like.weblog.weblog.expection.CustomizeErrorCode;
import com.like.weblog.weblog.map.CommentMapper;
import com.like.weblog.weblog.map.QuestionMap;
import com.like.weblog.weblog.model.Comment;
import com.like.weblog.weblog.model.CommentExample;
import com.like.weblog.weblog.model.Question;
import com.like.weblog.weblog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    QuestionMap questionMap;
    @Autowired
    CommentMapper commentMapper;

    @Transactional
    public ResultDTO createUpdate(Comment comment, CommentDTO commentDTO, HttpServletRequest request) {
        //获取用户
        User user = (User) request.getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        comment.setCommentorId(user.getAcountId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0);
        comment.setContent(commentDTO.getContent());
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        if (commentDTO.getType() == CommentType.QUESTION.getType()) {
            //回复问题
            Question question = questionMap.findQUestionById(commentDTO.getParentId());
            //问题是否存在
            if (question.getId() == null) {
                return ResultDTO.errorOf(CustomizeErrorCode.QUESTION_NOT_FIND);
            }
            //写入数据库
            commentMapper.insert(comment);
            questionMap.updateQuestionComment(commentDTO.getParentId());
            return ResultDTO.errorOf(2000, "success");
        }
        if (commentDTO.getType() == CommentType.COMMENT.getType()) {
            //回复评论
            CommentExample commentExample = new CommentExample();
            commentExample.createCriteria().andIdEqualTo(commentDTO.getParentId());
            List<Comment> pareComment = commentMapper.selectByExample(commentExample);
            if (pareComment.size() == 0) {
                return ResultDTO.errorOf(CustomizeErrorCode.QUESTION_NOT_FIND);
            }
            return ResultDTO.errorOf(2000, "success");
        }
        return ResultDTO.errorOf(00000,"未知错误");
    }
}
