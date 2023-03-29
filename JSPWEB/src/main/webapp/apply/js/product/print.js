	// console.log('연결 확인') //--- 확인 완료

// ---------------------------------------- 제품 출력 관련 메소드 ----------------------------------------
// 1. 제품 목록 출력 메소드
let productList = null;
function produclistprint(  ){
    let html = `<p style="font-size:12px; text-align:right" > 제품목록수 : ${ productList.length } 개 </h6>`;
    productList.forEach( ( p , i) => {
		html += `
			<div onclick="productprint( ${ i } )" class="productbox">
				<div class="pimgbox">
					<img src="/JSPWEB/apply/product/pimg/${p.pimglist[0]}">
				</div>
				<div class="pcontentbox">
					<div class="pdate"> ${ p.pdate } </div>
					<div class="pname"> ${ p.pname } </div>
					<div class="pprice"> ${ p.pprice.toLocaleString() } 원 </div>
					<div class="petc">
						<i class="far fa-eye"></i> ${ p.pview }
						<i class="far fa-thumbs-up"></i> 5
						<i class="far fa-comment-dots"></i> 2
					</div>
				</div>
			</div>
			`
	});
	document.querySelector('.produclistbox').innerHTML = html;
} // end 

// 2. 제품 개별 조회 메소드
function productprint( i ){
	let p = productList[i];
	// 이미지 슬라이드에 대입할 html 구성 
	let imghtml = ``;
	p.pimglist.forEach( (img , i )=>{
		// bs class : active	현재 보여지는 이미지
		if( i == 0 ){	// 첫 이미지에만 actvie 클래스 적용
			imghtml += `<div class="carousel-item active">
				      		<img src="/JSPWEB/apply/product/pimg/${img}" class="d-block w-100" alt="...">
				    	</div>
				    	`
		}else{
			imghtml += `<div class="carousel-item">
					    	<img src="/JSPWEB/apply/product/pimg/${img}" class="d-block w-100" alt="...">
					    </div>
					    `
	    }
	})
	
	let html = ``;
	// --------------------------------------------------------- 부트스트랩 carousel 적용
	html += `
			<div class="pviewbox">
				<div class="pviewinfo">
					<div class="mimgbox">
						<img src="/JSPWEB/apply/member/pimg/${ p.mimg == null ? 'default.webp':p.mimg }" class="hpimg">
						<span class="mid"> ${ p.mid } </span>
					</div>
					<div>
						<button onclick="produclistprint()" class="pbadge" type="button"> 목록보기 </button>
					</div>
				</div>
				<div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
				  <div class="carousel-inner">
				  
				  	${ imghtml }
				  	
				  </div>
				  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
				    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
				    <span class="visually-hidden">Previous</span>
				  </button>
				  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
				    <span class="carousel-control-next-icon" aria-hidden="true"></span>
				    <span class="visually-hidden">Next</span>
				  </button>
				</div>
				
					<div class="pdate"> ${ p.pdate } </div>
					<div class="pname"> ${ p.pname } </div>
					<div class="pcomment"> ${ p.pcomment } </div>
					<div class="pstate"> 
						<span class="pbadge">
							${ p.pstate == 1 ? '판매중' : p.pstate == 2 ? '거래중' : '판매완료'  }
						</span> 
					</div>
					<div class="pprice"> ${ p.pprice.toLocaleString() }원 </div>
					<div class="petc"> 
						<i class="far fa-eye"></i> ${ p.pview }
						<i class="far fa-thumbs-up"></i> 5
						<i class="far fa-comment-dots"></i> 2
					 </div>
					<div class="pviewbtnbox">
						<button class="plikebtn" onclick="setplike(${p.pno})"  type="button"> <i class="far fa-heart"></i> </button>
						<button onclick="chatprint( ${i} )" type="button"> 채팅 </button>
					</div>
				</div>
			`	
	document.querySelector('.produclistbox').innerHTML = html;
	getplike( p.pno ); // 찜하기 상태호출 	
}

// ---------------------------------------- 제품 찜꽁 관련 메소드 ----------------------------------------
// 3. 찜하기 버튼를 눌렀을때[ 첫 클릭시 찜하기등록 / 다음 클릭시 찜하기 취소 / 다음 클릭시 찜하기 등록 ]
// Servelet ProductLike 사용
function setplike( pno ){
	
		// alert(pno); //--- 확인 완료
	if( memberInfo == null ){
		alert('회원기능입니다. 로그인후 사용해주세요'); return;
	}
	
	// 1. pot 방식 전송 
	$.ajax({
		url : "/JSPWEB/apply/ProductLike",
		method : "post" ,
		data : { "pno" : pno } , 
		success : (r)=>{ 
			if( r == 'true'){
				alert('찜하기 등록');
				document.querySelector('.plikebtn').innerHTML = '♥';
			}else{
				alert("찜하기 취소")
				document.querySelector('.plikebtn').innerHTML = '♡';
			}
		}
	}) // ajax end
}

