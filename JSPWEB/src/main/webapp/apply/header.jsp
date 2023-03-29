<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> TT </title>

	<!-- 반응형 -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- CSS only -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css">
	
	<link href="/JSPWEB/apply/css/modal.css" rel="stylesheet">
	<link href="/JSPWEB/apply/css/index.css" rel="stylesheet">
</head>
<body>
	
	<div class="container">
	
		<div class="header">
		
			<div class="mainlogo">
				<a href="/JSPWEB/apply/index.jsp">
					<img src="/JSPWEB/apply/img/logo.jpg">
				</a>
			</div>
			
			<ul class="mainmenu">
				<li class="dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false"> <a href="#"> About Us </a> </li>
					<ul class="dropdown-menu">
		            	<li><a class="subList dropdown-item" href="#"> Information </a></li>
		            	<li><a class="subList dropdown-item" href="/JSPWEB/apply/api/api03.jsp"> Recommend Restaurant </a></li>
		            	<li><a class="subList dropdown-item" href="/JSPWEB/apply/api/api02.jsp"> Parking lot </a></li>
		        	</ul>

				<li> <a href="#"> Coffee </a> </li>
				<li> <a href="#"> Menu </a> </li>
				<li> <a href="/JSPWEB/apply/board/list.jsp?cNo=1"> Notice </a> </li>
				<li> <a href="/JSPWEB/apply/board/list.jsp?cNo=2"> Community </a> </li>
				<li> <a href="/JSPWEB/apply/board/list.jsp?cNo=3"> QnA </a> </li>
<!-- 				<li> <a href="/JSPWEB/apply/board/list.jsp?cNo=4"> Know-how </a> </li> -->
				<li> <a href="/JSPWEB/apply/board/chatting.jsp"> Chat </a> </li>
				<li> <a href="/JSPWEB/apply/product/print.jsp"> test </a> </li>
			</ul>
			
			<div class="submenu">
			</div>
			
		</div>
	</div>
	
	<div class="msgbox">
		읽지 않은 메시지가 있습니다.
	</div>
	
	<!-- 1. 최신 jquery(js 라이브러리) import (ajax 함수 활용하기 위함) -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<!-- 부트스트랩 js  -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
	
	<!-- 모든페이지 공통 js -->
	<script src="/JSPWEB/apply/js/modal.js" type="text/javascript"> </script>
	<script src="/JSPWEB/apply/js/member/header.js" type="text/javascript"> </script>
</body>
</html>