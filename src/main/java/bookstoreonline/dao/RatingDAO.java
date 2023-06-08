package bookstoreonline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bookstoreonline.JDBCConnection.JDBCConnection;
import bookstoreonline.model.Rating;

public class RatingDAO {
	private JDBCConnection jdbcConnection = new JDBCConnection();
	public int insertComment(String content,String datepost,int rating, int user_id,int book_id) {
		int row = 0;
		String INSERT_COMMENT_SQL = "insert into tblrating(content,date_post,rating,user_id,b_id) values (?,?,?,?,?)";
		try(Connection connection = jdbcConnection.getConnection();PreparedStatement ps = connection.prepareStatement(INSERT_COMMENT_SQL);){
			ps.setString(1, content);
			ps.setString(2, datepost);
			ps.setInt(3, rating);
			ps.setInt(4, user_id);
			ps.setInt(5, book_id);
			System.out.println(ps);
			row = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
}
