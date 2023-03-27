<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height:100%">
<head >
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="height:100%">

	<%@include file = "/apply/header.jsp" %>
	
	<div style="position: fixed; left: 10px; top: 40%; z-index: 999; width: 100px; height: 100px; background-color: white;">
		검색창
	</div>
	
	<div style="display: flex; width: 100%; height: 80%;">
		<div id="map" style="width:80%;height:100%;"></div>
		<div class="productlistbox" style="width: 20%;">
			사이드바
		</div>
	</div>

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b7c0acb1395b016fc6b2661dad73840f&libraries=clusterer"></script>
	<script src="/JSPWEB/apply/js/product/print.js" type="text/javascript"> </script>
	
</body>
</html>