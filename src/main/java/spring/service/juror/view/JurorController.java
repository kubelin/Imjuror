package spring.service.juror.view;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.extensions.Rating;
import com.google.gdata.data.geo.impl.GeoRssWhere;
import com.google.gdata.data.media.mediarss.MediaCategory;
import com.google.gdata.data.media.mediarss.MediaDescription;
import com.google.gdata.data.media.mediarss.MediaKeywords;
import com.google.gdata.data.media.mediarss.MediaPlayer;
import com.google.gdata.data.media.mediarss.MediaThumbnail;
import com.google.gdata.data.media.mediarss.MediaTitle;
import com.google.gdata.data.youtube.FormUploadToken;
import com.google.gdata.data.youtube.UserProfileEntry;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.data.youtube.YouTubeMediaContent;
import com.google.gdata.data.youtube.YouTubeMediaGroup;
import com.google.gdata.data.youtube.YouTubeMediaRating;
import com.google.gdata.data.youtube.YouTubeNamespace;
import com.google.gdata.data.youtube.YtPublicationState;
import com.google.gdata.data.youtube.YtStatistics;

import spring.service.domain.EntryVO;
import spring.service.juror.JurorService;

@Controller
@Named("jurorController")
public class JurorController {
	
	
//	@Inject
//	@Named("jurorServiceImpl")
	JurorService jurorService;

	static String tempstatus = null;
	static String tempvideoid = null;
	
	@Autowired
	public void setJurorService(JurorService jurorService){
		this.jurorService = jurorService ;
	}
	
	@RequestMapping("/getJurorList.do")
	public ModelAndView getJurorList() throws SQLException{
		
		ModelAndView mav = new ModelAndView("pageJsonReport");
		mav.addObject("jurorList", jurorService.getJurorList());
		
		return mav;
	}
	
//	@RequestMapping("/getJuror.do")
//	public ModelAndView getJuror(@RequestParam("userId") String userId) throws SQLException{
//		
//		ModelAndView mav = new ModelAndView("pageJsonReport");
//		mav.addObject("jurorVO",jurorService.getJuror(userId));
//		mav.addObject("jurorVideo", jurorService.getVideo(userId));
//		
//		return mav;
//	}

