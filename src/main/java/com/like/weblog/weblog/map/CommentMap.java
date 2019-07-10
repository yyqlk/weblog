package com.like.weblog.weblog.map;

import com.like.weblog.weblog.model.Comment;
import com.like.weblog.weblog.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMap {

    @Insert("INSERT INTO comment VALUE(#{id},#{parentId},#{commentorId},#{gmtCreate},#{gmtModified},#{likeCount},#{type},#{content})")
    Integer create(Comment comment);
}
