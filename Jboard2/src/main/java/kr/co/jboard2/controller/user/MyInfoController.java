package kr.co.jboard2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.UserService;

@WebServlet("/user/myInfo.do")
public class MyInfoController extends HttpServlet{

	private static final long serialVersionUID = 4104451605699619555L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("myInfo.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String kind = req.getParameter("kind"); //3가지 타입의 dopost 구분 - 1.비밀번호 변경, 2. 회원 탈퇴, 3.회원수정 
		String uid = req.getParameter("uid");
		String pass = req.getParameter("pass");
		

		String name = req.getParameter("name");
		String nick = req.getParameter("nick");
		String email = req.getParameter("email");
		String hp = req.getParameter("hp");
		String zip = req.getParameter("zip");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		
		logger.debug("kind: " + kind);
		logger.debug("uid: " + uid);

		logger.debug(uid+ "/" + nick+ "/" + email+ "/" + hp+ "/" + zip+ "/" + addr1+ "/" + addr2);
		
		
		switch(kind) {
		case "WITHDRAW" :
			int result1 = service.updateUserForWithdraw(uid);
			JsonObject json1 = new JsonObject();
			
			// JSON 생성 
			json1.addProperty("result", result1);
			
			// JSON 출력
			resp.getWriter().print(json1);
			
			// ajax요청이기 때문에 redirect응답처리 안됨
			// resp.sendRedirect("/Jboard2/user/login.do");
			// ajax요청이기 때문에 ajax에 대한 응답 결과는 json이 되어야 함 
			break;
		case "PASSWORD" : 
			int result2 = service.updateUserPass(uid, pass);
			JsonObject json2 = new JsonObject();
			json2.addProperty("result", result2);
			resp.getWriter().print(json2);
			break;
		case "MODIFY" : 
			UserDTO dto = new UserDTO();
			dto.setUid(uid);
			dto.setName(name);
			dto.setNick(nick);
			dto.setEmail(email);
			dto.setHp(hp);
			dto.setZip(zip);
			dto.setAddr1(addr1);
			dto.setAddr2(addr2);
			
			service.updateUser(dto);
			resp.sendRedirect("/Jboard2/user/logout.do?success=400");
			break;
		default : 
			resp.sendRedirect("/Jboard2/user/myInfo.do");
			break;
		}
	}
}
