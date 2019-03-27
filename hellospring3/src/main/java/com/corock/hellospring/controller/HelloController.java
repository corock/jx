package com.corock.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@RequestMapping({ "/hello", "/a/b/c/d" })
	public String hello() {
		return "hello";
	}
	
}
