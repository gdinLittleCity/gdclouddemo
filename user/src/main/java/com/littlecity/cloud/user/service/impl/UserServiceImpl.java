package com.littlecity.cloud.user.service.impl;

import com.littlecity.cloud.user.dao.UserDao;
import com.littlecity.cloud.user.dto.User;
import com.littlecity.cloud.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

  @Autowired
  UserDao userDao;

  @Override
  public List<User> getUser() {
    return userDao.getUser();
  }
}
