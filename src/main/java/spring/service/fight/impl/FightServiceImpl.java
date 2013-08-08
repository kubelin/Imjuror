package spring.service.fight.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Service;
 
import spring.service.domain.ArtistJurorVO;
import spring.service.domain.ArtistParamVO;
import spring.service.domain.ArtistScoreVO;
import spring.service.domain.ArtistVO;
import spring.service.domain.FightCommentVO;
import spring.service.domain.FightParamVO;
import spring.service.domain.FightVO;
import spring.service.fight.FightDAO;
import spring.service.fight.FightService;

@Service
@Named("fightService")
public class FightServiceImpl implements FightService{
	
	@Inject
	@Named("fightDAO")
	private FightDAO fightDAO;
	
	public FightServiceImpl() {
		System.out.println("[FightServiceImpl Default Constructor Call.....]");
	}


	public void setFightDAO(FightDAO fightDAO) {
		this.fightDAO = fightDAO;
	}

	

	@Override
	public int addFight(FightVO fight) throws Exception {
		return fightDAO.addFight(fight);
	}


	@Override
	public int delFight(int fightNo) throws Exception {
		return fightDAO.delFight(fightNo);
	}


	@Override
	public int addArtist(ArtistVO artist) throws Exception {
		return fightDAO.addArtist(artist);
	}


	@Override
	public int checkFight(int fightNo) throws Exception {
		return fightDAO.checkFight(fightNo);
	}


	@Override
	public int fightCount(FightVO fight) throws Exception {
		return fightDAO.fightCount(fight);
	}


	@Override
	public int addArtistScore(ArtistScoreVO artistScore) throws Exception {
		
		return fightDAO.addArtistScore(artistScore);
	}


	@Override
	public List<ArtistScoreVO> getArtistScore(int fightNo) throws Exception {
		return fightDAO.getArtistScore(fightNo);
	}


	@Override
	public int addFightComment(FightCommentVO fightComment) throws Exception {
		return fightDAO.addFightComment(fightComment);
	}


	@Override
	public int updatefightComment(int fightNo) throws Exception {
		return fightDAO.updatefightComment(fightNo);
	}


	@Override
	public List<Integer> getHotFight() throws Exception {
		return fightDAO.getHotFight();
	}


	
	///////////////////////////////////////////////////////////////////////////////
	//래욱 파트 // 미구현
	@Override
	public ArrayList<FightVO> getFightList(FightParamVO fp) throws Exception {
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
