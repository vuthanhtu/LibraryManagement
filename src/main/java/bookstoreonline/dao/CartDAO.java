package bookstoreonline.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import bookstoreonline.JDBCConnection.JDBCConnection;
import bookstoreonline.model.Cart;

public class CartDAO {
	private JDBCConnection jdbcConnection = new JDBCConnection();
	public List<Cart> getCartProduct(ArrayList<Cart> cartList) throws IOException{
		List<Cart> carts = new ArrayList<Cart>();
		try {
			if(cartList.size()>0) {
				for(Cart items:cartList) {
					String sql = "select * from tblbook inner join tblcategory on tblbook.categoryid = tblcategory.cate_id"+" and tblbook.book_id = ?";
					Connection connection = jdbcConnection.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setInt(1, items.getBook_id());
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						Cart row = new Cart();
						row.setBook_id(rs.getInt("book_id"));
						row.setTitle(rs.getString("title"));
						row.setAuthor(rs.getString("author"));
						row.setDor(rs.getString("dor"));
						row.setPublisher(rs.getString("publisher"));
						row.setPage(rs.getInt("page"));
						row.setDescription(rs.getString("description"));
						row.setPrice(rs.getFloat("price")*items.getQuantity());
						row.setQuantity(items.getQuantity());
						row.setAmount(rs.getInt("amount"));
						
						Blob blob = rs.getBlob("photo");
						InputStream inputStream = blob.getBinaryStream();
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
						
						
						byte[] buffer = new byte[4096];
						int bytesRead = -1;
						
						while ((bytesRead = inputStream.read(buffer)) != -1) {
		                    outputStream.write(buffer, 0, bytesRead);                  
		                }
						
						byte[] imageBytes = outputStream.toByteArray();
		                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
		                inputStream.close();
		                outputStream.close();
						
		                row.setPhoto(base64Image);
		                row.setCategoryid(rs.getInt("categoryid"));
		                carts.add(row);
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return carts;
	}
	
	public float getTotalCartPrice(ArrayList<Cart> cartList) {
		float sum = 0;
		try {
			if(cartList.size()>0) {
				for(Cart item:cartList) {
					String query = "select price from tblbook where book_id=?";
					Connection connection = jdbcConnection.getConnection();
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setInt(1, item.getBook_id());
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						sum+=rs.getFloat("price")*item.getQuantity();
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return sum;
	}
}
