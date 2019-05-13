package com.corock.helloweb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST 방식으로 넘어오는 데이터 인코딩(필수)
		request.setCharacterEncoding("utf-8");
		
		String email = request.getParameter("email");
		System.out.println(email);
		
		String passwd = request.getParameter("passwd");
		System.out.println(passwd);

		String gender = request.getParameter("gender");
		System.out.println(gender);
		
		String birthYear = request.getParameter("birth-year");
		System.out.println(birthYear);

		String[] hobbies = request.getParameterValues("hobby");
		for (String hobby : hobbies) {
			System.out.println(hobby);
		}
		
		String selfIntro = request.getParameter("self-intro");
		System.out.println(selfIntro);
		
		response.getWriter().println("ok");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
