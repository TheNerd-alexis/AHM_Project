<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>AJS HEALTH MANAGER</title>
<link rel="stylesheet" href="css/style.css">
<!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->

<input type="hidden" id="refreshed" value="no">
<script type="text/javascript">
	onload = function() {
		var e = document.getElementById("refreshed");
		if (e.value == "no")
			e.value = "yes";
		else {
			e.value = "no";
			location.reload();
		}
	}
</script>

</head>
<body>
	<form action="login.do" class="login">
		<div class="loginLogo">
			<img src="./img/Logo.jpg" width="560" height="300" align="middle">
		</div>
		<div class="loginInput">
			<ul>
				<li><input type="text" name="member_id" class="login-input"
					placeholder="Your ID" autofocus> <input type="password"
					name="member_pwd" class="login-input" placeholder="Password">
					<div align="middle">
						<input type="radio" name="type" value="1" checked="checked">
						일반회원 <input type="radio" name="type" value="2"> 트레이너
					</div></li>
				<br>
				<li><input type="submit" value="Login" class="login-submit">
				</li>
			</ul>
			<p class="login-help">
				<a href="joinForm.do">아직 회원이 아니세요?</a>
			</p>
		</div>
	</form>
</body>
</html>
