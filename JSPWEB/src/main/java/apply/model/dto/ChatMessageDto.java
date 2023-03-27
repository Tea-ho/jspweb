package apply.model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.websocket.Session;

import apply.controller.admin.Chatting;
import apply.model.dao.MemberDao;

public class ChatMessageDto {
	
	// private Session session;		// 세션
	private String frommid;			// 회원 아이디
	private String frommimg;		// 회원 프로필
	private String msg; 			// 메시지 내용
	private String date;			// 메시지 발송 일자
	private String time;			// 메시지 발송 시간
	
	// private String // 메시지 chat 번호
	
	public ChatMessageDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// 클라이언트에게 메시지 전송할 때 사용하는 생성자
	public ChatMessageDto(Session session, String msg) {
		super();
		// this.session = session;
		this.msg = msg;
		
		// 메시지 보낸 클라이언트 세션을 통해서 회원아이디 얻기
		for( ChatClientDto dto : Chatting.connectorList ) {
			if( dto.getSession() == session ) {
				this.frommid = dto.getMid();
				// 보낸 사람의 프로필 얻기
				this.frommimg = MemberDao.getInstance().getMember( this.frommid ).getMimg();
				this.date = new SimpleDateFormat("yyyy-MM-dd").format( new Date() );
				this.time = new SimpleDateFormat("aa hh:mm").format( new Date() );
			}
		}
	}

	public String getFrommid() {
		return frommid;
	}

	public void setFrommid(String frommid) {
		this.frommid = frommid;
	}

	public String getFrommimg() {
		return frommimg;
	}

	public void setFrommimg(String frommimg) {
		this.frommimg = frommimg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
