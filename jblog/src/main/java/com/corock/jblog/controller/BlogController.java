package com.corock.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corock.jblog.service.AdminService;
import com.corock.jblog.service.BlogService;
import com.corock.jblog.vo.BlogVO;
import com.corock.jblog.vo.CategoryVO;

@Controller
@RequestMapping( "/{id:(?!assets).*}" )
public class BlogController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private AdminService adminService;
	
	@RequestMapping({ "", "/{categoryNo}", "/{categoryNo}/{postNo}" })
	public String index( @PathVariable Optional<String> id,
						 @PathVariable Optional<Long> categoryNo,
						 @PathVariable Optional<Long> postNo, Model model ) {
		
		BlogVO blogVo = blogService.getBlogInformation( id.get() );
		model.addAttribute( "blogVo", blogVo );
		
		List<CategoryVO> categoryList = adminService.showCategoryNameList( id.get() );
		model.addAttribute( "categoryList", categoryList );
		
		if ( postNo.isPresent() ) {
			System.out.println("postNo.isPresent()");
		}
		
		if ( categoryNo.isPresent() ) {
			System.out.println("categoryNo.isPresent()");
		}
		
		if ( id.isPresent() ) {
			return "blog/blog-main";
		}
		
		return "blog/blog-main";
	}
	
}
