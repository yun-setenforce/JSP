package kr.farmstory2.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.farmstory2.service.UserService;

@WebServlet("/user/checkEmail.do")
public class CheckEmailController extends HttpServlet{

	private static final long serialVersionUID = 6375495286524483444L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		int result = service.selectCountEmail(email);
		
		logger.info("selectCountEmail() result : " + result);
		
		//JSON 생성
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		//JSON 출력 
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());

	}
	
}
