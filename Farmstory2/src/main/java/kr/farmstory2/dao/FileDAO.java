package kr.farmstory2.dao;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.farmstory2.db.DBHelper;
import kr.farmstory2.db.SQL;
import kr.farmstory2.dto.FileDTO;

public class FileDAO  extends DBHelper{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private FileDAO() {
	}
	private static FileDAO instance = new FileDAO();
	public static FileDAO getInstance() {
		return instance;
	}
	
	public void insertFile(FileDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_FILE);
			psmt.setInt(1, dto.getAno());
			psmt.setString(2, dto.getOriName());
			psmt.setString(3, dto.getNewName());
			psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.error("insertFile() : " + e.getMessage());
		}
	}
	public FileDTO selectFile(String fno) {
		FileDTO dto = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_FILE);
			psmt.setString(1, fno);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new FileDTO();
				dto.setFno(rs.getInt(1));
				dto.setAno(rs.getInt(2));
				dto.setOriName(rs.getString(3));
				dto.setNewName(rs.getString(4));
				dto.setDownload(rs.getInt(5));
				dto.setRdate(rs.getString(6));
			}
			close();
			
		} catch (Exception e) {
			logger.error("error selectFile() : " + e.getMessage());
		}
		return dto;
	}
	public List<FileDTO> selectFiles() {
		return null;
	}
	public void updateFile(FileDTO dto) {
		
	}
	public List<String> deleteFile(String fno) {
		ArrayList<String> savedFileName = new ArrayList<>();
		try {
			conn=getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_FILES_NEW_NAME);
			psmt.setString(1,fno);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				savedFileName.add(rs.getString(1));
			}
			
			psmt = conn.prepareStatement(SQL.DELETE_FILE);
			psmt.setString(1,fno);
			psmt.executeUpdate();
			close();
		
		}catch(Exception e) {
			logger.error("error deleteFile() : " + e.getMessage());
		}
		return savedFileName;
	}
	
	//다운로드수 업데이트
	public void updateDownload(int ano) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_FILE_DOWNLOAD);
			psmt.setInt(1, ano);
			
			psmt.executeUpdate();
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
