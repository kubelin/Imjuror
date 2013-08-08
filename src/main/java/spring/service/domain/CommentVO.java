package spring.service.domain;

import java.sql.Date;


//댓글을 위한 VO이고 입력과 출력에 
//모두 사용될 것으로 예상됩니다.
public class CommentVO {
	
	private Integer commentId;
	private String jurorComment;
	private String userId;
	private Integer fightNo;
	
	private Date regDate;


	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public String getJurorComment() {
		return jurorComment;
	}
	public void setJurorComment(String jurorComment) {
		this.jurorComment = jurorComment;
	}
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
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "CommentVO [commentId=" + commentId + ", jurorComment="
				+ jurorComment + ", userId=" + userId + ", fightNo=" + fightNo
				+ ", regDate=" + regDate + "]";
	}
	
	
	
	
}
