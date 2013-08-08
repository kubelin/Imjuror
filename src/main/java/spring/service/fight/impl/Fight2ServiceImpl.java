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
import spring.service.domain.FightParamVO;
import spring.service.domain.FightVO;
import spring.service.domain.LastestFightVO;
import spring.service.fight.Fight2DAO;
import spring.service.fight.Fight2Service;

@Service
@Named("fight2Service")
public class Fight2ServiceImpl implements Fight2Service{
	
	@Inject
	@Named("fight2DAO")
	private Fight2DAO fightDAO;
	
	public Fight2ServiceImpl() {
		System.out.println("[FightServiceImpl Default Constructor Call.....]");
	}


	public void setFightDAO(Fight2DAO fightDAO) {
		this.fightDAO = fightDAO;
	}




	@Override
	public FightVO getFight(Integer fightNo) throws Exception {

		return fightDAO.getFight(fightNo);
	}


	@Override
	public void addFight(FightVO fight) throws Exception {
		fightDAO.addFight(fight);
	}


	@Override
	public void delFight(int fightNo) throws Exception {
		fightDAO.delFight(fightNo);
		
	}


	@Override
	public void addArtistScoreVO(ArtistScoreVO artistScore) throws Exception {
		fightDAO.addArtistScoreVO(artistScore);
	}


	@Override
	public void addArtist(ArtistVO artist) throws Exception {

		fightDAO.addArtist(artist);
	}


	@Override
	public ArrayList<FightVO> getFightList(FightParamVO fp) throws Exception{
		return fightDAO.getFightList(fp);
	}
	@Override
	public ArrayList<ArtistJurorVO> getArtistJurorList(ArtistParamVO ap)throws Exception{
		return fightDAO.getArtistJurorList(ap);
	}
	@Override
	public List<Integer> checkFight(Integer fightNo)throws Exception{
		return fightDAO.checkFight(fightNo);
	}
	@Override
	public List<Integer> getPopularFightNos(String genreNo) throws Exception {
		
		return fightDAO.getPopularFightNos(genreNo);
	}
	@Override
	public List<FightVO> getFightListUseFightNos(ArtistParamVO ap)
			throws Exception {
		// TODO Auto-generated method stub
		return fightDAO.getFightListUseFightNos(ap);
	}
	@Override
	public List<LastestFightVO> getLastestFightList(FightParamVO fp) throws Exception {
		// TODO Auto-generated method stub
		return fightDAO.getLastestFightList(fp);
	}
}
