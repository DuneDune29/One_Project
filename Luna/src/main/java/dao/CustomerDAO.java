package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.ClassBean;
import vo.User;

public class CustomerDAO {
	DataSource ds;
	Connection con;

	private static CustomerDAO customerDAO;

	private CustomerDAO() {
	}

	public static CustomerDAO getInstance() {
		if (customerDAO == null) {
			customerDAO = new CustomerDAO();
		}
		return customerDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int selectClassListCount(User authUser) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			System.out.println("getConnection");
			pstmt = con.prepareStatement("SELECT count(*) FROM CLASS WHERE CL_WRITER_ID = ?");
			pstmt.setString(1, authUser.getId());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getListCount : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	public ArrayList<ClassBean> selectMyClassList(User authUser, int page, int limit) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from CLASS  where CL_WRITER_ID = ? order by CL_ID desc limit ?,5";
		ArrayList<ClassBean> MyClassList = new ArrayList<ClassBean>();
		ClassBean class1 = null;
		int startrow = (page - 1) * 5;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, authUser.getId());
			pstmt.setInt(2, startrow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				class1 = new ClassBean();
				class1.setCL_ID(rs.getInt("CL_ID"));
				class1.setCL_NAME(rs.getString("CL_NAME"));
				class1.setCL_CONTENT(rs.getString("CL_CONTENT"));
				class1.setCL_START_DATE(rs.getDate("CL_START_DATE"));
				class1.setCL_END_DATE(rs.getDate("CL_END_DATE"));
				class1.setCL_IMG_PATH(rs.getString("CL_IMG_PATH"));
				MyClassList.add(class1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return MyClassList;

	}

	public int selectParticipateListCount(User authUser) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			System.out.println("getConnection");
			pstmt = con.prepareStatement("SELECT count(*) FROM CLASS WHERE CL_WRITER_ID = ?");
			pstmt.setString(1, authUser.getId());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getListCount : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	
	public ArrayList<ClassBean> selectMyParticipateList(User authUser, int page, int limit) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from CLASS  where CL_WRITER_ID = ? order by CL_ID desc limit ?,5";
		ArrayList<ClassBean> MyClassList = new ArrayList<ClassBean>();
		ClassBean class1 = null;
		int startrow = (page - 1) * 5;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, authUser.getId());
			pstmt.setInt(2, startrow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				class1 = new ClassBean();
				class1.setCL_ID(rs.getInt("CL_ID"));
				class1.setCL_NAME(rs.getString("CL_NAME"));
				class1.setCL_CONTENT(rs.getString("CL_CONTENT"));
				class1.setCL_START_DATE(rs.getDate("CL_START_DATE"));
				class1.setCL_END_DATE(rs.getDate("CL_END_DATE"));
				class1.setCL_IMG_PATH(rs.getString("CL_IMG_PATH"));
				MyClassList.add(class1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return MyClassList;

	}
}
