package kr.farmstory2.service;

import kr.farmstory2.dao.TermsDAO;
import kr.farmstory2.dto.TermsDTO;

public enum TermsService {
	
	INSTANCE;
	TermsDAO dao = new TermsDAO();
	
	public TermsDTO selectTerms() {
		return dao.selectTerms();
	}
}
