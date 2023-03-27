console.log('연결 확인');

/*
	1. JS 정규표현식: 문자 특정 규칙, 패턴, 집합 표현에 사용하는 언어
	2. 관련 문법:
	1) /^ : 정규표현식 시작
	2) $/ : 정규표현식 끝
	3) [a-z] : 소문자 a-z패턴
	4) [A-Z] : 대문자 A-Z패턴
	5) [0-9] : 숫자 0-9 패턴
	6) [가-힣] : 한글 패턴
	7) { 최소길이, 최대길이 } : 문자열 길이 패턴
	
	3. 패턴검사 함수
	정규표현식.test( 데이터 ) : 패턴이 적합하면 true, 아니면 false 반환
	ex)
	/^[a-z]$/.test( qwe ) -- > true
	/^[a-z]$/.test( QWE ) -- > false
	
	4. 활용:
	[a-zA-Z] : 영문 입력
	[a-zA-Z0-9]: 영문 + 숫자 입력
	[a-zA-Z0-9가-힣]: 영문 + 숫자 + 한글 입력
	(?=.*[a-z]): 소문자 1개 이상
	(?=.*[A-Z]): 대문자 1개 이상
	(?=.*[0-9]): 숫자 1개 이상
	(?=.*[!@#$%^&*()_+-=`~]): 특수문자 1개 이상
*/


let checkconfirm = document.querySelectorAll('.checkconfirm');
// 해석1: 회원가입에 필요한 정보를 각 양식에 맞추어 정규식 표현으로 유효성 검사에 사용하기 위함.
// 해석2: checkconfirm으로 설정된 모든 클래스를 JS 배열에 저장
// 해석3: 필요에 맞게 저장되어 있는 배열 사용할 수 있음

// 아이디 유효성검사
function idcheck(){
		console.log('함수 작동 확인');
	let mid = document.querySelector('.mid').value;
		console.log(mid);
	
	let midj = /^[a-z0-9]{5,30}$/
		console.log(midj.test(mid));
		
	if( midj.test(mid) ){

		$.ajax({
			url: "/JSPWEB/apply/confirm",
			method: "get",
			data: {"mid" : mid},
			success: (o) => {
				console.log( '작동 확인' );
				
				if(o == 'true'){ checkconfirm[0].innerHTML = '사용중인 아이디'; }
				else{ checkconfirm[0].innerHTML = '사용가능'; }
			}
		});
	}
	else{
		checkconfirm[0].innerHTML = '영소문자 + 숫자 조합으로 작성[아이디 길이: 5~30]';
	}
}

// 비밀번호 유효성검사
function pwcheck(){
	let mpw = document.querySelector('.mpw').value;
	console.log(mpw);
	
	let mpwj = /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z\d]{5,20}$/
	// 해석: 영문 대소문자 1개 이상 포함, 숫자 1개 이상 포함 => 영문숫자 조합으로 자리수 5~20 범위안에 들어오면 true
		console.log(mpwj);
	if( mpwj.test(mpw) ){ checkconfirm[1].innerHTML = '사용가능' }
	else{ checkconfirm[1].innerHTML = '사용 불가[영대소문자+숫자 조합! (자리수: 5~20 범위 내)]' }
}

// 비밀번호(확인) 유효성검사
function pwccehck(){
	let mpw = document.querySelector('.mpw').value;
	let mpwc = document.querySelector('.mpwc').value;
	
	let mpwcj = /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z\d]{5,20}$/
	
	if( mpwcj.test(mpwc) ){
		if( mpw != mpwc ){ checkconfirm[2].innerHTML = '비밀번호 불일치' }
		else{ checkconfirm[2].innerHTML = '사용가능' }
	}
	else{ checkconfirm[2].innerHTML = '사용 불가[영대소문자+숫자 조합! (자리수: 5~20 범위 내)]' }
}

// 이메일 유효성검사
function emailcheck(){
	let memail = document.querySelector('.memail').value;
	
	let memailj = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-]+$/
	
	if(memailj.test(memail)){
		checkconfirm[3].innerHTML = '인증해주세요.'; 
		document.querySelector('.authbtn').disabled = false;
	
	}
	else{
		checkconfirm[3].innerHTML = '이메일 형식으로 입력';
		document.querySelector('.authbtn').disabled = true; // 인증 버튼 사용 불가 
	}
}

