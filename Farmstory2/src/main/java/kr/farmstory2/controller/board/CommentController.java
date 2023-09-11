package kr.farmstory2.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.farmstory2.dto.ArticleDTO;
import kr.farmstory2.dto.UserDTO;
import kr.farmstory2.service.ArticleService;

@WebServlet("/board/comment.do")
public class CommentController extends HttpServlet{

	private static final long serialVersionUID = 4917872886547747416L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService service = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String kind = req.getParameter("kind");
		String no = req.getParameter("no");
		String parent = req.getParameter("parent");
		String content = req.getParameter("content");
		int result = 0;
		
		// 아이디 맞는지 테스트 
		HttpSession session = req.getSession();
  		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		boolean userOK = service.isUserEqualWriter(sessUser.getUid(), no);

		if(!userOK) {
			//로그인한 상대와 삭제하려는 상대가 동일하지 않을 경우 return 
			result = 0;
		} else {
			switch(kind) {
			case "REMOVE":
				result = service.deleteComment(no);
				service.updateArticleForCommentMinus(parent);
				break;
			case "MODIFY":
				result = service.updateComment(no, content);
				break;
			}
		}
		//JSON 출력 
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().print(json);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cate = req.getParameter("cate");
		String parent = req.getParameter("parent");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		String regip = req.getRemoteAddr();
		
		logger.debug("cate : " + cate);
		logger.debug("parent : " + parent);
		logger.debug("content : " + content);
		logger.debug("writer : " + writer);
		logger.debug("regip : " + regip);
		
		ArticleDTO dto = new ArticleDTO();
		dto.setCate(cate);
		dto.setParent(parent);
		dto.setContent(content);
		dto.setWriter(writer);
		dto.setRegip(regip);

		// 댓글 입력
		int result[] = service.insertComment(dto, parent);
		
		// 댓글 카운트 수정 Plus
		service.updateArticleForCommentPlus(parent);

		//JSON (Ajax요청)
		JsonObject json = new JsonObject();
		json.addProperty("result", result[0]);
		json.addProperty("no", result[1]);
		resp.getWriter().print(json);
		
		
	}

}
