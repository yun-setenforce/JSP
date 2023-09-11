package kr.farmstory2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.farmstory2.dto.TermsDTO;
import kr.farmstory2.service.TermsService;

@WebServlet("/user/terms.do")
public class TermsController extends HttpServlet{

	private static final long serialVersionUID = -156465983683633388L;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	TermsService service = TermsService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TermsDTO dto = service.selectTerms();
		
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("terms.jsp");
		dispatcher.forward(req, resp);
	}
}
