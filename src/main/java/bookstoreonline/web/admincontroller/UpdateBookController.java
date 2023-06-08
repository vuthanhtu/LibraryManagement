package bookstoreonline.web.admincontroller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bookstoreonline.dao.BookDAO;
import bookstoreonline.model.Book;

/**
 * Servlet implementation class UpdateBookController
 */
@WebServlet("/admin/book/update")
@MultipartConfig(maxFileSize = 16177215)
public class UpdateBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;
    public void init() {
        bookDAO = new BookDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			updateBook(request,response);
		}catch(SQLException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void updateBook(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException{
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String dor = request.getParameter("dor");
		String publisher = request.getParameter("publisher");
		int page = Integer.parseInt(request.getParameter("page"));
		String description = request.getParameter("description");
		float price = Float.parseFloat(request.getParameter("price"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		InputStream inputStream = null;
		Part photo = request.getPart("photo");
		if(photo!=null) {
			System.out.println(photo.getName());
			System.out.println(photo.getSize());
			System.out.println(photo.getContentType());
			inputStream = photo.getInputStream();
		}
		String name = request.getParameter("category");
		int row = bookDAO.updateBook(title,author,dor,publisher,page,description,price,amount,inputStream,name,book_id);
		String link = request.getContextPath()+"/admin/book";
		System.out.println(link);
		response.sendRedirect(link);
	}
}
