package com.like.weblog.weblog.map;

import com.like.weblog.weblog.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE token = #{token}")
    User getUser(@Param("token") String token);

    @Insert("INSERT INTO user VALUE(#{acountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insertUser(User user);
}
