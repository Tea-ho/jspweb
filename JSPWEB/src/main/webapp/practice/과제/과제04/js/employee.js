// 1. 인사 등록
function create_employee(){
	
	let signupForm = document.querySelectorAll('.signupForm')[0];
	let signupFormData = new FormData( signupForm );
	console.log(signupForm);
	console.log(signupFormData);
	
	$.ajax({
		url : "/JSPWEB/employee",
		method : "post" ,
		data : signupFormData ,
		contentType : false ,
		processData : false ,
		success : (r) => {
			if ( r == 'true' ){
				alert('회원가입 성공!')
			}
			
			
		}
	}) // ajax end
} // create_employee end

// 2. 인사 전체 출력
function print_employee(){
	$.ajax({
		url : "/JSPWEB/employee",
		method : "get" ,
		success : (r) => {			
			let html = `<tr>
							<th width=10%> 사원번호 </th>
							<th width=10%> 사원사진 </th>
							<th width=10%> 사원명 </th>
							<th width=10%> 직급 </th>
							<th width=10%> 고용형태 </th>
							<th width=10%> 부서 </th>
							<th width=10%> 입사일 </th>
							<th width=10%> 퇴사일 </th>
							<th width=10%> 퇴사사유 </th>
							<th width=10%> 비고 </th>
						</tr>`
			r.forEach( ( o , i ) =>{
				html += `<tr>
							<td> ${o.empNo} </td>
							<td> <img width=100% src="/JSPWEB/practice/과제/과제04/employee/eimg/${ o.empImg == null ? 'default.png' : o.empImg}">  </td>
							<td> ${o.empName} </td>
							<td> ${o.empGrade} </td>
							<td> ${o.empConstruct} </td>
							<td> ${o.empDepart} </td>
							<td> ${o.empSdate} </td>
							<td> ${o.empLdate} </td>
							<td> ${o.empLcomment} </td>
							<td> 
								<button onclick="updateModalOpen( ${o.empNo} )" type="button"> 수정 </button>
								<button onclick="deleteModalOpen()" type="button"> 삭제 </button>
							</td>
						</tr>`
			}); // for end
			document.querySelector('.empList').innerHTML = html;
		}
	})
}

// 3. 인사 직무(부서별) 출력
function print_job(){
	let job = prompt('찾을 부서를 입력해주세요.' , '인사팀');
	
	$.ajax({
		url : "/JSPWEB/employee" ,
		method : "get" ,
		success : (r) => {
			let html = `<tr>
							<th width=10%> 부서 </th>
							<th width=10%> 사원명 </th>
							<th width=10%> 직급 </th>
						</tr>`
			r.forEach( ( o , i ) =>{
				html += `<tr>
							<td> ${o.empDepart} </td>
							<td> ${o.empName} </td>
							<td> ${o.empGrade} </td>
						</tr>`
			}); // for end
			document.querySelector('.empList').innerHTML = html;
		}
	})
	
}


// 4. 퇴사자 출력




