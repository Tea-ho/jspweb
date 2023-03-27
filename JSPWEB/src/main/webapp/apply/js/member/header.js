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