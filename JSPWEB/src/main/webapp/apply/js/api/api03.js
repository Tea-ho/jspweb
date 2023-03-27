var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
	center : new kakao.maps.LatLng(37.318336, 126.837608), // 지도의 중심좌표 
	level : 5 // 지도의 확대 레벨 
});

// ---------------------------------------------------------------------- 마커 이미지 변경 및 마커 생성
var imageSrc = '/JSPWEB/apply/api/img/markup_map.png', // 마커이미지의 주소입니다    
    imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
		
// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

// 마커 클러스터러 생성
var clusterer = new kakao.maps.MarkerClusterer({
	map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
	averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
	minLevel: 3 // 클러스터 할 최소 지도 레벨 
});
 
// ---------------------------------------------------------------------- 클러스터러 적용 (주소-좌표 변환 포함)
$.ajax({
	url : "https://api.odcloud.kr/api/3071314/v1/uddi:e4e7774d-0b16-4299-b830-dee5045df70f_201909291441?page=1&perPage=150&serviceKey=c%2BqC%2BX56OkzH7wQp0MRSmjqIonksla%2FFjJR2zJurJLRPptbOM9ymF8kHAA5dSTesd8z%2BF%2Fg77w270mdXrEX10w%3D%3D",
	method : "get",
	async : false,
	success: (o)=>{
		console.log(o)
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		o.data.forEach( (r) =>{
			
			let markers = [];
			
			// 주소로 좌표를 검색합니다
			geocoder.addressSearch( r.소재지도로명주소, function(result, status){
					
				// 정상적으로 검색이 완료됐으면 
				if (status === kakao.maps.services.Status.OK) {
				    
				    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
				    
					// 결과값으로 받은 위치를 마커로 표시합니다
				    let marker =  new kakao.maps.Marker({
						map: map,
				    	position : coords,
				        image : markerImage
				   	});
					
					markers.push(marker)
					console.log(marker);
					
				kakao.maps.event.addListener(marker, 'click', function() {
		
					document.querySelector('.modal_title').innerHTML = r.업소명;
						
					document.querySelector('.modal_content').innerHTML = `종류: ` + r.분야명 + `<br>영업시간: ` + r.영업시간;
						
					openModal();
				});
	
				clusterer.addMarkers(markers);

				}
			})
		});
	}
});

// ----------------------------------------------------- 현 위치 표기
var marker = new kakao.maps.Marker({
	position: map.getCenter(),
    image: markerImage
});

// 지도에 마커를 표시합니다
marker.setMap(map);

// ----------------------------------------------------- 지도 클릭 이벤트
// 지도에 클릭 이벤트를 등록합니다
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
    
    // 클릭한 위도, 경도 정보를 가져옵니다 
    var latlng = mouseEvent.latLng; 
    
    // 마커 위치를 클릭한 위치로 옮깁니다
    marker.setPosition(latlng);
    
    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
    message += '경도는 ' + latlng.getLng() + ' 입니다';
    
    var resultDiv = document.getElementById('clickLatlng'); 
    resultDiv.innerHTML = message;
});