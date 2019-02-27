package com.corock.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.corock.mysite.service.SiteService;
import com.corock.mysite.vo.SiteVO;
import com.corock.mysite.vo.UserVO;
import com.corock.security.Auth;
import com.corock.security.Auth.Role;
import com.corock.security.AuthUser;

// @Auth( Role.ADMIN )
@Controller
@RequestMapping( "/admin" )
public class AdminController {

	@Autowired
	private SiteService siteService;
	
	@Auth( Role.ADMIN )
	@RequestMapping({ "", "/main" })
	public String main( @AuthUser UserVO authUser, Model model ) {
		
		SiteVO siteVo = siteService.getSite();
		model.addAttribute( "siteVo", siteVo );

//		System.out.println( "main() authUser: " + authUser );
//		System.out.println( "main() siteVo: " + siteVo );
		
		return "admin/main";
	}

	@Auth( Role.ADMIN )
	@RequestMapping( "/board" )
	public String board() {
		return "admin/board";
	}
	
	@Auth( Role.ADMIN ) 
	@RequestMapping( value = "/main/update", method = RequestMethod.POST )
	public String update( @ModelAttribute SiteVO siteVo ) {
		siteService.update( siteVo );
		return "redirect:/";
	}
	
}
