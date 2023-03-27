<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/JSPWEB/apply/css/info.css" rel="stylesheet">
</head>
<body>
	
	<%@ include file = "/apply/header.jsp" %>
	
	<div class="container">
	
		<h3> 내프로필 </h3>
		<div>
			<div>
				<img class="mimg" alt="" src="">
			</div>
			<div>
				<div class="title"> 아이디 </div>
				<div class="mid"></div>
			</div>
			<div>
				<div class="title"> 이메일 </div>
				<div class="memail"></div>
			</div>
			<div>
				<div class="title"> 보유 포인트 </div>
				<div class="mpoint"></div>
			</div>
			
			<a href="/JSPWEB/apply/member/update.jsp"><button class="infoBtn" type="button"> 정보수정 </button> </a>
			<button class="infoBtn" onclick="openModal()" type="button"> 회원탈퇴 </button>
			
		</div>
	</div>
	
	<!-- 모달 HTMLs -->
	<div class="modal_wrap">
		<div class="modal_box">
			<h3 class="modal_title"> 회원 탈퇴 </h3>
			<div class="modal_content">
				회원 탈퇴 하시겠습니까? <br/>
				비밀번호: <input type="password" class="mpw">
			</div>
			<div class="modal_btns">
				<button onclick="setDelete()" class="modla_check" type="button"> 확인 </button>
				<button onclick="closeModal()" class="modla_cancle" type="button"> 취소 </button>
			</div>
		</div>
	</div>

	<script src="/JSPWEB/apply/js/member/info.js" type="text/javascript"> </script>
	
</body>
</html>