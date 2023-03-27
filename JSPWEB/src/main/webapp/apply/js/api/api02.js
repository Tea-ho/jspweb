// ----------------------------------------------------- 테스트 객체
/*var positions = [
    {
        title: '카카오', 
        latlng: new kakao.maps.LatLng(33.450705, 126.570677)
    },
    {
        title: '생태연못', 
        latlng: new kakao.maps.LatLng(33.450936, 126.569477)
    },
    {
        title: '텃밭', 
        latlng: new kakao.maps.LatLng(33.450879, 126.569940)
    },
    {
        title: '근린공원',
        latlng: new kakao.maps.LatLng(33.451393, 126.570738)
    }
];
*/

let positions = [];
/*
$.ajax({
	url: "https://api.odcloud.kr/api/15050093/v1/uddi:d19c8e21-4445-43fe-b2a6-865dff832e08?page=1&perPage=200&cond%5B%EC%A7%80%EC%97%AD%EC%BD%94%EB%93%9C%3A%3AEQ%5D=41273&serviceKey=c%2BqC%2BX56OkzH7wQp0MRSmjqIonksla%2FFjJR2zJurJLRPptbOM9ymF8kHAA5dSTesd8z%2BF%2Fg77w270mdXrEX10w%3D%3D",
	method: "get" ,
	async: 'false',
	success: ( o ) => {
			console.log( o );
		o.data.forEach( (o)=>{
			
			let info = {
				title: o.주차장명,
				latlng: new kakao.maps.LatLng( o['위도'], o['경도'] )
			}
				console.log(info);
			positions.push(info);
				console.log(positions);
		})
		
		// ----------------------------------------------------- 마크업 여러개 생성하는 반복문
		for (let i = 0; i < positions.length; i ++) {
			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
				map: map,
			    position: positions[i].latlng,
			    title: positions[i].title,
			    image: markerImage // 마커이미지 설정 
			});
			// ----------------------------------------------------- 마커 클릭 이벤트
			// 마커에 클릭이벤트를 등록합니다
			kakao.maps.event.addListener(marker, 'click', function() {
			      // 마커 위에 인포윈도우를 표시합니다
			      console.log('확인');  
			});
		}
	}
})
*/
// ----------------------------------------------------- 지도 생성
var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스

var options = { //지도를 생성할 때 필요한 기본 옵션
	center: new kakao.maps.LatLng(37.318336, 126.837608), //지도의 중심좌표.
	level: 5 //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

// ----------------------------------------------------- 마커 클러스터러 적용 [마커 집합소]
// 마커 클러스터러를 생성합니다 
var clusterer = new kakao.maps.MarkerClusterer({
	map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
    averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
    minLevel: 5 // 클러스터 할 최소 지도 레벨 
});

$.get("https://api.odcloud.kr/api/15050093/v1/uddi:d19c8e21-4445-43fe-b2a6-865dff832e08?page=1&perPage=200&cond%5B%EC%A7%80%EC%97%AD%EC%BD%94%EB%93%9C%3A%3AEQ%5D=41273&serviceKey=c%2BqC%2BX56OkzH7wQp0MRSmjqIonksla%2FFjJR2zJurJLRPptbOM9ymF8kHAA5dSTesd8z%2BF%2Fg77w270mdXrEX10w%3D%3D",
function(data) {
	// 데이터에서 좌표 값을 가지고 마커를 표시합니다
    // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
    var markers = $(data.data).map(function(i, o) {
		
		let marker = new kakao.maps.Marker({
        	position : new kakao.maps.LatLng(o['위도'], o['경도']),
        	image: markerImage
        });
        
        kakao.maps.event.addListener(marker, 'click', function() {
			
			document.querySelector('.modal_title').innerHTML = o.주차장명;
			document.querySelector('.modal_title').style.fontsize = '10px';
			
			document.querySelector('.modal_content').innerHTML = o.주차장지번주소;
			
			openModal();
		});
		
		return marker;
        
    });
	// 클러스터러에 마커들을 추가합니다
    clusterer.addMarkers(markers);
});
// 피드백: map 메소드(반복문)을 이욯하면 반환값이 있기 때문에, 리턴된 값을 배열에 저장할 수 있다.


// ----------------------------------------------------- 마크업 이미지 변경
var imageSrc = '/JSPWEB/apply/api/img/markup_map.png', // 마커이미지의 주소입니다    
    imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      
// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
    markerPosition = new kakao.maps.LatLng(37.54699, 127.09598); // 마커가 표시될 위치입니다

// ----------------------------------------------------- 마크업 1개 생성
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




