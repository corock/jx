package com.corock.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corock.dto.JSONResult;
import com.corock.mysite.service.SiteService;
import com.corock.mysite.vo.SiteVO;
import com.corock.mysite.vo.UserVO;

@Controller
public class MainController {

	@Autowired
	private SiteService siteService;
	
	@RequestMapping({ "", "/main" })
	public String index( Model model ) {
		SiteVO siteVo = siteService.getSiteInformation();
		model.addAttribute( "site", siteVo );
		return "main/index";
	}

	/** @see WEB-INF/spring-servlet.xml */
	@ResponseBody
	@RequestMapping( "/hello" )
	public String hello() {
		return "<h1>안녕하세요.</h1>";
	}

	/** @see WEB-INF/spring-servlet.xml */
	@ResponseBody
	@RequestMapping( "/hello2" )
	public JSONResult hello2() {
		// jsonResult.setResult("ok");
		return JSONResult.success(new UserVO());
	}

}
