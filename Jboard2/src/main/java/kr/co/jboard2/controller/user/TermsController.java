package kr.co.jboard2.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.dto.TermsDTO;
import kr.co.jboard2.service.TermsService;


@WebServlet("/user/terms.do")
public class TermsController extends HttpServlet{

	private TermsService service = TermsService.INSTANCE;
	private static final long serialVersionUID = -5526161827095080595L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TermsDTO dto = service.selectTerms();
    	
    	//View에서 users참조하기 위해 request Scope 저장 	
    	req.setAttribute("dto", dto);
    	
		RequestDispatcher dispatcher = req.getRequestDispatcher("terms.jsp");
		dispatcher.forward(req, resp);
	}
}
