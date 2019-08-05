package com.littlecity.cloud.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultDTO {

  private Integer code;

  private String msg;

  private Object data;

}
