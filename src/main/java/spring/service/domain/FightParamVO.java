package spring.service.domain;

public class FightParamVO {
	
	private Integer pageNo;
	private String genreNo;
	private Integer isActive;
	
	public FightParamVO() {
		// TODO Auto-generated constructor stub
	}
	public FightParamVO(Integer pageNo, String genreNo, Integer isActive) {
		this.pageNo = pageNo;
		this.genreNo = genreNo;
		this.isActive = isActive;
	}
	
	

	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public String getGenreNo() {
		return genreNo;
	}
	public void setGenreNo(String genreNo) {
		this.genreNo = genreNo;
	}
	@Override
	public String toString() {
		return "FightParamVO [pageNo=" + pageNo + ", genreNo=" + genreNo
				+ ", isActive=" + isActive + "]";
	}
	
	
}
