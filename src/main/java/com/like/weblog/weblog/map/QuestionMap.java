package com.like.weblog.weblog.map;

import com.like.weblog.weblog.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMap {
    @Insert("INSERT INTO question VALUE(#{id},#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{likeCount},#{viewCount},#{tag})")
    public void create(Question question);
}
