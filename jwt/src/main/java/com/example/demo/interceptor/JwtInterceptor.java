package com.example.demo.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.exception.UnauthorizedException;
import com.example.demo.jwt.WDjwt;

import lombok.extern.slf4j.Slf4j;

/**
 * 토큰 권한 인터셉터
 * @author Johnny
 */
@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		final Map<String, Object> requestMap = new HashMap<>();

		Enumeration<?> enums = request.getParameterNames();
		
		while (enums.hasMoreElements()) // request 객체에서 token 파라미터를 찾아서 map에 담는다
		{
			String paramName = enums.nextElement().toString();
			requestMap.put(paramName, request.getParameter(paramName));
		}
		
		log.info(">>>>> preHandle :: requestMap : " + requestMap.toString());
		
		if (!requestMap.isEmpty() && requestMap.containsKey("token") && WDjwt.isUsable(requestMap.get("token").toString()))
		{
			return true;
		}
		
		throw new UnauthorizedException();
	}
}