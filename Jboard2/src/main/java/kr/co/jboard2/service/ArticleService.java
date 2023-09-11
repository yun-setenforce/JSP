package kr.co.jboard2.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.jboard2.dao.ArticleDAO;
import kr.co.jboard2.db.SQL;
import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.FileDTO;

public enum ArticleService {
	
	INSTANCE;
	
	ArticleDAO dao = ArticleDAO.getInstance();

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public int insertArticle(ArticleDTO dto) {
		return dao.insertArticle(dto);
	}

	public ArticleDTO selectArticle(String no) {
		return dao.selectArticle(no);
	}
	public List<ArticleDTO> selectArticles(int start, String search) {
		return dao.selectArticles(start, search);
	}
	
	public void UpdateArticle(ArticleDTO dto) {
		dao.UpdateArticle(dto);
	}
	
	public void deleteArticle(String no) {
		dao.deleteArticle(no);
	}
	

	public int selectCountTotal(String search) {
		return dao.selectCountTotal(search);
	}
	
	
	// 업로드 경로 구하기 
	public String getFilePath(HttpServletRequest req) {
		// 파일 업로드 경로 구하기
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/upload");
				
		return path;
		
	}
	
	// 파일명 수정 
	public String renameToFile(HttpServletRequest req, String oName) {
		
		String path = getFilePath(req);
		
		int i = oName.lastIndexOf(".");
		String ext = oName.substring(i); //확장자 (.포함)
		
		String uuid = UUID.randomUUID().toString();
		String sName = uuid + ext;
		
		File f1 = new File(path + "/" + oName); //oName으로 저장된 파일 객체 
		File f2 = new File(path + "/" + sName); //가상의 파일 객체
		
		// 파일명 수정 
		f1.renameTo(f2); //f2로 파일명 수정
		
		return sName;
	}
	
	
	// 파일 업로드
	public MultipartRequest uploadFile(HttpServletRequest req) {
		//파일 경로 구하기 
		String path = getFilePath(req);
		
		// 최대 업로드 파일 크기
		int maxSize = 1024 * 1024 * 10; //10MB 
		MultipartRequest mr = null;
		try {
			// 파일 업로드 및 Multipart 객체 생성   (스트림 처리)
			mr = new MultipartRequest(req,  	//request 객체
									  path, 	//파일경로
									  maxSize, 	//파일Max크기
									  "UTF-8", 	//인코딩(UTF-8)
									  new DefaultFileRenamePolicy()); //defaultRename
		} catch (IOException e) {
			
			logger.error("error uploadFile() : " + e.getMessage());
		}
		return mr;
	}
	
	// 파일 다운로드 
	public void downloadFile(HttpServletRequest req, HttpServletResponse resp, FileDTO fileDto) throws IOException {

		/** 다운로드 처리 로직 **/
		
		//resp 파일 다운로드 헤더 수정 
		resp.setContentType("application/octet-stream"); ///resp = 서버에서 클라이언트로 넘어가는 내장 객체 . /// application파일이다. 
		resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fileDto.getOriName(), "utf-8")); ////파일에 대한 정보 
		resp.setHeader("Content-Transfer-Encoding", "binary");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "private");
		
		//resp 파일 스트림 작업 
		String path = getFilePath(req);
		
		File file = new File(path+"/"+ fileDto.getNewName());
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file)); /// /upload폴더에서 읽어옴 
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());/// resp객체를 통해 사용자에게 보냄
		
		while(true){
			int data = bis.read();
			if(data == -1){
				break;
			}
			bos.write(data);
		}
		bos.close();
		bis.close();
		
	}
	
	/** 코멘트 **/

	public int insertComment(ArticleDTO dto) {
		return dao.insertComment(dto);
	}
	// view.do : 게시물별 댓글들 조회 
	public List<ArticleDTO> selectComments(String parent) {
		return dao.selectComments(parent);
	}
	
	// insertComment > 게시물의 댓글 수 +1
	public void updateArticleForCommentPlus(String no) {
		dao.updateArticleForCommentPlus(no);
	}
	// deleteComment > 게시물의 댓글 수 -1
	public void updateArticleForCommentMinus(String no) {
		dao.updateArticleForCommentMinus(no);
	}
	
	// view.do > 댓글 수정 
	public int updateComment(String no, String comment) {
		return dao.updateComment(no, comment);
	}
	
	// view.do -> 댓글 삭제
	public int deleteComment(String no) {
		return dao.deleteComment(no);
	}

	
	/** 페이징 **/

	// 페이지 마지막 번호
	public int getLastPageNum(int total) {
		
		int lastPageNum = 0;
		
		if(total % 10 == 0){
			lastPageNum = total / 10;
		}else{
			lastPageNum = total / 10 + 1;
		}
		
		return lastPageNum;
	}
	
	// 페이지 그룹
	public int[] getPageGroupNum(int currentPage, int lastPageNum) {
		int currentPageGroup = (int)Math.ceil(currentPage / 10.0);
		int pageGroupStart = (currentPageGroup - 1) * 10 + 1;
		int pageGroupEnd = currentPageGroup * 10;
		
		if(pageGroupEnd > lastPageNum){
			pageGroupEnd = lastPageNum;
		}
		
		int[] result = {pageGroupStart, pageGroupEnd};
		
		return result;
	}
	
	// 페이지 시작번호
	public int getPageStartNum(int total, int currentPage) {
		int start = (currentPage - 1) * 10;
		return total - start;
	}
	
	// 현재 페이지 번호
	public int getCurrentPage(String pg) {
		int currentPage = 1;
		
		if(pg != null){
			currentPage = Integer.parseInt(pg);	
		}
		
		return currentPage;
	}
	
	// Limit 시작번호
	public int getStartNum(int currentPage) {
		return (currentPage - 1) * 10;
	}
}
