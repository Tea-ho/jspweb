// console.log('연결 확인') // --- 확인 완료

// 외원정보: 로그인 안되어 있으면 불가능!
if(memberInfo.mid == null ){
	alert('로그인된 회원만 확인 가능합니다.')
	location.href="/JSPWEB/apply/member/login.jsp"
}

// 포인트
// login.js와 같은 servlet 사용
// 이 경우 동기식 통신이 기본으로 설정되어 있기 때문에, 비동기 처리하지 않으면 응답이 늦음.
console.log(memberInfo);

document.querySelector('.mid').innerHTML = memberInfo.mid;
document.querySelector('.memail').innerHTML = memberInfo.memail;
document.querySelector('.mpoint').innerHTML = memberInfo.mpoint;
document.querySelector('.mimg').src='/JSPWEB/apply/member/pimg/'+(memberInfo.mimg==null?'default.png':memberInfo.mimg);

// 회원탈퇴 메소드
function setDelete(){
	$.ajax({
		url: "/JSPWEB/apply/member",
		method: "delete",
		data: { "mpw" : document.querySelector('.mpw').value },
		success: (o)=>{
			console.log(o);
			if( o == 'true' ){
				alert('[회원탈퇴 성공]이용해주셔 감사합니다.')
				location.href="/JSPWEB/apply/index.jsp"
			}
			else{ alert('[회원탈퇴 실패]비밀번호 불일치') }
		}
	})
}

// 회원정보 수정 메소드
function setUpdate(){
	
}