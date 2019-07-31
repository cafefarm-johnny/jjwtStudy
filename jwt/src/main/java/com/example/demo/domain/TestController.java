package com.example.demo.domain;

import java.util.HashMap;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jwt.WDjwt;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestController {

	/**
	 * 토큰 발급 요청
	 * @author Johnny
	 * @return 
	 * {
	 * <pre>
	 * token: {
	 * 	header : { Auth: 인증 헤더, type: 타입, createDate: 토큰 생성일, alg: 알고리즘 타입 }, 
	 * 	body : { iss: 발급자, sub: 토큰 제목, exp: 토큰 만료일, key: data }, 
	 * 	signature : 생성된 키
	 * }
	 * </pre>
	 * }
	 */
	@GetMapping("/getKey")
	public Map<String, Object> getKey() {
		String token = WDjwt.createToken("myKey", "myData", "Hi");
		
		log.info(">>>>> getKey :: token : " + token);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("token", token);
		return returnMap;
	}
	
	/**
	 * 토큰 유효성 검증을 테스트하기 위한 요청
	 * @author Johnny
	 * @return { msg: 인삿말 }
	 */
	@GetMapping("/hello")
	public Map<String, Object> hello() {
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("msg", "안녕하세요.");
		return returnMap;
	}
}
