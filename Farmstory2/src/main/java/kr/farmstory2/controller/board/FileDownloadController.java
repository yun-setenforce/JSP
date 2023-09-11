package kr.farmstory2.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.farmstory2.dto.FileDTO;
import kr.farmstory2.service.ArticleService;
import kr.farmstory2.service.FileService;

@WebServlet("/board/fileDownload.do")
public class FileDownloadController extends HttpServlet{

	private static final long serialVersionUID = 3520163111660989970L;

	private Logger logger =  LoggerFactory.getLogger(this.getClass());
	private ArticleService articleService = ArticleService.INSTANCE;
	private FileService fileService = FileService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//파일 조회 
		String fno = req.getParameter("fno");
		
		FileDTO fileDto = fileService.selectFile(fno);
		logger.debug(fileDto.toString());
		
		int result = articleService.downloadFile(req, resp, fileDto);
		if(result > 0) {
			fileService.updateDownload(fileDto.getAno());
		}
		/*
		//JSON 출력 
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().print(json);
		*/
	}
	
}
