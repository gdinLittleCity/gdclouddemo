package com.littlecity.cloud.user.controller;

import com.littlecity.cloud.user.dto.UserDTO;
import com.littlecity.cloud.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author Joey.huang
 * @date 2019/7/19
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/hello")
    public String hello() {
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

}
