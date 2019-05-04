<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:choose>
		<c:when test="${param.color == 0}">
			<h1 style="color: red;">Hello World</h1>
		</c:when>
		
		<c:when test="${param.color == 1}">
			<h1 style="color: green;">Hello World</h1>
		</c:when>
		
		<c:when test="${param.color == 2}">
			<h1 style="color: blue;">Hello World</h1>
		</c:when>
		
		<c:otherwise>
			<h1>Hello World</h1>
		</c:otherwise>
	</c:choose>

</body>
</html>