package apply.model.dto;

import javax.websocket.Session;

public class ChatClientDto {
	
	Session session;
	String mid;
	
	public ChatClientDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ChatClientDto(Session session, String mid) {
		super();
		this.session = session;
		this.mid = mid;
	}
	
	@Override
	public String toString() {
		return "ChatClientDto [session=" + session + ", mid=" + mid + "]";
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}
}
