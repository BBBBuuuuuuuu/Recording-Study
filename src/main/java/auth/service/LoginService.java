package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class LoginService {

	private MemberDao memberDao = new MemberDao();

	public User login(String id, String password) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			Member member = memberDao.selectById(conn, id);
			if (member == null) {
				throw new LoginFailException();
			}
			if (!member.matchPassword(password)) {
				throw new LoginFailException();
			}
			return new User(member.getId(), member.getName());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void checkMembership(User user) {
		String month = null;
		try(Connection con = ConnectionProvider.getConnection()){
			month = memberDao.selectMembership(con, user);
			if(month != null) {
				user.setMonth(month);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
