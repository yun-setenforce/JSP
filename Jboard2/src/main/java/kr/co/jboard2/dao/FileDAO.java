package kr.co.jboard2.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.db.SQL;
import kr.co.jboard2.dto.FileDTO;

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
	public FileDTO selectFile(int fno) {
		return null;
	}
	public List<FileDTO> selectFiles() {
		return null;
	}
	public void updateFile(FileDTO dto) {
		
	}
	public void deleteFile(int fno) {
		
	}
}
