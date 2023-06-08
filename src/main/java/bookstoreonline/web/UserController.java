package bookstoreonline.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstoreonline.dao.UserDAO;
import bookstoreonline.model.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/register")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UserDAO userDAO;
    public void init() {
    	userDAO = new UserDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/register/register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String dob = request.getParameter("dob");
		String email = request.getParameter("email");
		String dt = request.getParameter("dt");
		boolean isadmin = false;
		RequestDispatcher dispatcher = null;
		if (username==null||username.equals("")) {
			request.setAttribute("status", "usernameInvalid");
			dispatcher = request.getRequestDispatcher("/register/register.jsp");
			dispatcher.forward(request, response);
		}
		if(password==null||password.equals("")) {
			request.setAttribute("status", "passwordInvalid");
			dispatcher = request.getRequestDispatcher("/register/register.jsp");
			dispatcher.forward(request, response);
		}
		try {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setName(name);
			user.setDob(dob);
			user.setEmail(email);
			user.setDt(dt);
			user.setCheckadmin(isadmin);
			if(userDAO.existUsername(username)) {
				dispatcher = request.getRequestDispatcher("/register/register.jsp");
				request.setAttribute("status", "failed");
				dispatcher.forward(request, response);
			}
			else {
				userDAO.registerUser(user);
				RequestDispatcher dispacher = request.getRequestDispatcher("/login/login.jsp");
				dispacher.forward(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
