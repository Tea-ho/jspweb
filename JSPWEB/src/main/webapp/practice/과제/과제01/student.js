// alert('확인'); //------------------ 확인 완료

function submit(){
	
	let sInfo = {
		sName: document.querySelector('.sName').value,
		sPhone: document.querySelector('.sPhone').value,
		sLength: document.querySelector('.sLength').value,
		sAge: document.querySelector('.sAge').value,
		sDate: document.querySelector('.sDate').value,
		sSex: document.querySelector('input[name="sSex"]:checked').value,
		sHome: document.querySelector('.sHome').value,
		sIntro: document.querySelector('.sIntro').value,
		sAgree: document.querySelector('.sAgree').checked
	}
	
	$.ajax({
		url: "/JSPWEB/Student",
		method: "post",
		data: sInfo,
		success: (o) =>{
			if(o){ alert('DB저장 완료') }
			sList();
		}
	});
	
	// console.log(sInfo.sAgree);
	
}

sList();
function sList(){
	
	$.ajax({
		url: "/JSPWEB/Student",
		method: "get",
		success: ( o )=>{
			
			let html = `<table border="1">
							<tr>
								<th> No </th> <th> 이름 </th> <th> 연락처 </th> <th> 키 </th> <th> 나이 </th>
								<th> 등록일자 </th> <th> 성별 </th> <th> 사는곳 </th> <th> 자기소개 </th> <th> 동의여부 </th>
							</tr>
						`
			o.forEach( ( x ) => {
				html += `<tr>
							<th> ${ x.sNo } </th> <th> ${ x.sName } </th> <th> ${ x.sPhone } </th> <th> ${ x.sLength } </th> <th> ${ x.sAge } </th>
							<th> ${ x.sDate } </th> <th> ${ x.sSex } </th> <th> ${ x.sHome } </th> <th> ${ x.sIntro } </th> <th> ${ x.sAgree } </th>
						</tr>`
				
			});
			html += `</table>`
			document.querySelector(".sList").innerHTML = html;
			
		}
	});
	
}