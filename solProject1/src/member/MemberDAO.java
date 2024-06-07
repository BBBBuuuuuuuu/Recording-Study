package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public MemberDAO() {
		con = DBcon.getinstance();
	}
	
	public boolean checkID(String id) {
		String query = "SELECT COUNT(*) FROM member WHERE userid = ?";
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				if(rs.getInt("COUNT(*)") == 0) {
					return false;
				}
				else {
					return true;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(rs);
			DBcon.close(ps);
		}
		return false;
	}
	
	public void setCoinEA(MemberVO loginInfo) {
		String query = "SELECT * FROM usercoin WHERE userid = ?";
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, loginInfo.getId());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int[] coinArr = {rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)};
				loginInfo.setCoinArr(coinArr);
			}else System.out.println("아직 보유하신 칩이 없습니다."); 
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(rs);
			DBcon.close(ps);
		}
	}
	
	public boolean checkPW(String id, String pw) {
		boolean res = false;
		String query = "SELECT * FROM member WHERE userid = ?";
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("pwd").equals(pw)) {
					res = true;
				}
				else {
					res = false;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(rs);
			DBcon.close(ps);
		}
		return res;
	}
	
	public MemberVO loginRes(String id, String pw) {
		MemberVO loginInfo = null;
		String query = "SELECT * FROM member WHERE userid = ? && pwd = ?";
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, id);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				loginInfo = new MemberVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
			}else System.out.println("존재하지 않는 아이디입니다.");
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(rs);
			DBcon.close(ps);
		}
		
		return loginInfo;
	}
	
	public int inputMember(String id, String pw, String name, String phone) {
		int i = 0;
		String query = "INSERT INTO member(userid, pwd, name, phone) VALUES(?, ?, ?, ?)";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, id);
			ps.setString(2, pw);
			ps.setString(3, name);
			ps.setString(4, phone);
			i = ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(ps);
		}
		return i;
	}
	
	public int searchMember(String id, String newid, String newpw, String newname, String newPhone) {
		String query = "UPDATE member SET userid = ?, pwd = ?, name = ?, phone = ?WHERE userid = ?";
		int result = 0;
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(5, id);
			ps.setString(1, newid);
			ps.setString(2, newpw);
			ps.setString(3, newname);
			ps.setString(4, newPhone);
			result = ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(ps);
		}
		return result;
	}
	
	public int updateMember(String id, String newid, String newpw, String newname, String newPhone) {
		String query = "UPDATE member SET userid = ?, pwd = ?, name = ?, phone = ? WHERE userid = ?";
		int result = 0;
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(5, id);
			ps.setString(1, newid);
			ps.setString(2, newpw);
			ps.setString(3, newname);
			ps.setString(4, newPhone);
			result = ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(ps);
		}
		return result;
	}
	
	public void deleteMember(String id) {
		String query = "DELETE FROM member WHERE userid = ?";
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, id);
			int result = ps.executeUpdate();
			
			if(result == 0) {
				System.out.println("회원 삭제 실패");
			}
			else {
				System.out.println("삭제되었습니다.");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(rs);
		}
	}
	
	public int chooseAcc(MemberVO loginInfo, int accCode) {
		String query = "UPDATE member SET accountCode = ? WHERE userid = ?";
		int result = 0;
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, accCode);
			ps.setString(2, loginInfo.getId());
			result =ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(ps);
		}
		return result;
	}
	
	public String searchID(String name) {
		String query = "SELECT userid FROM member WHERE name = ?";
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, name);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(rs);
			DBcon.close(rs);
		}
		return null;
	}
	
	public String searchPW(String id) {
		String query = "SELECT pwd FROM member WHERE userid = ?";
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(rs);
			DBcon.close(rs);
		}
		return null;
	}
	
	public ArrayList<TransVO> printTransInfo(MemberVO loginInfo) {
		TransVO trans = null;
		ArrayList<TransVO> list = new ArrayList<TransVO>();
		String query = "SELECT * FROM transinfo where accountCode = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, loginInfo.getAccCode());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				trans = new TransVO(rs.getInt("accountCode"), rs.getString("state"), rs.getInt("price"), rs.getInt("balance"), rs.getString("tDate"));
				list.add(trans);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.close(rs);
			DBcon.close(ps);
		}
		return list;
	}
}
