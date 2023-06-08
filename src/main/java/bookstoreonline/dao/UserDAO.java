package bookstoreonline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import bookstoreonline.JDBCConnection.JDBCConnection;
import bookstoreonline.model.User;

public class UserDAO {
	private JDBCConnection jdbcConnection = new JDBCConnection();
	public int registerUser(User user) throws ClassNotFoundException  {
		int result = 0;
		String INSERT_USER_SQL = "INSERT INTO tbluser" + "(username,password,name,dob,email,dt,isadmin) VALUES "+"(?,?,?,?,?,?,?)";
		try(Connection connection = jdbcConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(INSERT_USER_SQL)){
			ps.setString(1,user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getDob());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getDt());
			ps.setInt(7, user.isCheckadmin()?1:0);
			System.out.println(ps);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean existUsername(String username) {
		Connection connection = jdbcConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT username FROM tbluser where username=?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			return rs.next();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public User validate(String username,String password) {
		User user = new User();
		try(Connection connection = jdbcConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM tbluser WHERE username = ? and password = ?");){
		ps.setString(1, username);
		ps.setString(2, password);
		System.out.println(ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setCheckadmin((rs.getInt("isAdmin")==1)?true:false);
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	public List<User> listAllUsers() throws ParseException{
		List<User> users = new ArrayList<>();
		String sql = "select * from tbluser";
		try(Connection connection = jdbcConnection.getConnection();PreparedStatement ps = connection.prepareStatement(sql);){
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String dob = rs.getString("dob");
				String email = rs.getString("email");
				String dt = rs.getString("dt");
				boolean isadmin = rs.getInt("isAdmin")==1?true:false;
				users.add(new User(id,username,password,name,dob,email,dt,isadmin));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
}
