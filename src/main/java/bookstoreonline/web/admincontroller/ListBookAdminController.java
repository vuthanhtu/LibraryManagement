package bookstoreonline.web.admincontroller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstoreonline.dao.BookDAO;
import bookstoreonline.model.Book;

/**
 * Servlet implementation class ListBookAdminController
 */
@WebServlet("/admin/book")
public class ListBookAdminController extends HttpServlet {
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
			listBook(request,response);
		}catch (ServletException | IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void listBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		List<Book> listBook = bookDAO.listAllBooks();
		request.setAttribute("listBook", listBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/book-list.jsp");
        dispatcher.forward(request, response);
	}
}
