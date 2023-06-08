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
 * Servlet implementation class CategoryController
 */
@WebServlet("/category")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;
    private CategoryDAO categoryDAO;
    public void init() {
        bookDAO = new BookDAO();
        categoryDAO = new CategoryDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cate_id = Integer.parseInt(request.getParameter("cate_id"));
		try {
			List<Book> listBookByCateId = bookDAO.getProductByCategoryId(cate_id);
			List<Category> listCategory = categoryDAO.listAllCategory();
			Book lastbook = bookDAO.getLast();
			request.setAttribute("listBook", listBookByCateId);
			request.setAttribute("listCategory", listCategory);
			request.setAttribute("p", lastbook);
			request.setAttribute("tag",cate_id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/customer/home-customer.jsp");
			dispatcher.forward(request, response);
		} catch (ParseException | IOException e) {
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
