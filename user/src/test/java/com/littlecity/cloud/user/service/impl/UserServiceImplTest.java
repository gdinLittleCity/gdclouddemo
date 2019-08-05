package com.littlecity.cloud.user.service.impl;

import com.littlecity.cloud.user.dto.ResultDTO;
import com.littlecity.cloud.user.dto.User;
import com.littlecity.cloud.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

  @Autowired
  UserService userService;

  @Test
  public void getUser() {
    List<User> userList = userService.getUser();

    System.out.println(userList);
  }

  @Test
  public void create() {
    User user = new User();
    user.setName("test_user");
    user.setPassword("123456");

    ResultDTO resultDTO = userService.create(user);

    System.out.println(resultDTO);

  }

  @Test
  public void lgoin() {
    User user = new User();
    user.setName("test_user");
    user.setPassword("123456");

    ResultDTO resultDTO = userService.login(user);

    System.out.println(resultDTO);
  }
}