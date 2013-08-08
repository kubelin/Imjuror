package spring.service.domain;

import java.sql.Date;

/*
 * Fight게시물에 대한 VO입니다.
 * 
 * */

public class FightCommentVO {
	
	private Integer fightNo;
	private Integer bad;
	private String userId;
	
	private Date regDate;
	private Date modDate;
	
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

	
	
	
	public Integer getFightNo() {
		return fightNo;
	}
	public void setFightNo(Integer fightNo) {
		this.fightNo = fightNo;
	}
	public Integer getBad() {
		return bad;
	}
	public void setBad(Integer bad) {
		this.bad = bad;
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
	@Override
	public String toString() {
		return "FightCommentVO [fightNo=" + fightNo
				+ ", bad=" + bad + ", regDate=" + regDate + ", modDate="
				+ modDate + "]";
	}
	

	
	
}
