package com.like.weblog.weblog.map;

import com.like.weblog.weblog.model.Question;
import com.like.weblog.weblog.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface QuestionMap {
    @Insert("INSERT INTO question VALUE(#{id},#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{likeCount},#{viewCount},#{tag})")
    public void create(Question question);

    @Select("SELECT * FROM question LIMIT #{offset},#{size}")
    List<Question> findAllQuestion(Integer offset, Integer size);

    @Select("select count(*) from question")
    Integer count();

    @Select("SELECT * FROM question  WHERE creator = #{creator} LIMIT #{offset},#{size}")
    List<Question> findQuestionByCreater(Integer offset, Integer size, Long creator);

    @Select("select count(*) from question WHERE creator = #{creator}")
    Integer countByCreater(Long creator);

    @Select("SELECT * FROM question WHERE id = #{id}")
    Question findQUestionById(String id);

    @Update("UPDATE question SET title = #{title},description= #{description}, tag=#{tag}, gmt_modified=#{modifiedTime} WHERE id =#{id}")
    void updateQuestion(String tag,String title,String description,String id ,long modifiedTime);
}
