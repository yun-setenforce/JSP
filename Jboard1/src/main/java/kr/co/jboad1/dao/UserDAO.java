package kr.co.jboad1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.co.jboad1.db.DBHelper;
import kr.co.jboad1.db.SQL;
import kr.co.jboad1.dto.TermsDTO;
import kr.co.jboad1.dto.UsersDTO;

public class UserDAO extends DBHelper{
	private static UserDAO  instance = new UserDAO();
	
	public static UserDAO getInstance() {
		return instance;
	}
	
	private UserDAO() {}
	
	public void insertUser(UsersDTO vo) {

		try{
			conn = getConnection();
			
			psmt = conn.prepareStatement(SQL.INSERT_USER);
			psmt.setString(1, vo.getUid());
			psmt.setString(2, vo.getPass());
			psmt.setString(3, vo.getName());
			psmt.setString(4, vo.getNick()); 
			psmt.setString(5, vo.getEmail()); 
			psmt.setString(6, vo.getHp()); 

			psmt.setString(7, vo.getZip()); 
			psmt.setString(8, vo.getAddr1()); 
			psmt.setString(9, vo.getAddr2()); 
			psmt.setString(10, vo.getRegip()); 
			
			psmt.executeUpdate();
			
			close();

			
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public UsersDTO selectUser(String uid, String pass) {
		UsersDTO user = new UsersDTO();
		
		//사용자 DB조회 
		try{

			conn = getConnection();
			
			///query문 실행객체
			PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_USER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
					
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()){
				user = new UsersDTO();
				user.setUid(rs.getString(1));
				user.setPass(rs.getString(2));
				user.setName(rs.getString(3));
				user.setNick(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setHp(rs.getString(6));
				user.setRole(rs.getString(7));
				user.setZip(rs.getString(8));
				user.setAddr1(rs.getString(9));
				user.setAddr2(rs.getString(10));
				user.setRegip(rs.getString(11));
				user.setRegDate(rs.getString(12));
				user.setLeaveDate(rs.getString(13));
			}
			
			close();

			
		}catch (Exception e){
			e.printStackTrace();
		}
		return user;
	}
	
	public int selectCountUid(String uid) {
		int result = 0;
		try{
			conn = getConnection();
			psmt =  conn.prepareStatement(SQL.SELECT_COUNT_UID);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt(1);
			}
			close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public int selectCountNick(String nick) {
		int result = 0;
		try{

			conn = getConnection();
			psmt =  conn.prepareStatement(SQL.SELECT_COUNT_NICK);
			psmt.setString(1, nick);
			
			ResultSet rs = psmt.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public int selectCountHp(String hp) {
		int result = 0;
		try{

			conn = getConnection();
			psmt =  conn.prepareStatement(SQL.SELECT_COUNT_HP);
			psmt.setString(1, hp);
			
			ResultSet rs = psmt.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public int selectCountEmail(String email) {

		int result = 0;
		try{

			conn = getConnection();
			psmt =  conn.prepareStatement(SQL.SELECT_COUNT_EMAIL);
			psmt.setString(1, email);
			
			ResultSet rs = psmt.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public TermsDTO selectTerms() {
		TermsDTO vo = new TermsDTO();
		try{
			conn = getConnection();
			
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL.SELECT_TERMS);

			if(rs.next()){
				vo.setTerms(rs.getString(1));
				vo.setPrivacy(rs.getString(2));
			}
			close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return vo;
	}
	
	public void selectUsers() {
	}
}
