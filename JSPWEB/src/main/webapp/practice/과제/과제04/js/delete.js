// console.log('연결확인');

function deleteModalOpen(){
	document.querySelector('.dmodal_wrap').style.display = 'flex';
}

function deleteModalclose(){
	document.querySelector('.dmodal_wrap').style.display = 'none';
}


// 직원 정보 삭제 메소드
function doDelete(){
	$.ajax({
		url: "/JSPWEB/employee",
		method: "delete",
		data: { "empNo" : document.querySelector('.empNo').value },
		success: (o)=>{
			console.log(o);
			if( o == 'true' ){
				alert('[성공] 해당 회원의 정보가 정상적으로 삭제되었습니다.')
				location.href="/JSPWEB/practice/과제/과제04/employee/employee.jsp"
			}
			else{ alert('[실패] 회원번호를 확인해주세요.') }
		}
	})
}