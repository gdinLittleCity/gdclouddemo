package com.littlecity.cloud.user.service.impl;

import com.littlecity.cloud.user.dto.ResultDTO;
import com.littlecity.cloud.user.dto.UserDTO;
import com.littlecity.cloud.user.entity.UserEntity;
import com.littlecity.cloud.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEntityServiceImplTest {

  @Autowired
  UserService userService;

  @Test
  public void getUser() {
    List<UserEntity> userEntityList = userService.getUser();

    System.out.println(userEntityList);
  }

  @Test
  public void create() {
    UserDTO userEntity = new UserDTO();
    userEntity.setName("test_user");
    userEntity.setPassword("123456");

    ResultDTO resultDTO = userService.create(userEntity);

    System.out.println(resultDTO);

  }

  @Test
  public void lgoin() {
    UserDTO userEntity = new UserDTO();
    userEntity.setName("test_user");
    userEntity.setPassword("123456");

    ResultDTO resultDTO = userService.login(userEntity);

    System.out.println(resultDTO);
  }
}