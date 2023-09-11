package kr.farmstory2.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.farmstory2.dto.ArticleDTO;
import kr.farmstory2.dto.UserDTO;
import kr.farmstory2.service.ArticleService;

@WebServlet("/board/view.do")
public class ViewController extends HttpServlet{

	private static final long serialVersionUID = 2395190540177935964L;
	
	private Logger logger =  LoggerFactory.getLogger(this.getClass());
	private ArticleService service = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no");
		String cate = req.getParameter("cate");
		String group = req.getParameter("group");
		
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		
		
		ArticleDTO article = service.selectArticle(no);
		if(! article.getWriter().equals(sessUser.getUid())) {
			service.updateHit(no); //글쓴이와 일치하지 않으면 조회수 +1
			article.setHit(article.getHit()+1);
		}
		List<ArticleDTO> comments = service.selectComments(no);
		
		req.setAttribute("no", no);
		req.setAttribute("cate", cate);
		req.setAttribute("group", group);
		req.setAttribute("article", article);
		req.setAttribute("comments", comments);

		RequestDispatcher dispatcher = req.getRequestDispatcher("view.jsp");
		dispatcher.forward(req, resp);			
		
		
	}
}
