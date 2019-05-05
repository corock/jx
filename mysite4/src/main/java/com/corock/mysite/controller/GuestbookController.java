package com.corock.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corock.mysite.service.GuestbookService;
import com.corock.mysite.vo.GuestbookVO;

@Controller
@RequestMapping( "/guestbook" )
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping( "" )
	public String index( Model model ) {
		List<GuestbookVO> list = guestbookService.getMessageList();
		model.addAttribute( "list", list );
		
		return "guestbook/index";
	}
	
	@PostMapping( "/add" )
	public String write( @ModelAttribute GuestbookVO vo ) {
		guestbookService.writeMessage( vo );
		return "redirect:/guestbook";
	}
	
	@GetMapping( "/delete/{no}" )
	public String delete( @PathVariable("no") Long no, Model model ) {
		model.addAttribute( "no", no );
		return "guestbook/delete";
	}

	@PostMapping( "/delete" )
	public String delete( @RequestParam("no") Long no, @ModelAttribute GuestbookVO vo ) {
		guestbookService.deleteMessage( vo );
		return "redirect:/guestbook";
	}
	
	@RequestMapping( "/ajax" )
	public String ajax() {
		return "guestbook/index-ajax";
	}
	
}
