package bookstoreonline.web.admincontroller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstoreonline.dao.BookDAO;

/**
 * Servlet implementation class DeleteBookController
 */
@WebServlet("/admin/book/delete")
public class DeleteBookController extends HttpServlet {
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
			deleteBook(request,response);
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
	private void deleteBook(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		bookDAO.deleteBook(id);
		String link = "baitaplon2-vuthanhtu-b19dcat165/admin/book";
		response.sendRedirect(link);
	}
}
