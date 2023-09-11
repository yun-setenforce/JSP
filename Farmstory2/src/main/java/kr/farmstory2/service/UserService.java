package kr.farmstory2.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.farmstory2.dao.UserDAO;
import kr.farmstory2.dto.UserDTO;

public enum UserService {
	INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 싱글톤 
	private UserDAO dao = UserDAO.getInstance();

	// 기본 CRUD 
	public void insertUser(UserDTO dto) {
		dao.insertUser(dto);
	}
	
	//로그인 
	public UserDTO selectUserForLogin(String uid, String pass) {
		return dao.selectUserForLogin(uid, pass);
	}
	
	public UserDTO selectUser() {
		return dao.selectUser();
	}
	public List<UserDTO> selectUsers(int start) {
		return dao.selectUsers(start);
	}
	public int selectCountUsers() {
		return dao.selectCountUsers();
	}
	
	public void updateUser(UserDTO dto) {
		dao.updateUser(dto);
	}
	
	public void updateUserRole(String uid, String role) {
		dao.updateUserRole(uid, role);
	}
	
	public void deleteUser(String uid) {
		dao.deleteUser(uid);
	}

	public int selectCountUid(String uid) {
		return dao.selectCountUid(uid);
	}
	public int selectCountNick(String nick) {
		return dao.selectCountNick(nick);
	}
	public int selectCountHp(String hp) {
		return dao.selectCountHp(hp);
	}
	public int selectCountEmail(String email) {
		return dao.selectCountEmail(email);
	}

	public List<UserDTO> selectLatestUsers(int size) {
		return dao.selectLatestUsers(size);
	}

	public String convertRole(String grade) {
		String role = "";
		switch (grade) {
		case "1": {
			role = "USER";
			break;
		}
		case "2": {
			role =  "VIP";
			break;
		}
		case "3": {
			role = "staff";
			break;
		}
		case "4": {
			role = "manager";
			break;
		}
		case "5": {
			role = "admin";
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + grade);
		}
		return role;
	}

	
}
