package com.corock.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootConfiguration
//@EnableAutoConfiguration
//@ComponentScan( "com.corock.hellospring.controller" )
@SpringBootApplication
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication.run( BootApplication.class, args );
	}

}
