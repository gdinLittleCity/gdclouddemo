package com.littlecity.cloud.user;

public enum ResponseCode {

  SUCCESS(0, "Success"),
  SYSTEM_ERROR(-1, "system internal error"),
  USER_LOGIN_NAME_EMPTY(1000001, "user name is empty"),
  USER_LOGIN_PASSWORD_EMPTY(1000002, "user name is empty");

  private Integer code;

  private String message;

  private ResponseCode(Integer code, String message){
    this.code = code;
    this.message = message;
  }

  public Integer getCode(){
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
