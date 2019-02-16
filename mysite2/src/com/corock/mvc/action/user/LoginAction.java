package com.corock.mvc.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.corock.mvc.action.Action;
import com.corock.mvc.util.WebUtils;
import com.corock.mysite.repository.UserDAO;
import com.corock.mysite.vo.UserVO;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVO authUser = new UserDAO().get(email, password);
		
		if (authUser == null) {
			/* auth fail */
			request.setAttribute("result", "fail");
			WebUtils.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			return;
		}
		
		/* auth success -> auth processing */
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		
		/* main redirect */
		WebUtils.redirect(request, response, request.getContextPath());
	}

}
