package com.corock.guestbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.corock.guestbook.dao.GuestbookDAO;
import com.corock.guestbook.vo.GuestbookVO;

@Controller
public class GuestbookController {

	@Autowired
	private GuestbookDAO guestbookDao;
	
	@RequestMapping({ "", "/index" })
	public String index( Model model ) {
		model.addAttribute( "list", guestbookDao.getList() );
		return "index";
	}
	
	@RequestMapping( value="/add", method = RequestMethod.POST )
	public String add( @ModelAttribute GuestbookVO vo ) {
		guestbookDao.insert( vo );
		return "redirect:/";
	}

	@RequestMapping( value = "/delete/{no}", method = RequestMethod.GET )
	public String delete( @PathVariable( "no" ) Long no, Model model ) {
		model.addAttribute( "no", no );
		return "delete";
	}

	@RequestMapping( value="/delete", method = RequestMethod.POST )
	public String delete( GuestbookVO vo ) {		
		guestbookDao.delete( vo );
		return "redirect:/";
	}
	
}
