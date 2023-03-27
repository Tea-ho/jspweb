console.log('update.js 연결확인');

let bNo = document.querySelector('.bNo').value;
console.log("bNo:" + bNo);

getBoard();
function getBoard(){
	$.ajax({
		url: "/JSPWEB/apply/Write",
		method: "get",
		data: { "type" : 2, "bNo": bNo },
		success: ( o )=>{ 
			console.log(o)
			
			document.querySelector('.bTitle').value = o.bTitle;
			document.querySelector('.bContent').value = o.bContent;
			
			let cnoSelect = document.querySelector('.cNo');
				console.log( cnoSelect );
				console.log( cnoSelect.options[0] );
			
			for( let i = 0; i < cnoSelect.options.length; i++ ){
				if( cnoSelect.options[i].value == o.cNo ){
					cnoSelect.options[i].selected = true;
				}
			}
			
			// 첨부파일
			let html = '';
			if( o.bFile == null ){
				html += '첨부파일 없음';
			} else{
				html += `
						기존 첨부파일: <span class="oldFile"></span>
						<button onclick="bfileDelete()" class="DeleteOldFile" type="button"> 삭제 </button>
						`;
			}
			
			html += `
					변경 첨부파일: <input class="bFile" name="bFile" type="file">
					`
			
			document.querySelector('.bFileBox').innerHTML = html;
			document.querySelector('.oldFile').innerHTML = o.bFile;
		}
	})
}

// 업데이트
function bupdate(){
	let updateForm = document.querySelectorAll('.updateForm')[0];
	
	let updateFormData = new FormData( updateForm );
	
	// updateFormData.set( 'bNo', bNo );
	// 피드백: form에 bNo 포함시키지 않을 경우, set메소드 이용해서 같이 전송할 수 있음.
	
	$.ajax({
		url: "/JSPWEB/apply/Write",
		method: "put",
		data: updateFormData,
		contentType: false,		
		processData: false,		
		success: ( o )=>{ 
			console.log(o);
			if( o == 'true' ){
				alert('[알림]게시글 수정 성공')
				location.href="/JSPWEB/apply/board/view.jsp?bNo="+bNo;
			}
			else{alert('[알림]수정 실패')}
		}
	})
}

// 첨부파일만 삭제
function bfileDelete(){
	alert('첨부파일만 삭제');
	
	$.ajax({
		url: "/JSPWEB/apply/Write",
		method: "delete",
		data: { "bNo": bNo, "type" : 2},
		success: (o)=>{
			if( o == 'true' ){
				// 특정 div만 reload(랜더링), jquery 사용
				// 주의사항: loaction.href+' .클래스명' // + 사용, 띄어쓰기 사용
				$('.bFileBox').load( location.href+' .bFileBox' );
			}
		}
	})
}

$(document).ready(function() {
  $('#summernote').summernote({
    height: 400,
	placeholder: '내용을 입력해주세요.'
  });
});