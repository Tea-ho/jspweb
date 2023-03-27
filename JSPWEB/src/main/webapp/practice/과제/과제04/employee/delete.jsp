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

	<link href="/JSPWEB/practice/과제/과제04/css/delete.css" rel="stylesheet">

</head>
<body>
	
	<!-- 모달 HTMLs -->
	<div class = "dmodal_wrap">
		<div class = "dmodal_box">
			<h3 class = "dmodal_title"> 회원 정보 삭제 </h3>
			<div class = "dmodal_content">
				해당 회원을 삭제하시겠습니까?
				<br> 사원번호: <input type="text" sclass="empNo">
			</div>
			<div class = "dmodal_btns">
				<button onclick = "doDelete()" class = "dmodal_check" type = "button"> 확인 </button>
				<button onclick = "deleteModalclose()" class = "dmodal_cancle" type = "button"> 취소 </button>
			</div>
		</div>
	</div>

	<!-- 1. 최신 jquery(js 라이브러리) import (ajax 함수 활용하기 위함) -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<!-- 부트스트랩 js  -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

	<script src="/JSPWEB/practice/과제/과제04/js/delete.js" type="text/javascript"> </script>

</body>
</html>