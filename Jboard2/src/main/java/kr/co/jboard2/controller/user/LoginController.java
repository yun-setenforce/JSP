package kr.co.jboard2.controller.user;

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

import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.UserService;

@WebServlet("/user/login.do")
public class LoginController  extends HttpServlet{

	private static final long serialVersionUID = -721710472324177999L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//로그인 실패 사유 
		String success = req.getParameter("success");
		req.setAttribute("success", success);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String pass = req.getParameter("pass");
		
		UserDTO user = service.selectUser(uid, pass);
		
		if(user!=null) {
			// 현재 세션 구하기 
			HttpSession session = req.getSession();
			
			// 사용자 세션 설정 
			session.setAttribute("sessUser", user);
			
			// 리다이렉트 
			resp.sendRedirect("/Jboard2/list.do");
		} else {
			//리다이렉트
			resp.sendRedirect("/Jboard2/user/login.do?success=100");
		}
	}

}
