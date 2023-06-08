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
 * Servlet implementation class OrderNowServlet
 */
@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderNowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			User acc = (User)request.getSession().getAttribute("acc");
			System.out.println(acc);
			if(acc!=null) {
				String book_id = request.getParameter("bid");
				int book_quantity = Integer.parseInt(request.getParameter("quantity"));
				if(book_quantity<=0) {
					book_quantity = 1;
				}
				Order order = new Order();
				System.out.print(book_id);
				System.out.print(book_quantity);
				order.setBook_id(Integer.parseInt(book_id));
				order.setUid(acc.getId());
				order.setQuantity(book_quantity);
				order.setDate(formatter.format(date));
				
				OrderDAO orderDAO = new OrderDAO();
				boolean result = orderDAO.insertOrder(order);
				if(result) {
					ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					if(cart_list!=null) {
						for(Cart c:cart_list) {
							if(c.getBook_id()==Integer.parseInt(book_id)) {
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
					}
					response.sendRedirect("order");
				}else {
					out.println("Order failed");
				}
			}else {
				response.sendRedirect("login");
			}
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
