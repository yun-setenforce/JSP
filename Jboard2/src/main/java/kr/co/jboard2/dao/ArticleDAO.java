package kr.co.jboard2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.db.SQL;
import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.FileDTO;

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

		ArticleDTO article = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLE);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				article = new ArticleDTO();
				article.setNo(rs.getInt("no"));
				article.setParent(rs.getInt("parent"));
				article.setComment(rs.getInt("comment"));
				article.setCate(rs.getString("cate"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				article.setFile(rs.getShort("file"));
				article.setHit(rs.getInt("hit"));
				article.setWriter(rs.getString("writer"));
				article.setRegip(rs.getString("regip"));
				article.setRdate(rs.getString("rdate"));
				
				//추가필
				article.setNick(rs.getString("nick"));
				
				//파일 정보 
				FileDTO fileDto = new FileDTO();
				fileDto.setFno(rs.getInt("fno"));
				fileDto.setAno(rs.getInt("ano"));
				fileDto.setOriName(rs.getString("oriName"));
				fileDto.setNewName(rs.getString("newName"));
				fileDto.setDownload(rs.getInt("download"));
				fileDto.setRdate(rs.getString("rdate"));

				article.setFileDto(fileDto);
				
			}
		
			close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return article;
	}
	
	public List<ArticleDTO> selectArticles(int start, String search) {
		List<ArticleDTO> articles = new ArrayList<>();

		try {
			conn = getConnection();

			if(search==null) {
				psmt = conn.prepareStatement(SQL.SELECT_ARTICLES);
				psmt.setInt(1,start);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_ARTICLES_FOR_SEARCH);
				psmt.setString(1,"%"+search+"%");
				psmt.setInt(2,start);
			}
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
	
	public void deleteArticle(String no) {
		try {
			conn=getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_ARTICLE);
			psmt.setString(1,no);
			psmt.setString(2,no);
			psmt.executeUpdate();
			close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//추가
	
	// list.do : 총 게시물 수 
	public int selectCountTotal(String search) {
		int total = 0;
		
		try {
			conn=getConnection();
			if(search == null) {
				logger.debug("search is null");
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL);
			} else {
				logger.debug("search is :"+search);
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL_FOR_SEARCH);
				psmt.setString(1, "%"+search+"%");
			}
			rs = psmt.executeQuery();
		
			if(rs.next()) {
				total = rs.getInt(1);
				logger.debug("total count is : " + total);
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
	public int insertComment(ArticleDTO dto) {
		
		int result = 0; 
		
		try {
			conn=getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_COMMENT);
			psmt.setInt(1,dto.getParent());
			psmt.setString(2,dto.getContent());
			psmt.setString(3, dto.getWriter());
			psmt.setString(4,dto.getRegip());
			
			
			 result = psmt.executeUpdate();
			close();
		
		}catch(Exception e) {
			logger.error("error insertComment() : " + e.getMessage());
		}
		return result;
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
			close();
		} catch (Exception e) {
			logger.error("error selectComments() : " + e.getMessage());
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
