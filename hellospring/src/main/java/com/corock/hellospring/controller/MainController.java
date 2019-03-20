package com.corock.hellospring.controller;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@ResponseBody
	@RequestMapping({ "/main", "" })
	// 2. request mapping: only method
	public String main() {
		return "MainController:main()";
	}
	
	@ResponseBody
	@RequestMapping( "/main2/a/b/c" )
	public String main2() {
		return "MainController:main2()";
	}
	
	/** not recommended: invasion of technology! */
	@ResponseBody
	@RequestMapping( "/main3" )
	public String main3( HttpServletRequest request, Writer out ) {
		request.getParameter( "n" );
		return "MainController:main3()";
	}
	
}
