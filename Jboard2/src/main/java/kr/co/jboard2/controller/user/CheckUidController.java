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

@WebServlet("/user/checkUid.do")
public class CheckUidController extends HttpServlet {

	private static final long serialVersionUID = 8854259056071601302L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		int result = service.selectCountUid(uid);
		
		logger.info("result : " + result);
		
		//JSON 출력 
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		// JSON 출력 
		PrintWriter writer = resp.getWriter(); //MVC1에서는 jsp파일에 out.print했다. 
		writer.print(json.toString());
	}
}