// 4. 현재 회원이 해당 제품의 찜하기 상태 호출 --- Servelet ProductLike 사용
function getplike( pno ){
	if( memberInfo == null ){ document.querySelector('.plikebtn').innerHTML = '♡'; }
	$.ajax({
		url : "/JSPWEB/apply/ProductLike",
		method : "get",
		async : 'false',
		data : { "pno" : pno },
		success : (r)=>{ 
				// console.log( r ) //--- 확인 완료
			if(r == "true"){ document.querySelector('.plikebtn').innerHTML = '♥'; }
			else{ document.querySelector('.plikebtn').innerHTML = '♡'; }
		 }
	}) // ajax end
}

// ---------------------------------------- 제품 채팅 관련 메소드 ----------------------------------------
// 5-2. 제품별 채팅 목록 페이지 이동 -- ProductChat Servlet 사용
function chatlistprint(i){
	let p = productList[i];
		
		console.log(p.pno);
		
	let html = ``;
	
	$.ajax({
		url: "/JSPWEB/apply/productchat",
		method: "get", 
		data: { "pno": p.pno, "chatmno": 0 },
		async: false, // 비동기식 처리
		success: (r)=>{
				console.log(r) //--- 채팅 내용 정보
			let printfrommno = [];
			
			r.forEach( (o) =>{
				// 구매자 별 1개씩만 출력
				if( !printfrommno.includes( o.frommno ) ){
					printfrommno.push( o.frommno )
				}
				html += `
						<div onclick="printchatbox( ${i}, ${o.frommno} )" class="chatlist">
							<div class="frommimg"> 
								<img src="/JSPWEB/apply/member/pimg/${o.frommimg == null ? 'default.png' : o.frommimg}" class="hpimg"> 
							</div>
							<div class="frominfo">
								<div class="fromndate"> ${o.ndate} </div>
								<div class="frommid"> ${o.frommno} </div>
								<div class="fromncontent"> ${o.ncontent} </div>
							</div>
						</div>
						`	
			})
			if( printfrommno.length == 0 ){ html += '채팅목록이 없습니다.'; }
			
		}
	}) // ajax end
	document.querySelector('.produclistbox').innerHTML = html;
}

// 5-1. 채팅 페이지 전환 메소드 (기능: 유효성 검사)
function chatprint( i ){
	
	if( memberInfo == null ){ // 로그인 유효성 검사
		alert('[알림]로그인 회원만 사용 가능합니다.')
		location.href = ""
	}
	
	let p = productList[i];
		// console.log(p); //--- 확인 완료
	
	if( p.mno == memberInfo.mno ){ // case1: 등록한 회원
		alert('본인이 등록한 제품입니다.');
		chatlistprint(i);
		return;
	}
	else{ // case2: 구매희망 회원
		printchatbox(i, p.mno)
	}
}

// 전역변수 사용 
let index = 0; // --- index 자주 사용함
let chatmno = 0; // --- 현재 채팅하고 있는 상대방의 mno 식별용

// 5-3. chat 내용물 출력 메소드[ JAVA-DB 통신 ] -- ProductChat Servlet 사용
function getcontent(){
	let chathtml = '';
	let pno = productList[index].pno;
		console.log(pno);
	$.ajax({
		url: "/JSPWEB/apply/productchat",
		method: "get",
		data: { "pno": pno, "chatmno": chatmno },
		async: false, // 비동기식 처리
		success: (r)=>{
				// console.log(r); //--- 확인 완료
			
				// console.log( r.frommno ); //--- 확인 완료
				// console.log( memberInfo.mno ); //--- 확인 완료
			
			r.forEach((o)=>{ // 목적: 보내는 사람에 따라 메시지 출력 위치 설정
				if ( o.frommno == memberInfo.mno ){
					chathtml += `
								<div class="sendbox"> ${ o.ncontent } </div>
								`
				} else {
					chathtml += `
								<div class="receivebox"> ${ o.ncontent } </div>
								`
				}
			})
		}
	}) // ajax end
	document.querySelector('.chatcontent').innerHTML = chathtml;
}

// 5-4. chatbox 출력용 메소드
function printchatbox( i, tomno ){
		console.log( tomno + '에게 메시지 전송' );
	index = i;			// -- 전역변수 초기화  
	chatmno = tomno;	// -- 전역변수 초기화
	
	let p = productList[index];
	
	let html = `
				<div class="chatbox">
					<div class="pviewinfo">
						<div class="mimgbox">
							<img src="/JSPWEB/apply/product/pimg/${p.pimglist[0]}" class="hpimg">
							<span class="pname"> ${ p.pname } </span>
						</div>
						<div>
							<button onclick="produclistprint()" class="pbadge" type="button"> 목록보기 </button>
						</div>
					</div>
					
					<div class="chatcontent"> </div>
					
					<div class="chatbtn">
						<textarea class="ncontentinput" rows="" cols=""> </textarea>
						<button onclick="sendchat( ${p.pno} )" type="button"> 전송 </button>
					</div>
				</div>
				`
	document.querySelector('.contentbox').innerHTML = html;
	getcontent();
}


