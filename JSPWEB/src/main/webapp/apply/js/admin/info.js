console.log('js열림');


// ---------------------------------------- chart code: jsChart api
const ctx = document.getElementById('myChart');

// DB에서 가져온 자료 jsChart에 입히기
$.get("/JSPWEB/apply/point", (r)=>{
	console.log( r );					// --- HashMap으로 받은 데이터
	console.log( Object.keys(r) ); 		// --- key 배열로 추출
	console.log( Object.values(r) ); 	// --- values 배열로 추출
	
  // --------------------------------------- JS Chart API: 객체 생성
  new Chart(ctx, {
    type: 'bar',	//---- 희망하는 chart type 설정: API에서 제공해줌
    data: {			//---- data 들어가는 자리
      labels: Object.keys(r), //---- 가로축
      datasets: [{
        label: '포인트 충전 내역',
        data: Object.values(r), //---- 실제 데이터
        borderWidth: 10,
        
      }]
    },
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });
	
	
});

// ---------------------------------------- 회원 정보 출력 메소드
let mListObject = {
	page: 1,
	listsize: 5,
	key: "",
	keyword: "",
}

mList(1);
function mList( page ){
	
	mListObject.page = page;
	
	$.ajax({
		url: "/JSPWEB/apply/member",
		method: "get",
		data: mListObject,
		success: ( o )=>{
			console.log('정상 작동')
			
			console.log(o.memberList);
			
			let html = `<tr>
							<th> 번호 </th>
							<th> 프로필 </th>
							<th> 아이디 </th>
							<th> 이메일주소 </th>
							<th> 비고 </th>
						</tr>`
			
			o.memberList.forEach( (x) =>{
				// 프로필 등록안하면 기본 양식으로 출력, 등록하면 등록한 사진으로 출력
				html += `<tr>
							<td> ${x.mno} </td>
							<td> <img src="/JSPWEB/apply/member/pimg/${x.mimg == null ? 'default.png' : x.mimg}" width="20%"> </td>
							<td> ${x.mid} </td>
							<td> ${x.memail} </td>
							<td> 비고 </td>
						</tr>`
				
			})
			document.querySelector('.mList').innerHTML = html;
			
			html = '';
			// 1페이지 이동 버튼
			html += `
					<button onclick="mList(1)" class="btn btn-light" type ="button"> << </button>
					`
			// 이전 페이지 버튼
			html += page<=1?
					`
					<button onclick="mList(${page})" class="btn btn-light" type ="button"> < </button>
					`:
					`
					<button onclick="mList(${page-1})" class="btn btn-light" type ="button"> < </button>
					`
			// 페이지 버튼
			for(let i = o.startBtn; i <= o.endBtn; i++){
				html += `
					<button onclick="mList(${i})" class="btn btn-light" type ="button"> ${i} </button>
					`
			}
			// 다음 페이지 버튼
			html += page >= o.totalPage?
					`
					<button onclick="mList(${page})" class="btn btn-light" type ="button"> > </button>
					`:
					`
					<button onclick="mList(${page+1})" class="btn btn-light" type ="button"> > </button>
					`
			// 마지막 페이지 이동 버튼
			html += `
					<button onclick="mList(${o.totalPage})" class="btn btn-light" type ="button"> >> </button>
					`
					
			document.querySelector('.pageBox').innerHTML = html;
			
			// ----------------------------------------------------- 회원 수 출력
			document.querySelector('.searchCount').innerHTML = `총 회원 수: ${ o.totalSize }`
		}
	})
}

function onSearch(){
	console.log('연결확인');
	
	mListObject.key = document.querySelector('.key').value;
	mListObject.keyword = document.querySelector('.keyword').value;
	
	mList(1);
}

function offSearch(){
	mListObject.key = '';
	mListObject.keyword = '';
	
	mList(1);
}

function setlistsize(){

	mListObject.listsize = document.querySelector('.listsize').value;
	mList(1);
}