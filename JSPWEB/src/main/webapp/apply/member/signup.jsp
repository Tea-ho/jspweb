<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<!-- 반응형 -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- CSS only -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	
	<link href="/JSPWEB/apply/css/signup.css" rel="stylesheet">
	
</head>
<body>

	<%@ include file = "../header.jsp" %>
	<div class="container">
		<h3> CuoreEspresso Ansan </h3>
		<p> 환영합니다. </p>
	
		<form class="signupForm" enctype="multipart/form">
		
			<div class="title"> 아이디 </div>
			<input onkeyup="idcheck()" maxlength="30" type="text" name="mid" class="mid" id="mid"> <br/>
			<div class = "checkconfirm"> </div>
			
			<div class="title"> 비밀번호 </div>
			<input onkeyup="pwcheck()" maxlength="20" type="password" name="mpw" class="mpw" id="mpw"> <br/>
			<div class = "checkconfirm"></div>
			
			<div class="title"> 비밀번호 확인</div>
			<input onkeyup="pwccehck()" maxlength="20" type="password" name="mpwc" class="mpwc" id="mpwc"> <br/>
			<div class = "checkconfirm"></div>
			
			<div class="title"> 이메일 </div>
			<div class="emailBox">
				<input onkeyup="emailcheck()" type="text" name="memail" class="memail" id="memail"> <br/>
				<button onclick="getauth()" class="authbtn" type="button" disabled> 인증 </button>
			</div>
			
			<div class="authbox">
				
			</div>
			
			<div class = "checkconfirm"></div>
			
			<div class="title"> 프로필 </div>
			<div class="pimgbox">
				<input onchange="premimg(this)" type="file" name="mimg" class="mimg" id="mimg"> <br/>
				<img class="premig" alt="" src="/JSPWEB/apply/member/pimg/default.png"> <br/>
			</div>
			
			<button class="signupbtn" onclick="signup()" type="button"> 가입 </button>	
		</form>
	</div>
	
	<!-- 모달 HTMLs -->
	<div class="modal_wrap">
		<div class="modal_box">
			<h3 class="modal_title"> 회원가입 성공! </h3>
			<div class="modal_content">
				가입해주셔서 감사합니다. <br/>
				회원가입 축하 포인트 1,000 Point 지급 
			</div>
			<div class="modal_btns">
				<a href="/JSPWEB/apply/member/login.jsp"> <button class="modla_check" type="button"> 로그인 </button> </a>
				<button onclick="closeModal()" class="modla_cancle" type="button"> 닫기 </button>
			</div>
		</div>
	</div>

	<!-- 1. 최신 jquery(js 라이브러리) import (ajax 함수 활용하기 위함) -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

	<script src="/JSPWEB/apply/js/member/signup.js" type="text/javascript"> </script>

</body>
</html>