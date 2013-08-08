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
import spring.service.domain.FightCommentVO;
import spring.service.domain.FightParamVO;
import spring.service.domain.FightVO;
import spring.service.fight.FightDAO;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
@Named("fightDAO")
public class FightDAOImpl implements FightDAO {
	
	@Inject
	@Named("sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	public FightDAOImpl() {
		System.out.println("[FightDAOImpl Default Constructor Call.....]");
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	
	
	@Override
	public int addFight(FightVO fight) throws Exception {
		return (int) sqlMapClient.insert("Fight.addFight",fight);
	}

	@Override
	public int delFight(int fightNo) throws Exception {
		return sqlMapClient.delete("Fight.delFight",fightNo);
	}

	@Override
	public int addArtist(ArtistVO artist) throws Exception {
		return (int) sqlMapClient.insert("Fight.addArtist", artist);
	}

	@Override
	public int checkFight(int fightNo) throws Exception {
		return (int) sqlMapClient.queryForObject("Fight.checkFight",fightNo);
	}

	@Override
	public int fightCount(FightVO fight) throws Exception {
		return (int)sqlMapClient.queryForObject("Fight.fightCount",fight);
	}

	@Override
	public int addArtistScore(ArtistScoreVO artistScore) throws Exception {
		
				sqlMapClient.insert("Fight.addArtistScore",artistScore);
				return 0;
	}

	@Override
	public List<ArtistScoreVO> getArtistScore(int fightNo) throws Exception {
		return (List<ArtistScoreVO>) sqlMapClient.queryForList("Fight.getArtistScore", fightNo);
	}

	@Override
	public int addFightComment(FightCommentVO fightComment) throws Exception {
		return (int) sqlMapClient.insert("Fight.addFightComment",fightComment);
	}

	@Override
	public int updatefightComment(int fightNo) throws Exception {
		return sqlMapClient.update("Fight.updatefightComment",fightNo);
	}

	@Override
	public List<Integer> getHotFight() throws Exception {
		return sqlMapClient.queryForList("Fight.getHotFight");
	}
	
	
	
///////////////////////////////////////////////////////////////////////////////
//래욱 파트 // 미구현
	@Override
	public ArrayList<FightVO> getFightList(FightParamVO fp) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ArtistJurorVO> getArtistJurorList(ArtistParamVO ap)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FightVO getFight(int fightNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	

	
}
