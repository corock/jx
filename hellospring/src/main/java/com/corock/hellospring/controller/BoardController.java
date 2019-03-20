package com.corock.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping( "/board" )
public class BoardController {

	@ResponseBody
	@RequestMapping( "/write" )
	public String write(
		@RequestParam( "e" ) String email,
		@RequestParam( value = "name", required = false ) String name,	// not recommended
		@RequestParam String password,
		@RequestParam( value = "content", required = true, defaultValue = "" ) String content ) {
		System.out.println( "e: " + email );
		System.out.println( "name: " + name );
		System.out.println( "password: " + password );
		System.out.println( "content: " + content );
		return "BoardController:write()";
	}

	@ResponseBody
	@RequestMapping( "/delete" )
	public String delete( @RequestParam(value = "no", required = false) Long no ) {
		System.out.println( "no: " + no );
		return "BoardController:delete()";
	}
	
	@ResponseBody
	@RequestMapping( "/view" )
	public String view( @RequestParam(value = "no", required = true, defaultValue = "0") Long no ) {
		System.out.println( "no: " + no );
		return "BoardController:view()";
	}

	@ResponseBody
	@RequestMapping( "/view2/{id}/{no}" )
	public String view2( @PathVariable( "id" ) String id, @PathVariable( "no" ) Long no ) {
		System.out.println( "id: " + id );
		System.out.println( "no: " + no );
		return "BoardController:view2()";
	}

}
