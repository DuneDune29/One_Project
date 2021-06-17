package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.sql.DataSource;

import db.JdbcUtil;
import vo.Customer_bean;
import vo.Join_bean;

public class SignInDAO {

	DataSource ds;
	   Connection con;

	   private static SignInDAO signinDAO;

	   private SignInDAO() {
	   }

	   public static SignInDAO getInstance() {
	      if (signinDAO == null) {
	         signinDAO = new SignInDAO();
	      }
	      return signinDAO;
	   }

	   public void setConnection(Connection con) {
	      this.con = con;
	   }
	      
	   public Customer_bean selectById(Connection con, String id) throws SQLException {
	      
	       PreparedStatement pstmt = null;
	         ResultSet rs = null;
	         try {
	            pstmt = con.prepareStatement(
	                  "select * from CUSTOMER where CUS_ID = ?");
	            pstmt.setString(1, id);
	            rs = pstmt.executeQuery();
	            Customer_bean member = null;
	            if (rs.next()) {
	               member = new Customer_bean(
	                     rs.getString("CUS_ID"),
	                     rs.getString("CUS_PWD"),
	                     rs.getString("CUS_NAME"),
	                     rs.getString("CUS_ADDR"),
	                     rs.getString("CUS_TEL"),
	                     toDate(rs.getTimestamp("CUS_REGDATE")));
	            }
	            return member;
	         } finally {
	            JdbcUtil.close(rs);
	            JdbcUtil.close(pstmt);
	         }      
	   }
	      
	   private Date toDate(Timestamp date) {
	      return date == null ? null : new Date(date.getTime());
	   }
	public void insert(Connection con, Join_bean CusBean) throws SQLException {
		try (PreparedStatement pstmt =
				con.prepareStatement("insert into customer (CUS_ID, CUS_PWD, CUS_NAME, CUS_ADDR, CUS_TEL) values(?,?,?,?,?)")) {
			pstmt.setString(1, CusBean.getCUS_ID());
			pstmt.setString(2, CusBean.getCUS_PWD());
			pstmt.setString(3, CusBean.getCUS_NAME());
			pstmt.setString(4, CusBean.getCUS_ADDR());
			pstmt.setString(5, CusBean.getCUS_TEL());
			pstmt.executeUpdate();
		}
	}
	
	public void update(Connection con, Customer_bean CusBean) throws SQLException {
		try (PreparedStatement pstmt =
				con.prepareStatement("update customer set CUS_PWD = ?, CUS_NAME = ?, CUS_ADDR = ?, CUS_TEL = ? where CUS_ID = ?")) {
			pstmt.setString(1, CusBean.getCUS_PWD());
			pstmt.setString(2, CusBean.getCUS_NAME());
			pstmt.setString(3, CusBean.getCUS_ADDR());
			pstmt.setString(4, CusBean.getCUS_TEL());
			pstmt.setString(5, CusBean.getCUS_ID());
			pstmt.executeUpdate();
		}
	}
	public boolean idCheck(Connection con, Customer_bean CusBean) throws SQLException { 
		try (PreparedStatement pstmt = 
				con.prepareStatement("SELECT * FROM customer WHERE CUS_ID = ?")) {
			pstmt.setString(1, CusBean.getCUS_ID()); 
			ResultSet rs = pstmt.executeQuery(); 
			if (rs.next()) { 
				return false; 
			} else { 
				return true; 
			} 
		}
	}
}