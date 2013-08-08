package spring.service.common;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import spring.service.domain.JurorVO;
import spring.service.juror.JurorService;

@Controller
@Named("commonController")
public class CommonController {
	
	@Inject
	@Named("jurorService")
	JurorService jurorService;
	
	
	@RequestMapping("/isMember.do")
	public ModelAndView UserCheck(@RequestParam("userId")String userId,HttpSession session) throws Exception{
		
		System.out.println("/isMember.do");
		String result = "FAIL";
		if(jurorService.getJuror(userId) != null){
			result = "OK";
			System.out.println(result);
			session.setAttribute("userId", userId);
		}		
		ModelAndView mav = new ModelAndView("pageJsonReport");
		mav.addObject("result", result);
		return mav;
	}
	
	@RequestMapping("/sendUserData.do")
	public ModelAndView addMember(JurorVO juror, HttpSession session) throws Exception{
		
		System.out.println("JurorVO Test : " + juror);
		
		//JurorVO Test : JurorVO [userId=100003979781181, name=이상민, nickName=null, gender=male, 
		//picture=https://graph.facebook.com/100003979781181/picture, record=null, userLevel=null, admin=null, 
		//link=http://www.facebook.com/profile.php?id=100003979781181, regDate=null, modDate=null]
		juror.setAdmin("0");
		juror.setUserLevel(1);
		juror.setNickName(juror.getName());
		System.out.println("JurorVO set info : " + juror);
		jurorService.addJuror(juror);
		
		//Insert가 정상적으로 완료 되었을때.
		session.setAttribute("userId", juror.getUserId());
		
		return null;
	}
	@RequestMapping("/userLogout.do")
	public ModelAndView userLogout(@RequestParam("userId")String userId, HttpSession session) throws Exception{
		System.out.println("userID : " + userId);
		String result = "FAIL";
		if(session.getAttribute("userId").equals(userId)){
			result = "OK";
			session.removeAttribute(userId);
		}
		ModelAndView mav = new ModelAndView("pageJsonReport");
		mav.addObject("result", result);
		return mav;
	}
	
	@RequestMapping("/ajaxTest.do")
//	@ResponseBody
	public String ajaxTest() throws Exception{
		
//		ModelAndView mav = new ModelAndView("pageJsonReport");
//		mav.addObject("result", "SUCCESS!");
//		return mav;
		
		String result = "SUCCESS You can controll this.";
		
		return result;
	}
}
