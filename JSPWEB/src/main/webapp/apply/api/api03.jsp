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
		<h3> 주변 모범음식점 정보 </h3>
		<!-- 카카오 지도 표시 구역 -->
		<div id="map" style="width:500px;height:400px;"></div>
		<!-- 클릭 마크업 -->
		<div id="clickLatlng"></div>
	</div>

	<!-- 모달 HTMLs -->
	<div class="modal_wrap">
		<div class="modal_box">
			<h3 class="modal_title"></h3>
			<div class="modal_content">
			</div>
			<div class="modal_btns">
				<button onclick="closeModal()" class="modla_cancle" type="button"> 닫기 </button>
			</div>
		</div>
	</div>
	
	<!-- 카카오 맵 -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=db8620d22f203bb5f5678d7b78ad885a&libraries=clusterer,services"></script>

	<script src="/JSPWEB/apply/js/api/api03.js" type="text/javascript"> </script>

</body>
</html>