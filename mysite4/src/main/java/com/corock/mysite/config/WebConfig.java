package com.corock.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.corock.config.web.FileUploadConfig;
import com.corock.config.web.MVCConfig;
import com.corock.config.web.MessagesConfig;
import com.corock.config.web.SecurityConfig;

/**
 * @EnableWebMvc: <mvc:annotation-driven /> in spring-servlet.xml
 * @ComponentScan: <context:component-scan> in applicationConext.xml
 */
@Configuration
@ComponentScan(value = { "com.corock.mysite.controller", "com.corock.mysite.exception" })
@Import(value = { MVCConfig.class, SecurityConfig.class, MessagesConfig.class, FileUploadConfig.class })
public class WebConfig {
}
