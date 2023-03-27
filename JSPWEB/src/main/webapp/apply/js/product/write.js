// console.log('연결확인');

let pLat = 0;
let pLng = 0;

// 제품 등록 메소드
function onwrite(){
	
	let writeForm = document.querySelectorAll('.writeForm')[0];
	
	let writeFormData = new FormData( writeForm );
	
	if(  pLat == 0 || pLat == 0 ){ alert('[알림] 위치 선택 후 제품 등록 부탁 드립니다.'); return; }
	
	writeFormData.set( "pLat" , pLat );
	writeFormData.set( "pLng" , pLng );
	
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