package spring.service.domain;

import java.sql.Date;

//ArtistVO를 저장하기위한 VO입니다.
//입력 부분에서 사용되겠습니다.

public class ArtistVO {
	private int fightNo;
	private String userId;
	private Integer videoSequence;
	private String video;
	private String artistComment;
	private Integer score;
	
	private Date regDate;
	private Date modDate;

	public int getFightNo() {
		return fightNo;
	}
	public void setFightNo(int fightNo) {
		this.fightNo = fightNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getVideoSequence() {
		return videoSequence;
	}
	public void setVideoSequence(Integer videoSequence) {
		this.videoSequence = videoSequence;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getArtistComment() {
		return artistComment;
	}
	public void setArtistComment(String artistComment) {
		this.artistComment = artistComment;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
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
		return "ArtistVO [fightNo=" + fightNo + ", userId=" + userId
				+ ", videoSequence=" + videoSequence + ", video=" + video
				+ ", artistComment=" + artistComment + ", score=" + score
				+ ", regDate=" + regDate + ", modDate=" + modDate + "]";
	}
	
	
	
}