	@RequestMapping("/getget.do")
	public void getget() throws Exception{
		System.out.println("getget Call.");
		
		
	}
	
	
	@RequestMapping("/getVideoUrl.do")
	public ModelAndView success(HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception{
		
		System.out.println("getVideoUrl.do 실행");
	// title, description, keyword, category, secret, token값(반드시 세션용으로 변환된 값이어야 함)
	// 을 리퀘스트 영역에 받아 어떠한 로직으로 처리하여 최종적으로 videoUrl과 videoToken을
	// 반환시켜 주는 servlet
	
	// request영역에서 title, description, keyword, category, secret, token을 뽑아와 변수에 저장.
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		
		String token = session.getAttribute("token").toString(); 
		System.out.println("session에 저장된 token값은 " + token);
	
		GoogleCredential credential = new GoogleCredential().setAccessToken(token);
		
	// service 인스턴스 초기화시에 개발자 id와 key를 집어넣어service인스턴스를 생성하고 AuthToken을 아까 받아온 token값으로 설정 	
		YouTubeService service = new YouTubeService("566725873556.apps.googleusercontent.com", "AI39si6_ayxpCoroav-87zRAxZVgHm_MZVzDAcrVKtngT6N0A6FwyPTCIU8pghO0jjurHkoYpw-rB2p_Gmu4soa658AjBP7v2g");
		service.setOAuth2Credentials(credential);
		
	// newEntry인스턴스에 Title, Category, Keywords, Description, Private, 위도-경도 정보를 집어넣는다.
		VideoEntry newEntry = new VideoEntry();
		YouTubeMediaGroup mg = newEntry.getOrCreateMediaGroup();
		
		mg.setTitle(new MediaTitle());
		mg.getTitle().setPlainTextContent(title);
		mg.addCategory(new MediaCategory(YouTubeNamespace.CATEGORY_SCHEME, "Autos"));
		mg.setKeywords(new MediaKeywords());
		mg.getKeywords().addKeyword(category);
		mg.setDescription(new MediaDescription());
		mg.getDescription().setPlainTextContent(description);
		mg.addCategory(new MediaCategory(YouTubeNamespace.DEVELOPER_TAG_SCHEME, "singhaheungtag"));
		mg.setPrivate(false);
		newEntry.setGeoCoordinates(new GeoRssWhere(37.0,-122.0));
	
	// 동영상을 올릴 fake URL을 설정하고 service인스턴스의 getFormUploadToken메소드에
	// fake URL과 방금 설정했던 newEntry 인스턴스를 인자값으로 넘기고 FormUploadToken클래스의 인스턴스인
	// videotoken인스턴스에 저장한다.
		URL uploadUrl = new URL("http://gdata.youtube.com/action/GetUploadToken");
		FormUploadToken videotoken = service.getFormUploadToken(uploadUrl, newEntry);
		
	// videotoken인스턴스에는 실제로 동영상을 업로드해야할 Url과 비디오와 같이 넘겨야 할 Token값이 담겨있다.
	// 주의 : 이 token값은 우리가 Youtube인증을 위해 넘겨받은 token값과 다른 token값이다.
	// 구분을 위해 이를 videotoken이라 부른다.
		System.out.println("얻어온 url과 token값 출력");
		System.out.println(videotoken.getUrl());
		System.out.println(videotoken.getToken());
		
		ModelAndView mav = new ModelAndView("pageJsonReport");
		mav.addObject("videoUrl", videotoken.getUrl());
		mav.addObject("videoToken", videotoken.getToken());
				
		System.out.println(mav.toString());
		
		System.out.println("getVideoUrl.do 종료");
		return mav;
	}

	@RequestMapping("/idSave.do")
	@ResponseBody
	public ModelAndView success2(HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception{
				
		System.out.println("idSave.do 실행");
		
		String status = null;
		String videoid = null;
		
		status = request.getParameter("status");
		System.out.println("리퀘스트영역의 status값을 status변수에 집어넣음");
		videoid = request.getParameter("id");
		System.out.println("리퀘스트영역의 id값을 videoid변수에 집어넣음");

		System.out.println("session id값은  = " + session.getId());
		System.out.println("저장된 변수 값 출력");
		System.out.println("status = " + status);
		System.out.println("videoid = " + videoid);
		
		JurorController.tempstatus = status;
		JurorController.tempvideoid = videoid;
		
		session.setAttribute("status",status);
		System.out.println("리퀘스트 영역의 status값을 세션 영역의 status값에 집어넣음");
		session.setAttribute("videoid",videoid);
		System.out.println("리퀘스트 영역의 id값을 세션 영역의 videoid값에 집어넣음");
		System.out.println("저장된 세션 영역 값 확인");
		System.out.println("status = " + (String)session.getAttribute("status"));
		System.out.println("videoid = " + (String)session.getAttribute("videoid"));
		
		ModelAndView mav = new ModelAndView("pageJsonReport");
		mav.addObject("status", status);
		mav.addObject("videoid", videoid);
		
		System.out.println("idSave.do 종료");
		return mav;
		
//		ArrayList<Object> list = new ArrayList<Object>();
//		list.add(videoid);
//		list.add(status);
		
//		return new ModelAndView("success22.jsp","list",list);
	}

	@RequestMapping("/idReturn.do")
	public ModelAndView success3(HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception{
		
//		HttpSession session = request.getSession(false);
		
		System.out.println("idReturn.do 실행");
		
		String status = null;
		String videoid = null;
		
		status = JurorController.tempstatus;
		videoid = JurorController.tempvideoid;
		
		System.out.println("세션으로부터 뽑아온 status값은 = " + status);
		System.out.println("세션으로부터 뽑아온 videoid값은 = " + videoid);
			
		ModelAndView mav = new ModelAndView("pageJsonReport");
		mav.addObject("status", status);
		mav.addObject("videoid", videoid);
		
		System.out.println("idReturn.do 종료");
		return mav;
	}	
	
	// 메인 페이지의 배틀 신청 페이지에 최종적으로 videoid값과 username정보를 넘겨주기 위해 
	// session에 위의 두 값을 저장.
	// 메인 페이지에서는 두 값을 이용하여 DB서버에 최종적으로 query문을 날린다.
	@RequestMapping("/sendDirect.do")
	public ModelAndView success4(HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception{
		
		System.out.println("sendDirect.do 실행");
		
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String status = request.getParameter("status");
		String videoid = request.getParameter("videoid");
		String username = (String)session.getAttribute("Username");
		
		System.out.println("title = " + title);
		System.out.println("description = " + description);
		System.out.println("status = " + status);
		System.out.println("videoid = " + videoid);
		System.out.println("username = " + username);
		
		session.setAttribute("videoid", videoid);
			
		ModelAndView mav = new ModelAndView("pageJsonReport");
		
		System.out.println("sendDirect.do 종료");
		return mav;
	}
	
	@RequestMapping("/sendLink.do")
	public ModelAndView sendLinkDB(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		System.out.println("sendLink.do실행");
		
		ModelAndView mav = new ModelAndView("pageJsonReport");

		String videoid = request.getParameter("videoid");
		System.out.println("리퀘스트에 있는 videoid값 확인 : " + videoid);
		
		session.setAttribute("videoid", videoid);
		System.out.println("세션의 videoid를 리퀘스트에서 추출한 값으로 변경");
		
		System.out.println("sendLink.do종료");
		
		return mav;
	}
	
	@RequestMapping("/receive.do")
	public ModelAndView receive(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		List<EntryVO> entryList = new ArrayList<EntryVO>();
		
		ModelAndView mav = new ModelAndView("pageJsonReport");

		GoogleCredential credential = new GoogleCredential().setAccessToken((String)session.getAttribute("token"));
		
		YouTubeService service = new YouTubeService("566725873556.apps.googleusercontent.com","AI39si6_ayxpCoroav-87zRAxZVgHm_MZVzDAcrVKtngT6N0A6FwyPTCIU8pghO0jjurHkoYpw-rB2p_Gmu4soa658AjBP7v2g");
		service.setOAuth2Credentials(credential);
					
		// 진정한 유튜브 아이디 얻기
		// 유튜브 아이디 없으면 여기서 에러. 만약 없을 경우 Developer의 계정으로 올려도 상관 없을듯.
		// or developer 계정으로 올릴 시, UTID를 받아오지 못 함. 
		//service.setUserCredentials("kubelin@gmail.com", "h");
		UserProfileEntry userfeed = service.getEntry(new URL("http://gdata.youtube.com/feeds/api/users/default"), UserProfileEntry.class);
		System.out.println("유저 아이디 : " + userfeed.getId());
		System.out.println("유저 네임 : " + userfeed.getUsername());
		
		// 접속한 유저의 비디오 정보 얻기
		String feedUrl = "http://gdata.youtube.com/feeds/api/users/" + userfeed.getUsername() + "/uploads";
		VideoFeed videoFeed = service.getFeed(new URL(feedUrl), VideoFeed.class);
		entryList = printVideoFeed(videoFeed, true);
		
		//mav.addObject("token",token);
		System.out.println("token값 반환");
		System.out.println("List값 확인");
		
		for(int i=0;i<entryList.size();i++) {
			System.out.println(i + "번째 List 내용 ->");
			System.out.println(entryList.get(i).getThumbnail_default());
			System.out.println(entryList.get(i).getThumbnail_mqdefault());
			System.out.println(entryList.get(i).getVideoid());
		}
		
		mav.addObject(entryList);
				
		return mav;
	}	

	@RequestMapping("/checkout.do")
	public ModelAndView checkout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView("pageJsonReport");
		System.out.println("checkout.do실행");
		
		String token = null;
		
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute("token") != null){
			token = session.getAttribute("token").toString();
			System.out.println("세션으로부터 token값 추출 :" + token);
		}
		
		mav.addObject("token",token);
		System.out.println("token값 반환");
		System.out.println("checkout.do 종료");
		
		return mav;
	}
		
	@RequestMapping("/fake.do")
	public ModelAndView fake(HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception{
		
		System.out.println("fake.do 실행");
		
		GoogleCredential credential = new GoogleCredential().setAccessToken(request.getParameter("token"));
		
		YouTubeService service = new YouTubeService("566725873556.apps.googleusercontent.com","AI39si6_ayxpCoroav-87zRAxZVgHm_MZVzDAcrVKtngT6N0A6FwyPTCIU8pghO0jjurHkoYpw-rB2p_Gmu4soa658AjBP7v2g");
		service.setOAuth2Credentials(credential);
					
		// 진정한 유튜브 아이디 얻기
		// 유튜브 아이디 없으면 여기서 에러. 만약 없을 경우 Developer의 계정으로 올려도 상관 없을듯.
		// or developer 계정으로 올릴 시, UTID를 받아오지 못 함. 
		//service.setUserCredentials("kubelin@gmail.com", "h");
		UserProfileEntry userfeed = service.getEntry(new URL("http://gdata.youtube.com/feeds/api/users/default"), UserProfileEntry.class);
		System.out.println("유저 아이디 : " + userfeed.getId());
		System.out.println("유저 네임 : " + userfeed.getUsername());
		
		// 세션 영역에 Username을 설정		
		session.setAttribute("Username", userfeed.getUsername());
		System.out.println("세션 영역에 Username값 저장");
		session.setAttribute("token", request.getParameter("token"));
		System.out.println("세션 영역에 token값 저장");
		
		ModelAndView mav = new ModelAndView("pageJsonReport");
//		mav.addObject("userId", userfeed.getId());
				
//		System.out.println(mav.toString());
//		response.sendRedirect("http://localhost:8080/StudyGroup/next.html");
		System.out.println("fake.do 종료");
		return mav;
	}	
	
	private static List<EntryVO> printVideoFeed(VideoFeed videoFeed, boolean detailed) {
		// TODO Auto-generated method stub
		EntryVO entry = new EntryVO();
		List<EntryVO> entryList = new ArrayList<EntryVO>();
		
		for(VideoEntry videoEntry : videoFeed.getEntries() ) {
			entry = printVideoEntry(videoEntry, detailed);
			entryList.add(entry);
		}	
		 
		return entryList;
	}

	public static EntryVO printVideoEntry(VideoEntry videoEntry, boolean detailed) {
		  EntryVO entry = new EntryVO();
		
		  System.out.println("Title: " + videoEntry.getTitle().getPlainText());
		  
		  if(videoEntry.isDraft()) {
		    System.out.println("Video is not live");
		    YtPublicationState pubState = videoEntry.getPublicationState();
		    if(pubState.getState() == YtPublicationState.State.PROCESSING) {
		      System.out.println("Video is still being processed.");
		    }
		    else if(pubState.getState() == YtPublicationState.State.REJECTED) {
		      System.out.print("Video has been rejected because: ");
		      System.out.println(pubState.getDescription());
		      System.out.print("For help visit: ");
		      System.out.println(pubState.getHelpUrl());
		    }
		    else if(pubState.getState() == YtPublicationState.State.FAILED) {
		      System.out.print("Video failed uploading because: ");
		      System.out.println(pubState.getDescription());
		      System.out.print("For help visit: ");
		      System.out.println(pubState.getHelpUrl());
		    }
		  }

		  if(videoEntry.getEditLink() != null) {
		    System.out.println("Video is editable by current user.");
		  }

		  if(detailed) {

		    YouTubeMediaGroup mediaGroup = videoEntry.getMediaGroup();

		    System.out.println("Uploaded by: " + mediaGroup.getUploader());

		    System.out.println("Video ID: " + mediaGroup.getVideoId());
		    entry.setVideoid(mediaGroup.getVideoId());
		    
		    System.out.println("Description: " + 
		      mediaGroup.getDescription().getPlainTextContent());

		    MediaPlayer mediaPlayer = mediaGroup.getPlayer();
		    System.out.println("Web Player URL: " + mediaPlayer.getUrl());
		    MediaKeywords keywords = mediaGroup.getKeywords();
		    System.out.print("Keywords: ");
		    for(String keyword : keywords.getKeywords()) {
		      System.out.print(keyword + ",");
		    }

		    GeoRssWhere location = videoEntry.getGeoCoordinates();
		    if(location != null) {
		      System.out.println("Latitude: " + location.getLatitude());
		      System.out.println("Longitude: " + location.getLongitude());
		    }

		    Rating rating = videoEntry.getRating();
		    if(rating != null) {
		      System.out.println("Average rating: " + rating.getAverage());
		    }

		    YtStatistics stats = videoEntry.getStatistics();
		    if(stats != null ) {
		      System.out.println("View count: " + stats.getViewCount());
		    }
		    System.out.println();

		    System.out.println("\tThumbnails:");
		    
		    int count = 0;
		    for(MediaThumbnail mediaThumbnail : mediaGroup.getThumbnails()) {
		      System.out.println("\t\tThumbnail URL: " + mediaThumbnail.getUrl());
		      
		      if(count == 0) {
		    	  entry.setThumbnail_default(mediaThumbnail.getUrl());
		    	  count++;
		      } else if(count == 1) {
		    	  entry.setThumbnail_mqdefault(mediaThumbnail.getUrl());
		    	  count++;
		      }
		      
		      System.out.println("\t\tThumbnail Time Index: " +
		      mediaThumbnail.getTime());
		      System.out.println();
		    }

		    System.out.println("\tMedia:");
		    for(YouTubeMediaContent mediaContent : mediaGroup.getYouTubeContents()) {
		      System.out.println("\t\tMedia Location: "+ mediaContent.getUrl());
		      System.out.println("\t\tMedia Type: "+ mediaContent.getType());
		      System.out.println("\t\tDuration: " + mediaContent.getDuration());
		      System.out.println();
		    }

		    for(YouTubeMediaRating mediaRating : mediaGroup.getYouTubeRatings()) {
		      System.out.println("Video restricted in the following countries: " +
		        mediaRating.getCountries().toString());
		    }
		  }
		  
		  return entry;
	}
}
