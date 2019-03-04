package com.corock.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.corock.jblog.service.UserService;
import com.corock.jblog.vo.UserVO;

@Controller
@RequestMapping( "/user" )
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping( value = "/join", method = RequestMethod.GET )
	public String join() {
		return "user/join";
	}

	@RequestMapping( value = "/join", method = RequestMethod.POST )
	public String join( @ModelAttribute UserVO userVo ) {
		userService.join( userVo );
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping ( "/joinsuccess" )
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping( value = "/login", method = RequestMethod.GET )
	public String login() {
		return "user/login";
	}

}
