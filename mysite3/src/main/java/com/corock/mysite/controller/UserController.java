package com.corock.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.corock.mysite.service.UserService;
import com.corock.mysite.vo.UserVO;

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

	@RequestMapping( value = "/login", method = RequestMethod.POST )
	public String login( HttpSession session, @ModelAttribute UserVO vo ) {
		UserVO authUser = userService.getUser( vo );
		
		if ( authUser == null ) {
			/**
			 * fail to auth
			 * session.setAttribute( "result", "fail" );
			 * return "/user/login";
			 */
			return "redirect:/user/login?result=fail";
		}
		
		/* auth success -> auth processing */
		session.setAttribute( "authUser", authUser );
		return "redirect:/";
	}
	
	@RequestMapping( value = "/logout" )
	public String logout( HttpSession session, @ModelAttribute UserVO userVo ) {
		userService.logout( session );
		return "redirect:/";
	}

	@RequestMapping( value = "/modify", method = RequestMethod.GET )
	public String modify( HttpSession session, Model model ) {
		/* access control */
		long no = ((UserVO) session.getAttribute("authUser")).getNo();
		UserVO vo = userService.getUser( no );
		
		if ( vo != null ) {
			model.addAttribute( "vo", vo );
		}
		
		return "/user/modify";
	}

	@RequestMapping( value = "/modify", method = RequestMethod.POST )
	public String modify( HttpSession session, @ModelAttribute UserVO userVo ) {
		/* access control(security) */
		UserVO authUser = null;
		
		if ( session != null ) {
			authUser = (UserVO) session.getAttribute("authUser");
		}
		if ( session == null ) {
			return "/user";
		}
		
		userVo.setNo( authUser.getNo() );		
		userService.modify( userVo );		
		session.setAttribute( "authUser", userVo );
		
		return "redirect:/";
	}

}
