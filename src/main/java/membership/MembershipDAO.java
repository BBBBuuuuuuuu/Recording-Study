package membership;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class MembershipDAO {

	public LoginVO selectByID(Connection con, String id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "Select * FROM member WHERE memberid = ?";
		try {
			con = ConnectionProvider.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				LoginVO loginInfo = new LoginVO();
				loginInfo.setId(rs.getString("memberid"));
				loginInfo.setAccNum(rs.getInt("accNum"));
				loginInfo.setGender(rs.getString("gender"));
				return loginInfo;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
			JdbcUtil.close(con);
		}

		return null;
	}

	public String checkPWD(Connection con, String id) {
		String pwd = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT password FROM member WHERE memberid = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				pwd = rs.getString("password");
				return pwd;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	public int checkBalance(Connection con, LoginVO loginInfo) {
		int balance = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "Select balance From account Where accNum = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, loginInfo.getAccNum());
			rs = ps.executeQuery();

			if (rs.next()) {
				balance = rs.getInt(1);
				return balance;
			}
		} catch (SQLException e) {
			JdbcUtil.rollback(con);
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}

		return 0;
	}

	public int checkPrice(Connection con, String month) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select price from membership where month = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, month);
			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			JdbcUtil.rollback(con);
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}

		return 0;
	}

	public MembershipInfo buyMembership(int balance, int price, String month, LoginVO loginInfo, Connection con)
			throws SQLException {
		MembershipInfo mvshipInfo = new MembershipInfo();
		PreparedStatement ps = null;
		String query = "Update account SET balance = ? where accNum = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, balance - price);
			ps.setInt(2, loginInfo.getAccNum());
			int updateRes = ps.executeUpdate();

			if (updateRes == 0) {
				JdbcUtil.rollback(con);
				return null;
			}
		} finally {
			JdbcUtil.close(ps);
		}
		try {
			ps = con.prepareStatement("Insert into mvshipUser values (?, ?)");
			ps.setString(1, loginInfo.getId());
			ps.setString(2, month);
			int insertRes = ps.executeUpdate();

			if (insertRes == 0) {
				JdbcUtil.rollback(con);
				return null;
			}
			mvshipInfo.setId(loginInfo.getId());
			mvshipInfo.setAccNum(loginInfo.getAccNum());
			mvshipInfo.setGender(loginInfo.getGender());
			mvshipInfo.setMonth(month);
			return mvshipInfo;
			
		} finally {
			JdbcUtil.close(ps);
		}
	}

}
