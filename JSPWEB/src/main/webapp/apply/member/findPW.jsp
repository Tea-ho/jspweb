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
		<h3> 비밀번호 찾기 </h3>
		<p> 회원님의 계정 정보를 찾아드립니다. </p>
	
		<form class="signupForm" enctype="multipart/form">
			
			<div class="title"> 아이디 </div>
			<input type="text" name="mid" class="mid" id="mid"> <br/>
			<div class = "checkconfirm"> </div>
			
			<div class="title"> 이메일 </div>
			<input type="text" name="memail" class="memail" id="memail"> <br/>
			
			<div class = "checkconfirm"></div>
			
			<button class="signupbtn" onclick="findPW()" type="button"> 비밀번호 찾기 </button>
			
			<div class="subbtnbox">
				<a href="/JSPWEB/apply/member/findID.jsp"> 아이디 찾기 </a>ㅣ
				<a href="/JSPWEB/apply/member/signup.jsp"> 회원가입 </a>ㅣ
				<a href="/JSPWEB/apply/member/login.jsp"> 로그인 </a>
			</div>
			
		</form>
	</div>

	<script src="/JSPWEB/apply/js/member/login.js" type="text/javascript"> </script>
</body>
</html>