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

public class BoardWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO authUser = (UserVO) request.getSession().getAttribute("authUser");

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContents(content);
		vo.setUserNo(authUser.getNo());
		
		new BoardDAO().insert(vo);
		
		WebUtils.redirect(request, response, request.getContextPath() + "/board?a=writesuccess");
	}

}
