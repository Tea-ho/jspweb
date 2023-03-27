<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%@ include file = "../header.jsp" %>
	<div class="container">
		
		<form class="writeForm">
			제품명: <input type="text" name="pName">
			제품설명: <input type="text" name="pComment"> 
			제품가격: <input type="text" name="pPrice">
			위치: 
			<div id="map" style="width:100%;height:350px;"></div>
			<button onclick="onwrite()" class="btn btn-dark" type="button"></button>
		</form>
	</div>

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=db8620d22f203bb5f5678d7b78ad885a"></script>
	<script src="/JSPWEB/apply/js/product/write.js" type="text/javascript"> </script>

</body>
</html>