package com.littlecity.cloud.user.utils;

import com.alibaba.fastjson.JSON;
import com.littlecity.cloud.user.config.SystemConfig;
import com.littlecity.cloud.user.dto.UserDTO;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huangxiaocheng
 * @Date 2019/10/11
 **/
public class JWTUtils {

    private static final Logger log = LoggerFactory.getLogger(JWTUtils.class);

    /**
     * @param ttlMillis
     * @param payload
     * @return
     */
    public static String createJWT(long ttlMillis, UserDTO payload, String subject) {
        // 签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long currentTime = System.currentTimeMillis();
        Date now = new Date(currentTime);

        String key = (String) SystemConfig.getConfig("jwt_key");

        Map<String, Object> claim = new HashMap();
        claim.put("user", payload);

        JwtBuilder jwtBuilder = Jwts.builder()
                // 一些实体（通常指的用户）的状态和额外的需要传递的元数据
                .setClaims(claim)
                // 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击
                .setId(payload.getId().toString())
                // 签发时间
                .setIssuedAt(now)
                // 代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志
                .setSubject(subject)
                // 设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, key);

        if (ttlMillis >= 0) {
            long expTime = ttlMillis + currentTime;
            jwtBuilder.setExpiration(new Date(expTime));
        }

        return jwtBuilder.compact();
    }


    public static UserDTO verfiyToken(String token) {
        String key = (String) SystemConfig.getConfig("jwt_key");

        try {
            Claims body = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();

            Map<String, Object> userMap = (Map<String, Object>) body.get("user");
            UserDTO userDTO = ObjectUtils.mapToObject(userMap, UserDTO.class);
            return userDTO;
        } catch (SignatureException signatureException) {
            log.warn("parse token exception.", signatureException);
            return null;

        }

    }

}
