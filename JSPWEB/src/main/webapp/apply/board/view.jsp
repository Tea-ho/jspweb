<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/JSPWEB/apply/css/view.css" rel="stylesheet">
</head>
<body>
	<%@ include file = "../header.jsp" %>
	<%	// JSP 스크립트문 이용
		// 1. JSP 이용하여 http url 변수 가져오기
		String bNo = request.getParameter("bNo");
	%>

	<div class="container">
		
		<h3> 게시물 개별 조회/보기 </h3>
		<div class="btop">
			<div class="btopT">
	
				<div class="bTitlewrap">
					<div class="bTitle">  </div>
				</div>
				
				<div class="bNowrap">
					<div> No. </div>
					<div class="bNo"><%=bNo%></div>
				</div>
		
			</div>
			
			<div class="btopB">
				<div class="bImgwrap">
					<div class="pimgbox">  </div>
					<div class="pmid">  </div>
				</div>
				<div class="bDatewrap">
					<div> 작성일 </div>
					<div class="bDate">  </div>
				</div>		
				
				<div class="bInfowrap">
					<div class="infoBox">  </div>
				</div>
			</div>
		</div>
		
		
		
		<div class="bmiddle">
			<div class="bFilewrap">
				<div class="bFile">  </div>
			</div>
			<div class="bContent">  </div>
		</div>
		
		<div class="bReplywrap">
			<div class="replyTitle"> </div>
			<div class="bReplyListBox">
			</div>
			
			<div class="bReplyBox">
				<textarea class="rContent form-control" id="rContent" rows="3" cols="3" placeholder="내용을 입력해주세요"></textarea>
				<div class="rwritebtnBox">
					<button onclick="rwrite()" class="rwritebtn btn btn-secondary" type="button"> 댓글작성 </button>
				</div>
			</div>
		</div>
		
		
		<div class="bupdateBtn">
		</div>

	</div>
	
	<script src="/JSPWEB/apply/js/board/view.js" type="text/javascript"> </script>
		
</body>
</html>