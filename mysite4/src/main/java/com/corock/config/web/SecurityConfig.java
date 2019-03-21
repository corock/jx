package com.corock.config.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.corock.security.AuthInterceptor;
import com.corock.security.AuthLoginInterceptor;
import com.corock.security.AuthLogoutInterceptor;
import com.corock.security.AuthUserHandlerMethodArgumentResolver;

@Configuration
@EnableWebMvc
public class SecurityConfig extends WebMvcConfigurerAdapter {

	/** Argument resolver */
	@Bean
	public AuthUserHandlerMethodArgumentResolver authUserHandlerMethodArgumentResolver() {
		return new AuthUserHandlerMethodArgumentResolver();
	}
		
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add( authUserHandlerMethodArgumentResolver() );
	}
	
	/** Interceptor */
	@Bean
	public AuthLoginInterceptor authLoginInterceptor() {
		return new AuthLoginInterceptor();
	}

	@Bean
	public AuthLogoutInterceptor authLogoutInterceptor() {
		return new AuthLogoutInterceptor();
	}

	@Bean
	public AuthInterceptor authInterceptor() {
		return new AuthInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// There is no toss to back
		registry.addInterceptor( authLoginInterceptor() )
				.addPathPatterns( "/user/auth" );
		
		registry.addInterceptor( authLogoutInterceptor() )
				.addPathPatterns( "/user/logout" );
		
		// There is toss to back
		registry.addInterceptor( authInterceptor() )
				.addPathPatterns( "/**" )
				.excludePathPatterns( "/user/auth" )
				.excludePathPatterns( "/user/logout" )
				.excludePathPatterns( "/assets/*" );
	}

}
