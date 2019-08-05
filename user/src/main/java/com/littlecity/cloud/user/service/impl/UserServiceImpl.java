package com.littlecity.cloud.user.service.impl;

import com.littlecity.cloud.user.ResponseCode;
import com.littlecity.cloud.user.dao.UserDao;
import com.littlecity.cloud.user.dto.ResultDTO;
import com.littlecity.cloud.user.dto.User;
import com.littlecity.cloud.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

  @Autowired
  UserDao userDao;

  @Override
  public List<User> getUser() {
    return userDao.getUser();
  }

  @Override
  public ResultDTO create(@NotNull User user) {
    ResponseCode checkResult = checkParam(user);
    if (checkParam(user).getCode() != 0){
      return buildResultDto(checkResult, null);
    }
    try{
      user.setPassword(genPassword(user.getPassword()));
      userDao.insertUser(user);
      return buildResultDto(ResponseCode.SUCCESS, null);
    } catch ( Exception ex){
      return buildResultDto(ResponseCode.SYSTEM_ERROR, null);
    }
  }

  @Override
  public ResultDTO login(@NotNull User user) {
    ResponseCode checkResult = checkParam(user);
    log.info("check result:{}", checkResult);
    if (checkParam(user).getCode() != 0){
      return buildResultDto(checkResult, null);
    }
    try {
      List<User> userList = userDao.getUserByUserName(user.getName(),genPassword(user.getPassword()));
      log.info("query result:{}", userList);
      if (!CollectionUtils.isEmpty(userList) && userList.size() == 1){
        return buildResultDto(ResponseCode.SUCCESS, null);
      }
      return buildResultDto(ResponseCode.SYSTEM_ERROR, null);
    } catch ( Exception ex){

      ex.printStackTrace();
      return buildResultDto(ResponseCode.SYSTEM_ERROR, null);
    }
  }

  private String genPassword(String password){
    return DigestUtils.md5DigestAsHex(password.getBytes());
  }

  private ResultDTO buildResultDto(ResponseCode responseCode, Object data){
    return ResultDTO.builder()
            .code(responseCode.getCode())
            .msg(responseCode.getMessage())
            .data(data)
            .build();
  }

  private ResponseCode checkParam(User user){
    if (StringUtils.isEmpty(user.getName())){
      return ResponseCode.USER_LOGIN_NAME_EMPTY;
    }
    if (StringUtils.isEmpty(user.getPassword())){
      return ResponseCode.USER_LOGIN_PASSWORD_EMPTY;
    }
    return ResponseCode.SUCCESS;
  }

}
