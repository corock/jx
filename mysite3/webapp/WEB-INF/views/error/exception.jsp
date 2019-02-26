<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exception</title>
</head>
<body>
	<h2>Ooops! - ${uri}</h2>
	<p>
		죄송합니다.<br>
		서비스 사용 중 장애가 발생했습니다.<br>
	</p>
	<hr>
	<pre style="color: red; font-weight: bold;">
		${exception}
	</pre>
</body>
</html>
