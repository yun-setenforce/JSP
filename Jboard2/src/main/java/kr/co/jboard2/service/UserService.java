package kr.co.jboard2.service;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.dao.UserDAO;
import kr.co.jboard2.dto.UserDTO;

public class UserService {

	private static UserService instance = new UserService();
	private UserService() {}
	public static UserService getInstance() {
		return instance;
	}
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private  static String generatedCode = "";
	
	UserDAO dao = UserDAO.getInstance();
	
	public void insertUser(UserDTO dto) {
		dao.insertUser(dto);
	}
	
	public int selectCountUid(String uid) {
		return dao.selectCountUid(uid);
	}

	
	public int selectCountNick(String nick) {
		return dao.selectCountNick(nick);
	}

	
	public int selectCountEmail(String email) {
		return dao.selectCountEmail(email);
	}
	public int selectCountNameAndEmail(String name, String email) {
		return dao.selectCountNameAndEmail(name, email);
	}
	public int selectCountIdAndEmail(String uid, String email) {
		return dao.selectCountIdAndEmail(uid, email);
	}
	
	public int selectCountHp(String hp) {
		return dao.selectCountHp(hp);
	}

	public UserDTO selectUser(String uid, String pass) {
		return dao.selectUser(uid, pass);
	}

	public UserDTO selectUserByNameAndEmail(String user, String email) {
		return dao.selectUserByNameAndEmail(user, email);
	}
	
	public List<UserDTO> selectUsers() {
		return dao.selectUsers();
	}
	
	public void updateUser(UserDTO dto) {
		dao.updateUser(dto);
	}

	public int updateUserPass(String uid, String pass) {
		return dao.updateUserPass(uid,pass);
	}
	public int updateUserForWithdraw(String uid) {
		return dao.updateUserForWithdraw(uid);
	}
	public void deleteUser(String id) {
		dao.deleteUser(id);
	}
	
	public int sendCodeByEmail(String receiver) {
		
		// 인증코드 생성 
		int code = ThreadLocalRandom.current().nextInt(100000, 1000000); //10만부터 100만까지
		generatedCode = code + "";
		
		logger.info(" gen  : "+ generatedCode);
		//메일 기본정보
		String sender   = "extendsabstractclass@gmail.com"; // 내 이메일 계정 
		String appPass  = "hfrezhsvhiitsckb"; //내 Gmail SMTP 앱 비밀번호
		String title    = "Jboard2 인증코드 입니다.";
		String content 	= "<h1>인증코드는 " + generatedCode + "  입니다.</h1>";
		
		//String content  = request.getParameter("content");
		
		// Gmail SMTP 서버 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		// Gmail STMP 세션 생성
		Session gmailSession = Session.getInstance(props, new Authenticator(){
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, appPass);
			}
		});
		
		// 메일 발송
		int status = 0;
		Message message = new MimeMessage(gmailSession);
		
		try{
			message.setFrom(new InternetAddress(sender, "Jboard2", "UTF-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html;charset=UTF-8");
			Transport.send(message);
			
			status = 1;
			
		}catch(Exception e){
			e.printStackTrace();
			status = 0;
		}
		return status;
	}
	
	public int confirmCodeByEmail(String code) {
		
		
		if(code.equals(generatedCode)) {
			return 1;
		} else {
			return 0;
		}
		
	}
}
