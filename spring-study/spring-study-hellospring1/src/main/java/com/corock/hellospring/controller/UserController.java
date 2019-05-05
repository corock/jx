package com.corock.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corock.hellospring.vo.UserVO;

/** 3. request mapping: type + method */
@Controller
@RequestMapping( "/user" )
public class UserController {

//	@RequestMapping( value = "/joinform", method = RequestMethod.GET )
//	public String joinform() {
//		return "/WEB-INF/views/joinform.jsp";
//	}
	
	@RequestMapping( value = "/join", method = RequestMethod.GET )
	public String join() {
		return "/WEB-INF/views/join.jsp";
	}

	@ResponseBody
	@RequestMapping( value = "/join", method = RequestMethod.POST )
	public String join( @ModelAttribute UserVO userVo ) {
		System.out.println( "userVo: " + userVo );
		return "UserController:join()";
	}
	
	@ResponseBody
	@RequestMapping( "/loginform" )
	public String loginform() {
		return "UserController:loginform()";
	}
	
}
