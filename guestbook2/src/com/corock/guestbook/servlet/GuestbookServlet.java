package com.corock.guestbook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corock.guestbook.dao.GuestbookDAO;
import com.corock.guestbook.vo.GuestbookVO;
import com.corock.web.WebUtils;

@WebServlet("/gb")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 요청 분리(식별)
		String action = request.getParameter("a");
		if ("deleteform".equals(action)) {
			WebUtils.forward(request, response, "/WEB-INF/views/deleteform.jsp");
		} else if ("delete".equals(action)) {
			String no = request.getParameter("no");
			String password = request.getParameter("password");
			
			GuestbookVO vo = new GuestbookVO();
			vo.setPassword(password);
			vo.setNo(Long.parseLong(no));
			
			new GuestbookDAO().delete(vo);
			
			WebUtils.redirect(request, response, request.getContextPath() + "/gb");
		} else if ("add".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String message = request.getParameter("message");
			
			GuestbookVO vo = new GuestbookVO();
			vo.setName(name);
			vo.setPassword(password);
			vo.setMessage(message);
			
			new GuestbookDAO().insert(vo);
			
			WebUtils.redirect(request, response, request.getContextPath() + "/gb");
		} else {
			/* default action processing: index */			
			GuestbookDAO dao = new GuestbookDAO();
			List<GuestbookVO> list = dao.getList();
			
			// 데이터를 request 범위에 저장
			request.setAttribute("list", list);
			
			// forwarding: getServletContext();
			WebUtils.forward(request, response, "/WEB-INF/views/index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
