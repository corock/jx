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

public class ModifyFormAction implements Action {

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
		
		UserVO vo = new UserDAO().get(authUser.getNo());
		request.setAttribute("vo", vo);
		WebUtils.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
	}

}
