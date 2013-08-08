package spring.service.fight.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring.service.domain.ArtistScoreVO;
import spring.service.domain.ArtistVO;
import spring.service.domain.FightCommentVO;
import spring.service.domain.FightParamVO;
import spring.service.domain.FightVO;
import spring.service.domain.LastestFightVO;
import spring.service.fight.Fight2Service;
import spring.service.fight.FightService;

@Controller
@Named("fightController")
public class FightController {

	@Inject
	@Named("fight2Service")
	private Fight2Service fight2Service;
	
	@Inject
	@Named("fightService")
	private FightService fightService;
	
	@Inject
	@Named("fightListMaker")
	private FightListMaker fightListMaker;
	
	/////////////////////////////////////////
	
	public Fight2Service getFight2Service() {
		return fight2Service;
	}

	public void setFight2Service(Fight2Service fight2Service) {
		this.fight2Service = fight2Service;
	}

	public FightService getFightService() {
		return fightService;
	}

	public void setFightService(FightService fightService) {
		this.fightService = fightService;
	}

	public FightListMaker getFightListMaker() {
		return fightListMaker;
	}

	public void setFightListMaker(FightListMaker fightListMaker) {
		this.fightListMaker = fightListMaker;
	}

	
	
	//////////////////////////////////

	public FightController() {
		System.out.println("[FightController Defualt Construntor Call...]");
	}


	// 메인 리스트 Fight 리스트
	@RequestMapping("/getContestList.do")
	public ModelAndView getFightList(@RequestParam("selectGenre")String genreNo, @RequestParam("pageNo")int pageNo,@RequestParam("isActive")Integer isActive) throws Exception {

		if(genreNo.equals("selectAll") || genreNo.equals("")){
			genreNo = "";
		}
		// FightController구성
		FightParamVO fp = new FightParamVO(pageNo,genreNo,isActive);
//		Annotation 기반
		
		
		ModelAndView mav = new ModelAndView("pageJsonReport");
		mav.addObject("fightList",fightListMaker.requestFightArtistList(fp));
		
		return mav;
	}
	@RequestMapping("/getContest.do")
	public ModelAndView getFight(@RequestParam("fightNo")Integer fightNo) throws Exception{
		
		ModelAndView mav = new ModelAndView("pageJsonReport");
		mav.addObject("fightList", fightListMaker.requestFightArtist(fightNo));
		
		if(fightService.getArtistScore(fightNo) == null)
			mav.addObject("fightScore", new ArtistScoreVO());
		else 
			mav.addObject("fightScore", fightService.getArtistScore(fightNo));
		return mav;
	}
	
	

	@RequestMapping("/getFamousList.do")
	public ModelAndView getPopularFight(@RequestParam("selectGenre") String selectGenre) throws Exception{
		
		
		System.out.println("aaa : " + selectGenre);
		
		if(selectGenre.equals("selectAll")){
			selectGenre = "";
		}
		
		List<Map> fightList = fightListMaker.requestPopularFightList(selectGenre);
		
		ModelAndView mav = new ModelAndView("pageJsonReport");
		mav.addObject("fightList", fightList);
		return mav;
	}

	
	//////////////////////////////////////////
	// Fight 만들기
		@RequestMapping("/addFight.do")
		public ModelAndView addFight(FightVO fight) throws Exception {
			ModelAndView mav = new ModelAndView("pageJsonReport");
			mav.addObject("addFight", fightService.addFight(fight));
			return mav;
		}
		
		// Fight 지우기 (테)
		@RequestMapping("/delFight.do")
		public ModelAndView delFight(@RequestParam("fightNo") int fightNo) throws Exception {
			ModelAndView mav = new ModelAndView("pageJsonReport");
			mav.addObject("delFight", fightService.delFight(fightNo));
			return mav;
		}
		
		// Fight 만들기 전에 동영상&코멘트 쓰기
		@RequestMapping("/addArtist.do")
		public ModelAndView addArtist(ArtistVO artist) throws Exception {
			
			ModelAndView mav = new ModelAndView("pageJsonReport");
			mav.addObject("delFight", fightService.addArtist(artist));
			return mav;
		}
		
		//현재의 파이트에 ARTIST를 추가 할 수 있는지 체크.(테)
		@RequestMapping("/checkFight.do")
		public ModelAndView checkFight(@RequestParam("fightNo") int fightNo) throws Exception{
			ModelAndView mav = new ModelAndView("pageJsonReport");
			mav.addObject("checkFight", fightService.checkFight(fightNo));
			return mav;
		}
		
		//fight 장르별 목록수(테)
		@RequestMapping("/getContestListCount.do")
		public ModelAndView fightCount(@RequestParam("selectGenre") String genre, @RequestParam("isActive")int isActive)throws Exception{
			if(genre.equals("selectAll")){
				genre = "";
			}
			FightVO fight = new FightVO();
			fight.setGenreNo(genre);
			fight.setIsActive(isActive);
			ModelAndView mav = new ModelAndView("pageJsonReport");
			mav.addObject("fightCount", fightService.fightCount(fight));
			return mav;
		}
		
