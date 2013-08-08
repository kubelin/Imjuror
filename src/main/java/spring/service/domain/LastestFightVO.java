package spring.service.domain;

import java.sql.Date;

public class LastestFightVO {
	
	private Integer fightNo;
	private String title;
	private Date startDate;
	private String userId;
	private String name;
	private String link;
	
	
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
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Override
	public String toString() {
		return "LastestFightVO [fightNo=" + fightNo + ", title=" + title
				+ ", startDate=" + startDate + ", userId=" + userId + ", name="
				+ name + ", link=" + link + "]";
	}
	
	
	
	
}
