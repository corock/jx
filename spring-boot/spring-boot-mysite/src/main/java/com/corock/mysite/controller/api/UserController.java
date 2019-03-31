package com.corock.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corock.dto.JSONResult;
import com.corock.mysite.service.UserService;

@Controller( "userApiController" )
@RequestMapping( "/user/api" )
public class UserController {

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping( "/checkemail" )
	public JSONResult checkEmail(
			@RequestParam(value = "email", required = true, defaultValue = "") String email ) {
		
		boolean exist = userService.existEmail( email );
		return JSONResult.success( exist );
	}
	
}
