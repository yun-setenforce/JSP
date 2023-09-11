package kr.farmstory2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.farmstory2.dto.FileDTO;
import kr.farmstory2.db.DBHelper;
import kr.farmstory2.db.SQL;
import kr.farmstory2.dto.ArticleDTO;

public class ArticleDAO extends DBHelper{
	
	// 싱글톤 
	private static ArticleDAO instance = new ArticleDAO();
	public static ArticleDAO getInstance() {
		return instance;
	}
	private ArticleDAO() {}
	
	// logger
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 기본 CRUD
	public int insertArticle(ArticleDTO dto) {
		int no = 0;

		try {
			conn = getConnection();
			conn.setAutoCommit(false); // 트랜잭션 시작  
			
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getCate());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setInt(4, dto.getFile());
			psmt.setString(5, dto.getWriter());
			psmt.setString(6, dto.getRegip());
			psmt.executeUpdate();
			rs = stmt.executeQuery(SQL.SELECT_MAX_NO);
			
			conn.commit();
			
			if(rs.next()) {
				no = rs.getInt(1);
			}
			close();
		}catch(Exception e){
			logger.error("insertArticle() error : " + e.getMessage());
		}
		
		
		return no;
	}
	public ArticleDTO selectArticle(String no) {
		ArticleDTO dto = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLE);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new ArticleDTO();
				dto.setNo(rs.getInt(1));
				dto.setParent(rs.getInt(2));
				dto.setComment(rs.getInt(3));
				dto.setCate(rs.getString(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setFile(rs.getInt(7));
				dto.setHit(rs.getInt(8));
				dto.setWriter(rs.getString(9));
				dto.setRegip(rs.getString(10));
				dto.setRdate(rs.getString(11));
				dto.setNick(rs.getString(12));
				
				//파일 정보 
				FileDTO fileDto = new FileDTO();
				fileDto.setFno(rs.getInt("fno"));
				fileDto.setAno(rs.getInt("ano"));
				fileDto.setOriName(rs.getString("oriName"));
				fileDto.setNewName(rs.getString("newName"));
				fileDto.setDownload(rs.getInt("download"));
				fileDto.setRdate(rs.getString("rdate"));

				dto.setFileDto(fileDto);
			}
			close();
		} catch (Exception e) {
			logger.error("selectArticle() error : " + e.getMessage());
		}
		return dto;
	}
	public List<ArticleDTO> selectArticles(String cate, int start) {
		List<ArticleDTO> articles = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLES);
			psmt.setString(1, cate);
			psmt.setInt(2, start);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleDTO dto = new ArticleDTO();
				dto.setNo(rs.getInt(1));
				dto.setParent(rs.getInt(2));
				dto.setComment(rs.getInt(3));
				dto.setCate(rs.getString(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setFile(rs.getInt(7));
				dto.setHit(rs.getInt(8));
				dto.setWriter(rs.getString(9));
				dto.setRegip(rs.getString(10));
				dto.setRdate(rs.getString(11));
				dto.setNick(rs.getString(12));
				articles.add(dto);
			}
			close();
		}catch (Exception e) {
			logger.error("selectArticles() error : " + e.getMessage());
		}
		
		return articles;
	}
	public void updateArticle(ArticleDTO dto) {
		try {
			conn=getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getFile());
			psmt.setString(4, dto.getRegip());
			psmt.setInt(5, dto.getNo());
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("updateArticle() error : " + e.getMessage());
		}
	}
	public void deleteArticle(String no) {
		try {
			conn = getConnection();
			logger.debug(no);
			psmt = conn.prepareStatement(SQL.DELETE_ARTICLE);
			psmt.setString(1, no);
			psmt.setString(2, no);
			psmt.executeUpdate();
			
			close();
		}catch(Exception e){
			logger.error("deleteArticle() error : " + e.getMessage());
		}
	}
	public boolean isUserEqualWriter(String uid, String no) {
		boolean isEqual = false;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLE_FOR_CHECK_WRITER);
			psmt.setString(1, no);
			psmt.setString(2, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				isEqual = true;
			}
			
			close();
		} catch (Exception e) {
			logger.error("isUserEqualWriter() error : " + e.getMessage());
		}
		return isEqual;
	}
	
	// 추가
	// index.do - 최신글 
	public List<ArticleDTO> selectLatests(String cate, int size) {
		List<ArticleDTO> latests = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_LATESTS);
			psmt.setString(1, cate);
			psmt.setInt(2, size);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleDTO dto = new ArticleDTO();
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setRdate(rs.getString("rdate"));
				latests.add(dto);
			}
			close();
		}catch (Exception e) {
			logger.error("selectLatest() error : " + e.getMessage());
		}
		
		return latests;
	}

	public int selectCountTotal(String cate) {		
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL);
			psmt.setString(1, cate);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result =  rs.getInt(1);
			}
			close();
		}catch (Exception e) {
			logger.error("selectLatest() error : " + e.getMessage());
		}
		
		return result;
	}
	
	public void updateHit(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE_HIT);
			psmt.setString(1, no);
			
			psmt.executeUpdate();
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	// 코멘트 - 게시글별 조회, 입력, 코멘트 컬럼++--, 코멘트 수정, 코멘트 삭제
	// view.do : 게시물별 댓글들 조회 
	public List<ArticleDTO> selectComments(String parent) {
		List<ArticleDTO>  comments = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COMMENTS);
			psmt.setString(1, parent);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				ArticleDTO dto = new ArticleDTO();
				dto.setNo(rs.getInt("no"));
				dto.setParent(rs.getInt("parent"));
				dto.setComment(rs.getInt("comment"));
				dto.setCate(rs.getString("cate"));
				dto.setContent(rs.getString("content"));
				dto.setFile(rs.getShort("file"));
				dto.setHit(rs.getInt("hit"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegip(rs.getString("regip"));
				dto.setRdate(rs.getString("rdate"));
				dto.setNick(rs.getString("nick"));
				comments.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("selectComments() error : " + e.getMessage());
		}
		return comments;
	}

	// view.do : 댓글 입력 
	public int[] insertComment(ArticleDTO dto , String parent) {
		
		int[] result = {0, 0}; 
		
		try {
			conn=getConnection();

			conn.setAutoCommit(false); // 트랜잭션 시작  
			
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.INSERT_COMMENT);
			psmt.setString(1,dto.getCate());
			psmt.setInt(2,dto.getParent());
			psmt.setString(3,dto.getContent());
			psmt.setString(4, dto.getWriter());
			psmt.setString(5,dto.getRegip());
			result[0] = psmt.executeUpdate();
			rs = stmt.executeQuery(SQL.SELECT_COMMENT_MAX_NO);
			
			conn.commit();
			
			if(rs.next()) {
				result[1] = rs.getInt(1);
			}
			close();
		
		}catch(Exception e) {
			logger.error("error insertComment() : " + e.getMessage());
		}
		return result;
	}
	
	// insertComment > 게시물의 댓글 수 +1
	public void updateArticleForCommentPlus(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE_FOR_COMMENT_PLUS);
			psmt.setString(1, no);
			
			psmt.executeUpdate();
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	// deleteComment > 게시물의 댓글 수 -1
	public void updateArticleForCommentMinus(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE_FOR_COMMENT_MINUS);
			psmt.setString(1, no);
			
			psmt.executeUpdate();
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// view.do > 댓글 수정 
	public int updateComment(String no, String comment) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_COMMENT);
			psmt.setString(1, comment);
			psmt.setString(2, no);
			
			result = psmt.executeUpdate();
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// view.do -> 댓글 삭제
	public int deleteComment(String no) {
		int result = 0;
		try {
			conn=getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_COMMENT);
			psmt.setString(1,no);
			
			result = psmt.executeUpdate();
			close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
