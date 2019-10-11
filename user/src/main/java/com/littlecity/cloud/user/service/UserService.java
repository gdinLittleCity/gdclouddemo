package com.littlecity.cloud.user.service;

import com.littlecity.cloud.user.dto.ResultDTO;
import com.littlecity.cloud.user.dto.UserDTO;
import com.littlecity.cloud.user.entity.UserEntity;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserService {

  List<UserEntity> getUser();

  ResultDTO create(@NotNull UserDTO userEntity);

  ResultDTO login(@NotNull UserDTO userEntity);

}
