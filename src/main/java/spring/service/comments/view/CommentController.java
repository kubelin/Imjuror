//package spring.service.comments.view;////import java.sql.SQLException;////import javax.inject.Named;//import javax.servlet.http.HttpServletRequest;////import org.springframework.beans.factory.annotation.Autowired;//import org.springframework.stereotype.Controller;//import org.springframework.web.bind.annotation.RequestMapping;//import org.springframework.web.bind.annotation.RequestParam;//import org.springframework.web.servlet.ModelAndView;////import spring.service.comments.CommentService;//import spring.service.domain.CommentVO;////@Controller//@Named("commentController")//public class CommentController {//	//	CommentService commentService;//	//	public CommentController(){//		System.out.println(getClass()+"Consturctor!!!!!");//	}//	//	@Autowired//	public void setCommentController(CommentService commentService){//		this.commentService = commentService;//	}//	//	@RequestMapping("/getCommentList.do")//	public ModelAndView getCommentList(HttpServletRequest request) throws SQLException{//		//		int fightNo = Integer.parseInt(request.getParameter("fightNo"));//		//		ModelAndView mav = new ModelAndView("pageJsonReport");//		//		mav.addObject("commentList1", commentService.getCommentList1(fightNo));//		mav.addObject("commentList2", commentService.getCommentList2(fightNo));//		return mav;//	}//	//	@RequestMapping("/addComment.do")//	public ModelAndView addComment() throws SQLException{//		CommentVO commentVO = new CommentVO();//		commentVO.setJurorComment("노래 몬하노 내보........");//		commentVO.setFightNo(1);//		commentVO.setArtist(2);//		commentVO.setUserId("100003045671334");//		commentService.addComment(commentVO);//		System.out.println("ddddd");//		System.out.println(commentVO);//		return new ModelAndView("/getCommentList.do");//		//	}//	@RequestMapping("/delComment.do")//	public ModelAndView delComment(@RequestParam("commentId") int commentId) throws SQLException{//		commentService.deleteComment(commentId);//		return new ModelAndView("/getCommentList.do");//	}//	////}