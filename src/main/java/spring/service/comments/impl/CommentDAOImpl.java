package spring.service.comments.impl;import java.sql.SQLException;import java.util.List;import javax.inject.Named;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Repository;import spring.service.comments.CommentDAO;import spring.service.domain.CommentVO;import com.ibatis.sqlmap.client.SqlMapClient;@Repository@Named("commentDAO")public class CommentDAOImpl implements CommentDAO {		SqlMapClient sqlMapClient;		public CommentDAOImpl(){		System.out.println(getClass()+"Constructor!!!");	}		@Autowired	public void setSqlMapClient(SqlMapClient sqlMapClient){		this.sqlMapClient = sqlMapClient;	}		@Override	public List<CommentVO> getCommentList1(int fightNo) throws SQLException {		// TODO Auto-generated method stub		return sqlMapClient.queryForList("Comment.getCommentList1",fightNo);	}		@Override	public List<CommentVO> getCommentList2(int fightNo) throws SQLException {		// TODO Auto-generated method stub		return sqlMapClient.queryForList("Comment.getCommentList2",fightNo);	}	@Override	public void addComment(CommentVO commentVO) throws SQLException {		// TODO Auto-generated method stub		System.out.println("call addComment");		sqlMapClient.insert("Comment.addComment",commentVO);	}	@Override	public void deleteComment(int commentId) throws SQLException {		// TODO Auto-generated method stub		sqlMapClient.delete("Comment.removeComment", commentId);	}}