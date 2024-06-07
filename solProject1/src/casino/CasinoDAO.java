package casino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.DBcon;
import member.MemberVO;

public class CasinoDAO {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public CasinoDAO() {
		con = DBcon.getinstance();
	}
	
	public boolean checkBlackList(String id) {
		String query = "SELECT COUNT(*) FROM blacklist WHERE userid = ?";
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				if(rs.getInt("COUNT(*)") == 0) {
					return true;
				}
				else {
					return false;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(rs);
			DBcon.close(ps);
		}
		return false;
	}
	
	public boolean chooseAcc(MemberVO loginInfo, int accCode, String pwd) {
		boolean res = false;
		String query = "SELECT accountCode, pwd FROM member WHERE userid = ?";
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, loginInfo.getId());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				loginInfo.setAccCode(accCode);
				res = true;
			} else {
				System.out.println("일치하는 계좌가 없습니다.");
				res = false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBcon.close(rs);
			DBcon.close(ps);
		}
		return res;
	}
	

	public int[] checkCoinEA(MemberVO loginInfo) {
		String query = "SELECT * FROM usercoin WHERE userid = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, loginInfo.getId());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int[] res = {rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)};
				return res;
			}else {
				System.out.println("코인 정보 불러오기 실패");
				return null;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(rs);
			DBcon.close(ps);
		}
		return null;
	}
	
	public int checkAccBalance(MemberVO loginInfo) {
		int balance = 0;
		String query = "SELECT balance FROM account WHERE accountCode = ?";
		
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, loginInfo.getAccCode());
			rs = ps.executeQuery();
			
			rs.next();
			balance = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(rs);
			DBcon.close(ps);
		}
		return balance;
	}
	
	public void accountToCoin(MemberVO loginInfo, int[] coinEA, int balance, int coinPrice) {
		String coinQuery = "UPDATE usercoin SET chip500 = ?, chip100 = ?, chip50 = ?, chip10 = ? WHERE userid = ?";
		
		try {
			ps = con.prepareStatement(coinQuery);
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
		
		String balanceQuery = "UPDATE account SET balance = ? WHERE accountCode = ?";
		
		try {
			ps = con.prepareStatement(balanceQuery);
			ps.setInt(1, balance - coinPrice);
			ps.setInt(2, loginInfo.getAccCode());
			ps.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(ps);
		}
	}
	
	public void coinToAccount(MemberVO loginInfo, int[] coinEA, int balance, int coinPrice) {
		String coinQuery = "UPDATE usercoin SET chip500 = ?, chip100 = ?, chip50 = ?, chip10 = ? WHERE userid = ?";
		String query = "UPDATE account SET balance = ? WHERE accountCode = ?";
		
		try {
			ps = con.prepareStatement(coinQuery);
			ps.setInt(1, loginInfo.getCoinArr()[0] - coinEA[0]);
			ps.setInt(2, loginInfo.getCoinArr()[1] - coinEA[1]);
			ps.setInt(3, loginInfo.getCoinArr()[2] - coinEA[2]);
			ps.setInt(4, loginInfo.getCoinArr()[3] - coinEA[3]);
			ps.setString(5, loginInfo.getId());
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(ps);
		}
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, coinPrice + balance);
			ps.setInt(2, loginInfo.getAccCode());
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(ps);
		}
	}
}
