package membership;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;

public class MembershipService {

	MembershipDAO mvshipDao = new MembershipDAO();

	public LoginVO selectByID(String id) {
		try (Connection con = ConnectionProvider.getConnection()) {
			LoginVO loginInfo = mvshipDao.selectByID(con, id);

			if (loginInfo != null) {
				return loginInfo;
			} else
				System.out.println("selectByID Error");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	public Boolean checkPWD(String id, String pwd) {
		try (Connection con = ConnectionProvider.getConnection()) {
			String slctPWD = mvshipDao.checkPWD(con, id);
			if (slctPWD.equals(pwd)) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public MembershipInfo buyMembership(LoginVO loginInfo, String month) {
		MembershipInfo mvshipInfo = null;
		try (Connection con = ConnectionProvider.getConnection()) {
			con.setAutoCommit(false);
			int balance = mvshipDao.checkBalance(con, loginInfo);
			int price = mvshipDao.checkPrice(con, month);

			if (balance < price) {
				con.commit();
				return null;
			}

			mvshipInfo = mvshipDao.buyMembership(balance, price, month, loginInfo, con);
			if (mvshipInfo == null) {
				return null;
			}
			con.commit();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return mvshipInfo;
	}

}