		//동영상에 대한 점수 매기기
		@RequestMapping("/addArtistScore.do")
		public ModelAndView addArtistScore(@RequestParam("fightNo")Integer fightNo, @RequestParam("jurorId")String jurorId,
				@RequestParam("artist1Id")String artist1Id, @RequestParam("artist1Score") Integer artist1Score,
				@RequestParam("artist2Id")String artist2Id, @RequestParam("artist2Score") Integer artist2Score
				) throws Exception{
			
			
			System.out.println("artist1Id : " + artist1Id +"artist1Score : "+ artist1Score + 
					"\n  artist2Id :  " + artist2Id +"artist2Score : " + artist2Score);
			
			ArtistScoreVO as1 = new ArtistScoreVO();
			as1.setFightNo(fightNo);
			as1.setJurorId(jurorId);
			as1.setArtistId(artist1Id);
			as1.setScore(artist1Score);
			
			ArtistScoreVO as2 = new ArtistScoreVO();
			as2.setFightNo(fightNo);
			as2.setJurorId(jurorId);
			as2.setArtistId(artist2Id);
			as2.setScore(artist2Score);
			
			fightService.addArtistScore(as1);
			fightService.addArtistScore(as2);
			
			ModelAndView mav = new ModelAndView("pageJsonReport");
			mav.addObject("getArtistScore", fightService.getArtistScore(fightNo));
			return mav;
			//100001994513306 : 100000996477869
		}
		
		//동영상에 대한 점수 가져오기(테)
		@RequestMapping("/getArtistScore.do")
		public ModelAndView getArtistScore(@RequestParam("fightNo") int fightNo)throws Exception{
			ModelAndView mav = new ModelAndView("pageJsonReport");
			mav.addObject("getArtistScore", fightService.getArtistScore(fightNo));
			return mav;
		}
		
		
		//게시판 점수 초기값 설정하기  (FIGHT가 생성되면 자동 불르기)
		@RequestMapping("/addFightComment.do")
		public ModelAndView addFightComment(FightCommentVO fightComment)throws Exception{
			ModelAndView mav = new ModelAndView("pageJsonReport");
			mav.addObject("addFightComment", fightService.addFightComment(fightComment));
			return mav;
		}
		
		
		//게시판 추천 올리기 ( juror이 동영상 점수를 주면 자동 증가)(테)
		@RequestMapping("/updatefightComment.do")
		public ModelAndView updatefightComment(@RequestParam("fightNo") int fightNo)throws Exception{
			ModelAndView mav = new ModelAndView("pageJsonReport");
			mav.addObject("updatefightComment", fightService.updatefightComment(fightNo));
			return mav;
		}
		
		
		//추천수 높은 게시판 TOP5 가져오기	
		@RequestMapping("/getHotFight.do")
		public ModelAndView getHotFight()throws Exception{
			ModelAndView mav = new ModelAndView("pageJsonReport");
			mav.addObject("getHotFight", fightService.getHotFight());
			return mav;
		}
		
		
		//"대결상태를 가져옵니다."getFightState , parameterClass : Integer(fightno)

		// Fight 상세보기 들어가서 Juror 점수 주기
		@RequestMapping("/artistScore.do")
		public ModelAndView artistScore(@RequestParam("fightNo") int fightNo,
				ArtistScoreVO artistScore, HttpSession session) throws Exception {
			artistScore.setFightNo(fightNo);
			System.out.println(session.getAttribute("user"));
			artistScore.setArtistId((String) session.getAttribute("user"));
			System.out.println(artistScore);

			fightService.addArtistScore(artistScore);

			return new ModelAndView("/list.do");
		}
		
		@RequestMapping("/getLastestContestList.do")
		public ModelAndView getLastestFightList(FightParamVO fp) throws Exception{
			
			System.out.println(fp);
			ModelAndView mav = new ModelAndView("pageJsonReport");
			mav.addObject("fightList", fight2Service.getLastestFightList(fp));
			return mav;
		}
		
		
		
		
		/////////////////////////////////////////////////////////////////
	
		// Forward
		@RequestMapping("/getFightListView.do")
		public String getFightListView() throws Exception {
			return "/bbs/listBbs.jsp";
		}

		@RequestMapping("/getFightView.do")
		public String getFightView() throws Exception {
			return "/bbs/getBbs.jsp";
		}

		// addArtistForm(동영상&코멘드 올리기)후에 fight폼으로 가기
		@RequestMapping("/addFightForm.do")
		public String addFightForm() throws Exception {
			return "/bbs/addFightForm.jsp";
		}

		// addFightForm 입력 완료후 리스트 뿌려주기
		@RequestMapping("/addFightView.do")
		public String addFightView() throws Exception {
			return "/list.do";
		}

		@RequestMapping("/delFightView.do")
		public String delFightView() throws Exception {
			return "/list.do";
		}

}
