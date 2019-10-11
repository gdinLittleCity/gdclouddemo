package com.littlecity.cloud.user.service.impl;

import com.littlecity.cloud.user.ResponseCode;
import com.littlecity.cloud.user.dao.UserDao;
import com.littlecity.cloud.user.dto.ResultDTO;
import com.littlecity.cloud.user.dto.UserDTO;
import com.littlecity.cloud.user.entity.UserEntity;
import com.littlecity.cloud.user.service.UserService;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
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
  public List<UserEntity> getUser() {
    return userDao.findAll();
  }

  @Override
  public ResultDTO create(@NotNull UserDTO userEntity) {
    ResponseCode checkResult = checkParam(userEntity);
    if (checkParam(userEntity).getCode() != 0){
      return buildResultDto(checkResult, null);
    }
    try{
      userEntity.setPassword(genPassword(userEntity.getPassword()));
      userDao.save(userEntity);
      return buildResultDto(ResponseCode.SUCCESS, null);
    } catch ( Exception ex){
      return buildResultDto(ResponseCode.SYSTEM_ERROR, null);
    }
  }

  @Override
  public ResultDTO login(@NotNull UserDTO userEntity) {
    ResponseCode checkResult = checkParam(userEntity);
    log.info("check result:{}", checkResult);
    if (checkParam(userEntity).getCode() != 0){
      return buildResultDto(checkResult, null);
    }
    try {
      List<UserEntity> userEntityList = userDao.findByNameAndPassword(userEntity.getName(),genPassword(userEntity.getPassword()));
      log.info("query result:{}", userEntityList);
      if (!CollectionUtils.isEmpty(userEntityList) && userEntityList.size() == 1){
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

  private ResponseCode checkParam(UserEntity userEntity){
    if (StringUtils.isEmpty(userEntity.getName())){
      return ResponseCode.USER_LOGIN_NAME_EMPTY;
    }
    if (StringUtils.isEmpty(userEntity.getPassword())){
      return ResponseCode.USER_LOGIN_PASSWORD_EMPTY;
    }
    return ResponseCode.SUCCESS;
  }

}
