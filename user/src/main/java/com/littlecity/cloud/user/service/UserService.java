package com.littlecity.cloud.user.service;

import com.littlecity.cloud.user.dto.ResultDTO;
import com.littlecity.cloud.user.dto.User;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserService {

  List<User> getUser();

  ResultDTO create(@NotNull User user);

  ResultDTO login(@NotNull User user);

}
