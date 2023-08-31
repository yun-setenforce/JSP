package kr.co.jboard2.dto;

public class PageDTO {
  	int start  			; // 각 페이지별 글 10개를 읽어오기 위한 SQL문에서 LIMIT 제시값 
  	int currentPage 	; //현재 페이지. 파라미터로 제시된 페이지가 없을 경우 기본은 1페이지 
  	int total 			; // 전체 게시물 갯수 
  	int lastPageNum 	; // 끝까지 넘겼을 떄 마지막 페이지 번호   
  	int pageGroupCurrent ; //페이지 번호를 10씩 나눴을 때 현재 그룹 몇번째인지. 그룹1(1~10p), 그룹2(11~20p)
  	int pageGroupStart  ; //현재 그룹의 시작 페이지 번호 
  	int pageGroupEnd  	; //현재 그룹의 끝 페이지 번호  -lastPageNum보다 클 경우 lastPageNum 대입 
  	int pageStartNum 	;
  	
  	public PageDTO(int start, int currentPage, int total, int lastPageNum, int pageGroupCurrent,int pageGroupStart, int pageGroupEnd, int pageStartNum  ) {
  		this.start = start;
  		this.currentPage = currentPage;
  		this.total = total;
  		this.lastPageNum = lastPageNum;
  		this.pageGroupCurrent = pageGroupCurrent;
  		this.pageGroupStart = pageGroupStart;
  		this.pageGroupEnd = pageGroupEnd;
  		this.pageStartNum = pageStartNum;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getLastPageNum() {
		return lastPageNum;
	}

	public void setLastPageNum(int lastPageNum) {
		this.lastPageNum = lastPageNum;
	}

	public int getPageGroupCurrent() {
		return pageGroupCurrent;
	}

	public void setPageGroupCurrent(int pageGroupCurrent) {
		this.pageGroupCurrent = pageGroupCurrent;
	}

	public int getPageGroupStart() {
		return pageGroupStart;
	}

	public void setPageGroupStart(int pageGroupStart) {
		this.pageGroupStart = pageGroupStart;
	}

	public int getPageGroupEnd() {
		return pageGroupEnd;
	}

	public void setPageGroupEnd(int pageGroupEnd) {
		this.pageGroupEnd = pageGroupEnd;
	}

	public int getPageStartNum() {
		return pageStartNum;
	}

	public void setPageStartNum(int pageStartNum) {
		this.pageStartNum = pageStartNum;
	}
}
