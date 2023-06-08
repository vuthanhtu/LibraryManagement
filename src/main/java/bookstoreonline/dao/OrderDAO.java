package bookstoreonline.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import bookstoreonline.JDBCConnection.JDBCConnection;
import bookstoreonline.model.Book;
import bookstoreonline.model.Order;

public class OrderDAO {
	private JDBCConnection jdbcConnection = new JDBCConnection();
	public OrderDAO() {
		
	}
	public boolean insertOrder(Order order) {
		boolean result = false;
		String query = "insert into tblorder(p_id,u_id,o_quantity,o_date) values(?,?,?,?)";
		try(Connection connection = jdbcConnection.getConnection();PreparedStatement ps=connection.prepareStatement(query)){
			ps.setInt(1, order.getBook_id());
			ps.setInt(2, order.getUid());
			ps.setInt(3, order.getQuantity());
			ps.setString(4, order.getDate());
			ps.executeUpdate();
			System.out.println(ps);
			result = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public List<Order> userOrders(int id) throws IOException, ParseException{
		List<Order> list = new ArrayList<>();
		String query = "select * from tblorder where u_id=? order by tblorder.o_id desc";
		try(Connection connection = jdbcConnection.getConnection();PreparedStatement ps = connection.prepareStatement(query);){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Order order = new Order();
				int pId = rs.getInt("p_id");
				BookDAO bookDAO = new BookDAO();
				Book book = bookDAO.selectBook(pId);
				order.setOrder_id(rs.getInt("o_id"));
				order.setBook_id(pId);
				order.setTitle(book.getTitle());
				order.setAuthor(book.getAuthor());
				order.setPrice(book.getPrice()*rs.getInt("o_quantity"));
				order.setQuantity(rs.getInt("o_quantity"));
				order.setDate(rs.getString("o_date"));
				list.add(order);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	public boolean cancelOrder(int id)  {
		boolean result = false;
		String query = "delete from tblorder where o_id=?";
		try(Connection connection = jdbcConnection.getConnection();PreparedStatement ps = connection.prepareStatement(query)){
			ps.setInt(1, id);
			ps.executeUpdate();
			result = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
