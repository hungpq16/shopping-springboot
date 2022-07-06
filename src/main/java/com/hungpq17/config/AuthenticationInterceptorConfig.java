package com.hungpq17.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hungpq17.interceptor.AdminAuthenticationInterceptor;
import com.hungpq17.interceptor.SiteAuthenticationInterceptor;

@Configuration
public class AuthenticationInterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private AdminAuthenticationInterceptor adminAuthenticationInterceptor;

	@Autowired
	private SiteAuthenticationInterceptor siteAuthenticationInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(adminAuthenticationInterceptor).addPathPatterns("/admin/**");

		registry.addInterceptor((HandlerInterceptor) siteAuthenticationInterceptor).addPathPatterns("/admin/**");
	}

}
