package com.corock.mvc.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.corock.mvc.action.Action;
import com.corock.mvc.util.WebUtils;
import com.corock.mysite.repository.BoardDAO;
import com.corock.mysite.vo.BoardVO;
import com.corock.mysite.vo.UserVO;

public class BoardModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* access control(security) */
		UserVO authUser = null;
		
		HttpSession session = request.getSession();
		if (session != null) {
			authUser = (UserVO) session.getAttribute("authUser");
		}
		if (session == null) {
			WebUtils.redirect(request, response, request.getContextPath());
			return;
		}
		
		BoardDAO boardDao = new BoardDAO();
		BoardVO vo = boardDao.get(Long.parseLong(request.getParameter("no")));
		vo.getContents().replace("<br />", "\r\n");
		request.setAttribute("vo", vo);
		
		WebUtils.forward(request, response, "/WEB-INF/views/board/modify.jsp");
	}

}
