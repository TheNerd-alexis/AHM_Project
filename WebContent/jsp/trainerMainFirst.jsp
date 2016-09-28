<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>joinForm</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
	<div class="trainerMainFirstCase">
		<div class="trainerMainFirst" style="position: relative; height: 550px;" >
			<br><br>
			<div style="font-size: xx-large;" align="center">
			[회원리스트]<br>
			</div>
			<br><br><br>
			<span><c:forEach items="${memberList}" var="member">

				<div style="margin-left: 39%;"><font size="3px">
				
				·<a href="trainerMain.do?member_id=${member.member_id }"> ${member.member_name} (${member.member_id })</a>
				
				</font></div>

				</c:forEach></span>



		
		
		
		<div style="position:absolute; bottom:10px; margin-left: 45%"><a href="logout.do">로그아웃</a></div>
		
		
		
		</div>

		

	</div>
</body>
</html>
