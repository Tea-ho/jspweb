<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head >
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/JSPWEB/apply/css/test/productprint.css" rel="stylesheet">
</head>
<body>

	<%@include file = "/apply/header.jsp" %>
	
	<!-- 모달창 -->
	<div class="searchbox">
		검색창
	</div>
	
	<div class="contentbox" >
		<!-- 지도 -->
		<div id="map" style="width:75%;height:100%;"></div>
		<!-- 사이드바 -->
		<div class="produclistbox">
		</div>
	</div>
	

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b7c0acb1395b016fc6b2661dad73840f&libraries=clusterer"></script>
	<script src="/JSPWEB/apply/js/product/print.js" type="text/javascript"> </script>
	
</body>
</html>