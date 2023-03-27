alert('연동확인');

function onAdd(){
	console.log('연결 확인');
	
	let pro = {
		pTitle: document.querySelector('.pTitle').value,
		pPrice: document.querySelector('.pPrice').value,
		pContent: document.querySelector('.pContent').value
	}
	
	console.log(pro);
	
	$.ajax({
		url: "/JSPWEB/과제03",
		method: "post",
		data: pro,
		success: ( o ) => {
			
			if( o ){ alert('등록 성공'); onList(); 
				document.querySelector('.pTitle').value = '';
				document.querySelector('.pPrice').value = '';
				document.querySelector('.pContent').value = '';
			}
			else{ alert('등록 실패') }
		}
	})
}

onList();
function onList(){
	$.ajax({
		url: "/JSPWEB/과제03",
		method: "get",
		success: ( o ) => {
			console.log( o );
			let html = `<tr>
							<th> 번호 </th> <th> 제목 </th> <th> 가격 </th> <th> 내용 </th> <th> 비고 </th>
						</tr>`;
			o.forEach( (x) => {
				html += `<tr>
							<td> ${x.pNo} </td>
							<td> ${x.pTitle} </td>
							<td> ${x.pPrice} </td>
							<td> ${x.pContent} </td>
							<td>
								<button onclick="onUpdate( ${ x.pNo } )" type="button">수정</button>
								<button onclick="onDelete( ${ x.pNo } )" type="button">삭제</button>
							</td>
						</tr>`
			}) // forEach E
			document.querySelector('.pList').innerHTML = html;
		} // success E
	}) //ajax E
} // onList E

function onUpdate( pNo, pTitle, pPrice, pContent ){
	
	let newTitle = prompt('수정할 제목명 입력');
	let newPrice = prompt('수정할 가격 입력');
	let newContent = prompt('수정할 내용 입력');
	
	$.ajax( {
		url: "/JSPWEB/과제03",
		method: "put",
		data: { "pNo": pNo, "newTitle": newTitle, "newPrice": newPrice, "newContent": newContent },
		success: (o) => {
			console.log(o);
			if( o ){ alert('수정 성공'); onList(); }
			else{ alert('수정 실패'); }
		}
	} )
	
}

function onDelete( pNo ){
	
	$.ajax( {
		url: "/JSPWEB/과제03",
		method: "delete",
		data: { "pNo": pNo},
		success: (o) => {
			console.log(o);
			if( o ){ alert('삭제 성공'); onList(); }
			else{ alert('삭제 실패'); }
		}
	} )
	
	
}