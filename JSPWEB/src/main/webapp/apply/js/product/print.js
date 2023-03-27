	// console.log('연결 확인'); // ---- 확인 완료
	
	let productlist = null;
	// ------------------------ 사이드바 제품 리스트 출력 ------------------------
    function printsidebar( ){
		let html = '<h3>제품목록페이지</h3>';
		productlist.forEach( (p) => {
			html += `
					<div>
						<span> ${ p.pName } </span>
						<span> ${ p.pComment } </span>
						<span> ${ p.pPrice } </span>
						<span> ${ p.pState } </span>
						<span> ${ p.pView } </span>
						<span> ${ p.pDate } </span>
					</div>
					`
		});
		document.querySelector('.productlistbox').innerHTML = html;
	}
	
	// ------------------------ 키키어멥 클러스터 API ------------------------
    var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center : new kakao.maps.LatLng(37.3218778,126.8308848), // 지도의 중심좌표 
        level : 6 // 지도의 확대 레벨 
    });
	    
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
        minLevel: 6 // 클러스터 할 최소 지도 레벨 
    });

	function getproductlist( east, west, south, north ){
		
		clusterer.clear();
		// 이유: clusterer 누적 출력 방지
		
		$.ajax({
			url : "/JSPWEB/apply/product/info",
			method: "get",
			async: false,
			data: { "east": east, "west": west, "south": south, "north": north },
			success: (r)=>{
				console.log(r);
				productlist = r;
				printsidebar();
						
		        // ------------------------ 제품 리스트 카카오맵 클러스터 적용 ------------------------
		        var markers = r.map( (p) => {
					// console.log( p ); // ---- 확인 완료
					let marker = new kakao.maps.Marker({
		                position : new kakao.maps.LatLng(p.pLat, p.pLng)
		            });
		            
				// ------------------------ 카카오맵 클릭 이벤트 적용 ------------------------            
		        kakao.maps.event.addListener(marker, 'click', function() {
						
					let html = `<button onclick="printsidebar()" type="button"> <== </button> <h3> 제품 상세페이지</h3>`;
						
					html += `<div>
								<div> ${ p.pName } </div>
								<div> ${ p.pComment } </div>
								<div> ${ p.pPrice } </div>
								<div> ${ p.pState } </div>
								<div> ${ p.pView } </div>
								<div> ${ p.pDate } </div>
								<div> <button onclick="setplike(${p.pNo})" class="plikebtn" type="button"> ${ getplike( p.pNo ) } </button> </div>
							</div>`
			        	document.querySelector('.productlistbox').innerHTML = html;
			    	});
			    		// console.log(marker); // ---- 확인 완료
		            return marker;
		        });
		       	 // console.log(markers); // ---- 확인 완료
		        clusterer.addMarkers(markers);
			}
		})
	} // --- getproductlist() End
	
	// ------------------------ 현재 보고 있는 지도에 포함되는 자료만 가져오는 작업 ------------------------
    // 사용한 카카오맵 API: 1)지도 정보 얻어오기, 2)중심좌표 변경 이벤트 등록하기
	
	// 카카오맵 API 2) 사용 + 이벤트: dragend로 변경하여 적용
	kakao.maps.event.addListener(map, 'dragend', function() {
		getpos();
	});
	
	// 현재 지도의 좌표 얻기
	getpos();
	function getpos(){
				// ------------------------ 카카오맵 API 1) start ------------------------
		// 지도의 현재 영역을 얻어옵니다 
	    var bounds = map.getBounds();
	    
	    // 영역의 남서쪽 좌표를 얻어옵니다 
	    var swLatLng = bounds.getSouthWest(); 
	    
	    // 영역의 북동쪽 좌표를 얻어옵니다 
	    var neLatLng = bounds.getNorthEast();
	    
	    // message += '지도의 남서쪽 좌표는 ' + swLatLng.getLat() + ', ' + swLatLng.getLng() + ' 이고 <br>';
	    // message += '북동쪽 좌표는 ' + neLatLng.getLat() + ', ' + neLatLng.getLng() + ' 입니다';
	    
	    // 개발자도구를 통해 직접 message 내용을 확인해 보세요.
	    // ex) console.log(message);
	    // ------------------------ 카카오맵 API 1) end ------------------------
	    
	    let east = neLatLng.getLng();
	    let west = swLatLng.getLng();
	    let south = swLatLng.getLat();
	    let north = neLatLng.getLat();
	    
	    // console.log( east + west + south + north ); // --- 확인 완료
	    
	    getproductlist( east, west, south, north );
	}
	
	// 제품 찜하기 설정 [홀수 클릭: 찜하기 / 짝수 클릭: 찜하기 취소] 
	function setplike( pNo ){
		console.log(memberInfo);
		if( memberInfo == null ){
			alert('[알림] 회원 기능입니다. 로그인 후 사용해주세요.'); return;
		}
		
		$.ajax({
			url: "/JSPWEB/apply/ProductLike",
			method: "post",
			data: { "pNo" : pNo }, 
			success: (r)=>{ 
				if( r == 'true' ){ alert('찜하기 등록'); document.querySelector('plikebtn').innerHTML = '♥';
				} else{ alert('찜하기 취소'); document.querySelector('plikebtn').innerHTML = '♡'; }
			 }
		})
		
	}
	
	function getplike( pNo ){
		
		console.log( memberInfo );
		
		if( memberInfo == null ){ return '♡'; }
		
		$.get( "/JSPWEB/apply/ProductLike?pNo="+pNo, 
			(r) => {
				if( r == 'true' ){ return '♥'; }
				else{ return '♡'; } 
			}
		)	
	}
