package com.corock.jblog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corock.jblog.service.BlogService;
import com.corock.jblog.vo.BlogVO;

@Controller
@RequestMapping( "/{id:(?!assets).*}" )
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@RequestMapping({ "", "/{categoryNo}", "/{categoryNo}/{postNo}" })
	public String index( @PathVariable Optional<String> id,
						 @PathVariable Optional<Long> categoryNo,
						 @PathVariable Optional<Long> postNo, Model model ) {
		
		BlogVO blogVo = blogService.getBlogInformation( id.get() );
		model.addAttribute( "blogVo", blogVo );
		
		return "blog/blog-main";
	}
	
}
