package com.corock.mysite.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.corock.mysite.service.BoardService;
import com.corock.mysite.vo.BoardVO;
import com.corock.mysite.vo.UserVO;
import com.corock.security.Auth;
import com.corock.web.util.WebUtil;

@Controller
@RequestMapping( "/board" )
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping( "" )
	public String index( @RequestParam(name = "p", required = true, defaultValue = "1") Integer page,
						 @RequestParam(name = "kwd", required = true, defaultValue = "") String keyword,
						 Model model ) {
		
		Map<String, Object> map = boardService.getMessageList( page, keyword );
		model.addAttribute( "map", map );
		
		return "board/index";
	}

	@Auth
	@RequestMapping( value = "/write", method = RequestMethod.GET )
	public String write() {
		return "board/write";
	}
	
	@Transactional
	@RequestMapping( value = "/write", method = RequestMethod.POST )
	public String write( HttpSession session,
						 @ModelAttribute BoardVO boardVo,
						 @RequestParam(value = "p", required = true, defaultValue = "1") Integer page,
						 @RequestParam(value = "kwd", required = true, defaultValue = "") String keyword) {
		
		/* 접근 제어 */
		UserVO authUser = (UserVO) session.getAttribute( "authUser" );
		if ( authUser == null ) {
			return "redirect:/";
		}
		
		boardVo.setUserNo( authUser.getNo() );
		
		if ( boardVo.getGroupNo() != null ) {
			boardService.increaseGroupOrderNo( boardVo );
		}
		boardService.addMessage( boardVo );
		
		return ( boardVo.getGroupNo() != null ) ?
					"redirect:/board?p=" + page + "&kwd" + WebUtil.encodeURL( keyword, "UTF-8" ) :
					"redirect:/board";
	}
	
	@RequestMapping( "/view/{no}" )
	public String view( @PathVariable("no") Long no, Model model ) {
		BoardVO boardVo = boardService.getMessage( no );
		model.addAttribute( "boardVo", boardVo );
		return "board/view";
	}
	
	@RequestMapping ( "/delete/{no}" )
	public String delete( HttpSession session,
						  @PathVariable("no") Long boardNo,
						  @RequestParam(value = "p", required = true, defaultValue = "1") Integer page,
						  @RequestParam(value = "kwd", required = true, defaultValue = "") String keyword) {

		/* 접근 제어 */
		UserVO authUser = (UserVO) session.getAttribute( "authUser" );
		if ( authUser == null ) {
			return "redirect:/";
		}
		
		boardService.deleteMessage( boardNo, authUser.getNo() );
		return "redirect:/board?p=" + page + "&kwd=" + WebUtil.encodeURL( keyword, "UTF-8" );
	}
	
	@RequestMapping( value = "/modify/{no}", method = RequestMethod.GET )
	public String modify( HttpSession session, @PathVariable("no") Long no, Model model ) {
		/* 접근 제어 */
		UserVO authUser = (UserVO) session.getAttribute( "authUser" );
		if ( authUser == null ) {
			return "redirect:/";
		}
		
		BoardVO boardVo = boardService.getMessage( no, authUser.getNo() );
		model.addAttribute( "boardVo", boardVo );
		
		return "board/modify";
	}

	@RequestMapping( value = "/modify", method = RequestMethod.POST )
	public String modify( HttpSession session,
						  @ModelAttribute BoardVO boardVo,
						  @RequestParam(value = "p", required = true, defaultValue = "1") Integer page,
						  @RequestParam(value = "kwd", required = true, defaultValue = "") String keyword) {

		/* 접근 제어 */
		UserVO authUser = (UserVO) session.getAttribute( "authUser" );
		if ( authUser == null ) {
			return "redirect:/";
		}
		
		boardVo.setUserNo( authUser.getNo() );
		boardService.modifyMessage( boardVo );
		
		return "redirect:/board/view/" + boardVo.getNo() +
			   "?p=" + page + "&kwd=" + WebUtil.encodeURL( keyword, "UTF-8" );
	}
	
	@RequestMapping( value = "/reply/{no}", method = RequestMethod.GET )
	public String reply( HttpSession session,
						 @PathVariable("no") Long no, Model model ) {
		/* 접근 제어 */
		if ( null == session.getAttribute("authUser") ) {
			return "redirect:/";
		}
		
		BoardVO boardVo = boardService.getMessage( no );
		boardVo.setOrderNo( boardVo.getOrderNo() + 1 );
		boardVo.setDepth( boardVo.getDepth() + 1 );

		model.addAttribute( "boardVo", boardVo );
		
		return "board/reply";
	}
	
}
