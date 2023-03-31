<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/JSPWEB/apply/css/point.css" rel="stylesheet">
</head>
<body>

	<%@ include file = "../header.jsp" %>
	<!-- 결제 시스템: 포트원 -->
	<div class="container">
		<button onclick="setpay(10000)" type="button"> 10000원 </button>
		<button onclick="setpay(50000)" type="button"> 50000원 </button>
		<button onclick="requestPay()" type="button"> 카드결제 </button>
	</div>
	
	<!-- 포트원 결제 JS -->
	<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
	<script src="/JSPWEB/apply/js/member/point.js" type="text/javascript"> </script>

</body>
</html>