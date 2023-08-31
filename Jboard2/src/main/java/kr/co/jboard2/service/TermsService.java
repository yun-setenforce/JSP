package kr.co.jboard2.service;

import kr.co.jboard2.dao.TermsDAO;
import kr.co.jboard2.db.SQL;
import kr.co.jboard2.dto.TermsDTO;

public enum TermsService {
	INSTANCE;
	private TermsDAO dao = TermsDAO.getInstance();
	public void insertTerms() {
		dao.insertTerms();
	}

	public TermsDTO selectTerms() {
		return dao.selectTerms();
	}

	public void updateTerms() {
		dao.updateTerms();
	}

	public void deleteTerms() {
		dao.deleteTerms();
	}
}
