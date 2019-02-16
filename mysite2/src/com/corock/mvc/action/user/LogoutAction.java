package com.corock.mvc.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.corock.mvc.action.Action;
import com.corock.mvc.util.WebUtils;
import com.corock.mysite.vo.UserVO;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session != null && session.getAttribute("authUser") != null) {
			// logout processing
			session.removeAttribute("authUser");
			session.invalidate();
		}
		
		WebUtils.redirect(request, response, request.getContextPath());
	}

}
