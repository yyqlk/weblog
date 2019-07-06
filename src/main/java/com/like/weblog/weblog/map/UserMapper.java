package com.like.weblog.weblog.map;

import com.like.weblog.weblog.model.Question;
import com.like.weblog.weblog.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE token = #{token}")
    User getUser(@Param("token") String token);

    @Select("SELECT * FROM user WHERE acount_id = #{acountId} " )
    User getUserById(@Param("acountId") Long id);

    @Insert("INSERT INTO user VALUE(#{acountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insertUser(User user);

}
