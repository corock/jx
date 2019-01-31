<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>MySite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile" src="${pageContext.servletContext.contextPath}/assets/images/profile.jpg" style="width: 120px">
					<h2>안녕하세요. CoRock의  MySite입니다.</h2>
					<p>
						이 사이트는  웹 프로그래밍 실습과제 사이트입니다.<br>
						메뉴는 사이트 소개, 방명록, 게시판이 있습니다.<br>
						CoRock는 Coding Rockstar의 줄임말이며, <a href="https://corock.tistory.com" target="_blank">개인 블로그</a>를 운영중입니다.<br><br>
						<a href="${pageContext.servletContext.contextPath}/guestbook">방명록</a>에 글 남기기<br>
					</p>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="main" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>