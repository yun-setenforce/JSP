package kr.co.jboard2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.db.SQL;
import kr.co.jboard2.dto.ArticleDTO;

public class ArticleDAO extends DBHelper{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 싱글톤
	private ArticleDAO() {}
	private static ArticleDAO instance = new ArticleDAO();
	public static ArticleDAO getInstance() {
		return instance;
	}
	
	public int insertArticle(ArticleDTO dto) {
		int no = 0;
		try {
			conn = getConnection();
			conn.setAutoCommit(false); // 트랜잭션 시작  
			
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE );
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getFile());
			psmt.setString(4, dto.getWriter());
			psmt.setString(5, dto.getRegip());
			psmt.executeUpdate();
			rs = stmt.executeQuery(SQL.SELECT_MAX_NO);
			
			conn.commit(); // 작업확정 
			
			if(rs.next()) {
				no = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
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
				dto.setNo(rs.getInt("no"));
				dto.setParent(rs.getInt("parent"));
				dto.setComment(rs.getInt("comment"));
				dto.setCate(rs.getString("cate"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setFile(rs.getShort("file"));
				dto.setHit(rs.getInt("hit"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegip(rs.getString("regip"));
				dto.setRdate(rs.getString("rdate"));
				dto.setNick(rs.getString("nick"));
				
			}
		
			close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public List<ArticleDTO> selectArticles(int start) {
		List<ArticleDTO> articles = new ArrayList<>();

		try {
			conn = getConnection();

			psmt = conn.prepareStatement(SQL.SELECT_ARTICLES);
			psmt.setInt(1,start);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleDTO vo = new ArticleDTO();
				vo.setNo(rs.getInt(1));
				vo.setParent(rs.getInt(2));
				vo.setComment(rs.getInt(3));
				vo.setCate(rs.getString(4));
				vo.setTitle(rs.getString(5));
				vo.setContent(rs.getString(6));
				vo.setFile(rs.getShort(7));
				vo.setHit(rs.getInt(8));
				vo.setWriter(rs.getString(9));
				vo.setRegip(rs.getString(10));
				vo.setRdate(rs.getString(11));
				vo.setNick(rs.getString(12));
				articles.add(vo);
			}
			
			
			close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return articles;
	}
	
	public void UpdateArticle(ArticleDTO dto) {
		
	}
	
	public void deleteArticle(int no) {
		
	}
	
	//추가
	
	// list.do : 총 게시물 수 
	public int selectCountTotal() {
		int total = 0;
		
		try {
			conn=getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL);
			rs = psmt.executeQuery();
		
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * comment
	 */

	// view.do : 댓글 입력 
	public void insertComment(ArticleDTO dto) {
		try {
			conn=getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_COMMENT);
			psmt.setInt(1,dto.getParent());
			psmt.setString(2,dto.getContent());
			psmt.setString(3, dto.getWriter());
			psmt.setString(4,dto.getRegip());
			
			
			psmt.executeUpdate();
			close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	// view.do : 게시물별 댓글들 조회 
	public List<ArticleDTO> selectComments(String parent) {
		List<ArticleDTO> comments = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COMMENTS);
			psmt.setString(1, parent);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleDTO dto = new ArticleDTO();
				dto = new ArticleDTO();
				dto.setNo(rs.getInt("no"));
				dto.setParent(rs.getInt("parent"));
				dto.setComment(rs.getInt("comment"));
				dto.setCate(rs.getString("cate"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setFile(rs.getShort("file"));
				dto.setHit(rs.getInt("hit"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegip(rs.getString("regip"));
				dto.setRdate(rs.getString("rdate"));
				dto.setNick(rs.getString("nick"));
				comments.add(dto);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comments;
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
	public void updateComment(String no, String comment) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_COMMENT);
			psmt.setString(1, comment);
			psmt.setString(2, no);
			
			psmt.executeUpdate();
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// view.do -> 댓글 삭제
	public void deleteComment(String no) {
		try {
			conn=getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_COMMENT);
			psmt.setString(1,no);
			
			psmt.executeUpdate();
			close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	
	
}
