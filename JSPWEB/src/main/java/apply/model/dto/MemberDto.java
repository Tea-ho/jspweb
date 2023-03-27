package apply.model.dto;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MemberDto {
	
	private int mno;
	private String mid;
	private String mpw;
	private String mimg;
	private String memail;
	private int mpoint;

	// -------------------------------------------------------------- 이메일 전송 메소드
	public boolean sendEmail( String toEmail, String contentHTML ) {
		
		// 1. 보내는 사람 정보
		String fromEmail = "ID@naver.com";
		String Emailpw = "realPW";
		
		// 2. 호스팅 설정 [네이버 기준] 
		Properties properties = new Properties();
		
		properties.put( "mail.smtp.host", "smtp.naver.com" ); 		// smtp.gmail.com [gmail]
		properties.put( "mail.smtp.port", 587 );					// 동일
		properties.put( "mail.smtp.auth", true );					// 동일
		properties.put( "mail.smtp.ssl.protocols", "TLSv1.2" );		// 돟일
		
		// 3. 인증처리
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			// 패스워드 인증함수 오버라이딩
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, Emailpw);
			}
		});
		
		// 4. 메일 보내기
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom( new InternetAddress( fromEmail ) );
			message.addRecipient( Message.RecipientType.TO, new InternetAddress( toEmail ) );
			
			message.setSubject("CuoreEspresso Ansan 회원가입 메일 인증코드");
			message.setText(contentHTML);
			
			
			return true;
		} catch(Exception e) { System.out.println("예외 발생: " + e); }
		return false;
	}
	
	
	public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MemberDto(int mno, String mid, String mpw, String mimg, String memail) {
		super();
		this.mno = mno;
		this.mid = mid;
		this.mpw = mpw;
		this.mimg = mimg;
		this.memail = memail;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpw() {
		return mpw;
	}
	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	public String getMimg() {
		return mimg;
	}
	public void setMimg(String mimg) {
		this.mimg = mimg;
	}
	public String getMemail() {
		return memail;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public int getMpoint() {
		return mpoint;
	}
	public void setMpoint(int mpoint) {
		this.mpoint = mpoint;
	}

	@Override
	public String toString() {
		return "MemberDto [mno=" + mno + ", mid=" + mid + ", mpw=" + mpw + ", mimg=" + mimg + ", memail=" + memail
				+ "]";
	}
}
