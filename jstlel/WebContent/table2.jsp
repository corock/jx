<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<c:choose>
	<c:when test="${empty param.r}">
		<c:set var="row" value="3" />	
	</c:when>
	<c:otherwise>
		<c:set var="row" value="${param.r}" />
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${empty param.c}">
		<c:set var="col" value="3" />
	</c:when>
	<c:otherwise>
		<c:set var="col" value="${param.c}" />
	</c:otherwise>
</c:choose>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table border="1" cellspacing="0" cellpadding="5px">
		<c:forEach begin="1" end="${param.r}" step="1" var="i">
			<tr>
				<c:forEach begin="1" end="${param.c}" step="1" var="j">
					<td>cell(${i - 1}, ${j - 1})</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>

</body>
</html>