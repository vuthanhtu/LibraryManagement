package bookstoreonline.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import bookstoreonline.JDBCConnection.JDBCConnection;
import bookstoreonline.model.Category;
public class CategoryDAO {
	private JDBCConnection jdbcConnection = new JDBCConnection();
	public List<Category> listAllCategory(){
		List<Category> category = new ArrayList<>();
		String sql = "select * from tblcategory ORDER BY name";
		try(Connection connection = jdbcConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("cate_id");
				String name = rs.getString("name");
				category.add(new Category(id,name));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return category;
	}
}
