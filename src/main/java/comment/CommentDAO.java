package comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.model.Member;

public class CommentDAO {

	public int insertComment(CommentInfo comInfo, Connection con) throws SQLException {
		PreparedStatement ps = null;
		String query = "insert into comment (article_no, comment_id, content, regdate) values (?, ?, ?, ?)";
		int res = 0;

		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, comInfo.getArticle_no());
			ps.setString(2, comInfo.getId());
			ps.setString(3, comInfo.getContent());
			ps.setTimestamp(4, toTimestamp(comInfo.getRegdate()));
			res = ps.executeUpdate();

			if (res > 0) {
				return res;
			}
		} finally {
			JdbcUtil.close(ps);
		}

		return 0;
	}

	public List<CommentInfo> selectComment(int articleNum, Connection con) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CommentInfo> comments = new ArrayList<CommentInfo>();
		String query = "SELECT * FROM comment WHERE article_no = ?";

		ps = con.prepareStatement(query);
		ps.setInt(1, articleNum);
		rs = ps.executeQuery();

		while (rs.next()) {
			comments.add(new CommentInfo(rs.getString("comment_id"), rs.getString("content"), articleNum,
					toDate(rs.getTimestamp("regdate"))));
		}
		
		return comments;
		
	}

	public Member selectMember(String id) throws SQLException{
		Connection con = ConnectionProvider.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Member mem = new Member();
		String query = "select memberid, password from member where memberid = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				mem.setId(rs.getString("memberid"));
				mem.setPassword(rs.getString("password"));
				return mem;
			}
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
			JdbcUtil.close(con);
		}
		return null;
	}
	
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
}
