package com.corock.emaillist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.corock.emaillist.dao.EmaillistDAO;
import com.corock.emaillist.vo.EmaillistVO;

@Controller
public class EmaillistController {

	@Autowired
	private EmaillistDAO emaillistDao;
	// cmp. private String message;

	// @ResponseBody
	@RequestMapping({ "", "/list", "/main" })
	public String list( Model model ) {
		model.addAttribute( "list", emaillistDao.getList() );
		return "/WEB-INF/views/list.jsp";
	}
	
	@RequestMapping( "/form" )
	public String form() {
		return "/WEB-INF/views/form.jsp";
	}

	@RequestMapping( value = "/add", method = RequestMethod.POST )
	public String add( EmaillistVO emaillistVo ) {
		emaillistDao.insert( emaillistVo );
		return "redirect:/";
	}

}
