package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.JwtInterceptor;


@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> addPathPatternList = new ArrayList<>();
		addPathPatternList.add("/**");
		
		List<String> excludePathPatternList = new ArrayList<>();
		excludePathPatternList.add("/getKey");
		
		registry.addInterceptor(new JwtInterceptor())
			.addPathPatterns(addPathPatternList)
			.excludePathPatterns(excludePathPatternList);
	}
}
