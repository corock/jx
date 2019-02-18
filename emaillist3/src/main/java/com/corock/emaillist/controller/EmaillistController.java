package com.corock.emaillist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corock.emaillist.dao.EmaillistDAO;
import com.corock.emaillist.vo.EmaillistVO;

@Controller
public class EmaillistController {

	@Autowired
	private EmaillistDAO emaillistDao;
	// cmp. private String message;

	@ResponseBody
	@RequestMapping({ "", "/list", "/main" })
	public String list() {
		List<EmaillistVO> list = emaillistDao.getList();
		System.out.println( "list: " + list );
		return "/WEB-INF/views/list.jsp";
	}

}
