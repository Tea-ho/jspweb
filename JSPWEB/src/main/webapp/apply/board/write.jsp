<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">

	<link href="/JSPWEB/apply/css/write.css" rel="stylesheet">

</head>
<body>

	<%@ include file = "../header.jsp" %>
	
	<!-- JSP에서 미로그인 직접 제어하는 방법 -->
	<%
		Object o = request.getSession().getAttribute("login");
		if( o == null ){
			response.sendRedirect("/JSPWEB/apply/member/login.jsp");
		}
	%>
	
	<div class="container">
		
		<h3 class="title"> 글쓰기 </h3>
		<form class="writeForm">
			<select class="cNo form-select" name="cNo" aria-label="Default select example">
				<option selected> 게시판을 선택해 주세요. </option>
				<option value="1"> 공지사항 </option>
				<option value="2"> 커뮤니티 </option>
				<option value="3"> QnA </option>
				<!-- <option value="4"> 노하우 </option> -->
			</select>
			<input class="bTitle form-control" name="bTitle" type="text" placeholder="제목을 입력해주세요" > 
			<textarea class="bContent" name="bContent" id="summernote" rows="3" cols="3"></textarea>
			<input class="bFile form-control" name="bFile" type="file">
			<div class="wirteButtonWarp">
				<button onclick="doWrite()"  class="writeBtn btn btn-dark" type="button"> <i class="fas fa-pencil-alt"></i> 쓰기 </button>
			</div>
		</form>
	
	</div>
	
	<!-- 
		HTML form [ 동기식 소통만 가능(페이지전환 필수) ]
		<form action="통신할 URL" method="HTTP메소드"></form>
		<button> type 생략 시, submit으로 자동 설정되어 form 형식으로 보내는 것으로 인식됨.
		위와 같은 형식으로 선언 시, 스크립트 없이 소통 가능함
		그러나, 프론트 쪽에서 유효성 검사 적용이 어렵고, 동기식으로만 사용이 가능하다는 단점이 있다.	
		
		JS AJAX [ 동기식, 비동기식 소통 가능 ]
		
	-->

	<!-- include libraries(jQuery, bootstrap) -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

	<!-- include summernote css/js -->
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	
	<script src="/JSPWEB/apply/js/board/write.js" type="text/javascript"> </script>
	
</body>
</html>