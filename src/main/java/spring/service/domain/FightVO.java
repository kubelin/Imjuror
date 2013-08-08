package spring.service.domain;

import java.sql.Date;


public class FightVO {

	private Integer fightNo;
	private String title;
	private String genreNo;
	private String content;
	private String startDate;
	private String endDate;
	private Integer isActive;
	
	private Date regDate;
	private Date modDate;
	
	
	public Integer getFightNo() {
		return fightNo;
	}
	public void setFightNo(Integer fightNo) {
		this.fightNo = fightNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getGenreNo() {
		return genreNo;
	}
	public void setGenreNo(String genreNo) {
		this.genreNo = genreNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getModDate() {
		return modDate;
	}
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}
	
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "FightVO [fightNo=" + fightNo + ", title=" + title
				+ ", genreNo=" + genreNo + ", content=" + content
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", isActive=" + isActive + ", regDate=" + regDate
				+ ", modDate=" + modDate + "]";
	}
}
