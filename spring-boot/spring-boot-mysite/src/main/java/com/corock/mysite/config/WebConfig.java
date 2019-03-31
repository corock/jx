package com.corock.mysite.config;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.corock.security.AuthInterceptor;
import com.corock.security.AuthLoginInterceptor;
import com.corock.security.AuthLogoutInterceptor;
import com.corock.security.AuthUserHandlerMethodArgumentResolver;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
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
	
	/** Resources mapping to url */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler( "/uploads/images/**" )
				.addResourceLocations( "file:/uploads/" );
	}
	
	/** Message converters */
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		Jackson2ObjectMapperBuilder builder =
				new Jackson2ObjectMapperBuilder().indentOutput( true )
												 .dateFormat( new SimpleDateFormat("yyyy-MM-dd") )
												 .modulesToInstall( new ParameterNamesModule() );
		
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList( new MediaType("application", "json", Charset.forName("utf-8")) ));

		return converter;
	}
	
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList( new MediaType("text", "html", Charset.forName("utf-8")) ));
		return converter;
	}
	
	@Override
	public void configureMessageConverters( List<HttpMessageConverter<?>> converters ) {
		converters.add( mappingJackson2HttpMessageConverter() );
		converters.add( stringHttpMessageConverter() );
	}
	
//	public void m() {
//		Thread t = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				System.out.println("Hello");
//			}
//		});
//		
//		Thread t2 = new Thread(() -> {
//			System.out.println("Hello");
//		});
//		
//		t.start();
//		t2.start();
//	}
	
}
