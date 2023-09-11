package kr.co.jboard2.dto;

public class FileDTO {
	
	private int fno;
	private int ano;
	private String oriName;
	private String newName;
	private int download;
	private String rdate;
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getOriName() {
		return oriName;
	}
	public void setOriName(String oriName) {
		this.oriName = oriName;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public int getDownload() {
		return download;
	}
	public void setDownload(int download) {
		this.download = download;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	
	@Override
	public String toString() {
		return "FileDTO [fno=" + fno + ", ano=" + ano + ", oriName=" + oriName + ", newName=" + newName + ", download="
				+ download + ", rdate=" + rdate + "]";
	}
	
}
