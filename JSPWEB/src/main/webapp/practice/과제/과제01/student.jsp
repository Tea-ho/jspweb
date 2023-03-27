<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3> [C] 학생 정보 입력 </h3>
	이름: 	<input type="text" class="sName"> 	<br/>
	전화번호: 	<input type="text" class="sPhone"> 	<br/>
	키: 		<input type="text" class="sLength">	<br/>
	나이:		<input type="number" class="sAge">	<br/>
	등록일:	<input type="date" class="sDate">	<br/>
	성별:		<input type="radio" name="sSex"> 남
			<input type="radio" name="sSex"> 여	<br/>
	사는지역:	<select class="sHome">				
				<option> 안산 </option> <option> 안양 </option> <option> 수원 </option> <option> 시흥 </option>
			</select> <br/>
	자기소개:	<textarea class="sIntro" rows="" cols=""></textarea>	<br/>
	개인정보동의:<input type="checkbox" class="sAgree"> 동의 <br/>
	
	<button type="button" onclick="submit()" > 전송 </button>
	
	<h3> [R] 학생 정보 출력 </h3>
	<div class="sList" onclick="sList()">
	
	</div>

	<!-- 최신 jquery(js 라이브러리) import (ajax 함수 활용하기 위함) -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<!-- JS import (경로: /프로젝트명/[webapp폴더생략]/폴더/파일명 -->
	<script src="student.js" type="text/javascript"></script>

</body>
</html>