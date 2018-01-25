package com.cmb.okr.api.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cmb.okr.api.interceptor.AuthInterceptor;

@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor((new AuthInterceptor())).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

}
