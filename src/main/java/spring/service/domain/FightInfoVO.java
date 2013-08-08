package spring.service.domain;

public class FightInfoVO {

	private Integer fightNo;
	private String video;
	private String title;
	private String content;
	private Integer videoNum;
	private String genreName;
	private String startDate;
	private String endDate;
	private String nickName;
	private Integer score;
	private String artistId;
	
	
	public Integer getFightNo() {
		return fightNo;
	}
	public void setFightNo(Integer fightNo) {
		this.fightNo = fightNo;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
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
	public Integer getVideoNum() {
		return videoNum;
	}
	public void setVideoNum(Integer videoNum) {
		this.videoNum = videoNum;
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getArtistId() {
		return artistId;
	}
	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	@Override
	public String toString() {
		return "FightInfoVO [fightNo=" + fightNo + ", video=" + video
				+ ", title=" + title + ", content=" + content + ", videoNum="
				+ videoNum + ", genreName=" + genreName + ", startDate="
				+ startDate + ", endDate=" + endDate + ", nickName=" + nickName
				+ ", score=" + score + ", artistId=" + artistId + "]";
	}
	
	
}
