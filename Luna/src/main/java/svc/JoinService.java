package svc;

import java.sql.Connection;
import java.sql.SQLException;

import dao.SignInDAO;
import db.JdbcUtil;
import vo.Customer_bean;
import vo.Join_bean;

public class JoinService {
	private SignInDAO signInDao = SignInDAO.getInstance();
	public void join(Join_bean cusbean) {
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);
			
			Customer_bean cusmo = signInDao.selectById(con, cusbean.getCUS_ID());
			if (cusmo != null) {
				JdbcUtil.rollback(con);
			}
			
			signInDao.insert(con, cusbean);
			con.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(con);
			
		} finally { 
			JdbcUtil.close(con);
		}
	}
}