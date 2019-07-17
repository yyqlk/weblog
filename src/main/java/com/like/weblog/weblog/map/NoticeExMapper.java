package com.like.weblog.weblog.map;

import com.like.weblog.weblog.model.Notice;
import com.like.weblog.weblog.model.NoticeExExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface NoticeExMapper {


    @Update("UPDATE notice SET status = #{status} WHERE id =#{noticeId}")
    void updateStatusById(Integer status,Integer noticeId);

    List<Notice> selectByExample(NoticeExExample example);
}