// 6. 채팅 기능 구현 메소드[DB처리] -- ProductChat Servlet 사용
function sendchat( pno ){
		// console.log( pno ); //--- 확인 완료
		console.log( "tomno:" + chatmno ); //--- 확인 완료
	let ncontent = document.querySelector('.ncontentinput').value;
	// 목적: 메시지 내용 가져오기
	
	$.ajax({ // 제품번호(식별용), 제품등록자 번호(식별용), 메시지 내용 통신
		url: "/JSPWEB/apply/productchat",
		method: "post",
		data: { "pno": pno, "tomno": chatmno, "ncontent": ncontent },
		success: (r)=>{
			console.log( r );
		}
	})
	document.querySelector('.ncontentinput').value = '';
	getcontent();
}

// ---------------------------------------------------------------------------------- kakao map API

var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
    center : new kakao.maps.LatLng(37.3218778,126.8308848), // 지도의 중심좌표 
    level : 6 // 지도의 확대 레벨 
});
    
// 마커 클러스터러를 생성합니다 
var clusterer = new kakao.maps.MarkerClusterer({
    map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
    averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
    minLevel: 7 // 클러스터 할 최소 지도 레벨 
});
// $.ajax( { url:"/JSPWEB/product/info" , success : (r) => { } );

// ----------------------- 마커 이미지 변경 ------------------------------------ //
var imageSrc = '/JSPWEB/apply/img/ezenlogo.png', // 마커이미지의 주소입니다    
    imageSize = new kakao.maps.Size(40, 40), // 마커이미지의 크기입니다
    imageOption = {offset: new kakao.maps.Point(10, 20)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      
// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
    markerPosition = new kakao.maps.LatLng(37.54699, 127.09598); // 마커가 표시될 위치입니다

// 1. 제품목록 호출 [ 1. 현재 보이는 지도좌표내 포함된 제품만 2. ]
function getproductlist( 동 , 서  , 남 , 북 ){
	
	clusterer.clear()
	// 클러스터 비우기 처리 [이유: 기존 데이터 위에 추가로 쌓이는 현상 해결하기 위함]
	
	$.ajax({
		url: "/JSPWEB/apply/product/info" ,
		method: "get",
		async: false ,
		data: { "동" : 동 , "서" : 서 ,"남" : 남 , "북": 북 },
		success : (r)=>{
			console.log('getproductlist 연결확인')
			console.log( r );
			
		    // ------------ 사이드바 제품목록 --------------------------------
		    productList = r;	// 제품목록 결과를 전역변수 담아주기 
			produclistprint(  );
		   //------------ 마커 작업 ----------------------
		    var markers = r.map( ( p , i ) => {		console.log( p )
				// 마커에 추가코드 작성하기 위해 변수화
		        let marker = new kakao.maps.Marker({	
		            position : new kakao.maps.LatLng( p.plat, p.plng) ,
		            image: markerImage
		        });
		        // 마커에 클릭이벤트를 등록합니다
				kakao.maps.event.addListener(marker, 'click', function() {
				      productprint( i )
				}); // 클릭이벤트 end 
		        return marker;
		    }); // map end 
		    clusterer.addMarkers(markers);   // 클러스터러에 마커들을 추가합니다
		    //-------------------------------------------------
		} // success end 
	}); // ajax end  
} // getproductlist end 

// 2. 현재 지도의 좌표얻기
getPos();
function getPos(){
	var bounds = map.getBounds();  // 지도의 현재 영역을 얻어옵니다 
    var swLatLng = bounds.getSouthWest();   // 영역의 남서쪽 좌표를 얻어옵니다 
    var neLatLng = bounds.getNorthEast();   // 영역의 북동쪽 좌표를 얻어옵니다 
    
    // 동,서,남,북 좌표 변수처리하여 getproductlist 메소드에 인자로 전달 (이유: 범위안에 있을 때만 제품 리스트 출력하기 위함)
    let 남 = swLatLng.getLat();
    let 서 = swLatLng.getLng();
    let 북 = neLatLng.getLat();
    let 동 = neLatLng.getLng();
    
    getproductlist( 동 , 서  , 남 , 북 );
}
// ------------  지도 중심좌표 이동 이벤트 //
// 지도가 이동, 확대, 축소로 인해 중심좌표가 변경되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
kakao.maps.event.addListener( map, 'dragend', ()=>{ getPos(); });