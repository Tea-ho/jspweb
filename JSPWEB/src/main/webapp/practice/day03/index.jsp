<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h3> HTTP 메소드 </h3>
	<button onclick="doPOST()" type="button"> doPOST </button>
	<button onclick="doGET()" type="button"> doGET </button>
	<button onclick="doPUT()" type="button"> doPUT </button>
	<button onclick="doDELETE()" type="button"> doDELETE </button>

	<h3> 활용 예쩨: 방명록 </h3>
	내용:		<input type="text" class="content">	<br>
	작성자:	<input type="text" class="writer">	<br>
	<button onclick="onWrite()" type="button"> 방문록 등록 </button>
	
	<table class="btable" border = "1"></table>


	<!-- 1. 최신 jquery(js 라이브러리) import (ajax 함수 활용하기 위함) -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<!-- 2. 사용자 정의 JS -->
	<script src="index.js" type="text/javascript"></script>

</body>
</html>