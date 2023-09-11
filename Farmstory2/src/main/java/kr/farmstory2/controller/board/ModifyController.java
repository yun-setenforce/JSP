package kr.farmstory2.controller.board;

import java.io.File;
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

import com.oreilly.servlet.MultipartRequest;

import kr.farmstory2.dto.FileDTO;
import kr.farmstory2.dto.ArticleDTO;
import kr.farmstory2.service.ArticleService;
import kr.farmstory2.service.FileService;

@WebServlet("/board/modify.do")
public class ModifyController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService articleService = ArticleService.INSTANCE;
	private FileService fileService = FileService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String no = req.getParameter("no");
		String cate = req.getParameter("cate");
		String group = req.getParameter("group");

		ArticleDTO article = articleService.selectArticle(no);
		

		req.setAttribute("no", no);
		req.setAttribute("cate", cate);
		req.setAttribute("group", group);
		req.setAttribute("article", article);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("modify.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MultipartRequest mr = articleService.uploadFile(req);
		
		// 폼 데이터 수신 
		String group = mr.getParameter("group");
		String cate = mr.getParameter("cate");
		String no = mr.getParameter("no");
		
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String writer = mr.getParameter("writer");
		String oName = mr.getOriginalFileName("file");
		String regip = req.getRemoteAddr();
		//폼 데이터 - 기존 파일 여부 
		String existedFile1 = mr.getParameter("existedFile1");
		String existedFile2 = mr.getParameter("existedFile2");

		logger.debug("no : " + no);
		logger.debug("title : " + title);
		logger.debug("content : " + content);
		logger.debug("writer : " + writer);
		logger.debug("oName : " + oName);
		logger.debug("regip : " + regip);
		logger.debug("existedFile1 : " + existedFile1);
		logger.debug("existedFile2 : " + existedFile2);
		
		// 글 DTO 생성
		ArticleDTO dto = new ArticleDTO();
		dto.setNo(no);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setWriter(writer);
		if(existedFile2 == null) {
			logger.debug("기존 파일 없거나 삭제됨");
			dto.setFile(oName);
		}else {
			dto.setFile(existedFile2);
		}
		dto.setRegip(regip);
		dto.setTitle(title);

		// 글 Update 
		articleService.updateArticle(dto);
		
		//기존 파일 삭제할지 
		boolean condition1 = existedFile1 != null && existedFile2 == null ;
		boolean condition2 = oName != null; 
		// 파일 삭제 
		if(condition1 || condition2) {
			// 기존 파일이 있는데 삭제된 것으로 판명 

			// 파일 삭제 (DB)
			List<String> savedFileNames = fileService.deleteFile(no);

			// 파일 삭제(디렉터리) 
			if(savedFileNames.size() != 0) {
				String path = articleService.getFilePath(req);
				
				for(String fileName : savedFileNames) {
					File file = new File(path + "/" + fileName);
					if(file.exists()) {
						file.delete();
					}
				}
			}
		}
		
		// 파일명 수정 및 파일테이블 Insert 
		if(oName != null) {
			
			String sName = articleService.renameToFile(req, oName);
					
			// 파일 테이블 Insert
			FileDTO fileDto = new FileDTO();
			fileDto.setAno(no);
			fileDto.setOriName(oName);
			fileDto.setNewName(sName);
			
			fileService.insertFile(fileDto);		
		}		
		resp.sendRedirect("/Farmstory2/board/view.do?group="+group+"&cate="+cate+"&no="+no);		
	}
}
