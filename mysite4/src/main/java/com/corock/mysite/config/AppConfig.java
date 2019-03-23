package com.corock.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.corock.config.app.DBConfig;
import com.corock.config.app.MyBatisConfig;

@Configuration
@ComponentScan(value = { "com.corock.mysite.service", "com.corock.mysite.repository" })
@Import(value = { DBConfig.class, MyBatisConfig.class })
public class AppConfig {
}
