package com.corock.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	// #1. @ResponseBody
	@RequestMapping( "/hello" )
	public String hello() {
		// #1. return "Hello Spring!";
		return "/WEB-INF/views/hello.jsp";
	}
	
}
