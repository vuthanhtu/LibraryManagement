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

import bookstoreonline.dao.UserDAO;
import bookstoreonline.model.User;

/**
 * Servlet implementation class ListUserController
 */
@WebServlet("/admin/user")
public class ListUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    public void init() {
        userDAO = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			listUser(request,response);
		}catch (ServletException | IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		List<User> listUser = userDAO.listAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user-list.jsp");
        dispatcher.forward(request, response);
	}
}
