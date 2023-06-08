package bookstoreonline.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bookstoreonline.model.User;

import bookstoreonline.dao.UserDAO;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    public LoginController() {
        userDAO = new UserDAO();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispacher = request.getRequestDispatcher("/login/login.jsp");
		dispacher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		authenticate(request,response);
	}
	private void authenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		RequestDispatcher dispatcher = null;
		if(username==null||username.equals("")) {
			request.setAttribute("status", "invalidUsername");
			dispatcher = request.getRequestDispatcher("/login/login.jsp");
			dispatcher.forward(request, response);
		}
		if(password==null||password.equals("")) {
			request.setAttribute("status", "invalidPassword");
			dispatcher = request.getRequestDispatcher("/login/login.jsp");
			dispatcher.forward(request, response);
		}
		try {
			
			User user = new User();
			user = userDAO.validate(username, password);
			System.out.println(user.getId());
			if(user.getId()!=0) {
				if(user.isCheckadmin()) {
					response.sendRedirect("admin");
				}
				else {
					HttpSession session = request.getSession();
					session.setAttribute("acc", user);
					response.sendRedirect("home");
				}
			}
			else {
				dispatcher = request.getRequestDispatcher("/login/login.jsp");
				request.setAttribute("status", "failed");
				dispatcher.forward(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
