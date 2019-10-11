package com.littlecity.cloud.user.utils;

import com.alibaba.fastjson.JSON;
import com.littlecity.cloud.user.config.SystemConfig;
import com.littlecity.cloud.user.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huangxiaocheng
 * @Date 2019/10/11
 **/
public class JWTUtils {


    /**
     *
     * @param ttlMillis
     * @param payload
     * @return
     */
    public static String createJWT(long ttlMillis, Object payload){
        // 签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long currentTime = System.currentTimeMillis();
        Date now = new Date(currentTime);

        String key = (String) SystemConfig.getConfig("jwt_key");
        String subject = "admin";

        String json = JSON.toJSONString(payload);
        Map<String, Object> map = new HashMap();
        map.put("user", payload);

        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(map)
                .setId("23")
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);

        if (ttlMillis >= 0){
            long expTime = ttlMillis + currentTime;
            jwtBuilder.setExpiration(new Date(expTime));
        }

        return jwtBuilder.compact();
    }


    public static String parseJWT(String token){
        String key = (String) SystemConfig.getConfig("jwt_key");

        Claims body = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        return JSON.toJSONString(body);
    }

}
