package com.littlecity.cloud.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.littlecity.cloud.user.entity.UserEntity;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO extends UserEntity {

  private String verifyCode;


}
