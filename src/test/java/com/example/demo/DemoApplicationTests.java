package com.example.demo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.HashMap;


class DemoApplicationTests {

	@Test
	void contextLoads() {
		HashMap<String, Object>map =new HashMap<>();
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.SECOND,200);
		String token= JWT.create()
				.withHeader(map)//header
				.withClaim("userId",21)//payload
				.withExpiresAt(instance.getTime())//指定令牌的过期时间
				.sign(Algorithm.HMAC256("1h!uix"));//签名
		System.out.println(token);
	}
	@Test
	void test(){
		JWTVerifier jwtVerifier =JWT.require(Algorithm.HMAC256("1h!uix")).build();
		DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MDc1ODkwNzcsInVzZXJJZCI6MjF9.cg2I9mNaPYcW_zzM_moaHCQ1_AACKKGQZ1ZNssaorKY");
		System.out.println(verify.getClaim("userId").asString());

	}

}
