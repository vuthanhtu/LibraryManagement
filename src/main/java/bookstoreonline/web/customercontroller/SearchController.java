package bookstoreonline.web.customercontroller;

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
 * Servlet implementation class SearchController
 */
@WebServlet("/search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;
    private CategoryDAO categoryDAO;
    public void init() {
        bookDAO = new BookDAO();
        categoryDAO = new CategoryDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String txtSearch = request.getParameter("txt");
			List<Book> listBookByTitle = bookDAO.getProductByTitle(txtSearch);
			List<Category> listCategory = categoryDAO.listAllCategory();
			Book lastbook = bookDAO.getLast();
			request.setAttribute("listBook", listBookByTitle);
			request.setAttribute("listCategory", listCategory);
			request.setAttribute("p", lastbook);
			request.setAttribute("txtS", txtSearch);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/customer/home-customer.jsp");
			dispatcher.forward(request, response);
		}catch (IOException | ParseException e) {
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

}
