<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/JSPWEB/apply/css/productwrite.css" rel="stylesheet">
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
			<!-- 첨부파일 여러개 ㅇ -->
			<input type="file" name="pFiles" multiple="multiple" accept="image/*">
			
			<div class="fileDrop"> 
				[드래그앤드랍] 첨부파일을 넣어주세요.
			</div>
			
			
			<!-- accept 이용하여 파일 업로드 시 이미지 파일만 허용 설정 // audio, video 등 설정 가능 -->
			<button onclick="onwrite()" class="btn btn-dark" type="button"> 제품 등록 </button>
			
			<!-- 첨부파일 1개 업로드 -->
			<!-- <input type="file" name="pFile" accept="image/*">  -->
		</form>
	</div>

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=db8620d22f203bb5f5678d7b78ad885a"></script>
	<script src="/JSPWEB/apply/js/product/write.js" type="text/javascript"> </script>

</body>
</html>