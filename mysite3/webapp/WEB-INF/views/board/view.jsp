<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute( "newLine", "\n" ); %>
<!DOCTYPE html>
<html>
<head>
<title>MySite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr><th colspan="2">글보기</th></tr>
					<tr>
						<td class="label">제목</td>
						<td>${boardVo.title}</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${fn:replace( boardVo.content, newLine, "<br>" )}
							</div>
						</td>						
					</tr>
				</table>
				<div class="bottom">
					<a href="${pageContext.request.contextPath}/board?p=${param.p}&kwd=${param.kwd}">글 목록</a>
					<c:if test="${!empty authUser}">
						<a href="${pageContext.servletContext.contextPath}/board/reply/${boardVo.no}?p=${param.p}&kwd=${param.kwd}">
							답글
						</a>
						<c:if test="${authUser.no == boardVo.userNo}">
							<a href="${pageContext.request.contextPath}/board/modify/${boardVo.no}?p=${param.p}&kwd=${param.kwd}">
								글 수정
							</a>
						</c:if>					
					</c:if>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>