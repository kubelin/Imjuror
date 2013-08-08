package spring.service.fight;

import java.util.ArrayList;
import java.util.List;

import spring.service.domain.ArtistJurorVO;
import spring.service.domain.ArtistParamVO;
import spring.service.domain.ArtistScoreVO;
import spring.service.domain.ArtistVO;
import spring.service.domain.FightParamVO;
import spring.service.domain.FightVO;
import spring.service.domain.LastestFightVO;

public interface Fight2Service {
	
	FightVO getFight(Integer fightNo) throws Exception;
	void addFight(FightVO fight)throws Exception;
	void delFight(int fightNo)throws Exception;
	void addArtistScoreVO(ArtistScoreVO artistScore) throws Exception;
	void addArtist(ArtistVO artist)throws Exception;
	
	ArrayList<FightVO> getFightList(FightParamVO fp)throws Exception;
	ArrayList<ArtistJurorVO> getArtistJurorList(ArtistParamVO ap)throws Exception;
	List<Integer> checkFight(Integer fightNo) throws Exception;
	List<Integer> getPopularFightNos(String genreNo) throws Exception;
	List<FightVO> getFightListUseFightNos(ArtistParamVO ap) throws Exception;
	List<LastestFightVO> getLastestFightList(FightParamVO fp) throws Exception;
}