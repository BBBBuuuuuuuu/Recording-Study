package casino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.DBcon;
import member.MemberVO;

public class GameDAO {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public GameDAO() {
		con = DBcon.getinstance();
	}
	
	public void betCoin(MemberVO loginInfo, int[] coinEA) {
		CasinoService cs = new CasinoService();
		String query = "INSERT INTO bettingtbl VALUES(null, ?, ?, ?, ?, ?, ?)";
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, loginInfo.getId());
			ps.setInt(2, coinEA[0]);
			ps.setInt(3, coinEA[1]);
			ps.setInt(4, coinEA[2]);
			ps.setInt(5, coinEA[3]);
			ps.setInt(6, cs.checkCoinPrice(coinEA)/1000);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(ps);
		}
	}
	
	public void deleteBetTbl(MemberVO loginInfo) {
		String query = "DELETE FROM bettingtbl WHERE userid = ?";
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, loginInfo.getId());
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(ps);
		}
	}
	public void loseCoin(MemberVO loginInfo, int[] betCoinEA) {
		String query = "UPDATE usercoin SET chip500 = ?, chip100 = ?, chip50 = ?, chip10 = ? WHERE userid = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, loginInfo.getCoinArr()[0] - betCoinEA[0]);
			ps.setInt(2, loginInfo.getCoinArr()[1] - betCoinEA[1]);
			ps.setInt(3, loginInfo.getCoinArr()[2] - betCoinEA[2]);
			ps.setInt(4, loginInfo.getCoinArr()[3] - betCoinEA[3]);
			ps.setString(5, loginInfo.getId());
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(ps);
		}
	}
	
	public void loseGame(int historyNum) {
		String query1 = "INSERT INTO gamehistory (result, historyNum) VALUES ('패배', ?)";
		String query2 = "DELETE FROM bettingtbl";
		
		try {
			ps = con.prepareStatement(query1);
			ps.setInt(1, historyNum);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(ps);
		}
		try {
			ps = con.prepareStatement(query2);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(ps);
		}
	}

	public void winGame(int historyNum) {
		String query1 = "INSERT INTO gamehistory (result, historyNum) VALUES ('승리', ?)";
		String query2 = "DELETE FROM bettingtbl";
		
		try {
			ps = con.prepareStatement(query1);
			ps.setInt(1, historyNum);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(ps);
		}
		
		try {
			ps = con.prepareStatement(query2);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(ps);
		}
	}
	
	public void getCoin(MemberVO loginInfo, int[] coinEA) {
		String query = "UPDATE usercoin SET chip500 = ?, chip100 = ?, chip50 = ?, chip10 = ? WHERE userid = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, loginInfo.getCoinArr()[0] + coinEA[0]);
			ps.setInt(2, loginInfo.getCoinArr()[1] + coinEA[1]);
			ps.setInt(3, loginInfo.getCoinArr()[2] + coinEA[2]);
			ps.setInt(4, loginInfo.getCoinArr()[3] + coinEA[3]);
			ps.setString(5, loginInfo.getId());
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(ps);
		}
	}
	
	
}
