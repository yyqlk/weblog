package com.like.weblog.weblog.service;

import com.like.weblog.weblog.dto.CommentCreateDTO;
import com.like.weblog.weblog.dto.CommentDTO;
import com.like.weblog.weblog.dto.ResultDTO;
import com.like.weblog.weblog.enums.CommentType;
import com.like.weblog.weblog.expection.CustomizeErrorCode;
import com.like.weblog.weblog.map.CommentMapper;
import com.like.weblog.weblog.map.QuestionMap;
import com.like.weblog.weblog.map.UserMapper;
import com.like.weblog.weblog.model.Comment;
import com.like.weblog.weblog.model.CommentExample;
import com.like.weblog.weblog.model.Question;
import com.like.weblog.weblog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    QuestionMap questionMap;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    NoticeService noticeService;

    @Transactional
    public ResultDTO createUpdate(Comment comment, CommentCreateDTO commentCreateDTO, HttpServletRequest request) {
        //获取用户
        User user = (User) request.getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        comment.setCommentorId(user.getAcountId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0);
        comment.setContent(commentCreateDTO.getContent());
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setType(commentCreateDTO.getType());
        if (commentCreateDTO.getType() == CommentType.QUESTION.getType()) {
            //回复问题
            Question question = questionMap.findQUestionById(commentCreateDTO.getParentId());
            //问题是否存在
            if (question.getId() == null) {
                return ResultDTO.errorOf(CustomizeErrorCode.QUESTION_NOT_FIND);
            }
            //写入数据库
            commentMapper.insert(comment);
            //创建通知
            noticeService.create(question,user,1);
            //更新问题回复数
            questionMap.updateQuestionComment(commentCreateDTO.getParentId());
            return ResultDTO.okOf(2000, "success");
        }
        if (commentCreateDTO.getType() == CommentType.COMMENT.getType()) {
            //回复评论
            CommentExample commentExample = new CommentExample();
            commentExample.createCriteria().andIdEqualTo(commentCreateDTO.getParentId());
            List<Comment> pareComments = commentMapper.selectByExample(commentExample);
            if (pareComments.size() == 0) {
                return ResultDTO.errorOf(CustomizeErrorCode.QUESTION_NOT_FIND);
            }
            commentMapper.insert(comment);
            //创建通知
            Comment pareComment = pareComments.get(0);
            Question pareQuestion = questionMap.findQUestionById(pareComment.getParentId());
            noticeService.create(comment,pareQuestion,user,2);

            //更新评论的子评论数（未开发）
            return ResultDTO.okOf(2000, "success");
        }
        return ResultDTO.okOf(00000,"未知错误");
    }



    public List<Comment> getCommentByParentId(Integer id){
        //根据id查出全部comment
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id).andTypeEqualTo(1);
        //设置排序
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if(comments.size()==0){
            return null;
        }
        return  comments;
    };

    public List<Comment> getCommentByComment(Integer id) {
        //根据id查出全部comment
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id).andTypeEqualTo(2);
        //设置排序
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if(comments.size()==0){
            return null;
        }
        return  comments;
    }

    public List<CommentDTO> getCommentDTO(List<Comment> comments) {
        //第一种方式，遍历coment，取出评论人id，查出user，setcomment，setuser，addlist
        //第二种方式
        //去重id避免同一个用户多次评论需要查多次
        Set<Long> usersId = comments.stream().map(comment -> comment.getCommentorId()).collect(Collectors.toSet());
        //把查出来的user存在map中直接通过key取值防止每一次都要遍历，
        HashMap<Long, User> userMAP = new HashMap<>();
        for(Long userid:usersId){
            User user = userMapper.getUserById(userid);
            userMAP.put(userid,user);
        }
        //赋值存入list
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setComment(comment);
            commentDTO.setUser(userMAP.get(comment.getCommentorId()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }


}
