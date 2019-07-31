package com.example.demo.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jwt.WDjwt;

@RestController
public class TestController {

	@GetMapping("/getKey")
	public Map<String, Object> getKey() {
		String token = WDjwt.createToken("k", "myData", "Hi");
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("token", token);
		return returnMap;
	}
	
	@GetMapping("/hello")
	public Map<String, Object> hello() {
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("mgs", "안녕하세요.");
		return returnMap;
	}
}
