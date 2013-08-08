package spring.service.domain;

/*
 * Artist Table의 값과 Juror Table의 값을 조인해서 가져오기 위한 VO입니다.
 * 
 * */

public class ArtistJurorVO {
	
	private Integer fightNo;
	private String userId;
	private String video;
	private String artistComment;
	private Integer videoSequence;
	private String battleResult;
	private String nickName;
	private String link;
	public Integer getFightNo() {
		return fightNo;
	}
	public void setFightNo(Integer fightNo) {
		this.fightNo = fightNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public Integer getVideoSequence() {
		return videoSequence;
	}
	public void setVideoSequence(Integer videoSequence) {
		this.videoSequence = videoSequence;
	}
	public String getBattleResult() {
		return battleResult;
	}
	public void setBattleResult(String battleResult) {
		this.battleResult = battleResult;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Override
	public String toString() {
		return "ArtistJurorVO [fightNo=" + fightNo + ", userId=" + userId
				+ ", video=" + video + ", artistComment=" + artistComment
				+ ", videoSequence=" + videoSequence + ", battleResult="
				+ battleResult + ", nickName=" + nickName + ", link=" + link
				+ "]";
	}
	
	
	
	
}
