console.log('작동 확인');

// 로그인 메소드
function login(){
	
	let mid = document.querySelector('.mid').value;
	let mpw = document.querySelector('.mpw').value;
	
	$.ajax({
		url: "/JSPWEB/apply/login",
		method: "post",
		data: {"mid": mid, "mpw": mpw},
		success: (o) => { 
				// console.log(o)
			if( o == 'true' ){ location.href="/JSPWEB/apply/index.jsp" }
			else{ document.querySelector('.checkconfirm').innerHTML = '회원정보가 다릅니다.' }
		}
	})
}

// 아이디 찾기
function findID(){
	// console.log('연결 확인');
	
	let memail = document.querySelector('.memail').value;
	
	$.ajax({
		url:"/JSPWEB/apply/Find",
		method: "get",
		data: {"type": 1, "memail" : memail},
		success: (o)=>{
			console.log(o)
			if ( o == 'false'){
				document.querySelector('.checkconfirm').innerHTML = '동일한 회원정보가 없습니다.';
			}
			else{
				document.querySelector('.checkconfirm').innerHTML = 'ID: ' + o;
			}
		}
	})	
}

// 비밀번호 찾기
function findPW(){
	// console.log('연결 확인');
	
	// 배열 생성하여 전송! [아이디 찾기에서는 데이터별로 전송]
	let info = {
		type: 2,
		mid: document.querySelector('.mid').value,
		memail: document.querySelector('.memail').value
	}
	
	$.ajax({
		url:"/JSPWEB/apply/Find",
		method: "get",
		data: info,
		success: (o)=>{
			console.log(o)
			if ( o == 'false'){
				document.querySelector('.checkconfirm').innerHTML = '동일한 회원정보가 없습니다.';
			}
			else{
				document.querySelector('.checkconfirm').innerHTML = '임시비밀번호를 등록된 이메일로 전송하였습니다.';
				alert( '임시비밀번호:' + o );
			}
		}
	})
}