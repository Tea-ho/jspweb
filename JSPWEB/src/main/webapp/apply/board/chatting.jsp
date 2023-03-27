<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/JSPWEB/apply/css/chatting.css" rel="stylesheet">
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
	
	<div class="container chattingwrap">
		
		<div class="connectlistbox"> <!-- 접속 명단 표시 구역 -->

		</div>
		
		<div class="cattingBox">
			<!-- 채팅 내용물 표시되는 구역 -->
			<div class="contentbox">
				
				<!-- 보낼 때 메시지 정보: 시간, 내용 -->

				
				<!-- 알람 -->
	
				
				<!-- 받을 때 메시지 정보: 프로필, 시간, 내용-->

				
				
			</div>
		
			<!-- 채팅 입력창 -->
			<textarea class="messagebox form-control" rows="" cols="">
			</textarea>
			
			<!-- 전송 버튼 -->
			<div class="ButtonWarp">
				<button class="emobtn" type="button" data-bs-toggle="dropdown" > <i class="far fa-smile"></i> </button>
				<div class="dropdown-menu emolist">
				</div>
				<button onclick="sendMessage()" class="sendbtn btn btn-dark" type="button"> 전송 </button>
			</div>
		</div>	
	</div>
	
	<script src="/JSPWEB/apply/js/board/chatting.js" type="text/javascript"></script>

</body>
</html>