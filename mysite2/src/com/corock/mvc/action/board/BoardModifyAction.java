package com.corock.mvc.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corock.mvc.action.Action;
import com.corock.mvc.util.WebUtils;
import com.corock.mysite.repository.BoardDAO;
import com.corock.mysite.vo.BoardVO;
import com.corock.mysite.vo.UserVO;

public class BoardModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO authUser = (UserVO) request.getSession().getAttribute("authUser");

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		long no = Long.parseLong(request.getParameter("no"));
		
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContents(content);
		vo.setUserNo(authUser.getNo());
		vo.setNo(no);
		
		new BoardDAO().update(vo);
		
		WebUtils.redirect(request, response, request.getContextPath() + "/board");
	}

}
