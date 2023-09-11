package kr.co.jboard2.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.FileDTO;
import kr.co.jboard2.service.ArticleService;
import kr.co.jboard2.service.FileService;

@WebServlet("/write.do")
public class WriteController extends HttpServlet {

	private static final long serialVersionUID = 992090960044622875L;

	private ArticleService aService = ArticleService.INSTANCE;
	private FileService fService = FileService.INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("write.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = aService.getFilePath(req);
		
		MultipartRequest mr = aService.uploadFile(req);
		
		// 폼 데이터 수신 
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
		dto.setTitle(title);
		dto.setContent(content);
		dto.setWriter(writer);
		dto.setFile(oName);
		dto.setRegip(regip);
		dto.setTitle(title);
		
		// 글 Insert 
		int aNo = aService.insertArticle(dto);

		// 파일명 수정 및 파일테이블 Insert 
		if(oName != null) {
			
			String sName = aService.renameToFile(req, oName);
					
			// 파일 테이블 Insert
			FileDTO fileDto = new FileDTO();
			fileDto.setAno(aNo);
			fileDto.setOriName(oName);
			fileDto.setNewName(sName);
			
			fService.insertFile(fileDto);		
		}		
		resp.sendRedirect("/Jboard2/list.do");			
	}
}
