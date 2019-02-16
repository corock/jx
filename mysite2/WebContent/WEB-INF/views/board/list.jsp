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
<script>
	var liSelected = null;
	
	var onClicked = function() {
		if (liSelected != null) {
			// unselect
			liSelected.className = "";
		}
		// select
		this.className = "selected";
		liSelected = this;
	}
	
	onClicked();
	
	window.onload = function() {
		document.getElementById("page1").onclick = onClicked;
		document.getElementById("page2").onclick = onClicked;
		document.getElementById("page3").onclick = onClicked;
		document.getElementById("page4").onclick = onClicked;
		document.getElementById("page5").onclick = onClicked;
	}
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath}/board" method="post">
					<input type="hidden" name="a" value="search">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
					<c:set var="count" value="${fn:length(list)}" />
					<c:forEach items="${list}" var="vo" varStatus="status">
						<tr>
							<td>${count - status.index}</td>
							<c:choose>
								<c:when test="${vo.depth > 0}">
									<td style="text-align: left; padding-left: ${vo.depth * 20}px;">
										<img src="${pageContext.servletContext.contextPath}/assets/images/reply.png">
										<a href="${pageContext.servletContext.contextPath}/board?a=view&no=${vo.no}">
											${vo.title}
										</a>
									</td>
								</c:when>
								<c:otherwise>
									<td style="text-align: left;">
										<a href="${pageContext.servletContext.contextPath}/board?a=view&no=${vo.no}">
											${vo.title}
										</a>
									</td>								
								</c:otherwise>
							</c:choose>
							<td>${vo.userName}</td>							
							<td>${vo.hit}</td>
							<td>${vo.writeDate}</td>
							<td>
								<c:if test="${sessionScope.authUser.no == vo.userNo}">
									<a href="${pageContext.servletContext.contextPath}/board?a=delete&no=${vo.no}" class="del">삭제</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:if test="${end > 5}">
							<li><a href="${pageContext.servletContext.contextPath}/board?page=${begin - 1}">◀</a></li>
						</c:if>
					
						<c:forEach begin="${begin}" end="${end}" step="1" varStatus="status">
							<c:choose>
								<c:when test="${status.current < last}">							
									<li><a href="${pageContext.servletContext.contextPath}/board?page=${status.current}">${status.current}</a></li>
								</c:when>
								<c:when test="${status.current == current}">
									<li class="selected"><a href="${pageContext.servletContext.contextPath}/board?page=${status.current}">${status.current}</a></li>
								</c:when>
								<c:otherwise>
									<li>${status.current}</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					
						<c:if test="${end < last}">
							<li><a href="${pageContext.servletContext.contextPath}/board?page=${end + 1}">▶</a></li>
						</c:if>						
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
					<c:if test="${!empty authUser}">
						<a href="${pageContext.servletContext.contextPath}/board?a=writeform" id="new-book">글쓰기</a>
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