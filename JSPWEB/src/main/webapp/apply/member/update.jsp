<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@ include file = "/apply/header.jsp" %>
	
	<div class="container">
	
		<h3> 내프로필 수정 </h3>
		<p> 프로필 수정 및 회원 탈퇴를 하실 수 있습니다. </p>
		<form class="updateForm">
			<div>
				<div>
					<img class="mimg" alt="" src="">
					<br>프로필 변경:<input type="file" name="newmimg">
					<br> <input class="defaultimg" type="checkbox"> 기본프로필 사용
				</div>
				<div>
					<div> 아이디 </div>
					<div class="mid"></div>
				</div>
				<div>
					<div> 현재 비밀번호 </div>
					<input class="mpw" name="mpw"> 
				</div>
				<div>
					<div> 변경할 비밀번호 </div>
					<input class="newmpw" name="newmpw"> 
				</div>
				<div>
					<div> 변경할 비밀번호 확인 </div>
					<input class="newmpwc" name="newmpwc">
				</div>
				<div>
					<div> 이메일 주소 </div>
					<input class="memail" name="memail">
					<button type="button"> 인증 </button>
				</div>
				
				<button onclick="setUpdate()" type="button"> 정보수정 </button>
				
			</div>
		</form>
	</div>

	<script src="/JSPWEB/apply/js/member/update.js" type="text/javascript"> </script>
</body>
</html>