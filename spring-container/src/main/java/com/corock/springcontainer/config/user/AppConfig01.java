package com.corock.springcontainer.config.user;

import org.springframework.context.annotation.Bean;

import com.corock.springcontainer.user.User;

public class AppConfig01 {

	@Bean
	public User user() {
		return new User();
	}
	
}
