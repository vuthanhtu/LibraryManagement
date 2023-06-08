package bookstoreonline.JDBCConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
	private String jdbcURL = "jdbc:mysql://localhost:3306/bookstore-online-database";
	private String jdbcUsername = "root";
	private String jdbcPassword = "vuthanhtu";
	public JDBCConnection() {
		
	}
	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
