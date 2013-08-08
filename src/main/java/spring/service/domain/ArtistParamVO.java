package spring.service.domain;

import java.util.List;

/*
 * 가져온 FightList의 FightNo가 저장되고 video_sequence가 저장됩니다.
 * 이 값을 이용해서 Artist1과 Artist2를 추출하게 되는데
 *  Artist1과 2를 나누는 기준은 videoSequence의 값입니다.
 * 
 * */

public class ArtistParamVO {

	private List<Integer> fightNos;
	private Integer videoSequence;
	
	public ArtistParamVO() {
		// TODO Auto-generated constructor stub
	}
	public ArtistParamVO(List<Integer> fightNos, Integer videoSequence) {
		this.fightNos = fightNos;
		this.videoSequence = videoSequence;
	}
	
	public List<Integer> getFightNos() {
		return fightNos;
	}
	public void setFightNos(List<Integer> fightNos) {
		this.fightNos = fightNos;
	}
	public Integer getVideoSequence() {
		return videoSequence;
	}
	public void setVideoSequence(Integer videoSequence) {
		this.videoSequence = videoSequence;
	}
	@Override
	public String toString() {
		return "ArtistParamVO [fightNos=" + fightNos + ", videoSequence="
				+ videoSequence + "]";
	}
	
	
	
	
}
