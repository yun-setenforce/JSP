package kr.farmstory2.controller.market;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.farmstory2.dto.ProductDTO;
import kr.farmstory2.service.ProductService;

@WebServlet("/market/view.do")
public class ViewController extends HttpServlet {

	private static final long serialVersionUID = 7782952492719065724L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ProductService service = ProductService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pNo = req.getParameter("pNo");
		ProductDTO dto = service.selectProduct(pNo);
		
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("view.jsp");
		dispatcher.forward(req, resp);
		
	}
}
