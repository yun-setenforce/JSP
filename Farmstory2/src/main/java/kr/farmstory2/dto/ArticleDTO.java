package kr.farmstory2.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArticleDTO {
	private int no;
	private int parent;
	private int comment;
	private String cate;
	private String title;
	private String content;
	private int file;
	private int hit;
	private String writer;
	private String regip;
	private String rdate;
	private FileDTO fileDto;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public FileDTO getFileDto() {
		return fileDto;
	}
	public void setFileDto(FileDTO fileDto) {
		this.fileDto = fileDto;
	}
	//추가필드 
	private String nick;
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		logger.debug("닉 : "+ nick);
		if(nick == null) {
			this.nick = "(탈퇴한 사용자)";
		} else {
			this.nick = nick;
		}
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public void setNo(String no) {
		this.no = Integer.parseInt(no);
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public void setParent(String parent) {
		this.parent = Integer.parseInt(parent);
	}
	public int getComment() {
		return comment;
	}
	public void setComment(int comment) {
		this.comment = comment;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		
		String escapedStr1 = title.replaceAll("<br>", "\n");
		String escapedStr2 = escapedStr1.replaceAll("&gt;", ">");
		String escapedStr3 = escapedStr2.replaceAll("&lt;", "<");
		String escapedStr4 = escapedStr3.replaceAll("&quot;", "\"");
		String escapedStr5 = escapedStr4.replaceAll("&nbsp;", " ");
		String escapedStr6 = escapedStr5.replaceAll("&amp;", "&");
		
		this.title = escapedStr6;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getFile() {
		return file;
	}
	public void setFile(int file) {
		this.file = file;
	}
	public void setFile(String oName) {
		if(oName != null) {
			this.file = 1;
		}else {
			this.file = 0;
		}
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegip() {
		return regip;
	}
	public void setRegip(String regip) {
		this.regip = regip;
	}
	public String getRdate() {
		return rdate.substring(2,10);
	}
	public String getFullRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	@Override
	public String toString() {
		return "ArticleDTO [no=" + no + ", parent=" + parent + ", comment=" + comment + ", cate=" + cate + ", title="
				+ title + ", content=" + content + ", file=" + file + ", hit=" + hit + ", writer=" + writer + ", regip="
				+ regip + ", rdate=" + rdate + "]";
	}
	
	
	
}
