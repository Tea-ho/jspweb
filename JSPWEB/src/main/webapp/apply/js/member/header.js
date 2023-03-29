console.log('작동확인');

let memberInfo = null;

getLogin();
function getLogin(){
	$.ajax({
		url: "/JSPWEB/apply/login",
		async: false,
		method: "get",
		success: (o)=>{
			
			memberInfo = o;
			console.log(o);
			
			let html = ``;
			
			if ( o == null){
				html += `<a class="submenuRight" href="/JSPWEB/apply/member/signup.jsp"> join us </a>`;
				html += ' ㅣ'
				html += `<a class="submenuRight" href="/JSPWEB/apply/member/login.jsp"> login </a>`;
			}
			else{
				html += 
						`
						<div class="dropdown"> <!-- bs : 드롭다운 -->
							<button class="hpimgbtn" type="button" data-bs-toggle="dropdown" aria-expanded="false">
								<img src="/JSPWEB/apply/member/pimg/${ o.mimg == null ? 'default.png' : o.mimg }" class="hpimg">
							</button>
							<p> ${o.mid}님 </p>
							<ul class="dropdown-menu">	<!-- 드롭다운시 표기되는 구역 -->
								<li> <a class="dropdown-item" href="/JSPWEB/apply/member/info.jsp"> 내프로필 </a></li>
								<li> <a class="dropdown-item" href="#"> 친구목록 </a></li>
								<li> <a class="dropdown-item" href="/JSPWEB/apply/member/logout.jsp"> 로그아웃 </a></li>
							</ul>
						</div>	<!-- 드롭다운 end  -->
							
							<a href="#"> 쪽지함 </a>
							<a href="#"> ${ o.mpoint }포인트 </a>
						`
				if( o.mid == 'admin'){
					html += `<a href="/JSPWEB/apply/admin/info.jsp"> 관리자페이지 </a>
							`;
				}
			}
			document.querySelector(".submenu").innerHTML = html;
		}
	})
}

// ------------------------------------------------- 쪽지 알림용 소캣
let alarmsockat = null;
if( memberInfo == null ){
	
} else{
	// 1. 클라이언트 소켓 생성
	alarmsockat = new WebSocket('ws://localhost:8080/JSPWEB/apply/alarm/'+memberInfo.mid);
	// 2. 클라이언트 소켓 이벤트 메소드 정의
	alarmsockat.onopen = (e) => { console.log('enter into alarm server sockat') }
	alarmsockat.onclose = (e) => { console.log('leave alarm server sockat') }
	alarmsockat.onerror = (e) => { console.log('error alarm server sockat') }
	alarmsockat.onmessage = (e) => { onalarm(e); }
}

function onalarm(e){
	let msgbox = document.querySelector('.msgbox');
	msgbox.style.bottom = "50px";
	
	// 4초 후에 자동 내려가기: setInterval(정해진 시간을 주기로 실시), setTimeout(정해진 시간에 한번 실시)
	setTimeout( ()=>{
		msgbox.style.bottom = "-100px";
	}, 4000 )
	getcontent();
	// 다수가 동시에 채팅 요청 시, DB 순서 꼬이는 상황 발생될 수 있음
	// 해결 방안: DB와 통신하는 DAO 메소드에 synchronized 선언하여 동기화 방식으로 설정 적용
	// 원리: 사용하고 있는 thread가 있으면, 다음 thread는 wait 상태처리
	// 멀티스레드 선언하지 않았는데, thread 처리가 되는 이유: HttpServlet 멀티스레드 기반
}