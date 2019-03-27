package com.corock.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 1. request mapping: only type
 */ 
@Controller
@RequestMapping( "/guestbook/*" )
public class GuestbookController {

	public void commonFunction() {
	}
	
	@ResponseBody
	@RequestMapping
	public String list() {
		return "GuestbookController:list()";
	}
	
	@ResponseBody
	@RequestMapping
	public String add() {
		return "GuestbookController:add()";
	}
	
	@ResponseBody
	@RequestMapping
	public String delete() {
		return "GuestbookController:delete()";
	}
	
}
