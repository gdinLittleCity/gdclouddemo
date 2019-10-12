package com.littlecity.cloud.product.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author huangxiaocheng
 * @Date 2019/10/12
 **/
@Data
@Builder
public class ProductDto {

    private String id;

    private String productName;

    private Long price;




}
