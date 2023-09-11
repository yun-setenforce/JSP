package kr.farmstory2.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.farmstory2.dto.ArticleDTO;
import kr.farmstory2.service.ArticleService;

@WebServlet("/board/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = 8630271538602900585L;
	
	private Logger logger =  LoggerFactory.getLogger(this.getClass());
	private ArticleService service = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String group = req.getParameter("group");
		String cate  = req.getParameter("cate");
		String pg    = req.getParameter("pg");

		// 페이지 관련 변수 선언
		int start = 0;
		int currentPage = 1;
		int total = 0;
		int lastPageNum = 0;
		int pageGroupCurrent = 1;
		int pageGroupStart = 1;
		int pageGroupEnd = 0;
		int pageStartNum = 0;
		
		// 현재 페이지 계산
		if(pg != null){
			currentPage = Integer.parseInt(pg);
		}
		
		// Limit 시작값 계산
		start = (currentPage - 1) * 10;
		
		// 전체 게시물 갯수 조회
		total = service.selectCountTotal(cate);
		
		// 페이지 번호 계산
		if(total % 10 == 0){
			lastPageNum = (total / 10);
		}else{
			lastPageNum = (total / 10) + 1;
		}
		
		// 페이지 그룹 계산
		pageGroupCurrent = (int) Math.ceil(currentPage / 10.0);
		pageGroupStart = (pageGroupCurrent - 1) * 10 + 1;
		pageGroupEnd = pageGroupCurrent * 10;
		
		if(pageGroupEnd > lastPageNum){
			pageGroupEnd = lastPageNum;
		}
		
		// 페이지 시작번호 계산
		pageStartNum = total - start;	
		
		// 현재 페이지 게시물 조회
		List<ArticleDTO> articles = service.selectArticles(cate, start);
		
		//데이터 
		req.setAttribute("articles", articles);
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		//페이지
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", pageGroupStart);
		req.setAttribute("pageGroupEnd", pageGroupEnd);
		req.setAttribute("pageStartNum", pageStartNum+1);
				
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("list.jsp");
		dispatcher.forward(req, resp);
		
	}
}
