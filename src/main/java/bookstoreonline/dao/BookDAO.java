package bookstoreonline.dao;

import bookstoreonline.JDBCConnection.JDBCConnection;
import bookstoreonline.model.Book;
import bookstoreonline.model.Category;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.util.*;

public class BookDAO {
	private JDBCConnection jdbcConnection = new JDBCConnection();
	public List<Book> listAllBooks() throws ParseException, IOException{
		List<Book> books = new ArrayList<>();
		String sql = "select * from tblbook inner join tblcategory on tblbook.categoryid = tblcategory.cate_id ORDER BY tblbook.book_id";
		try(Connection connection = jdbcConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ResultSet rs = ps.executeQuery();
			System.out.println(ps);
			while(rs.next()) {
				int book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String dor = rs.getString("dor");
				String publisher = rs.getString("publisher");
				int page = rs.getInt("page");
				float price = rs.getFloat("price");
				int amount = rs.getInt("amount");
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
                
				int categoryid = rs.getInt("categoryid");
				int cate_id = rs.getInt("cate_id");
				String name = rs.getString("name");
				System.out.println(name);
				Category category = new Category(cate_id,name);
				Book book = new Book(book_id,title,author,dor,publisher,page,price,amount,base64Image,categoryid,category);
				books.add(book);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	public Book selectBook(int book_id) throws IOException, ParseException {
		Book book = null;
		Category category = null;
		String SELECT_BOOK_BY_ID = "select * from tblbook inner join tblcategory on tblbook.categoryid = tblcategory.cate_id"+" and tblbook.book_id = ?";
		try(Connection connection = jdbcConnection.getConnection();PreparedStatement ps = connection.prepareStatement(SELECT_BOOK_BY_ID);){
			ps.setInt(1, book_id);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				book = new Book();
				category = new Category();
				String title = rs.getString("title");
				String author = rs.getString("author");
				String dor = rs.getString("dor");
				String publisher = rs.getString("publisher");
				int page = rs.getInt("page");
				String description = rs.getString("description");
				float price = rs.getFloat("price");
				int amount = rs.getInt("amount");
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
                int categoryid = rs.getInt("categoryid");
                int cate_id = rs.getInt("cate_id");
                String name = rs.getString("name");
                category.setCate_id(cate_id);
                category.setName(name);
                book.setBook_id(book_id);
                book.setTitle(title);
                book.setAuthor(author);
                book.setDor(dor);
                book.setPublisher(publisher);
                book.setPage(page);
                book.setDescription(description);
                book.setPrice(price);
                book.setAmount(amount);
                book.setPhoto(base64Image);
                book.setCategoryid(categoryid);
                book.setCategory(category);
				}
			}catch(SQLException e) {
				e.printStackTrace();
		}
		return book;
	}
	public int insertBook(String title,String author,String dor,String publisher,int page,String description,float price,int amount,InputStream file,String name) {
		int row = 0;
		String INSERT_BOOK_SQL = "INSERT INTO tblbook(title,author,dor,publisher,page,description,price,amount,photo,categoryid)"
				+ " values (?,?,?,?,?,?,?,?,?,(select tblcategory.cate_id from tblcategory where tblcategory.name=?))";
		try(Connection connection = jdbcConnection.getConnection();PreparedStatement ps = connection.prepareStatement(INSERT_BOOK_SQL);){
			ps.setString(1,title);
			ps.setString(2, author);
			ps.setString(3, dor);
			ps.setString(4, publisher);
			ps.setInt(5,page);
			ps.setString(6, description);
			ps.setFloat(7, price);
			ps.setInt(8, amount);
			if(file!=null) {
				ps.setBlob(9, file);
			}
			ps.setString(10,name);
			System.out.println(ps);
			row = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	public int deleteBook(int book_id) {
		Connection connection = jdbcConnection.getConnection();
		PreparedStatement ps = null;
		String DELETE_BOOK_SQL = "delete from tblbook where tblbook.book_id=?";
		try {
			ps = connection.prepareStatement(DELETE_BOOK_SQL);
			ps.setInt(1, book_id);
			ps.executeUpdate();
			return 1;
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public boolean exists(int id) {
		Connection connection = jdbcConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT book_id FROM tblbook " + "WHERE book_id = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			return rs.next();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public int updateBook(String title,String author,String dor,String publisher,int page,String description,float price,int amount, InputStream file,String name,int book_id) {
		int rowUpdate = 0;
		String UPDATE_BOOK_SQL = "UPDATE tblbook SET tblbook.title=?,tblbook.author=?,tblbook.dor=?,tblbook.publisher=?,"+
		"tblbook.page=?,tblbook.description=?,tblbook.price=?,tblbook.amount=?,tblbook.photo=?,tblbook.categoryid = (select tblcategory.cate_id from tblcategory where tblcategory.name=?) "+
				"WHERE tblbook.book_id=?";
		try(Connection connection = jdbcConnection.getConnection();PreparedStatement ps = connection.prepareStatement(UPDATE_BOOK_SQL);){
			ps.setString(1, title);
			ps.setString(2, author);
			ps.setString(3, dor);
			ps.setString(4, publisher);
			ps.setInt(5,page);
			ps.setString(6, description);
			ps.setFloat(7, price);
			ps.setInt(8,amount);
			if(file!=null) {
				ps.setBlob(9, file);
			}
			ps.setString(10,name);
			ps.setInt(11, book_id);
			System.out.println(ps);
			rowUpdate = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rowUpdate;
	}
	
	public Book getLast() throws IOException {
		Book book = null;
		Category category = null;
		String SELECT_BOOK_BY_ID = "select * from tblbook inner join tblcategory on tblbook.categoryid = tblcategory.cate_id and tblbook.book_id = (select max(book_id) from tblbook);";
		try(Connection connection = jdbcConnection.getConnection();PreparedStatement ps = connection.prepareStatement(SELECT_BOOK_BY_ID);){
			
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				book = new Book();
				category = new Category();
				int book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String dor = rs.getString("dor");
				String publisher = rs.getString("publisher");
				int page = rs.getInt("page");
				String description = rs.getString("description");
				float price = rs.getFloat("price");
				int amount = rs.getInt("amount");
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
                int categoryid = rs.getInt("categoryid");
                int cate_id = rs.getInt("cate_id");
                String name = rs.getString("name");
                category.setCate_id(cate_id);
                category.setName(name);
                book.setBook_id(book_id);
                book.setTitle(title);
                book.setAuthor(author);
                book.setDor(dor);
                book.setPublisher(publisher);
                book.setPage(page);
                book.setDescription(description);
                book.setPrice(price);
                book.setAmount(amount);
                book.setPhoto(base64Image);
                book.setCategoryid(categoryid);
                book.setCategory(category);
				}
			}catch(SQLException e) {
				e.printStackTrace();
		}
		return book;
	}
	
	public List<Book> getProductByCategoryId(int cate_id) throws ParseException, IOException{
		List<Book> books = new ArrayList<>();
		String sql = "select * from tblbook inner join tblcategory on tblbook.categoryid=tblcategory.cate_id and tblcategory.cate_id=?";
		try(Connection connection = jdbcConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, cate_id);
			ResultSet rs = ps.executeQuery();
			System.out.println(ps);
			while(rs.next()) {
				int book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String dor = rs.getString("dor");
				String publisher = rs.getString("publisher");
				int page = rs.getInt("page");
				float price = rs.getFloat("price");
				int amount = rs.getInt("amount");
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
                
				int categoryid = rs.getInt("categoryid");
				
				String name = rs.getString("name");
				System.out.println(name);
				Category category = new Category(cate_id,name);
				Book book = new Book(book_id,title,author,dor,publisher,page,price,amount,base64Image,categoryid,category);
				books.add(book);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	
	public List<Book> getProductByTitle(String txtSearch) throws ParseException, IOException{
		List<Book> books = new ArrayList<>();
		String sql = "select * from tblbook inner join tblcategory on tblbook.categoryid=tblcategory.cate_id and tblbook.title like ?;";
		try(Connection connection = jdbcConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setString(1, "%"+txtSearch+"%");
			ResultSet rs = ps.executeQuery();
			System.out.println(ps);
			while(rs.next()) {
				int book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String dor = rs.getString("dor");
				String publisher = rs.getString("publisher");
				int page = rs.getInt("page");
				float price = rs.getFloat("price");
				int amount = rs.getInt("amount");
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
                
				int categoryid = rs.getInt("categoryid");
				int cate_id = rs.getInt("cate_id");
				String name = rs.getString("name");
				System.out.println(name);
				Category category = new Category(cate_id,name);
				Book book = new Book(book_id,title,author,dor,publisher,page,price,amount,base64Image,categoryid,category);
				books.add(book);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
}
