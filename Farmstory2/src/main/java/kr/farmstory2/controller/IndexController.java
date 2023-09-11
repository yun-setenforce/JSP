package kr.farmstory2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.farmstory2.dto.ArticleDTO;
import kr.farmstory2.service.ArticleService;

//시작페이지 index.jsp가 없기 때문에 기본주소("")에 대한 맵핑 추가
@WebServlet(value = {"", "/index.do"})
public class IndexController extends HttpServlet{

	private static final long serialVersionUID = 7936707976664594115L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService service = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		List<ArticleDTO> latests1 = service.selectLatests("grow", 5);
		List<ArticleDTO> latests2 = service.selectLatests("school", 5);
		List<ArticleDTO> latests3 = service.selectLatests("story", 5);
		
		List<ArticleDTO> tabLatest1 = service.selectLatests("notice", 3);
		List<ArticleDTO> tabLatest2 = service.selectLatests("qna", 3);
		List<ArticleDTO> tabLatest3 = service.selectLatests("faq", 3);
		
		req.setAttribute("latests1", latests1);
		req.setAttribute("latests2", latests2);
		req.setAttribute("latests3", latests3);
		
		req.setAttribute("tabLatest1", tabLatest1);
		req.setAttribute("tabLatest2", tabLatest2);
		req.setAttribute("tabLatest3", tabLatest3);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
		dispatcher.forward(req, resp);
	}
}
