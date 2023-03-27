// console.log('연결확인');
// console.log(empNo);

function updateModalOpen( no ){
	document.querySelector('.umodal_wrap').style.display = 'flex';
	printInfo( no );
}

function updateModalClose(){
	document.querySelector('.umodal_wrap').style.display = 'none';
}

function printInfo( no ){
	console.log( no )
	
	let empNo = no;
	
	$.ajax({
		url: "/JSPWEB/update",
		method: "get",
		data: { "empNo": empNo},
		success: ( o ) => {
			console.log(o);
			
			document.querySelector('.empImg').src =
				 `/JSPWEB/practice/과제/과제04/employee/eimg/${ o.empImg == null ? 'default.png' : o.empImg }`;
			document.querySelector('.empNo').innerHTML = o.empNo;
			document.querySelector('.newName').value = o.empName;
			document.querySelector('.newGrade').value = o.empGrade;
			document.querySelector('.newConstruct').value = o.empConstruct;
			document.querySelector('.newDepart').value = o.empDepart;
			document.querySelector('.newSdate').value = o.empSdate;
			document.querySelector('.newLdate').value = o.empLdate;
			document.querySelector('.newLcomment').value = o.empLcomment;
		}
	})
}

function doUpdate(){
	
		console.log('연결확인')
	
	let updateForm = document.querySelectorAll('.updateForm')[0];
	let updateFormData = new FormData( updateForm );
	
	let empNo = document.querySelector('.empNo').innerHTML;
	console.log(empNo);
	updateFormData.set( "empNo" , empNo );
		// console.log(updateFormData);
	
	$.ajax({
		url: "/JSPWEB/employee",
		method: "put",
		data: updateFormData,
		contentType: false,
		processData: false,
		success: (o) => {
				console.log(o);
			if( o == 'true' ){
				alert('[알림] 수정성공');
				location.href="/JSPWEB/practice/과제/과제04/employee/employee.jsp";
			}
			else{ alert('[알림] 수정실패'); }
		}
	})
}