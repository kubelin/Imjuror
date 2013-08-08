package spring.service.juror.impl;import java.sql.SQLException;import java.util.List;import javax.inject.Named;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import spring.service.domain.JurorVO;import spring.service.juror.JurorDAO;import spring.service.juror.JurorService;@Service@Named("jurorService")public class JurorServiceImpl implements JurorService {	//	@Inject//	@Named("jurorDAOImpl")	private JurorDAO jurorDAO;		public JurorServiceImpl(){		System.out.println("Constructor.!");	}	@Autowired	public void setJurorDAO(JurorDAO jurorDAO){		this.jurorDAO = jurorDAO;	}		@Override	public List<JurorVO> getJurorList() throws SQLException {		// TODO Auto-generated method stub		return jurorDAO.getJurorList();	}	@Override	public void deleteJuror(String userId) throws SQLException {		// TODO Auto-generated method stub		jurorDAO.deleteJuror(userId);			}	@Override	public JurorVO getJuror(String userId) throws SQLException {		// TODO Auto-generated method stub		return jurorDAO.getJuror(userId);	}	@Override	public void addJuror(JurorVO juror) throws SQLException {		// TODO Auto-generated method stub		jurorDAO.addJuror(juror);			}}