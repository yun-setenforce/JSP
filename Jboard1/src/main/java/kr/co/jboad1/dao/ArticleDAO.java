package kr.co.jboad1.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.co.jboad1.db.DBHelper;
import kr.co.jboad1.vo.ArticleVO;
import kr.co.jboad1.vo.UsersVO;
import kr.co.jboad1.db.SQL;

public class ArticleDAO extends DBHelper {
	private static ArticleDAO insatnce = new ArticleDAO();
	public static ArticleDAO getInstance() {
		return insatnce;
	}
	private ArticleDAO() {}
	
	public void insertArticle(ArticleVO vo) {
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
	
	public ArticleVO selectArticle(int no) {
		return null;
		
	}
	public List<ArticleVO> selectArticles(int start) {
		List<ArticleVO> articles = new ArrayList<>();

		try {
			conn = getConnection();

			psmt = conn.prepareStatement(SQL.SELECT_ARTICLES);
			psmt.setInt(1,start);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleVO vo = new ArticleVO();
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
	public void updateArticle(ArticleVO vo) {}
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
	
}
