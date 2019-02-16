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
<link href="${pageContext.servletContext.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${requestScope.vo.title}</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${requestScope.vo.contents}
							</div>
						</td>						
					</tr>
				</table>
				<c:choose>
					<c:when test="${authUser.no == vo.userNo}">
						<div class="bottom">
							<a href="${pageContext.servletContext.contextPath}/board">글 목록</a>
							<a href="${pageContext.servletContext.contextPath}/board?a=replyform&no=${vo.no}">답글</a>
							<a href="${pageContext.servletContext.contextPath}/board?a=modifyform&no=${vo.no}">글 수정</a>
						</div>
					</c:when>
					<c:when test="${!empty authUser}">
						<div class="bottom">
							<a href="${pageContext.servletContext.contextPath}/board">글목록</a>
							<a href="${pageContext.servletContext.contextPath}/board?a=replyform&no=${vo.no}">답글</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="bottom">
							<a href="${pageContext.servletContext.contextPath}/board">글목록</a>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>