<%@page import="com.corock.guestbook.dao.GuestbookDAO"%>
<%@page import="com.corock.guestbook.vo.GuestbookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");

	String no = request.getParameter("no");
	String password = request.getParameter("password");
	
	GuestbookVO vo = new GuestbookVO();
	vo.setPassword(password);
	vo.setNo(Long.parseLong(no));
	
	new GuestbookDAO().delete(vo);
	
	response.sendRedirect("/guestbook1");
%>