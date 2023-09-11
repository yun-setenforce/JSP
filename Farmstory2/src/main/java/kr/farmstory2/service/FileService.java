package kr.farmstory2.service;

import java.util.List;

import kr.farmstory2.dto.FileDTO;
import kr.farmstory2.dao.FileDAO;

public enum FileService {
	INSTANCE;
	
	private FileDAO dao = FileDAO.getInstance();
	

	public void insertFile(FileDTO dto) {
		dao.insertFile(dto);
	}
	public FileDTO selectFile(String fno) {
		return dao.selectFile(fno);
	}
	public List<FileDTO> selectFiles() {
		return dao.selectFiles();
	}
	public void updateFile(FileDTO dto) {
		dao.updateFile(dto);
	}
	public List<String> deleteFile(String ano) {
		return dao.deleteFile(ano);
	}

	//다운로드수 업데이트
	public void updateDownload(int ano) {
		dao.updateDownload(ano);
	}

}
