package com.corock.mvc.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corock.mvc.action.Action;
import com.corock.mvc.util.WebUtils;
import com.corock.mysite.repository.GuestbookDAO;
import com.corock.mysite.vo.GuestbookVO;

public class InsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String passwd = request.getParameter("pass");
		String content = request.getParameter("content");
		
		GuestbookVO vo = new GuestbookVO();
		vo.setName(name);
		vo.setPassword(passwd);
		vo.setMessage(content);

		new GuestbookDAO().insert(vo);
		
		WebUtils.redirect(request, response, request.getContextPath() + "/guestbook");
	}

}
