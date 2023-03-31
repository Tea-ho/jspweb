	// console.log('poing.js 연결 확인'); //--- 확인 완료

  let pay = 0;
  function setpay(p){
	  pay = p;
	  alert('결제 금액 선택')
  }

  // 포트원: 객체 초기화
  var IMP = window.IMP;   // 생략 가능
  IMP.init("imp35631338"); // 예: imp00000000 
  
  // 포트원: 결제 기능 메소드
  function requestPay() {
	  
	if( pay == 0 ){ 
		alert('충전할 금액을 선택해주세요.')
		return;
	}
	  
    IMP.request_pay({
      pg: "kcp.INIBillTst",
      pay_method: "card",
      merchant_uid: "ORD20180131-0000011",   // 주문번호: 결제 DB에서 사용할 PK번호 사용하면 됨
      name: "노르웨이 회전 의자",
      amount: pay,        	                 // 숫자 타입
      buyer_email: "gildong@gmail.com",		 // 관리자 정보
      buyer_name: "홍길동",
      buyer_tel: "010-4242-4242",
      buyer_addr: "서울특별시 강남구 신사동",
      buyer_postcode: "01181"
    }, function (rsp) { // callback
      if (rsp.success) { //--- 결제 성공 시 로직
      } else { //--- 결제 실패 시 로직
      	  // 결제 성공했다는 가정하에 내용 작성(실결제 방지를 위함)
      	  let info = {
			  mpcomment: '포인트 충전',
			  mpamount: pay,
			  mmno: memberInfo.mno
		  }
		  $.ajax({
			  url: "/JSPWEB/apply/point",
			  data: info,
			  method: "post",
			  success: ( r )=>{
					  console.log(r);
					if(r=="true"){ alert('포인트충전완료'); } 
			  }
		  })
      }
    });
  }
  

  
  
  
  
  /*
  // 포트원: iframe 결제창 결과처리 메소드
  IMP.request_pay({ // 요청 객체를 추가해주세요 },
  function (rsp) {
    if (rsp.success) {
      // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
      // jQuery로 HTTP 요청
      jQuery.ajax({
        url: "{서버의 결제 정보를 받는 가맹점 endpoint}", 
        method: "POST",
        headers: { "Content-Type": "application/json" },
        data: {
          imp_uid: rsp.imp_uid,            // 결제 고유번호
          merchant_uid: rsp.merchant_uid   // 주문번호
        }
      }).done(function (data) {
        // 가맹점 서버 결제 API 성공시 로직
      })
    } else {
      alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
    }
  });

  IMP.request_pay({
  // 결제 요청 객체를 채워주세요
  m_redirect_url: "{리디렉션 될 URL}"
}, // callback ); // 리디렉션 방식의 경우 callback은 실행되지 않습니다.
*/