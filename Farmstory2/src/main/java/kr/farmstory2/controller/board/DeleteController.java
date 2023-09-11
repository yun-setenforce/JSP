package kr.farmstory2.controller.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.farmstory2.dto.UserDTO;
import kr.farmstory2.service.ArticleService;
import kr.farmstory2.service.FileService;

@WebServlet("/board/delete.do")
public class DeleteController extends HttpServlet{

	private static final long serialVersionUID = -231386126827062765L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService articleService = ArticleService.INSTANCE;
	private FileService fileService = FileService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no");
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		
		HttpSession session = req.getSession();
  		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		// 아이디 맞는지 테스트 
		boolean userOK = articleService.isUserEqualWriter(sessUser.getUid(), no);

		if(!userOK) {
			//로그인한 상대와 삭제하려는 상대가 동일하지 않을 경우 return 
			resp.sendRedirect("/Farmstory2/board/view.do?group="+group+"&cate="+cate+"&no="+no);
		}

		// 파일 삭제 (DB)
		List<String> savedFileNames = fileService.deleteFile(no);
		
		// 게시글과 댓글 삭제 
		articleService.deleteArticle(no);
		
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
		resp.sendRedirect("/Farmstory2/board/list.do?group="+group+"&cate="+cate+"&no="+no);
			
		
	}
}
