package com.like.weblog.weblog.service;


import com.like.weblog.weblog.dto.PageNoticeDTO;
import com.like.weblog.weblog.map.NoticeMapper;
import com.like.weblog.weblog.map.UserMapper;
import com.like.weblog.weblog.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    @Autowired
    NoticeMapper noticeMapper;

    @Autowired
    UserMapper userMapper;

    public PageNoticeDTO getNoticeByReceiver(User user ,Integer page, Integer size) {
        NoticeExample noticeExample = new NoticeExample();
        noticeExample.createCriteria().andReceiverIdEqualTo(user.getAcountId());
        List<Notice> notices = noticeMapper.selectByExample(noticeExample);
        PageNoticeDTO pageNoticeDTO = new PageNoticeDTO();
        //设置数据
        pageNoticeDTO.setNotices(notices);
        //设置分页
        Long count = noticeMapper.countByExample(noticeExample);
        pageNoticeDTO.setNoticesPage(page,size,count.intValue());
        return pageNoticeDTO;
    }

    public void create(Question question, User user,Integer type) {
        Notice notice = new Notice();
        notice.setContentId(question.getId());
        notice.setNotifiedCotent(question.getDescription());
        notice.setNotifiedTime(System.currentTimeMillis());
        notice.setNotifier(user.getName());
        notice.setNotifierId(user.getAcountId());
        notice.setReceiverId(question.getCreator());
        notice.setReceiver(userMapper.getUserById(question.getCreator()).getName());
        notice.setType(type);
        notice.setStatus(0);
        noticeMapper.insert(notice);
    }
    public void create(Comment comment, Question question, User user,Integer type) {
        Notice notice = new Notice();
        //如果是评论，获取评论的问题的id
        notice.setContentId(question.getId());
        //获取评论的内容
        notice.setNotifiedCotent(comment.getContent());
        notice.setNotifiedTime(System.currentTimeMillis());
        //user是创建评论是传过来的
        notice.setNotifier(user.getName());
        notice.setNotifierId(user.getAcountId());
        notice.setReceiverId(comment.getCommentorId());
        notice.setReceiver(userMapper.getUserById(comment.getCommentorId()).getName());
        notice.setType(type);
        notice.setStatus(0);
        noticeMapper.insert(notice);
    }
}
