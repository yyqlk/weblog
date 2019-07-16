package com.like.weblog.weblog.map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface NoticeExMapper {


    @Update("UPDATE notice SET status = #{status} WHERE id =#{noticeId}")
    void updateStatusById(Integer status,Integer noticeId);
}
