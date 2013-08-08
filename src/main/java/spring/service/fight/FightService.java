package spring.service.fight;

import java.util.ArrayList;
import java.util.List;

import spring.service.domain.ArtistJurorVO;
import spring.service.domain.ArtistParamVO;
import spring.service.domain.ArtistScoreVO;
import spring.service.domain.ArtistVO;
import spring.service.domain.FightCommentVO;
import spring.service.domain.FightParamVO;
import spring.service.domain.FightVO;
 
public interface FightService {
	
	int addFight(FightVO fight)throws Exception;
	int delFight(int fightNo)throws Exception;
	int addArtist(ArtistVO artist)throws Exception;
	int checkFight(int fightNo)throws Exception;
	
	//genre값을 어떤것을 넘겨주는지? String(genreName),int(genreNo)
	int fightCount(FightVO fight) throws Exception;
	int addArtistScore(ArtistScoreVO artistScore) throws Exception;
	List<ArtistScoreVO> getArtistScore(int fightNo)throws Exception;
	
	int addFightComment(FightCommentVO fightComment)throws Exception;
	int updatefightComment(int fightNo) throws Exception;
	
	List<Integer> getHotFight()throws Exception;
	
	/////////////////////////////////////////////////////////////////////////////
	ArrayList<FightVO> getFightList(FightParamVO fp)throws Exception;
	ArrayList<ArtistJurorVO> getArtistJurorList(ArtistParamVO ap)throws Exception;
	FightVO getFight(int fightNo) throws Exception;
	



}
