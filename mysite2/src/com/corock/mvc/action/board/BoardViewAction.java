package com.corock.mvc.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corock.mvc.action.Action;
import com.corock.mvc.util.WebUtils;
import com.corock.mysite.repository.BoardDAO;
import com.corock.mysite.vo.BoardVO;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long no = Long.parseLong(request.getParameter("no"));

		BoardDAO boardDao = new BoardDAO();
		boardDao.addHitCount(no);
		BoardVO vo = boardDao.get(no);
		vo.setContents(vo.getContents().replaceAll("\n", "<br />"));
		request.setAttribute("vo", vo);
		
		WebUtils.forward(request, response, "/WEB-INF/views/board/view.jsp");
	}

}
