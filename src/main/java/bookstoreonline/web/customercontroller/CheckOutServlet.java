package bookstoreonline.web.customercontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstoreonline.dao.OrderDAO;
import bookstoreonline.model.Cart;
import bookstoreonline.model.Order;
import bookstoreonline.model.User;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/cart-check-out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			User acc = (User)request.getSession().getAttribute("acc");
			if(cart_list!=null&acc!=null) {
				for(Cart c:cart_list) {
					//Chuan bi cho order
					Order order = new Order();
					order.setBook_id(c.getBook_id());
					order.setUid(acc.getId());
					order.setQuantity(c.getQuantity());
					order.setDate(formatter.format(date));
					OrderDAO orderDAO = new OrderDAO();
					boolean result = orderDAO.insertOrder(order);
					if(!result) break;
				}
				cart_list.clear();
				response.sendRedirect("order");
			}
			else {
				if(acc==null) response.sendRedirect("login");
				response.sendRedirect("cart");
			}
		}catch(Exception e) {
			
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
