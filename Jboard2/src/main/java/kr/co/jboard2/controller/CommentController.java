package kr.co.jboard2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.mysql.cj.xdevapi.Result;

import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.service.ArticleService;

@WebServlet("/comment.do")
public class CommentController  extends HttpServlet {

	private static final long serialVersionUID = 3011899206736700294L;


	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService service = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String kind = req.getParameter("kind");
		String no = req.getParameter("no");
		String content = req.getParameter("content");
		
		int result = 0;
		switch(kind) {
		case "REMOVE":
			result = service.deleteComment(no);
			break;
		case "MODIFY":
			result = service.updateComment(no, content);
			break;
		}
		
		//JSON 출력 
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().print(json);
		
	}
	private void doGet() {
		// TODO Auto-generated method stub

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String parent = req.getParameter("parent");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		String regip = req.getRemoteAddr();
		
		logger.debug("parent : " + parent);
		logger.debug("content : " + content);
		logger.debug("writer : " + writer);
		logger.debug("regip : " + regip);
		
		ArticleDTO dto = new ArticleDTO();
		dto.setParent(parent);
		dto.setContent(content);
		dto.setWriter(writer);
		dto.setRegip(regip);
		
		// 댓글 입력
		int result = service.insertComment(dto);
		
		// 댓글 카운트 수정 Plus
		service.updateArticleForCommentPlus(parent);
		
		// 리다이렉트
		// resp.sendRedirect("/Jboard2/view.do?no="+parent);
		
		//JSON (Ajax요청)
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().print(json);
	}

}