// 이메일 인증함수
function getauth(){
	// console.log('함수 작동')
	
	// ajax JAVA에게 이메일 전송 후 인증코드 방기! [실제 인증 절차 적용할 때 코드!]
	/*
	$.ajax({
		url:"/JSPWEB/apply/Email",
		method: "post",
		data: {  "memail" : document.querySelector('.memail').value },
		success: ( o )=>{
			console.log(o);
			
			let html =	`
				<input type="text" class="authinput" placeholder="인증코드">
				<div class="timebox"> </div>
				<button onclick="authconfirm()" class="authconfirmbtn" type="button"> 확인  </button>
				`
			document.querySelector('.authbox').innerHTML = html;
	
			// 타이머 함수 적용
			auth = o;
			timer = 120;
			settimer();
		}	
	})
	*/
	
	let html =	`
				<input type="text" class="authinput" placeholder="인증코드">
				<div class="timebox"> </div>
				<button onclick="authconfirm()" class="authconfirmbtn" type="button"> 확인  </button>
				`
	document.querySelector('.authbox').innerHTML = html;
	
	// 타이머 함수 적용
	atuh = 1234;
	timer = 120;
	settimer();
}

let auth = 0;
let timer = 0;
let timerInter; 

// 타이머 함수 [이메일]
function settimer(){
	timerInter = setInterval( ()=>{
		let min = parseInt(timer / 60); let sec = parseInt(timer % 60);
		
		min = min < 10 ? "0" + min : min;
		sec = sec < 10 ? "0" + sec : sec;
		
		let timeHTML = min + ":" + sec;
		document.querySelector('.timebox').innerHTML = timeHTML;
		timer--;
		
		if( timer < 0 ){
			clearInterval( timerInter )
			checkconfirm[3].innerHTML = "인증 실패";
			document.querySelector('.authbox').innerHTML = "";
			
		}
	} , 1000 )
}

// 인증코드 확인
function authconfirm(){
	console.log('함수 동작 확인');
	
	let authinput = document.querySelector('.authinput').value;
	// 2. 발급된 인증코드 와 입력한 인증코드 비교 
	if( auth == authinput ){ // 인증코드 일치 
		clearInterval( timerInter );
		document.querySelector('.authbox').innerHTML = "";
		document.querySelector('.authbtn').innerHTML = "완료";
		document.querySelector('.authbtn').disabled = true;
		checkconfirm[3].innerHTML = '사용가능';
	}else{ // 인증코드 불일치 
		checkconfirm[3].innerHTML = '인증코드 일치하지 않습니다.';
	}
}

// 첨부파일 미리보기 기능
function premimg( e ){
	console.log('첨부파일 변경');
	console.log( e.files[0]);
	console.log( document.querySelector('.mimg').files[0] );
	
	// 1. 파일 읽기 클래스
	let file = new FileReader();
	
	// 2. 첨부된 파일 읽어오기
	file.readAsDataURL( e.files[0] );
	
	// 3. 읽어온 파일 꺼내오기 [형식: 바이트]
	file.onload = ( e ) => {
		console.log(e.target.result)
		document.querySelector('.premig').src = e.target.result;
	}
}

// 회원가입
function signup(){
	console.log('함수 작동 확인');
	
	// 첨부파일 없을 경우 사용하는 폼
	/*
	let info = {
		mid : document.querySelector(".mid").value,
		mpw : document.querySelector(".mpw").value,
		mpwc : document.querySelector(".mpwc").value,
		memail : document.querySelector(".memail").value,
		mimg : document.querySelector(".mimg").value
	}
	
	$.ajax({
		url: "/JSPWEB/apply/member",
		method: "post",
		data: info,
		success: ( o )=>{
			console.log('정상 작동')
			
			if( o ){
				alert('회원가입 성공');
				location.href="/JSPWEB/apply/index.jsp"
			} else{ alert('회원가입 실패') }
		}
	})
		console.log(info);
	*/
	
	let count = 0;
	for( let i = 0; i<checkconfirm.length; i++ ){
		if( checkconfirm[i].innerHTML == '사용가능' ){ count++; }
	}
	if( count != 4 ) { alert('정상적으로 입력되지 않은 데이터가 있습니다.'); return; }
	
	
	
	
	let signupForm = document.querySelectorAll('.signupForm')[0];
	
	let signupFormData = new FormData( signupForm );
		console.log(signupFormData);
	
	
	// 첨부파일 있을 경우 사용하는 폼: html file input 직접적으로 조작 불가능함.
	

	
	// 첨부파일 있을 때 추가되는 속성
	$.ajax({
		url: "/JSPWEB/apply/member",
		method: "post",			// 첨부파일은 post/put으로만 전송 가능
		data: signupFormData,	// FormData 객체 전송
		contentType: false,		
		processData: false,		
		// 기본값: true [의미: application/x-www-form-urlencoded 형식으로 전송 (문자 전송)]
		// 설정 해제: false [의미: multipart/form 형식으로 전송]
		success: ( o )=>{
				console.log('정상 작동');
				console.log(o);
			
			if( o ){
				// alert('회원가입 성공');
				openModal();
				//location.href="/JSPWEB/apply/index.jsp"
			} else{ alert('회원가입 실패') }
		}
	})
}