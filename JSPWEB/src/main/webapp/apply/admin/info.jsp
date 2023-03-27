<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/JSPWEB/apply/css/adminInfo.css" rel="stylesheet">
</head>
<body>
	
	<%@ include file = "../header.jsp" %>
	
	<div class="container">
		<h3 class="infoTitle"> 회원 리스트 </h3>
		
		<div class="searchSetBox">
			<div class="searchCount"></div>
			<select onchange="setlistsize()" class="listsize">
				<option value="5"> 5개씩 </option>
				<option value="10"> 10개씩 </option>
				<option value="15"> 15개씩 </option>
			</select>
		</div>
		
		<table class="mList boardTable table table-hover">
			
		</table>
		
		<!-- 페이징 처리 -->
		<div class="pageBox">
		</div>
		
		<!-- 검색창 -->
		<div class="searchBox">
			<select class="key">
				<option value="mno"> 회원번호 </option>
				<option value="mid"> 아이디 </option>
				<option value="memail"> 이메일 </option>
			</select>
			<input class="keyword" type="text">
			<button onclick="onSearch()" class="btn btn-dark" type="button"> 검색 </button>
			<button onclick="offSearch()" class="btn btn-dark" type="button"> 전체보기 </button>
		</div>
		
		
	</div>

	<!-- 1. 최신 jquery(js 라이브러리) import (ajax 함수 활용하기 위함) -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

	<script src="/JSPWEB/apply/js/admin/info.js" type="text/javascript"> </script>

</body>
</html>