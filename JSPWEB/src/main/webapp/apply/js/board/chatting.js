/*
	JS WebSocket: JS에서 제공하는 클래스
	
	1. 관련 용어
	- 소켓: 두 프로그램간의 양방향 통신 종착점[도착지]
	- 소버소켓: 서버가 가지고 있는 소켓
	- clientsockat: 클ㄹ라이언트가 가지고 있는 소켓
	
	2. 사용 방법
	1) 소켓 객체 만들기
	let clientsockat = new WebSocket('ws://ip:포트번호/프로젝트명/서버소켓URL');
	2) JAVA ServerSocket 만들기
	 (1) 클래스 생성
	 (2) 서버 소켓으로 선언
	 *선언방법: 클래스 위에 @ServerEndpoint("/서버소켓경로의 URL") 작성
	3) 클라이언트 소켓 - 서버소켓 연동
	 (1) 서버 소켓[엔드포인트/java]에 @OnOpen 선언하여 메소드 생성: clientsockat이 접속하면 매핑해주는 역할
	 (2) 클라이언트 소켓[js]에서는 onopen 메소드 이용
	4) 클라이언트 소켓 <-> 서버 소켓 메시지 송수신
	 (1) 클라이언트 소켓 -> 서버 소켓
	 (2) 클라이언트 소켓 <- 서버 소켓
	
*/

let contentbox = document.querySelector('.contentbox');

// 1. (필수옵션) clientsockat 생성
let clientsockat = new WebSocket('ws://localhost:8080/JSPWEB/Chatting/'+memberInfo.mid);
// 해석: 식별자 회원 아이디 사용
// console.log(clientsockat); //----- 확인 완료
// WebSocket 클래스에서 제공해주는 메소드: onopen
clientsockat.onopen = function(e){ inputChat(e); }
// WebSocket 클래스에서 제공해주는 메소드: onmessage
clientsockat.onmessage = function(event){ onMessage(event); }
// 역할: 서버 소켓의 @OnMessage에서 보내는 메시지 송신
// 해석1: 매개변수 event에 관련 정보를 담아 송신함
// 해석2: 메시지 정보는 event 객체의 data에 담겨 있음
clientsockat.onclose = function( event ){ onClose( event ); }


// 2. (선택옵션) clientsockat 접속 시, 이벤트/함수 정의
function inputChat(e){
	console.log(e);
	sendEmo(memberInfo.mid + '님이 채팅방에 입장하셨습니다.', "alarm");
}

// 3. (필수옵션) clientsockat이 서버소켓에게 메시지 보내기
// 메시지 보내는 시점: 1)보내기 버튼 클릭 시, 2)엔터 키 눌렀을 때
function sendMessage(){
		// console.log('sendMessage 연결 확인')
	let msgbox = document.querySelector('.messagebox').value;
	
	// JSON형식의 문자열 타입으로 전송 (이모티콘과 구분하기 위해 객체화)
	let msg = {
		type: 'msg',
		data: msgbox
	}
	
	// WebSocket 클래스에서 제공해주는 메소드: send
	clientsockat.send( JSON.stringify(msg) );
	// 역할: 매개변수(메시지)를 서버 소켓의 @OnMessage로 정의한 메소드에 전송
	// JSON형식으로 객체를 string 타입으로 변환
	
	document.querySelector('.messagebox').value = '';
}

// 메시지 타입 구분
function msgType( msg ){
	let json = JSON.parse(msg);
	let html = '';
	if( json.type == 'msg' ){
		html += `<div class="content"> ${json.data} </div>`
		console.log(json.data);
	} else if( json.type == 'emo' ){
		html += `<div class="content imojicontent"> <img class="Sendemoji" alt="" src="/JSPWEB/apply/img/imoji/emo${json.data}.gif"> </div>`
		console.log(json.data);
	}
	return html;	
}

// 4. (필수옵션) 서버 메시지 받기
function onMessage( event ){
	
	console.log(event);
	// 출력값: event 객체 전체 
	console.log(event.data);
	// 출력값: event 객체 중 data 관련 정보
	// 특이점: 객체가 아닌 String으로 확인됨
	// 원인: Chatting.java에서 JSON으로 변환하여 메시지 전달함, 기본적으로 JSON은 String으로 설정되어 있음.
	// 참고: servlet에서는 setContentType 메소드를 제공하기 때문에, response.setContentType("application/json");을 통해 바로 형 변환하여 사용가능 
	console.log( JSON.parse( event.data ) );
	// 해결: JSON.parse( 데이터 );
	// 출력값: event 객체 중 data 관련 정보 객체로 출력
	
	let data = JSON.parse( event.data );
		console.log( data );
	
	console.log( Array.isArray( data ) );
	if( Array.isArray( data ) ){
		alert('접속 명단 확인')
		
		let html = '';
		data.forEach( ( o )=>{
			html += `
					<div class="connectbox">
						<div> <img alt="" src="/JSPWEB/apply/member/pimg/${ o.frommimg==null ? 'default.png':o.frommimg }" class="hpimg"> </div>
						<div class="name"> ${o.frommid} </div>
					</div>
					`
		})
		document.querySelector('.connectlistbox').innerHTML = html;
		
	} else if( JSON.parse(data.msg).type == "alarm" ){
			contentbox.innerHTML += ` 
									<div class="alarm">
										<span> ${ JSON.parse(data.msg).data } </span>
									</div>
									`
	} else if( data.frommid == memberInfo.mid ){
		contentbox.innerHTML += `
								<div class="secontent">
									<div class="date"> ${ data.time } </div>
									${ msgType(data.msg) } 
								</div>
								`
	}else{
		contentbox.innerHTML += `
								<div class="tocontent">
									<div> <img src="/JSPWEB/apply/member/pimg/${ data.frommimg==null ? 'default.png':data.frommimg }" class="hpimg"> </div>
									<div class="rcontent">
										<div class="name"> ${ data.frommid } </div>
										<div class="contentdate">
											${ msgType(data.msg) } 
											<div class="date"> ${ data.time } </div>
										</div>
									</div>
								</div>
								`
	}
	// ---------------------------------------- 스크롤 하단으로 내리기 ----------------------------------------
	// 참고사항: scroll 사용하면 지원해주는 기능 (scrollTop: 스크롤 위치 ,scrollHeight: 스크롤 높이)
	let top = contentbox.scrollTop;
		console.log(top);
	
	let height = contentbox.scrollHeight;
		console.log(height);
	
	contentbox.scrollTop = contentbox.scrollHeight;
	
}

// 5. 서버와 연결이 끊겼을 때  [클라이언트 소켓 객체 초기화]
function onClose( event ){
	// console.log('연결해제');
	// 서버에서 직접 메시지를 보내줘야함.
}

// 6. (추가 옵션) Enter key 이용하여 메시지 전송
document.addEventListener('keyup', (e)=>{
		console.log(e);
	if( e.keyCode == 13 ){
		sendMessage();
	}
});

// 7. 이모티콘 출력
getemo();
function getemo(){
	let html = '';
	for(let i = 1; i <= 43; i++){
		html +=`
				<img onclick="sendEmo( ${i}, 'emo' )" class="emoji" alt="" src="/JSPWEB/apply/img/imoji/emo${i}.gif" width="23%">
				`
	}
	document.querySelector('.emolist').innerHTML = html;
}

function sendEmo( msgbox, type ){
	let msg = {
		type: type,
		data: msgbox
	}
	
	// WebSocket 클래스에서 제공해주는 메소드: send
	clientsockat.send( JSON.stringify(msg) );
}


