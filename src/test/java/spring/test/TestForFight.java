package spring.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.service.domain.ArtistJurorVO;
import spring.service.domain.ArtistParamVO;
import spring.service.domain.ArtistVO;
import spring.service.domain.FightJoinArtistVO;
import spring.service.domain.FightParamVO;
import spring.service.domain.FightVO;
import spring.service.fight.impl.Fight2DAOImpl;
import spring.service.fight.impl.Fight2ServiceImpl;
import spring.service.fight.view.FightListMaker;

import com.ibatis.sqlmap.client.SqlMapClient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./src/main/resource/config/datasourceservice.xml",
		"file:./src/main/resource/config/commonservice.xml" })
public class TestForFight {

	@Inject
	@Named("sqlMapClient")
	private SqlMapClient sqlMapClient;

	static int fightNo;

	public int getFightNo() {
		return fightNo;
	}

	public static void setFightNo(int fn) {
		fightNo = fn;
	}

	// @Test
	public void addFight() throws Exception {

		FightVO fightVO = new FightVO();
		fightVO.setTitle("Last");
		fightVO.setGenreNo("1");
		fightVO.setContent("내용도 내꺼");
		Integer no = (Integer) sqlMapClient.insert("Fight.addFight", fightVO);

		fightNo = Integer.parseInt(no.toString());

		TestForFight.setFightNo(fightNo);
		System.out.println(no);
	}

	// @Test
	public void addArtist() throws Exception {

		ArtistVO artistVO = new ArtistVO();

		System.out.println("ArtistTest fightNo: " + fightNo);

		artistVO.setFightNo(TestForFight.fightNo);
		artistVO.setUserId("112221322232");
		artistVO.setVideo("videPath");
		artistVO.setArtistComment("Test중입니다.");
		artistVO.setVideoSequence(1);

		System.out.println(artistVO);

		sqlMapClient.insert("Fight.addArtist", artistVO);

	}

	// 리스트 뿌리기
	// @Test
	public void getFightListCount() throws Exception {
		ArrayList<FightVO> fightList1 = (ArrayList<FightVO>) sqlMapClient
				.queryForList("Fight.fightList", 1);
		ArrayList<FightVO> fightList2 = (ArrayList<FightVO>) sqlMapClient
				.queryForList("Fight.fightList", 2);
		ArrayList<Object> fightList3 = new ArrayList();

		// fightList.add((FightVO)sqlMapClient.queryForList("Fight.fightList"));
		HashMap<Integer, FightVO> artist1 = new HashMap<Integer, FightVO>();
		HashMap<Integer, FightVO> artist2 = new HashMap<Integer, FightVO>();

		for (FightVO i : fightList1) {
			fightList3.add(artist1.put(i.getFightNo(), i));
		}
		for (FightVO i : fightList2) {
			artist1.put(i.getFightNo(), i);
		}

		fightList3.add(artist1);
		fightList3.add(artist2);

	}

	// @Test
	public void getFightArtistList() throws Exception {

		List<FightJoinArtistVO> list = (List<FightJoinArtistVO>) sqlMapClient
				.queryForList("Fight2.getFightArtistList", "");

		for (FightJoinArtistVO fja : list) {
			System.out.println(fja);
		}

	}

