package comment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import member.model.Member;

public class CommentService {

	private CommentDAO comDao = new CommentDAO();
	
	public void writeComment(CommentInfo comInfo) {
		int res = 0;
		try(Connection con = ConnectionProvider.getConnection()){
			
			res = comDao.insertComment(comInfo, con);
			if(res == 0) {
				System.out.println("insert 실패");
			}else {
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<CommentInfo> selectComment(int articleNum) {
		List<CommentInfo> comments = new ArrayList<CommentInfo>();
		try(Connection con= ConnectionProvider.getConnection()){
			comments = comDao.selectComment(articleNum, con);
			if(comments.isEmpty()) {
				System.out.println("댓글없음");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return comments;
	}
	
	public Member checkID(String id, String pwd) {
		Member mem = null;
		try {
			mem = comDao.selectMember(id);
		} catch (SQLException e) {
			System.out.println("예외:"+e.getMessage());
		}
		if(mem != null) {
			if(mem.getId().equals(id)&& mem.getPassword().equals(pwd)) {
				return mem;
			}
		}
		return null;
		
		
	}
	
	
}
