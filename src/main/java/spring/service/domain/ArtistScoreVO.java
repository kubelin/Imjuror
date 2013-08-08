package spring.service.domain;

import java.sql.Date;

//ArtistScore를 계산하기 위한 VO인데 
//저는 아직 사용하지 않았습니다.

public class ArtistScoreVO {
	private String jurorId;
	private int fightNo;
	private String artistId;
	private Integer score;
	
	private Date regDate;
	private Date modDate;
	
	public String getJurorId() {
		return jurorId;
	}
	public void setJurorId(String jurorId) {
		this.jurorId = jurorId;
	}
	public int getFightNo() {
		return fightNo;
	}
	public void setFightNo(int fightNo) {
		this.fightNo = fightNo;
	}
	public String getArtistId() {
		return artistId;
	}
	public void setArtistId(String artistId) {
		this.artistId = artistId;
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
		return "ArtistScoreVO [jurorId=" + jurorId + ", fightNo=" + fightNo
				+ ", artistId=" + artistId + ", score=" + score + ", regDate="
				+ regDate + ", modDate=" + modDate + "]";
	}
	
	
	
	
}
