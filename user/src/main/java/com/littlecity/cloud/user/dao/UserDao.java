package com.littlecity.cloud.user.dao;


import com.littlecity.cloud.user.dto.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

  @Select({
          "select * from user "
  })
  List<User> getUser();

  @Insert({
          "insert into user(name, password) values (#{user.name},#{ user.password})"
  })
  @Options(useGeneratedKeys = true, keyProperty = "id")
  int insertUser(@Param("user") User user);

  @Select("select * from user where name = #{name} and password = #{password}")
  List<User> getUserByUserName(@Param("name") String userName,@Param("password") String pwd);

}
