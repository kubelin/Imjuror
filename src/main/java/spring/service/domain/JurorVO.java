package spring.service.domain;

import java.sql.Date;

public class JurorVO {

	private String userId;
	private String name;
	private String nickName;
	private String gender;
	private String picture;
	private Integer record;
	private Integer userLevel;
	private String admin;
	private String link;
	
	private Date regDate;
	private Date modDate;

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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Integer getRecord() {
		return record;
	}
	public void setRecord(Integer record) {
		this.record = record;
	}
	public Integer getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
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
		return "JurorVO [userId=" + userId + ", name=" + name + ", nickName="
				+ nickName + ", gender=" + gender + ", picture=" + picture
				+ ", record=" + record + ", userLevel=" + userLevel
				+ ", admin=" + admin + ", link=" + link + ", regDate="
				+ regDate + ", modDate=" + modDate + "]";
	}
	
		
}
