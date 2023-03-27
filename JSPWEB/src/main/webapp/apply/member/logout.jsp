<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 모든 세션 지우기 -->
	<% request.getSession().invalidate(); %>
	
	<!-- 특정 세션의 속성 지우기 -->
	<% request.getSession().setAttribute("login", null); %>
	
	<script type="text/javascript"> location.href="/JSPWEB/apply/index.jsp" </script>
</body>
</html>