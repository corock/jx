<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Expression Lanauage</title>
</head>
<body>

	<h1>데이터 타입</h1>
	${iVal} <br>
	${lVal} <br>
	${fVal} <br>
	${bVal} <br>
	--${obj}-- <br>

	<h1>연산</h1>
	${iVal + 20 * lVal / 2 - 10} <br>
	${iVal > lVal && fval < 5} <br>
	${empty obj} <br>
	${not empty obj} <br>

	<h1>요청 파라미터 가져오기</h1>
	<%= request.getParameter("a") + 10 %> <br>
	${param.a + 10} <br>
	
	<h1>객체 접근</h1>
	${requestScope.vo.no} <br>
	${vo.name} <br>
	${vo} <br>

</body>
</html>