package kr.farmstory2.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.farmstory2.dto.FileDTO;
import kr.farmstory2.dto.ArticleDTO;
import kr.farmstory2.dto.UserDTO;
import kr.farmstory2.service.ArticleService;
import kr.farmstory2.service.FileService;

@WebServlet("/board/write.do")
public class WriteController extends HttpServlet{

	private static final long serialVersionUID = 8630271538602900585L;
	
	private Logger logger =  LoggerFactory.getLogger(this.getClass());
	private ArticleService service = ArticleService.INSTANCE;
	private FileService fileService = FileService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String group = req.getParameter("group");
		String cate  = req.getParameter("cate");
		
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		
		if(sessUser == null) {
			resp.sendRedirect("/Farmstory2/user/login.do?success=101&target=write&group="+group+"&cate="+cate);
			return;
		}
			
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("write.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MultipartRequest mr = service.uploadFile(req);

		// 폼 데이터 수신 
		String group = mr.getParameter("group");
		String cate = mr.getParameter("cate");
		
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String writer = mr.getParameter("writer");
		String oName = mr.getOriginalFileName("file");
		String regip = req.getRemoteAddr();

		logger.debug("title : " + title);
		logger.debug("content : " + content);
		logger.debug("writer : " + writer);
		logger.debug("oName : " + oName);
		logger.debug("regip : " + regip);

		// 글 DTO 생성
		ArticleDTO dto = new ArticleDTO();
		dto.setCate(cate);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setWriter(writer);
		dto.setFile(oName);
		dto.setRegip(regip);
		dto.setTitle(title);

		// 글 Insert 
		int aNo = service.insertArticle(dto);

		// 파일명 수정 및 파일테이블 Insert 
		if(oName != null) {
			
			String sName = service.renameToFile(req, oName);
					
			// 파일 테이블 Insert
			FileDTO fileDto = new FileDTO();
			fileDto.setAno(aNo);
			fileDto.setOriName(oName);
			fileDto.setNewName(sName);
			
			fileService.insertFile(fileDto);		
		}		
		logger.info("WriteController write INFO : " + dto.toString());
		resp.sendRedirect("/Farmstory2/board/list.do?group="+group+"&cate="+cate);		
		
		
		
	}
}
