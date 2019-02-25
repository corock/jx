package com.corock.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.corock.mysite.service.UserService;
import com.corock.mysite.vo.UserVO;
import com.corock.security.Auth;
import com.corock.security.AuthUser;

@Controller
@RequestMapping( "/user" )
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping( value = "/join", method = RequestMethod.GET )
	public String join() {
		return "/user/join";
	}

	@RequestMapping( value = "/join", method = RequestMethod.POST )
	public String join( @ModelAttribute UserVO userVo ) {
		userService.join( userVo );
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping ( "/joinsuccess" )
	public String joinSuccess() {
		return "/user/joinsuccess";
	}
	
	@RequestMapping( value = "/login", method = RequestMethod.GET )
	public String login() {
		return "/user/login";
	}
	
	// @Auth( value = "ADMIN", method = 2 )
	// @Auth( Role.ADMIN )
	@Auth
	@RequestMapping( value = "/modify", method = RequestMethod.GET )
	public String modify( @AuthUser UserVO authUser, Model model ) {
		System.out.println( "modify() authUser: " + authUser );
		
		UserVO userVo = userService.getUser( authUser.getNo() );
		model.addAttribute( "userVo", userVo );
		return "/user/modify";
	}

	@Auth
	@RequestMapping( value = "/modify", method = RequestMethod.POST )
	public String modify( @AuthUser UserVO authUser, @ModelAttribute UserVO userVo ) {
		System.out.println( "modify() authUser: " + authUser );
		System.out.println( "modify() userVo: " + userVo );

		userVo.setNo( authUser.getNo() );		
		userService.modifyUser( userVo );	
		
		// Modify authUser of session
		authUser.setName( userVo.getName() );
		
		return "redirect:/user/modify?result=success";
	}

}
