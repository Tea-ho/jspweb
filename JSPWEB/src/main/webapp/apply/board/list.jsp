<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/JSPWEB/apply/css/list.css" rel="stylesheet">
</head>
<body>

	<%@ include file = "../header.jsp" %>
	
	<%	// HTTP 
		String cNo = request.getParameter("cNo");
	%>
	
	<input type="hidden" class="cNo" value="<%=cNo%>">
	
	
	<div class="container">
		
		<h3 class="cName"></h3>
		<div class="searchSetBox">
			<div class="searchCount"></div>
			<select onchange="setlistsize()" class="listsize">
				<option value="5"> 5개씩 </option>
				<option value="10"> 10개씩 </option>
				<option value="15"> 15개씩 </option>
			</select>
		</div>
		
		<table class="boardTable table table-hover">
		</table>
		
		<div class="writeBtn">
		<a href="write.jsp"> <button class="btn btn-secondary" type="button"> <i class="fas fa-pencil-alt"></i> 글쓰기 </button> </a>
		</div>
		
		<!-- 페이징 처리 -->
		<div class="pageBox">
		</div>
	
		<!-- 검색창 -->
		<div class="searchBox">
			<select class="key">
				<option value="b.bTitle"> 제목 </option>
				<option value="b.bContent"> 내용 </option>
				<option value="m.mId"> 작성자 </option>
			</select>
			<input class="keyword" type="text">
			<button onclick="onSearch()" class="btn btn-dark" type="button"> 검색 </button>
			<button onclick="offSearch()" class="btn btn-dark" type="button"> 전체보기 </button>
		</div>
	
	</div>
	
	<script src="/JSPWEB/apply/js/board/list.js" type="text/javascript"> </script>
	
</body>
</html>