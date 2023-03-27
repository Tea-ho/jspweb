/* AJAX 이용해서 공공데이터 가져오기
$.ajax({
	url: "URL 주소"
	method: "get",
	success: (o) => { console.log(o) }
})
*/

console.log('api.js 연동확인')

// 안산시 전기충전소 정보
getapi1();
function getapi1(){
	$.ajax({
		url: "https://api.odcloud.kr/api/15090398/v1/uddi:6fe0e3f2-0285-4999-9edf-995afe19a6ea?page=1&perPage=96&serviceKey=c%2BqC%2BX56OkzH7wQp0MRSmjqIonksla%2FFjJR2zJurJLRPptbOM9ymF8kHAA5dSTesd8z%2BF%2Fg77w270mdXrEX10w%3D%3D",
		method: "get" ,
		success: (o) => {
			console.log(o);
			document.querySelector('.totalcount').innerHTML = o.currentCount;
			
			let html = `<tr>
							<th> 시군명 </th>
							<th> 충전소명 </th>
							<th> 소재지도로명주소 </th>
							<th> 소재지지번주소 </th>
							<th> 소재지우편번호 </th>
						</tr>
						`
			
			o.data.forEach( (o) =>{
				html += `<tr>
							<td> ${ o.시군명 }  </td>
							<td> ${ o.충전소명 } </td>
							<td> ${ o.소재지도로명주소 } </td>
							<td> ${ o.소재지지번주소 } </td>
							<td> ${ o.소재지우편번호 } </td>
						</tr>
						`
			})
			document.querySelector('.data').innerHTML = html;
			
		}
	})
}

// 안산시 공영주차장 정보
getapi2();
function getapi2(){
	$.ajax({
		url: "https://api.odcloud.kr/api/15050093/v1/uddi:d19c8e21-4445-43fe-b2a6-865dff832e08?page=1&perPage=200&cond%5B%EC%A7%80%EC%97%AD%EC%BD%94%EB%93%9C%3A%3AEQ%5D=41273&serviceKey=c%2BqC%2BX56OkzH7wQp0MRSmjqIonksla%2FFjJR2zJurJLRPptbOM9ymF8kHAA5dSTesd8z%2BF%2Fg77w270mdXrEX10w%3D%3D",
		method: "get" ,
		success: ( o ) => {
			console.log( o );
		}
	})
}
