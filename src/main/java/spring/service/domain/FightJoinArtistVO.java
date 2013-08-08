package spring.service.domain;

import java.sql.Date;

/*
 * 최종적으로 가져오는 값을 위한 VO인데, 어떻게 사용할지 고민중입니다.
 * */


//Fight를 추상화한 객체
public class FightJoinArtistVO {

	//fightTable
	 private Integer fightno;
	 private String genreNo;
	 private String title;
	 private String content;
	 private Date startDate;
	 private Date endDate;
	 
	 //ArtistTable 1
	 private String art1UserId;
	 private String art1NickName;
	 private String art1Video;
	 private String art1Comment;
	 private Integer 	art1VideoSeq;
	 private String art1Result;
	 
	//ArtistTable 2
	 private String art2UserId;
	 private String art2NickName;
	 private String art2Video;
	 private String art2Comment;
	 private Integer 	art2VideoSeq;
	 private String art2Result; 
	 
	 
	public Integer getFightno() {
		return fightno;
	}
	public void setFightno(Integer fightno) {
		this.fightno = fightno;
	}
	public String getGenreNo() {
		return genreNo;
	}
	public void setGenreNo(String genreNo) {
		this.genreNo = genreNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getArt1UserId() {
		return art1UserId;
	}
	public void setArt1UserId(String art1UserId) {
		this.art1UserId = art1UserId;
	}
	public String getArt1NickName() {
		return art1NickName;
	}
	public void setArt1NickName(String art1NickName) {
		this.art1NickName = art1NickName;
	}
	public String getArt1Video() {
		return art1Video;
	}
	public void setArt1Video(String art1Video) {
		this.art1Video = art1Video;
	}
	public String getArt1Comment() {
		return art1Comment;
	}
	public void setArt1Comment(String art1Comment) {
		this.art1Comment = art1Comment;
	}
	public Integer getArt1VideoSeq() {
		return art1VideoSeq;
	}
	public void setArt1VideoSeq(Integer art1VideoSeq) {
		this.art1VideoSeq = art1VideoSeq;
	}
	public String getArt1Result() {
		return art1Result;
	}
	public void setArt1Result(String art1Result) {
		this.art1Result = art1Result;
	}
	public String getArt2UserId() {
		return art2UserId;
	}
	public void setArt2UserId(String art2UserId) {
		this.art2UserId = art2UserId;
	}
	public String getArt2NickName() {
		return art2NickName;
	}
	public void setArt2NickName(String art2NickName) {
		this.art2NickName = art2NickName;
	}
	public String getArt2Video() {
		return art2Video;
	}
	public void setArt2Video(String art2Video) {
		this.art2Video = art2Video;
	}
	public String getArt2Comment() {
		return art2Comment;
	}
	public void setArt2Comment(String art2Comment) {
		this.art2Comment = art2Comment;
	}
	public Integer getArt2VideoSeq() {
		return art2VideoSeq;
	}
	public void setArt2VideoSeq(Integer art2VideoSeq) {
		this.art2VideoSeq = art2VideoSeq;
	}
	public String getArt2Result() {
		return art2Result;
	}
	public void setArt2Result(String art2Result) {
		this.art2Result = art2Result;
	}
	@Override
	public String toString() {
		return "FightJoinArtistVO [fightno=" + fightno + ", genreNo=" + genreNo
				+ ", title=" + title + ", content=" + content + ", startDate="
				+ startDate + ", endDate=" + endDate + ", art1UserId="
				+ art1UserId + ", art1NickName=" + art1NickName
				+ ", art1Video=" + art1Video + ", art1Comment=" + art1Comment
				+ ", art1VideoSeq=" + art1VideoSeq + ", art1Result="
				+ art1Result + ", art2UserId=" + art2UserId + ", art2NickName="
				+ art2NickName + ", art2Video=" + art2Video + ", art2Comment="
				+ art2Comment + ", art2VideoSeq=" + art2VideoSeq
				+ ", art2Result=" + art2Result + "]";
	}
	
	
}
