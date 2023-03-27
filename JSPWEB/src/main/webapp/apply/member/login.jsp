<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	
	<link href="/JSPWEB/apply/css/signup.css" rel="stylesheet">

</head>
<body>
	
	<%@ include file = "../header.jsp" %>
	
	<div class="container">
		<h3> CuoreEspresso Ansan </h3>
		<p> 환영합니다. </p>
	
		<form class="signupForm" enctype="multipart/form">
		
			<div class="title"> 아이디 </div>
			<input maxlength="30" type="text" name="mid" class="mid" id="mid"> <br/>
			<div class = "checkconfirm"> </div>
			
			<div class="title"> 비밀번호 </div>
			<input maxlength="20" type="password" name="mpw" class="mpw" id="mpw"> <br/>
			<div class = "checkconfirm"></div>
			
			<button class="signupbtn" onclick="login()" type="button"> 로그인 </button>	
			
			<div class="subbtnbox">
				<a href="/JSPWEB/apply/member/findID.jsp"> 아이디 찾기 </a>ㅣ
				<a href="/JSPWEB/apply/member/findPW.jsp"> 비밀번호 찾기 </a>ㅣ
				<a href="/JSPWEB/apply/member/signup.jsp"> 회원가입 </a>
			</div>
			
		</form>
	</div>
	
	<script src="/JSPWEB/apply/js/member/login.js" type="text/javascript"> </script>
</body>
</html>