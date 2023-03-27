alert('JS 연동 확인');

function doPOST(){
	
		alert('doPOST Go');
	$.ajax({
		url: "/JSPWEB/practice2",
		method: "post",
		success: ( o ) => {}
	})
}

function doGET(){
	
		alert('doGET Go')
	$.ajax({
		url: "/JSPWEB/practice2",
		method: "get",
		success: ( o ) => {}
	})
}

function doPUT(){
	
		alert('doPUT Go')
	$.ajax({
		url: "/JSPWEB/practice2",
		method: "put",
		success: ( o ) => {}
	})
}

function doDELETE(){
	
		alert('doDELETE Go')
	$.ajax({
		url: "/JSPWEB/practice2",
		method: "delete",
		success: ( o ) => {}
	})
}

function onWrite(){
	
		console.log('연결 확인');
	
	let info = {
		content: document.querySelector('.content').value,
		writer: document.querySelector('.writer').value
	}
	
		console.log( info );
	
	$.ajax({
		url: "/JSPWEB/Ex03/Board",
		method: "post",
		data: info,
		success: ( o )=>{
			
			if(o){ alert('등록 성공'); onlist();
				document.querySelector('.content').value = '';
				document.querySelector('.writer').value = '';			
			}
			else{ alert('등록 실패') }
		}
	})
}

onlist();
function onlist(){

	$.ajax({
		url: "/JSPWEB/Ex03/Board",
		method: "get",
		success: ( o )=>{
			console.log(o);
			let html = `<tr> 
							<th> 번호 </th> <th> 제목 </th> <th> 작성자 </th> <th> 비곤 </th>
						</tr>`;
			
			o.forEach( (x) => { 
				html += `<tr>
							<td> ${ x.bno } </td>
							<td> ${ x.bcontent} </td>
							<td> ${x.bwriter} </td>
							<td> 
								<button onclick="ondelete( ${ x.bno } )" type="button">삭제</button>
								<button onclick="onupdate( ${ x.bno } )" type="button">수정</button>
							</td>
						</tr>`	
			})
			
			document.querySelector('.btable').innerHTML = html;

		}
	})
}

function ondelete( bno ){
		
	console.log(bno);
			
	$.ajax({
		url: "/JSPWEB/Ex03/Board",
		method: "delete",
		data: { "bno" : bno },
		success: ( o )=>{
			console.log(o)
			if( o ){ alert( '삭제 성공' );	onlist(); }	
			else{ alert('삭제 실패') }
		}
	})

}

function onupdate( bno ){
	
	let newContent = prompt('수정할 내용 입력');
	
	
	$.ajax({
		url: "/JSPWEB/Ex03/Board",
		method: "put",
		data: { "bno" : bno, "newContent" : newContent },
		success: ( o )=>{
			console.log(o)
			if( o ){ alert( '수정 성공' );	onlist(); }	
			else{ alert('수정 실패') }	

		}
	})

}

