console.log('연결확인');

console.log( memberInfo );
/*
if( memberInfo.mid == null ){
	alert('로그인 회원만 접근 가능한 메뉴입니다.')
	location.href="/JSPWEB/apply/member/login.jsp"
}
*/
// 해석1: 변수 memberInfo는 header에 연결된 heard.js에서 생성함 
// 해석2: header include하고 있기 때문에 연결해서 사용 가능함


function doWrite(){
	let writeForm = document.querySelectorAll('.writeForm')[0];
	
	let writeFormData = new FormData( writeForm );
	
	$.ajax({
		url: "/JSPWEB/apply/Write",
		method: "post",			
		data: writeFormData,	
		contentType: false,		
		processData: false,		
		success: ( o )=>{
			console.log('정상 작동');
			console.log(o);
			
			if( o == 'true' ){
				alert('[알림]글쓰기 성공')
				location.href="/JSPWEB/apply/board/list.jsp?cNo="+document.querySelector('.cNo').value;
			}
			else{ alert('[알림]글쓰기 실패') }

		}
	})
}

$(document).ready(function() {
  $('#summernote').summernote({
    height: 400,
	placeholder: '내용을 입력해주세요.'
  });
});
