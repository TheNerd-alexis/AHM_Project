<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>joinForm</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/joinScript.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"
	integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
	crossorigin="anonymous">
	
</script>
</head>

<body>
<form action="join.do" class="join">
    <div class = "joinForm">
    	<div>
    	    	<span><input type="text" name="id" class="join-input" placeholder="ID" autofocus></span>
            	<span><input type="button" name = "idCheckBtn" class="joinBtn" value="ID Cehck" onclick="idCheck()"></span>
<input type="hidden" name="idCheckFlag" value="-1">
    	</div>
		
         <div>
               <input type="text" name="name" class="login-input" placeholder="이름">    	    	
        </div>
        
        
        <div>
               <input type="email" name="email" class="login-input" placeholder="E-Mail Address">    	    	
        </div>
         <div>
               <input type="text" name="phone" class="login-input" placeholder= "전화번호 (010-1234-1234)">    	    	
        </div>
        <div>
           	<input type="radio" name="sex" value="1" checked="checked">남자
			<input type="radio" name="sex" value="0" >여자
        </div><br>
       
        
        <div>
               <input type="password" name="pwd" class="login-input" placeholder="Password">
               <input type="password" name="pwdCheck" class="login-input" placeholder="Password Check">    	    	
        </div>
        
        <div>
        	<span>
           	<input type="radio" name="type" value="1" checked="checked">회원
			<input type="radio" name="type" value="2" >매니저
      		</span> 
            
            <br>
            <br>
            <br>
              <input type="submit" value="JOIN" class="join-submit" onclick="return submitCheck()">
<!--               <input type="button" value="JOIN" class="join-submit" onclick="submitCheck()"> -->
        </div>
        
        
  </div>
</form>
</body>
</html>
