package com.corock.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// @Auth( Role.ADMIN )
@Controller
@RequestMapping( "/admin" )
public class AdminController {

	// @Auth( Role.ADMIN )
	@RequestMapping({ "", "/main" })
	public String main() {
		return "/admin/main";
	}

	// @Auth( Role.ADMIN )
	@RequestMapping( "/board" )
	public String board() {
		return "/admin/board";
	}
	
}
