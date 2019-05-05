package com.corock.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.corock.mysite.service.FileUploadService;
import com.corock.mysite.service.GalleryService;
import com.corock.mysite.vo.GalleryVO;
import com.corock.security.Auth;
import com.corock.security.Auth.Role;

@Controller
@RequestMapping( "/gallery" )
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping({ "", "/index" })
	public String index( Model model ) {
		model.addAttribute( "list", galleryService.getGalleryList() );
		return "gallery/index";
	}
	
	@Auth( Role.ADMIN )
	@RequestMapping( "/upload" )
	public String updateSite( @ModelAttribute GalleryVO galleryVo,
							  @RequestParam("file") MultipartFile multipartFile ) {
		
		String imageURL = fileUploadService.restore( multipartFile );
		galleryVo.setImageUrl( imageURL );
		System.out.println("imageURL:"+imageURL);
		galleryService.saveImageInformation( galleryVo );
		return "redirect:/gallery";
	}
	
	@Auth( value = Auth.Role.ADMIN )
	@RequestMapping( "/delete/{no}" )
	public String delete( @PathVariable("no") Long no ) {
		galleryService.deleteImageInformation( no );
		return "redirect:/gallery";
	}
	
}
