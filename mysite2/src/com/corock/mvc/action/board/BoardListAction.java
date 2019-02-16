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

public class BoardListAction implements Action {

	private class Paging {
		
		private int begin;
		private int end;
		private int last;
		
		public Paging(int totalCount, int currentPage) {
			if (totalCount % 10 == 0) 	{ this.last = totalCount / 10; }
			else 						{ this.last = (totalCount / 10) + 1; }
			
			this.begin = ( (currentPage - 1) / 5 ) * 5 + 1;
			// else { this.begin = ( currentPage - (currentPage % 5) ) + 1; }

			this.end = this.begin + 4;
		}		
		
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}
		
		String kwd = request.getParameter("kwd");
		if (kwd == null) {
			kwd = "";
		}
		BoardDAO boardDao = new BoardDAO();
		List<BoardVO> list = boardDao.getList(kwd, Integer.parseInt(page));
		
		/** paging alogrithm */
		Paging p = new Paging(boardDao.getTotalCount(kwd), Integer.parseInt(page));
		
		request.setAttribute("list", list);
		request.setAttribute("begin", p.begin);
		request.setAttribute("end", p.end);
		request.setAttribute("last", p.last);
		request.setAttribute("current", Integer.parseInt(page));
		
		WebUtils.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}

}
