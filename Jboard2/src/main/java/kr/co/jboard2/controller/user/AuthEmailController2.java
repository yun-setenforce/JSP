package kr.co.jboard2.controller.user;

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

import kr.co.jboard2.service.UserService;

@WebServlet("/user/authEmail2.do")
public class AuthEmailController2 extends HttpServlet{
	
	private static final long serialVersionUID = 9166647122258489388L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		
		// 중복여부 
		int result = service.selectCountEmail(email);
		// 전송여부 
		int status = service.sendCodeByEmail(email);
		

		//JSON 출력 
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		json.addProperty("status", status);
		
		// JSON 출력 
		PrintWriter writer = resp.getWriter(); 
		writer.print(json.toString());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");

		int result = service.confirmCodeByEmail(code);
		//JSON 출력 
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		// JSON 출력 
		PrintWriter writer = resp.getWriter(); 
		writer.print(json.toString());
		
	}
}
