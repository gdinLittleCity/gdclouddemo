package com.littlecity.cloud.user.utils;

import com.alibaba.fastjson.JSON;
import com.littlecity.cloud.user.dto.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author huangxiaocheng
 * @Date 2019/10/11
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class JWTUtilsTest {

    @Test
    public void createJWT() {

        UserDTO user = new UserDTO();
        user.setId(1);
        user.setName("hello");
        user.setPassword("12334546");

        String jwt = JWTUtils.createJWT(100000, user, "admin");
        UserDTO userDTO = JWTUtils.verfiyToken(jwt);

        System.out.println("token:" + jwt);
        Assert.assertNotNull(jwt);

        System.out.println("parse result: " + JSON.toJSONString(userDTO));
        Assert.assertNotNull(userDTO);


    }

//    @Test
//    public void parseJWT() {
//    }
}