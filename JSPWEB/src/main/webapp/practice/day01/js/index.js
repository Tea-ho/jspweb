alert('js파일 열림');

function ex1(){
	
	// HTML에서 입력받은 데이터 JS로 가져오는 방법 (document.querySelector or querySelectorAll 사용!)
	// document.querySelector( 식별자 ).속성명;
	// value: input, select, textarea 데이터
	// innerHTML: div, span, td 등의 데이터
	let data = document.querySelector('.inputdata').value;
	console.log(data);
	
	// JS -> 서블릿으로 데이터 이동 ( ajax 메서드 이용: $.ajax() 
	// 기본 형태: $.ajax({ url: "", method: "", data: { 이름: 값 }, success: function(){} });
	// $: jqeury 표현식
	// url: 통신할 경로[서블릿(클래스)주소: /프로젝트명/클래스명
	// method: http 메소드방식 (get, post 사용)
	// data: 통신할 때 데이터 보내기 ( js객체 형태: {매개변수명1 : 데이터} )
	// success: 통신 후 응답 데이터 받기 (  )
	
	$.ajax( {
		url: "/JSPWEB/IndexTest", // 통신할 서블릿 주소
		method: "post",
		data: { "data": data },
		success: function(result){
			console.log(result);
			ex2();
		}
	} );
}

ex2();
function ex2(){
	
		$.ajax( {
		url: "/JSPWEB/IndexTest", // 통신할 서블릿 주소
		method: "get",
		// data: { "data": data },
		success: function(result){
			console.log(result);
			document.querySelector('.ex2box').innerHTML = result;
		}
	} );
	
}

function homework1(){
	
	let data = document.querySelector('.homework').value;
	
	$.ajax({
		url: "/JSPWEB/hw1",
		method: "post",
		data: { "data": data},
		success: ( o )=>{
			console.log( o );
			homework2();
		 }
	});
}

homework2();
function homework2(){
	$.ajax( {
		url: "/JSPWEB/hw1", 
		method: "get",
		success: ( o ) => {
			console.log(o);
			document.querySelector('.hwbox').innerHTML = o;
		 }
	} );
}
