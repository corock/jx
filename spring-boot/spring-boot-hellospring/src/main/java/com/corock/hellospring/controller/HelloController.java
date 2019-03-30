package com.corock.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping({ "/hello", "/a/b/c/d" })
	public String hello() {
//		return "/WEB-INF/views/hello.jsp";
		return "hello";
	}

}
