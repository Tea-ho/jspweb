// console.log('연결확인');

let pLat = 0;
let pLng = 0;

// 제품 등록 메소드
function onwrite(){
	
	let writeForm = document.querySelectorAll('.writeForm')[0];
	
	let writeFormData = new FormData( writeForm );
	
	if(  pLat == 0 || pLat == 0 ){ alert('[알림] 위치를 선택해주세요.'); return; }
	if( fileList.length < 1 ){ alert('[알림]하나 이상의 이미지를 등록해주세요.'); return; }
	
	writeFormData.set( "pLat" , pLat );
	writeFormData.set( "pLng" , pLng );
	
	// 폼에 드래그된 파일 등록하기
	fileList.forEach( (f) =>{
		writeFormData.append( "fileList", f );
	})
	
	
	$.ajax({
		url: "/JSPWEB/apply/product/info",
		method: "post",
		data: writeFormData,
		contentType: false,
		processData: false,
		success: (r)=>{
			console.log( r );
			alert('[알림] 제품 등록 성공!');
		}
	});
}

printProductList();
function printProductList(){
	
	$.ajax({
		url: "/JSPWEB/apply/product/info",
		method: "get",
		success: (r)=>{
			console.log(r);
		}
	})
}


// --------------------------  카카오 지도를 표시할 div 객체 ----------------------------
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// ---------------------------- 지도를 클릭한 위치에 표출할 마커입니다 ----------------------
var marker = new kakao.maps.Marker({ 
    // 지도 중심좌표에 마커를 생성합니다 
    position: map.getCenter() 
}); 
// 지도에 마커를 표시합니다
marker.setMap(map);

// ----------------------------- 지도에 클릭 이벤트를 등록합니다 ---------------------------------
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
    var latlng = mouseEvent.latLng;   // 클릭한 위도, 경도 정보를 가져옵니다 
    marker.setPosition(latlng); // 마커 위치를 클릭한 위치로 옮깁니다
    pLat = latlng.getLat();		 console.log(   "위도: "+latlng.getLat() )
    pLng = latlng.getLng();		 console.log(   "경도: "+latlng.getLng() )
});

// ----------------------------- 드래그앤드랍 구현 ---------------------------------
// 1. 드래그앤드랍 구역 호출
let fileDrop = document.querySelector('.fileDrop');

// 2. 이벤트 등록
fileDrop.addEventListener("dragenter", (e) => {
	console.log('드래그 요소가 해당 구역에 닿을 때 실행');
	e.preventDefault(); // 고유 이벤트 제거
})

fileDrop.addEventListener("dragover", (e) => {
	console.log('드래그 요소가 해당 구역에 위치하고 있을 때 실행');
	e.preventDefault(); // 고유 이벤트 제거
})

fileDrop.addEventListener("dragleave", (e) => {
	console.log('드래그 요소가 해당 구역을 떠날 때 실행');
	e.preventDefault(); // 고유 이벤트 제거
})

fileDrop.addEventListener("drop", (e) => {
	console.log('드래그 요소가 해당 구역에 드랍되었을 때 실행');
	// 문제점: 브라우저 영역에 드랍했을 때도 작동함
	e.preventDefault(); // 고유 이벤트 제거
	
	// 드랍된 파일 호출
	let files = e.dataTransfer.files;
	console.log( files );
	for( let i; i < files.length; i++ ){ // foreach사용 불가
		if( files[i] != null && files[i] != undefined ){// null 아니거나 정의가 되어있으면(파일 존재하면)
			fileList.push( files[i] );
		}
	}
	console.log( fileList );
})

let fileList = []; // file담을 배열 생성
