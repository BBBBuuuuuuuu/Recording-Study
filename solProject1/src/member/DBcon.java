package member;

import java.sql.*;

public class DBcon {

	static Connection con;
	static PreparedStatement ps;
	static ResultSet rs;
	
	private DBcon() {}
	
	static {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/solproject_1", "root", "mysql");
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getinstance() {
		return con;
	}
	
	public static void close(PreparedStatement ps) {
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
