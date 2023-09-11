package kr.farmstory2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.farmstory2.dto.UserDTO;
import kr.farmstory2.service.UserService;

@WebServlet("/user/register.do")
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 7301844891499077883L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid   = req.getParameter("uid");
		String pass1 = req.getParameter("pass1");
		String pass2 = req.getParameter("pass2");
		String name  = req.getParameter("name");
		String nick  = req.getParameter("nick");
		String email = req.getParameter("email");
		String hp    = req.getParameter("hp");
		String zip   = req.getParameter("zip");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
	 	String regip = req.getRemoteAddr();
	 	
	 	UserDTO dto = new UserDTO();
	 	dto.setUid(uid);
	 	dto.setPass(pass1);
	 	dto.setName(name);
	 	dto.setNick(nick);
	 	dto.setEmail(email);
	 	dto.setHp(hp);
	 	dto.setZip(zip);
	 	dto.setAddr1(addr1);
	 	dto.setAddr2(addr2);
	 	dto.setRegip(regip);
	 	
	 	service.insertUser(dto);
	 	
	 	/*
	 	//로그인 처리 
		HttpSession session = req.getSession();
		session.setAttribute("sessUser", dto);

		resp.sendRedirect("/Farmstory2");
		*/

		resp.sendRedirect("/Farmstory2/user/login.do?success=0");
	}
}
