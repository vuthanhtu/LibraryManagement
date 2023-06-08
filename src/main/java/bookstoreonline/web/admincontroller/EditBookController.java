package bookstoreonline.web.admincontroller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstoreonline.dao.BookDAO;
import bookstoreonline.dao.CategoryDAO;
import bookstoreonline.model.Book;
import bookstoreonline.model.Category;

/**
 * Servlet implementation class EditBookController
 */
@WebServlet("/admin/book/edit")
public class EditBookController extends HttpServlet {
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
			showEditForm(request,response);
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
	private void showEditForm(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException, ParseException{
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		System.out.println(book_id);
		Book existingBook = bookDAO.selectBook(book_id);
		System.out.println(existingBook.getBook_id());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/book-detail.jsp");
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> category = categoryDAO.listAllCategory();
		request.setAttribute("book", existingBook);
		request.setAttribute("listCategory",category);
		dispatcher.forward(request, response);
	}
}
