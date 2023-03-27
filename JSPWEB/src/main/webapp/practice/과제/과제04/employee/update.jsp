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

	<link href="/JSPWEB/practice/과제/과제04/css/update.css" rel="stylesheet">
	
</head>
<body>
	
	<!-- 모달 HTMLs -->
	<div class = "umodal_wrap">
		<div class = "umodal_box">
			<h3 class = "umodal_title"> 회원 정보 수정 </h3>
			<div class = "umodal_content">
				<form class="updateForm">
					<div>
						<div>
							<img class="empImg" alt="" src="">
							<br>프로필 변경: <input type="file" name="newImg">
						</div>
						<div>
							<div> 사원번호 </div>
							<div class="empNo" name="empNo"> </div>
						</div>
						<div>
							<div> 사원명 </div>
							<input class="newName" name="newName">
						</div>
						<div>
							<div> 직급 </div>
							<input class="newGrade" name="newGrade"> 
						</div>
						<div>
							<div> 고용형태 </div>
							<input class="newConstruct" name="newConstruct"> 
						</div>
						<div>
							<div> 부서 </div>
							<input class="newDepart" name="newDepart">
						</div>
						<div>
							<div> 입사일 </div>
							<input class="newSdate" name="newSdate">
						</div>
						<div>
							<div> 퇴사일 </div>
							<input class="newLdate" name="newLdate">
						</div>
						<div>
							<div> 퇴사사유 </div>
							<input class="newLcomment" name="newLcomment">
						</div>
						<div class = "umodal_btns">
							<button onclick="doUpdate()" type="button"> 정보수정 </button>
							<button onclick="updateModalClose()" class="modal_cancle" type="button"> 취소 </button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>	

	<!-- 1. 최신 jquery(js 라이브러리) import (ajax 함수 활용하기 위함) -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<!-- 부트스트랩 js  -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
	
	<script src="/JSPWEB/practice/과제/과제04/js/update.js" type="text/javascript"> </script>

</body>
</html>