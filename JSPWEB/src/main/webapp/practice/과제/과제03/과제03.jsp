<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h3> 제품 등록 </h3>
	제품명: <input class="pTitle" type="text"> <br> 
	가격:  <input class="pPrice" type="text"> <br>
	제품설명: <input class="pContent" type="text"> <br> 
	<button onclick="onAdd()" type="button"> 제출 </button>
	
	<h3> 제품 출력 </h3>
	<table class="pList" border = "1">
	
	</table>

	<!-- 1. 최신 jquery(js 라이브러리) import (ajax 함수 활용하기 위함) -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<!-- 2. 사용자 정의 JS -->
	<script src="과제03.js" type="text/javascript"></script>
</body>
</html>