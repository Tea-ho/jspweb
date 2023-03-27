package apply.controller.admin;

import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import apply.model.dto.ChatClientDto;
import apply.model.dto.ChatMessageDto;

@ServerEndpoint("/Chatting/{mid}") // 해당 클래스를 서버소켓[종착점]으로 만들기
// 특이사항: /경로/{매개변수} => 중괄호 처리 시, 경로가 아닌 매개변수로 인식함
// 활용: /경로/{아이디}/{채팅방} => 매개변수 여러개 취급 가능
public class Chatting {
	
	// 0. 접속한 클라이언트 소켓 명단 저장 [채팅 활성을 위함]
	public static ArrayList< ChatClientDto > connectorList = new ArrayList<>();
	
	// 1-1. 클라이언트 소켓이 접속했을 때 실행되는 메소드
	@javax.websocket.OnOpen // Session session: 보낸 사람, @PathParam("URL매개변수") String mid: 접속자
	public void OnOpen( Session session , @PathParam("mid") String mid ) throws Exception {
		System.out.println("웹소켓 입장");	System.out.println( session );
		
		// 접속한 클라이언트 소켓 명단에 저장
		connectorList.add( new ChatClientDto( session, mid ) );
			System.out.println( connectorList.toString() );
		
		// 모든 접속명단에게 연결된 클라이언트 소캣을 알림 [접속목록]
		OnMessage( session, "clienter" );
		
	}
	
	// 1-2. 클라이언트 소켓이 나갔을 때 반영
	@OnClose
	public void onClose( Session session ) throws Exception {
		
		for( ChatClientDto dto : connectorList ) {
		// 해석: connectorList 모든 인덱스를 순차적으로 반복
			if( dto.getSession() == session ) {
			// 해석: connectorList 각 인덱스의 getSession 정보와 매개변수 session 정보가 일치하면 List에서 제거
				connectorList.remove(dto);
				
				// JSON형식으로 메시지 작성
				String msg = "{\"type\":\"alarm\",\"data\":\""+dto.getMid()+"님이 채팅방에서 나갔습니다.\"}";
				// 형태: { "필드명" : "데이터", "필드명" : 데이터 }
				// 해석: 큰따음표 사용을 위에 이스케이프 문자 사용
				OnMessage( session, msg);
				
				// 모든 접속명단에게 연결 끊긴 클라이언트 소캣을 알림 [접속목록]
				OnMessage( session, "clienter" );
				break;
			}
		}
	}
	
	// 2. 메시지 받는 메소드
	@OnMessage // Session session: 보낸 사람, String msg: 보낸 내용 
	public void OnMessage( Session session, String msg ) throws Exception {
		System.out.println("클라이언트 웹소켓이 메시지 발송 [서버 웹소켓 메시지 송신]");	System.out.println(msg);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		
		// ------------------------------------- 접속 명단 -------------------------------------
		if( msg.equals("clienter") ) {
			
			ArrayList<ChatMessageDto> list = new ArrayList<>();
			for( ChatClientDto dto : connectorList ) {
				list.add( new ChatMessageDto(dto.getSession(), msg));
			}
			json = mapper.writeValueAsString( list );
		} else {
			// ------------------------------------- 메시지 -------------------------------------
			// 메시지 형식 구성(Dto 필드 생성)
			ChatMessageDto messageDto = new ChatMessageDto(session, msg);
			// JSON으로 형변환
			json = mapper.writeValueAsString(messageDto);
		}
		// 서버 소켓이 클라이언트 소켓에게 메시지 보내기
		// 기본: session(보낸 사람)에게 메시지 전송
		// session.getBasicRemote().sendText(msg);
		// 활용: 접속된 모든 사람에게 메시지 전송
		for( ChatClientDto dto : connectorList ) {
			dto.getSession().getBasicRemote().sendText(json);
		}
	}
}
	
