package com.corock.mvc.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corock.mvc.action.Action;
import com.corock.mvc.util.WebUtils;
import com.corock.mysite.repository.GuestbookDAO;
import com.corock.mysite.vo.GuestbookVO;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String password = request.getParameter("password");

		GuestbookVO vo = new GuestbookVO();
		vo.setPassword(password);
		vo.setNo(Long.parseLong(no));

		new GuestbookDAO().delete(vo);

		WebUtils.redirect(request, response, request.getContextPath() + "/guestbook");
	}

}
