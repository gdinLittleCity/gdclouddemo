package com.littlecity.cloud.user.controller;

import com.littlecity.cloud.user.dto.UserDTO;
import com.littlecity.cloud.user.entity.UserEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author Joey.huang
 * @date 2019/7/19
 */
@RestController
@RequestMapping
@Slf4j
public class UserController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/hello")
    public String hello() {
        log.info("hello run ............");
        return "hello";
    }


    @PostMapping("/login")
    public String login(@RequestBody UserDTO user) {
        redisTemplate.opsForValue().set("userRedis_Test", "user_redis_test");

        return "sdf";
    }


    @GetMapping("getRedisValue")
    public String getRedisValue() {
        return redisTemplate.opsForValue().get("userRedis_Test");
    }


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getProduct")
    public String getProduct(String id){

        log.info("product id:{}",id);
        String url = !StringUtils.isEmpty(id)? "http://product/getProduct?id="+id : "http://product/getProduct";

        log.info("get from product, url:{}", url);

        String product = restTemplate.getForObject(url, String.class);

        return product;
    }

    @GetMapping("testHystrix")
    @HystrixCommand(fallbackMethod = "callProductException")
    public String testHystrix(String name){
        String productRes = restTemplate.postForObject("http://product/runException", null, String.class);

        return "call product runException";
    }

    // 断路器生效时调用
    public String callProductException(String name){

        return "hystrix call back:"+ name;
    }



}
