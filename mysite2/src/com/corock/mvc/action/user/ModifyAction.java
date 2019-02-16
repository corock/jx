package com.corock.mvc.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corock.mvc.action.Action;
import com.corock.mvc.util.WebUtils;
import com.corock.mysite.repository.UserDAO;
import com.corock.mysite.vo.UserVO;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserVO vo = new UserVO();
		vo.setNo(Long.parseLong(no));
		vo.setName(name);
		vo.setPassword(password);
		vo.setGender(gender);
		
		new UserDAO().update(vo);
		
		WebUtils.redirect(request, response, request.getContextPath() + "/user?a=modifysuccess");
	}

}
