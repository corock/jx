package com.corock.guestbook.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan( "com.corock.guestbook.dao" )
public class RootConfig {
}
