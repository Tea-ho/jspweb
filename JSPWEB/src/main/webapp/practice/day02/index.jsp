<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3> [C] HTML -> JS -> servlet -> dao -> DB </h3>
	
	data1[문자열]: <input type="text" 		class="data1"> <br/>
	data2[패스워드]: <input type="password" class="data2"> <br/>
	data3[실수]: <input type="text" 		class="data3"> <br/>
	data4[정수]: <input type="number" 	class="data4"> <br/>	
	data5[날짜]: <input type="date" 		class="data5"> <br/>
	data6[날짜/시간]: <input type="datetime-Local" class="data6"> <br/>
	data7[선택버튼]: <input type="radio" name="data7" value="남"> 남 <input type="radio" name="data7" value="여"> 여 <br/>
	
	data8[체크상자]: <input type="checkbox" class="data8"> 승인 <br/>
	data9[목록상자]: <select class="data9">
					<option> 안산 </option> <option> 수원 </option> <option> 안양 </option>
					<option> 시흥 </option> <option> 용인 </option> <option> 과천 </option>
				 </select> <br/>
	data10[긴글]: <textarea class="data10" rows="" cols=""></textarea>
	
	<button type="button" onclick="submit()"> 전송 </button>
	<h3> [R] DB -> dao -> servlet -> JS -> HTML </h3>
	<div onclick="getData()" class="ex1_box">  </div>
	


	<!-- 1. 최신 jquery(js 라이브러리) import (ajax 함수 활용하기 위함) -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<!-- 2. 사용자 정의 JS -->
	<script src="index.js" type="text/javascript"></script>
	
</body>
</html>