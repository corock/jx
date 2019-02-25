package com.corock.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corock.mysite.service.SiteService;

// @Auth( Role.ADMIN )
@Controller
@RequestMapping( "/admin" )
public class AdminController {

	@Autowired
	private SiteService siteService;
	
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
