package apply.controller.admin;

import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import apply.model.dao.MemberDao;
import apply.model.dto.ChatClientDto;

@ServerEndpoint("/apply/alarm/{mid}")
public class Alarm {
	
	private static ArrayList<ChatClientDto> alramList = new ArrayList<>();

	@OnOpen // 클라이언트 소켓 들어왔을 때
	public void onOpen( Session session, @PathParam("mid") String mid ) throws Exception {
		System.out.println( session );
		alramList.add(new ChatClientDto(session, mid));
	}
	
	@OnMessage // 클라이언트 소켓이 메시지 보냈을 때
	public static void onMessage( Session session, String msg ) throws Exception {
		System.out.println( session );
		
		int tomno = Integer.parseInt(msg.split(",")[0]);
		String tomsg = msg.split(",")[1];
		
		for( ChatClientDto c : alramList ) {
			int cmno = MemberDao.getInstance().getMember(c.getMid()).getMno();
			// 현재 소켓의 접속된 회원명단의 아이디로 회원번호 가져오기
			if( cmno == tomno ) { // 받는 회원번호가 알림명단에 존재하면 전달
				c.getSession().getBasicRemote().sendText(tomsg);
			}
		}
		
	}
	@OnClose // 클라이언트 소켓이 나갔을 때
	public void onClose( Session session ) throws Exception {
		System.out.println( session );
		for( ChatClientDto c : alramList ) {
			if( c.getSession() == session ) { alramList.remove(c); }
		}
	}
	@OnError // 클라이언트 소켓 에러 발생했을 때 (기본 포멧
	public void onError( Session session, Throwable e ) throws Exception {
		System.out.println( session );
		System.out.println( e );
	}
}
