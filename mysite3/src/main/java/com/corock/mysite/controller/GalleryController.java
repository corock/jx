package com.corock.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.corock.mysite.service.GalleryService;
import com.corock.mysite.vo.SiteVO;
import com.corock.security.Auth;
import com.corock.security.Auth.Role;

@Controller
@RequestMapping( "/gallery" )
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping({ "", "/index" })
	public String index( Model model ) {
//		List<GalleryVO> list = galleryService.getGalleryList();
//		model.addAttribute( "list", list );
		return "gallery/index";
	}
	
	@Auth( Role.ADMIN )
	@RequestMapping( "/upload" )
	public String updateSite( @ModelAttribute SiteVO siteVo,
							  @RequestParam(value = "upload-image") MultipartFile multipartFile ) {
		
		return "redirect:/gallery";
	}
	
}
