package com.littlecity.cloud.product.controller;

import com.alibaba.fastjson.JSON;
import com.littlecity.cloud.product.dto.ProductDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangxiaocheng
 * @Date 2019/10/12
 **/
@RestController
@RequestMapping
public class ProductController {


    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/getProduct")
    public String getProduct(String id){
        if (StringUtils.isEmpty(id)){
            return "no product";
        }

        ProductDto pr = ProductDto.builder().id("123")
                .price(10020L)
                .productName("YSL 特价")
                .build();

        return JSON.toJSONString(pr);
    }

}
