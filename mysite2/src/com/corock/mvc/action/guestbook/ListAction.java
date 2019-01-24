package com.corock.mvc.action.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corock.mvc.action.Action;
import com.corock.mvc.util.WebUtils;
import com.corock.mysite.repository.GuestbookDAO;
import com.corock.mysite.vo.GuestbookVO;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<GuestbookVO> list = new GuestbookDAO().getList();
		
		request.setAttribute("list", list);
		
		WebUtils.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
	}

}
