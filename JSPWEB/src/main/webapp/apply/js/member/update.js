console.log('연결 확인')

if( memberInfo.mid == null ){
	alert('로그인 회원만 접근 가능한 메뉴입니다.')
	location.href="/JSPWEB/apply/member/login.jsp"
}

document.querySelector('.mid').innerHTML = memberInfo.mid;
document.querySelector('.memail').innerHTML = memberInfo.memail;
document.querySelector('.mimg').src = `/JSPWEB/apply/member/pimg/${ memberInfo.mimg == null ? 'default.png' : memberInfo.mimg }`;

function setUpdate(){
	
	// 1. 첨부파일 없을 경우
	/*
	let info = {
		'mpw': document.querySelector('.mpw').value,
		'newmpw': document.querySelector('.newmpw').value,
		'memail': document.querySelector('.memail').value
	};
	
	$.ajax({
		url: "/JSPWEB/apply/member",
		method: "put",
		data: info,
		success: (o) => {
			
		}
	})
	*/
	
	// 2. 첨부파일 있는 경우
	let updateForm = document.querySelectorAll('.updateForm')[0];
	let updateFormData = new FormData( updateForm );
	
	let defaultimg = document.querySelector('.defaultimg').checked;
	updateFormData.set("defaultimg", defaultimg);	
	
	$.ajax({
		url: "/JSPWEB/apply/member",
		method: "put",
		data: updateFormData,
		contentType: false,
		processData: false,
		success: (o) => {
			console.log(o);
			if( o == 'true' ){
				alert('[수정성공] 다시 로그인해주세요.');
				location.href="/JSPWEB/apply/member/logout.jsp"
			}
			else{ alert('[수정실패] 기존 비밀번호 확인해주세요.') }
		}
	})
}