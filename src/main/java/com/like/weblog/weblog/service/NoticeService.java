package com.like.weblog.weblog.service;


import com.like.weblog.weblog.dto.PageNoticeDTO;
import com.like.weblog.weblog.map.NoticeExMapper;
import com.like.weblog.weblog.map.NoticeMapper;
import com.like.weblog.weblog.map.UserMapper;
import com.like.weblog.weblog.model.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    @Autowired
    NoticeMapper noticeMapper;
    @Autowired
    NoticeExMapper noticeExMapper;

    @Autowired
    UserMapper userMapper;

    public PageNoticeDTO getNoticeByReceiver(User user ,Integer page, Integer size) {
        Integer offset = size*(page-1);//第一页从0开始，，第二页从7开始
        NoticeExExample noticeExample = new NoticeExExample();
        noticeExample.setOrderByClause("`notified_time` desc");
        noticeExample.setPageIndex(offset);
        noticeExample.setPageSize(size);
        noticeExample.createCriteria().
                andReceiverIdEqualTo(user.getAcountId());
        List<Notice> notices = noticeExMapper.selectByExample(noticeExample);
        PageNoticeDTO pageNoticeDTO = new PageNoticeDTO();
        //设置数据,查出每一页的数据
        pageNoticeDTO.setNotices(notices);

        //设置分页
        NoticeExample exampleCount = new NoticeExample();
        exampleCount.createCriteria().andReceiverIdEqualTo(user.getAcountId());
        Long count = noticeMapper.countByExample(exampleCount);
        pageNoticeDTO.setNoticesPage(page,size,count.intValue());
        return pageNoticeDTO;
    }

    //创建问题的通知
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
    //创建评论的通知
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

    //继续登陆人的通知数
    public Integer countNotice(Long id){
        NoticeExample countNoticeExample = new NoticeExample();
        countNoticeExample.createCriteria().andReceiverIdEqualTo(id).andStatusEqualTo(0);
        Long count = noticeMapper.countByExample(countNoticeExample);
        return count.intValue();
    }


}
