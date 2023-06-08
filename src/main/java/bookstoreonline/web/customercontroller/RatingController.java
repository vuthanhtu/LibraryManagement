package bookstoreonline.web.customercontroller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstoreonline.dao.RatingDAO;
import bookstoreonline.model.Rating;

/**
 * Servlet implementation class RatingController
 */
@WebServlet("/rate")
public class RatingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RatingDAO ratingDAO;
    public void init() {
        ratingDAO = new RatingDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = request.getParameter("content");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String datepost = formatter.format(date);
		int rating  = Integer.parseInt(request.getParameter("rating"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int book_id = Integer.parseInt(request.getParameter("b_id"));
		System.out.println(rating+" "+user_id+" "+book_id);
		int row = ratingDAO.insertComment(content, datepost, rating, user_id, book_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/customer/success.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
