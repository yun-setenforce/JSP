package kr.co.jboad1.dao;

import java.util.ArrayList;
import java.util.List;

import kr.co.jboad1.db.DBHelper;
import kr.co.jboad1.db.SQL;
import kr.co.jboad1.dto.ArticleDTO;

public class ArticleDAO extends DBHelper {
	private static ArticleDAO insatnce = new ArticleDAO();
	public static ArticleDAO getInstance() {
		return insatnce;
	}
	private ArticleDAO() {}
	
	public void insertArticle(ArticleDTO vo) {
		try {
			conn = getConnection();
			

			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2,vo.getContent());
			psmt.setString(3, vo.getWriter());
			psmt.setString(4, vo.getRegip());
			
			psmt.executeUpdate();
			
			close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
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
/*
	public String selectUserNick(String uid) {
		String name = "";
		try{

			conn = getConnection();
			
			///query문 실행객체
			psmt = conn.prepareStatement(SQL.SELECT_USER_NICK);
			psmt.setString(1, uid);
					
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()){
				name = rs.getString(1);
			}
			
			close();

			
		}catch (Exception e){
			e.printStackTrace();
		}
		return name; 
	}
	*/
	public void updateArticle(ArticleDTO vo) {}
	public void deleteArticle(int no) {}
	
	// 추가 

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
	public void updateArticleForComment(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE_FOR_COMMENT);
			psmt.setString(1, no);
			
			psmt.executeUpdate();
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
