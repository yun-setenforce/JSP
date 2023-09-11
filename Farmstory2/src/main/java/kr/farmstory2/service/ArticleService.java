package kr.farmstory2.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.farmstory2.dto.FileDTO;
import kr.farmstory2.dao.ArticleDAO;
import kr.farmstory2.dto.ArticleDTO;

public enum ArticleService {
	
	INSTANCE;
	
	private ArticleDAO dao = ArticleDAO.getInstance();
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int insertArticle(ArticleDTO dto) {
		return dao.insertArticle(dto);
	}
	public ArticleDTO selectArticle(String no) {
		return dao.selectArticle(no);
	}
	public List<ArticleDTO> selectArticles(String cate, int start) {
		return dao.selectArticles(cate, start);
	}
	public void updateArticle(ArticleDTO dto) {
		dao.updateArticle(dto);
	}
	public void deleteArticle(String no) {
		dao.deleteArticle(no);
	}
	
	public List<ArticleDTO> selectLatests(String cate, int size) {
		return dao.selectLatests(cate, size);
	}

	public int selectCountTotal(String cate) {
		return dao.selectCountTotal(cate);
	}

	//조회수 업데이트 
	public void updateHit(String no) {
		dao.updateHit(no);
	}
	// 글쓴이와 로그인 사용자 일치하는지 확인 
	public boolean isUserEqualWriter(String uid, String no) {
		return dao.isUserEqualWriter(uid, no);
		
	}

	/** 코멘트 **/

	public int[] insertComment(ArticleDTO dto, String parent) {
		return dao.insertComment(dto , parent);
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

	
	//추가
	
	/** 파일 **/
	

	
	// 업로드 경로 구하기 
	public String getFilePath(HttpServletRequest req) {
		// 파일 업로드 경로 구하기
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/upload");
				
		return path;
		
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
	public int downloadFile(HttpServletRequest req, HttpServletResponse resp, FileDTO fileDto)  {

		int result = 0;
		/** 다운로드 처리 로직 **/
		try {
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
			result = 1;
		} catch (Exception e) {
			logger.error("error downloadFile() : " + e.getMessage());
		}
		return result;
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
	
}
