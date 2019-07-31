package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.exception.UnauthorizedException;
import com.example.demo.jwt.WDjwt;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

	private static final String HEADER_AUTH = "Auth";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		final String token = request.getHeader(HEADER_AUTH);
		
		log.info(">>>>> preHandle :: token : " + token);
		
		if (token != null && WDjwt.isUsable(token))
		{
			return true;
		}
		
		throw new UnauthorizedException();
	}
}
