package com.corock.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corock.dto.JSONResult;
import com.corock.mysite.service.GuestbookService;
import com.corock.mysite.vo.GuestbookVO;

@Controller( "guestbookAPIController" )
@RequestMapping( "/guestbook/ajax" )
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping( "/list" )
	public JSONResult list(
		@RequestParam(value = "sno", required = true, defaultValue = "0") Long startNo ) {
		
		List<GuestbookVO> list = guestbookService.getMessageList( startNo );
		return JSONResult.success( list );
	}
	
	@ResponseBody
	@PostMapping( "/add" )
	public JSONResult add( @RequestBody GuestbookVO vo ) {
		guestbookService.writeMessage( vo );
		return JSONResult.success( vo );
	}

	@ResponseBody
	@PostMapping( "/delete" )
	public JSONResult delete( @ModelAttribute GuestbookVO vo ) {
		boolean result = guestbookService.deleteMessage( vo );
		return JSONResult.success( result ? vo.getNo() : -1 );
	}
	
}
