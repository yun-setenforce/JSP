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

import kr.co.jboard2.service.UserService;

@WebServlet("/user/findPass.do")
public class FindPassController extends HttpServlet{

	private static final long serialVersionUID = -2456492756360307051L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("findPass.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		String uid = req.getParameter("uid");
		
		/*
		 * 
		req.setAttribute("uid", uid);
		RequestDispatcher dispatcher = req.getRequestDispatcher("findPassChange.jsp");
		dispatcher.forward(req, resp);
		 */
		HttpSession session = req.getSession();
		session.setAttribute("uid", uid);
		
		
		resp.sendRedirect("/Jboard2/user/findPassChange.do");
	}
}