	// @Test
	public void getFightList() throws Exception {

		// ProtoType입니다. 막장 코딩인것 같아요...
		// 최종리스트
		List<List> fightList = new ArrayList<List>();

		// 만약 페이지 요청에서 pageNum가 주어지지 않는다면 프로그램적으로
		// 기본 1을 주어야함
		FightParamVO fp = new FightParamVO();
		fp.setPageNo(1);
		// 장르가 주어지지 않으면 전체가 기본
		// fp.setGenreNo(1);

		List<FightVO> list = sqlMapClient
				.queryForList("Fight.getFightList", fp);

		//
		List<Integer> fightNos = new ArrayList<Integer>();

		System.out.println("::::: getFightList :::::");
		for (FightVO fight : list) {
			System.out.println(fight);
			fightNos.add(fight.getFightNo());
		}
		ArtistParamVO ap = new ArtistParamVO();
		ap.setFightNos(fightNos);
		ap.setVideoSequence(1);
		List<ArtistJurorVO> list2 = sqlMapClient.queryForList(
				"Fight.getArtistJurorList", ap);

		System.out.println("::::: getArtist1List :::::");
		for (ArtistJurorVO aj : list2) {

			System.out.println(aj);
		}
		ap.setVideoSequence(2);
		List<ArtistJurorVO> list3 = sqlMapClient.queryForList(
				"Fight.getArtistJurorList", ap);

		System.out.println("::::: getArtist2List :::::");
		for (ArtistJurorVO aj2 : list3) {
			System.out.println(aj2);

		}

		List<Object> fightRecord = null;

		System.out.println("\n\nFor문 시작");
		total: for (int i = 0; i < list.size(); i++) {
			System.out.println("List -> index : " + i);
			System.out.println("List's size : " + list.size());
			for (int j = 0; j < list2.size(); j++) {
				System.out.println("List2 -> index : " + j);
				System.out.println("List2's size : " + list2.size());
				System.out.println("list -> " + list.get(i).getFightNo()
						+ " : " + "list2 ->" + list2.get(j).getFightNo());

				if (list.get(i).getFightNo().equals(list2.get(j).getFightNo())) {
					System.out.println("list[" + i + "]와 list2[" + j + "]일치\n");
					fightRecord = new ArrayList<Object>();
					fightRecord.add(list.get(i));
					fightRecord.add(list2.get(j));

					for (int k = 0; k < list3.size(); k++) {
						System.out.println("List3 -> index : " + k);
						System.out.println("List3's size : " + list3.size());
						System.out.println("list2 -> "
								+ list.get(j).getFightNo() + " : " + "list3 ->"
								+ list2.get(k).getFightNo());
						if (list2.get(j).getFightNo()
								.equals(list3.get(k).getFightNo())) {
							System.out.println("list[" + j + "]와 list2[" + k
									+ "]일치\n");
							fightRecord.add(list3.get(k));
							// list3.remove(k);
						}

					}
					// list2.remove(j);
				}

			}
			fightList.add(fightRecord);
			System.out.println("::fightList's Size" + fightList.size());
			System.out.println("::List2's Size" + list2.size());
			if (fightList.size() >= list2.size()) {
				break total;
			}

		}

		System.out.println("최종값은?!");
		for (List temp : fightList) {
			System.out.println("Line : " + fightList.size());
			for (int i = 0; i < temp.size(); i++) {
				System.out.println(temp.get(i));
			}
			System.out.println("\n");
		}
	}

	// @Test
	public void getArtistList() throws Exception {

		ArtistParamVO ap = new ArtistParamVO();

		List<Integer> fightNos = new ArrayList<Integer>();
		fightNos.add(1);
		fightNos.add(2);
		ap.setFightNos(fightNos);
		ap.setVideoSequence(1);
		sqlMapClient.queryForList("Fight.getArtistJurorList", ap);
	}

	// @Test
	public void getFightState() throws Exception {

		List<Integer> list = sqlMapClient.queryForList("Fight.getFightState",
				12);

		for (Integer var : list) {
			System.out.println("value : " + var);
		}
	}

//	 @Test
	public void getFightListTest() throws Exception {

		// Dependencies
		FightListMaker flm = new FightListMaker();
		Fight2ServiceImpl fsi = new Fight2ServiceImpl();
		Fight2DAOImpl fdi = new Fight2DAOImpl();
		fdi.setSqlMapClient(sqlMapClient);

		int pageNo = 1;
		String genreNo = "";
		Integer isActive = 1;
		
		FightParamVO fp = new FightParamVO(pageNo, genreNo,isActive);
		System.out.println(fp);

		// List<Map> fightList = flm.requestFightList(pageNo, genreNo);
		List<FightVO> fightList = sqlMapClient.queryForList(
				"Fight.getFightList", fp);
		
		System.out.println("Hello");
		System.out.println(fightList);
		
	}

//	 @Test
	 public void getPopularFightNos() throws Exception{
		 List<Integer> list = sqlMapClient.queryForList("Fight.getPopularFightNos");
		 System.out.println("list : " + list);
		 
		 ArtistParamVO ap = new ArtistParamVO();
		 ap.setFightNos(list);
		 ap.setVideoSequence(1);
		 
		 List<FightVO> fightList = 
				 sqlMapClient.queryForList("Fight.getFightListUseFightNos",ap);
		 
		 for(FightVO fight : fightList){
			 System.out.println(fight);
		 }
	 }
//	 @Test
	 public void testtest() throws Exception{
		 
		 FightParamVO fp = new FightParamVO();
		 fp.setGenreNo("1");
		 fp.setIsActive(1);
		 fp.setPageNo(1);
		 
		 
		 
		 List<Integer> fightList = sqlMapClient.queryForList("Fight2.getFightList",fp);
		 System.out.println(fightList);
		 
	 }
	 
	@Test
	public void add2Fight() throws Exception{
	
		FightVO fight = new FightVO();
		fight.setTitle("연습용3");
		fight.setGenreNo("1");
		fight.setContent("없음");
		
		
		int flag = (int)sqlMapClient.insert("Fight.addFight", fight);
		System.out.println("obj : " + flag);
		
	}
	 
}
