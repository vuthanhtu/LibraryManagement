package bookstoreonline.web.admincontroller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstoreonline.dao.BookDAO;
import bookstoreonline.dao.CategoryDAO;
import bookstoreonline.model.Category;


/**
 * Servlet implementation class AddNewBookController
 */
@WebServlet("/admin/book/new")
 
public class AddNewBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;
    public AddNewBookController() {
        bookDAO = new BookDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CategoryDAO categoryDAO = new CategoryDAO();
			List<Category> category = categoryDAO.listAllCategory();
			RequestDispatcher dispatcher1 = request.getRequestDispatcher("/admin/book-detail.jsp");
			request.setAttribute("listCategory", category);
			dispatcher1.forward(request, response);
		}catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
