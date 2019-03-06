package com.corock.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.corock.jblog.service.AdminService;
import com.corock.jblog.service.BlogService;
import com.corock.jblog.service.FileUploadService;
import com.corock.jblog.service.UserService;
import com.corock.jblog.vo.BlogVO;
import com.corock.jblog.vo.CategoryVO;

@Controller
@RequestMapping( "/{id:(?!assets).*}/admin" )
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private AdminService adminService;

	@RequestMapping({ "", "/basic" })
	public String basic( @PathVariable Optional<String> id, Model model ) {
		BlogVO blogVo = blogService.getBlogInformation( id.get() );
		model.addAttribute( "blogVo", blogVo );
		return "blog/blog-admin-basic";
	}
	
	@PostMapping( "/upload" )
	public String upload( @PathVariable Optional<String> id,
						  @ModelAttribute BlogVO blogVo,
						  @RequestParam("logo-file") MultipartFile multipartFile,
						  Model model ) {
		
		// 1. get user number using id
		Long userNo = userService.getNoById( id.get() );
		blogVo.setUserNo(userNo);
		
		// 2. update logo url
		String logo = fileUploadService.restore( multipartFile );
		blogVo.setLogo( logo );
		blogService.modifyBasicSettings( blogVo );

		// 3. add a attribute to model
		model.addAttribute( "blogVo", blogVo );

		return "redirect:/" + id.get();
	}

	@GetMapping( "/category" )
	public String category( @PathVariable Optional<String> id, Model model ) {
		BlogVO blogVo = blogService.getBlogInformation( id.get() );
		model.addAttribute( "blogVo", blogVo );
		
		List<CategoryVO> list = adminService.getCategoryList( id.get() );
		model.addAttribute( "list", list );
		
		return "blog/blog-admin-category";
	}

	@PostMapping( "/category/add" )
	public String add( @PathVariable Optional<String> id, @ModelAttribute CategoryVO vo ) {
		adminService.writeCategory( vo );
		return "redirect:/blog/blog-admin-category";
	}
	
	@PostMapping( "/category/delete" )
	public String delete( @PathVariable Optional<String> id, @ModelAttribute CategoryVO vo ) {
		adminService.deleteCategory( vo );
		return "redirect:/blog/blog-admin-category";
	}
	
	@GetMapping( "/write" )
	public String write( @PathVariable Optional<String> id, Model model ) {
		BlogVO blogVo = blogService.getBlogInformation( id.get() );
		model.addAttribute( "blogVo", blogVo );
		
		List<CategoryVO> list = adminService.getCategoryList( id.get() );
		model.addAttribute( "list", list );
		
		for ( CategoryVO vo : list ) {
			System.out.println( "[AdminController#write#vo] " + vo );
		}
		
		return "blog/blog-admin-write";
	}
	
	@RequestMapping( "/category/ajax" )
	public String ajax() {
		return "blog/blog-admin-category";
	}

}
