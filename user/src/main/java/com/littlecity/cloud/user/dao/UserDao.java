package com.littlecity.cloud.user.dao;


import com.littlecity.cloud.user.dto.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

  @Select({
          "select * from user "
  })
  List<User> getUser();
}
