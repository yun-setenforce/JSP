package kr.co.jboard2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.TermsService;
import kr.co.jboard2.service.UserService;

@WebServlet("/user/register.do")
public class RegisterController extends HttpServlet{
	private UserService service = UserService.getInstance();
	
	private static final long serialVersionUID = 8198977515204637397L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//전송 데이터 수신
		String uid 	= req.getParameter("uid");
		String pass = req.getParameter("pass1");
		String name = req.getParameter("name");
		String nick = req.getParameter("nick");
		String email = req.getParameter("email");
		String hp = req.getParameter("hp");
		String zip = req.getParameter("zip");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String regip = req.getRemoteAddr(); //사용자 아이피 주소 
		
		UserDTO dto = new UserDTO();
		dto.setUid(uid);
		dto.setPass(pass);
		dto.setName(name);
		dto.setNick(nick);
		dto.setEmail(email);
		dto.setHp(hp);
		dto.setZip(zip);
		dto.setAddr1(addr1);
		dto.setAddr2(addr2);
		dto.setRegip(regip);
		
		service.insertUser(dto);
		
		resp.sendRedirect("/Jboard2/user/login.do?success=200");
	}
}
