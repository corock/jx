<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- <form action="join.jsp" method="post"> -->
	<form action="/helloweb/join" method="post">
		이메일: <input type="text" name="email" />
		<br /><br />
		
		비밀번호: <input type="password" name="passwd" />
		<br /><br />

		성별: 
		<input type="radio" name="gender" value="male" checked="checked" />남
		<input type="radio" name="gender" value="female" />여
		<br /><br />
		
		생년: 
		<select name="brith-year">
			<option value="1998">1998년</option>
			<option value="1999">1999년</option>
			<option value="2000">2000년</option>
			<option value="2001">2001년</option>
		</select>
		<br /><br />
		
		취미: 
		<input type="checkbox" name="hobby" value="reading" />독서
		<input type="checkbox" name="hobby" value="swimming" />수영
		<input type="checkbox" name="hobby" value="coding" />코딩
		<br /><br />
		
		자기소개:
		<textarea name="self-intro"></textarea>
		<br /><br />
		
		<input type="submit" value="가입" />
	</form>

</body>
</html>