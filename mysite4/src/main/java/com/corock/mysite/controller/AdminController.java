package com.corock.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.corock.mysite.service.FileUploadService;
import com.corock.mysite.service.SiteService;
import com.corock.mysite.vo.SiteVO;
import com.corock.security.Auth;
import com.corock.security.Auth.Role;

@Auth( Role.ADMIN )
@Controller
@RequestMapping( "/admin" )
public class AdminController {

	@Autowired
	private SiteService siteService;
	
	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping({ "", "/main" })
	public String main( Model model ) {
		
		SiteVO siteVo = siteService.getSiteInformation();
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
	public String updateSite( @ModelAttribute SiteVO siteVo,
							  @RequestParam(value = "upload-profile") MultipartFile multipartFile ) {
		
		String profile = fileUploadService.restore( multipartFile );
		siteVo.setProfile( profile );		
		siteService.updateSiteInformation( siteVo );
		
		return "redirect:/admin/main";
	}
	
}
