package com.corock.jblog.controller.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corock.dto.JSONResult;
import com.corock.jblog.service.AdminService;
import com.corock.jblog.service.BlogService;
import com.corock.jblog.service.UserService;
import com.corock.jblog.vo.BlogVO;
import com.corock.jblog.vo.CategoryVO;

@Controller( "adminAPIController" )
@RequestMapping( "/{id:(?!assets).*}/admin/category/api" )
public class AdminController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping( "/list" )
	public JSONResult list( @PathVariable Optional<String> id, Model model ) {
		BlogVO blogVo = blogService.getBlogInformation( id.get() );
		model.addAttribute( "blogVo", blogVo );

		List<CategoryVO> list = adminService.getCategoryList( id.get() );
		
//		for ( CategoryVO vo : list ) {
//			System.out.println( "[AdminController#list#vo] " + vo );
//		}
		
		return JSONResult.success( list );
	}
	
	@ResponseBody
	@PostMapping( "/add" )
	public JSONResult add( @PathVariable Optional<String> id, @RequestBody CategoryVO vo ) {
		// 1. get user number using id
		Long userNo = userService.getNoById( id.get() );
		vo.setUserNo( userNo );
		vo.setPostCount(0);
				
		System.out.println( "[AdminController#add#vo] " + vo );

		adminService.writeCategory( vo );
		return JSONResult.success( vo );
	}
	
	@ResponseBody
	@PostMapping( "/delete" )
	public JSONResult delete( @PathVariable Optional<String> id, @RequestBody CategoryVO vo ) {
		// 1. get user number using id
		Long userNo = userService.getNoById( id.get() );
		vo.setUserNo( userNo );
		
		boolean result = adminService.deleteCategory( vo );
		return JSONResult.success( result ? vo.getNo() : -1 );
	}
		
}
