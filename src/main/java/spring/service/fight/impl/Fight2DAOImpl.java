package spring.service.fight.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Repository;

import spring.service.domain.ArtistJurorVO;
import spring.service.domain.ArtistParamVO;
import spring.service.domain.ArtistScoreVO;
import spring.service.domain.ArtistVO;
import spring.service.domain.FightParamVO;
import spring.service.domain.FightVO;
import spring.service.domain.LastestFightVO;
import spring.service.fight.Fight2DAO;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
@Named("fight2DAO")
public class Fight2DAOImpl implements Fight2DAO {
	
	@Inject
	@Named("sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	public Fight2DAOImpl() {
		System.out.println("[FightDAOImpl Default Constructor Call.....]");
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}



	@Override
	public FightVO getFight(Integer fightNo) throws Exception {
		
		return (FightVO) sqlMapClient.queryForObject("Fight2.getFight",fightNo);
	}
	

	@Override
	public void addFight(FightVO fight) throws Exception {
		sqlMapClient.insert("Fight2.addFight",fight);		
	}

	@Override
	public void delFight(int fightNo) throws Exception {
		sqlMapClient.insert("Fight2.delFight",fightNo);
		
	}

	@Override
	public void addArtistScoreVO(ArtistScoreVO artistScore) throws Exception {
		sqlMapClient.insert("Fight2.addArtistScore",artistScore);
	}

	@Override
	public void addArtist(ArtistVO artist) throws Exception {

		sqlMapClient.insert("Fight2.addArtist",artist);
	}
	@Override
	public ArrayList<FightVO> getFightList(FightParamVO fp) throws Exception{
		return (ArrayList<FightVO>) sqlMapClient.queryForList("Fight2.getFightList", fp);
	}
	@Override
	public ArrayList<ArtistJurorVO> getArtistJurorList(ArtistParamVO ap)throws Exception{
		return (ArrayList<ArtistJurorVO>) sqlMapClient.queryForList("Fight2.getArtistJurorList", ap);
	}
	@Override
	public List<Integer> checkFight(Integer fightno)throws Exception{
		
		List<Integer> list = sqlMapClient.queryForList("Fight2.checkFight",fightno);
		if(list.isEmpty()){
			list = null;
		}
		return list;
		
	}
	@Override
	public List<Integer> getPopularFightNos(String genreNo) throws Exception {
		
		return sqlMapClient.queryForList("Fight2.getPopularFightNos",genreNo);
	}
	@Override
	public List<FightVO> getFightListUseFightNos(ArtistParamVO ap)
			throws Exception {
		
		return sqlMapClient.queryForList("Fight2.getFightListUseFightNos",ap);
	}
	@Override
	public List<LastestFightVO> getLastestFightList(FightParamVO fp) throws Exception {
		
		return sqlMapClient.queryForList("Fight2.getLastestFightList",fp);
	}
}
