package spring.service.domain;

public class EntryVO {
	
	private String thumbnail_default;
	private String thumbnail_mqdefault;
	private String videoid;
	
	public String getThumbnail_default() {
		return thumbnail_default;
	}
	public void setThumbnail_default(String thumbnail_default) {
		this.thumbnail_default = thumbnail_default;
	}
	public String getThumbnail_mqdefault() {
		return thumbnail_mqdefault;
	}
	public void setThumbnail_mqdefault(String thumbnail_mqdefault) {
		this.thumbnail_mqdefault = thumbnail_mqdefault;
	}
	public String getVideoid() {
		return videoid;
	}
	public void setVideoid(String videoid) {
		this.videoid = videoid;
	}
	
}
