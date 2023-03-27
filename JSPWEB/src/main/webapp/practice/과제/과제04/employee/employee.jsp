<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<!-- 반응형 -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- CSS only -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
	<%@ include file = "delete.jsp" %>
	<%@ include file = "update.jsp" %>
	
	<div class="container">
	<h3> 인사관리 과제 </h3>
		<form class="signupForm">
		사원사진 : <input class="empImg" name="empImg" type="file"> <br>
		
		사원명 : <input class="empName" name = "empName" type="text"> <br>
		
		직급 : 	<select class="empGrade" name = "empGrade">
					<option> 사원 </option> <option> 대리 </option> <option> 부장 </option> <option> 과장 </option> <option> 대표 </option>
			  	</select> <br>
			
			  	
		고용형태 : <select class="empConstruct" name = "empConstruct">
					<option> 임용직 </option> <option> 정규직 </option> <option> 임원 </option>
			 	 </select> <br>	 
			 	 	  	
		부서 : 	<select class="empDepart" name = "empDepart">
					<option> 인사팀 </option> <option> 영업팀 </option> <option> 개발팀 </option>
			 	 </select>	<br>
		입사일 : <input class="empSdate" type="date" name = "empSdate"> <br>	
		
		퇴사일 : <input class="empLdate" type="date" name = "empLdate"> <br>
		
		퇴사사유 : <input class="empLcomment" type="text" name = "empLcomment"> <br>
		
			<button onclick="create_employee()" type="button"> 인사 등록 </button>
		</form>
	</div>	
	
	<div class="container">	
		<h3> 인사 출력 </h3>
		<button onclick="print_employee()" type="button"> 전체 출력</button>
		<button onclick="print_job()" type="button"> 직무(부서별) </button>
		<button onclick="print_leave()" type="button"> 퇴사자 </button>

		<table class="empList" border="1"></table>
	</div>		
		
	<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
	
	<!-- 부트스트랩 js  -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
	
	<script src="/JSPWEB/practice/과제/과제04/js/employee.js" type="text/javascript"></script>

</body>
</html>