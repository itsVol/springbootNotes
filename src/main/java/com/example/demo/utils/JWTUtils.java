package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    /**
     * 生成token
     */
    private static final String SIGN = "Hello";
    public static String getToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        //默认7日过期
        instance.add(Calendar.DATE, 7);
        //创建JWT builder
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach(builder::withClaim);
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN));
        return token;
    }
    /**
     * 验证token合法性
     */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }
}


