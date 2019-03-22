<%@page import="com.corock.emaillist.dao.EmaillistDAO"%>
<%@page import="com.corock.emaillist.vo.EmaillistVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");

	String firstName = request.getParameter("fn");
	String lastName= request.getParameter("ln");
	String email = request.getParameter("email");
	
	EmaillistVO vo = new EmaillistVO();
	vo.setFirstName(firstName);
	vo.setLastName(lastName);
	vo.setEmail(email);
	
	new EmaillistDAO().insert(vo);
	
	response.sendRedirect("/emaillist1");
%>