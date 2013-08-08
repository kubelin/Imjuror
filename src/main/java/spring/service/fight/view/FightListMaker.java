package spring.service.fight.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Component;

import spring.service.domain.ArtistJurorVO;
import spring.service.domain.ArtistParamVO;
import spring.service.domain.FightParamVO;
import spring.service.domain.FightVO;
import spring.service.fight.Fight2Service;

@Component
@Named("fightListMaker")
public class FightListMaker {

	// FightService Injection

	@Inject
	@Named("fight2Service")
	public Fight2Service fight2Service;

	// ARTIST의 수를 나타냄 즉, 몇명인가에 대한 값.
	final static int ARTIST_NUMBER = 2;

	final static int ARTIST1 = 1;
	final static int ARTIST2 = 2;

	// setter,getter
	public Fight2Service getFight2Service() {
		return fight2Service;
	}

	public void setFight2Service(Fight2Service fight2Service) {
		this.fight2Service = fight2Service;
	}

	// ArtistJurorList가져오기 set
	public List<ArtistJurorVO> requestArtistJurorList(List<Integer> fightNos,
			int artistSeq) throws Exception {

		return (List<ArtistJurorVO>) fight2Service
				.getArtistJurorList(new ArtistParamVO(fightNos, artistSeq));
	}

	public List<ArtistJurorVO> requestArtistJurorList(ArtistParamVO ap)
			throws Exception {
		return (List<ArtistJurorVO>) fight2Service.getArtistJurorList(ap);
	}

	// fightList에서 fightNo들만 추출
	public List<Integer> getFightNos(List<FightVO> fightList) throws Exception {

		List<Integer> fightNos = new ArrayList<Integer>();

		for (FightVO fight : fightList) {
			fightNos.add(fight.getFightNo());
		}

		return fightNos;
	}

	// fightNos에서 fightList가져오기
	public List<Map> requestPopularFightList(String genreNo) throws Exception {
		List<Integer> fightNos = fight2Service.getPopularFightNos(genreNo);

		if (fightNos.isEmpty()) {
			return null;
		}
		List<FightVO> fightList = fight2Service
				.getFightListUseFightNos(new ArtistParamVO(fightNos, null));

		return this.joinFightArtistList(fightList);

	}

	public List<Map> requestFightArtistList(Integer pageNo, String genreNo,
			Integer isActive) throws Exception {
		return this.requestFightArtistList(new FightParamVO(pageNo, genreNo,
				isActive));
	}

	public List<Map> requestFightArtistList(FightParamVO fp) throws Exception {

		List<FightVO> fightList = fight2Service.getFightList(fp);
		
		if (fightList.isEmpty()) {
			return null;
		}
		return this.joinFightArtistList(fightList);
	}

	// getFight
	public List<Map> requestFightArtist(Integer fightNo) throws Exception {

		List<FightVO> fightList = new ArrayList<FightVO>();
		fightList.add(fight2Service.getFight(fightNo));

		return this.joinFightArtistList(fightList);
	}

	// 준비단계
	public List<Map> joinFightArtistList(List<FightVO> fightList)
			throws Exception {

		List<Integer> fightNos = this.getFightNos(fightList);

		System.out.println("fightNos : " + fightNos);
		
		ArtistParamVO ap1 = new ArtistParamVO();
		ap1.setFightNos(fightNos);
		ap1.setVideoSequence(ARTIST1);

		ArtistParamVO ap2 = new ArtistParamVO();
		ap2.setFightNos(fightNos);// Artist1List의 FightNos로 바꾸자.
		ap2.setVideoSequence(ARTIST2);

		List<ArtistJurorVO> artist1List = fight2Service.getArtistJurorList(ap1);
		List<ArtistJurorVO> artist2List = fight2Service.getArtistJurorList(ap2);

		return makeFightArtistList(fightList, artist1List, artist2List);
	}

	// 실질적인 연결
	public List<Map> makeFightArtistList(List<FightVO> fightList,
			List<ArtistJurorVO> artist1List, List<ArtistJurorVO> artist2List)
			throws Exception {

		
		this.getFightNos(fightList);
		List<Map> fightRecordList = new ArrayList<Map>();
		HashMap<String, Object> fightRecord = null;
			
		
		System.out.println("fightListSize : " + fightList.size());
		System.out.println("artist1ListSize : " + artist1List.size());
		System.out.println("artist2ListSize : " + artist2List.size());
		for(int i=0; i<fightList.size(); i++){
			
			for (int j = 0; j < artist1List.size(); j++) {
				if(fightList.get(i).getFightNo().equals( artist1List.get(j).getFightNo())){
				System.out.println("::artist1List : " + artist1List.get(j).getFightNo());
				fightRecord = new HashMap<String, Object>();
				
					fightRecord.put("fight", fightList.get(i));
					fightRecord.put("artist1", artist1List.get(j));
				
				for (int k = 0; k < artist2List.size(); k++) {
					if (artist1List.get(j).getFightNo().equals(
							artist2List.get(k).getFightNo())) {
						fightRecord.put("artist2", artist2List.get(k));
						}
					}
				}
			}
			fightRecordList.add(fightRecord);
		}
		return fightRecordList;
		}
	
}
