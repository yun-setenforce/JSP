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

@WebServlet("/user/login.do")
public class LoginController extends HttpServlet{

	private static final long serialVersionUID = 2839882265339637682L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/*
		String target = req.getParameter("target");
		String group  = req.getParameter("group");
		String cate   = req.getParameter("cate");
		String no     = req.getParameter("no");	
		*/
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		String uid   = req.getParameter("uid");
		String pass  = req.getParameter("pass");
		String target = req.getParameter("target");
		String group = req.getParameter("group");
		String cate  = req.getParameter("cate");
		String no    = req.getParameter("no");
		
		UserDTO user = service.selectUserForLogin(uid, pass);
		
		if(user != null) {
			HttpSession session = req.getSession();
			session.setAttribute("sessUser", user);

			logger.info(user.toString());
			
			if(target.equals("write")){
				resp.sendRedirect("/Farmstory2/board/write.do?group="+group+"&cate="+cate);	
			}else if(target.equals("view")){
				resp.sendRedirect("/Farmstory2/board/view.do?group="+group+"&cate="+cate+"&no="+no);	
			}else{
				resp.sendRedirect("/Farmstory2");
			}
		} else {
			resp.sendRedirect("/Farmstory2/user/login.do?success=100");
		}
				
	}
	
}
