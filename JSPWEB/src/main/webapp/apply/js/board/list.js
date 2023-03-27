console.log('연결확인');

// pageObject 생성: 현재 페이지, 검색, 전송타입 저장 객체
// 전역 변수 선언 이유: 게시글 리스트 출력, 검색으로 게시글 검색 등에 사용하기 위함
let pageObject = {
	page: 1,
	key: "",
	keyword: "",
	type: 1,
	cNo: document.querySelector('.cNo').value,
	listsize: 5
}

console.log( "pageObject.cNo:" + pageObject.cNo)

let cNameHTML = '';
if( pageObject.cNo == 1 ){ cNameHTML = '공지사항'; }
if( pageObject.cNo == 2 ){ cNameHTML = '커뮤니티'; }
if( pageObject.cNo == 3 ){ cNameHTML = 'QnA'; }
if( pageObject.cNo == 4 ){ cNameHTML = '문의사항'; }
document.querySelector('.cName').innerHTML = cNameHTML;

getBoardList(1);
function getBoardList( page ){
	
	pageObject.page = page;
	
	
	$.ajax({
		url: "/JSPWEB/apply/Write",
		method: "get",
		data: pageObject,	// type 1: 전체 출력					
		success: ( o )=>{
			console.log('정상 작동');
			console.log(o);
			
			// ----------------------------------------------------- 테이블 출력
			let html = `
						<tr>
							<th> 번호 </th>
							<th width=40%> 제목 </th>
							<th> 작성자 </th>
							<th> 작성일 </th>
							<th> <i class="far fa-eye"></i> </th>
							<th> <i class="far fa-thumbs-up"></i>  </th>
							<th> <i class="far fa-thumbs-down"></i> </th>
							<th> <i class="far fa-comment-dots"></i> </th>
						</tr>
						`
			o.boardList.forEach( (o) => {
				html += `
						<tr>
							<td> ${ o.bNo } </td>
							<td class="boardTableTitleContent"> <a href="/JSPWEB/apply/board/view.jsp?bNo=${ o.bNo }"> ${ o.bTitle } </a> </td>
							<td> ${ o.mId } </td>
							<td> ${ o.bDate } </td>
							<td> ${ o.bView } </td>
							<td> ${ o.bUp } </td>
							<td> ${ o.bDown } </td>
							<td> ${ o.rCount } </td>
						</tr>
						`
			})
			document.querySelector('.boardTable').innerHTML = html;
			
			// ----------------------------------------------------- 페이징 처리 
			html = '';
			
			// 1페이지 이동 버튼
			html += `
					<button onclick="getBoardList(1)" class="btn btn-light" type ="button"> << </button>
					`
			
			// 이전 페이지 버튼
			html += page<=1?
					`
					<button onclick="getBoardList(${page})" class="btn btn-light" type ="button"> < </button>
					`:
					`
					<button onclick="getBoardList(${page-1})" class="btn btn-light" type ="button"> < </button>
					`
			
			// 페이지 버튼
			for(let i = o.startBtn; i <= o.endBtn; i++){
				html += `
					<button onclick="getBoardList(${i})" class="btn btn-light" type ="button"> ${i} </button>
					`
			}
			
			// 다음 페이지 버튼
			html += page >= o.totalPage?
					`
					<button onclick="getBoardList(${page})" class="btn btn-light" type ="button"> > </button>
					`:
					`
					<button onclick="getBoardList(${page+1})" class="btn btn-light" type ="button"> > </button>
					`
			
			// 마지막 페이지 이동 버튼
			html += `
					<button onclick="getBoardList(${o.totalPage})" class="btn btn-light" type ="button"> >> </button>
					`
			
			document.querySelector('.pageBox').innerHTML = html;
			
			// ----------------------------------------------------- 게시물 수 출력
			document.querySelector('.searchCount').innerHTML = `총 게시물 수: ${ o.totalSize }`
		}
	})
}

/*
	# 클릭한 PK[식별자]로 이동하는 방법
	1. HTTP 이용한 pk 이동
	<a href="/JSPWEB/apply/board/view.jsp">
	<a href="/JSPWEB/apply/board/view.jsp?변수명=데이터">
	<a href="/JSPWEB/apply/board/view.jsp?변수명=데이터&변수명=데이터">
	<a href="/JSPWEB/apply/board/view.jsp?bNo=${ o.bNo }"> ${ o.bTitle } </a>
*/

function onSearch(){
	console.log('연결확인');
	
	pageObject.key = document.querySelector('.key').value;
	pageObject.keyword = document.querySelector('.keyword').value;
	
	getBoardList(1);
}

function offSearch(){
	pageObject.key = '';
	pageObject.keyword = '';
	
	getBoardList(1);
}

function setlistsize(){
	// 피드백: onchange 이벤트가 없을 경우, 전환이 되지 않음. 때문에 onchange 이벤트 활용하여 문제 해결
	
	// console.log( document.querySelector('.listsize').value );
	pageObject.listsize = document.querySelector('.listsize').value;
	getBoardList(1);
}

